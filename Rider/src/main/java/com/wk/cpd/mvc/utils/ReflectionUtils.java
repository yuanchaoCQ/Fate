/*
 * Copyright (c) 2015, Xinlong.inc and/or its affiliates. All rights reserved.
 */
package com.wk.cpd.mvc.utils;

import java.lang.annotation.Inherited;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Java反射工具类
 * 
 * @since 1.0
 *
 */
public class ReflectionUtils {
    /**
     * 调用Getter方法
     */
    public static Object invokeGetterMethod(Object target, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(target, getterMethodName, new Class[] {}, new Object[] {});
    }

    /**
     * 调用Getter方法
     */
    public static Object invokeGetMethod(Object target, String methodName) {
        return invokeMethod(target, methodName, new Class[] {}, new Object[] {});
    }

    /**
     * 调用Setter方法.使用value的Class来查找Setter方法.
     */
    public static void invokeSetterMethod(Object target, String propertyName, Object value) {
        invokeSetterMethod(target, propertyName, value, null);
    }

    /**
     * 调用Setter方法.
     * 
     * @param propertyType 用于查找Setter方法,为空时使用value的Class替代
     */
    public static void invokeSetterMethod(Object target, String propertyName, Object value, Class<?> propertyType) {
        Class<?> type = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethod(target, setterMethodName, new Class[] { type }, new Object[] { value });
    }

    /**
     * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
     */
    public static Object getFieldValue(final Object object, final String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }
        makeAccessible(field);
        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("直接读取对象属性值出现异常", e);
        }
        return result;
    }

    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
     */
    public static void setFieldValue(final Object object, final String fieldName, final Object value) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }
        makeAccessible(field);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("直接设置对象属性值出现异常", e);
        }
    }

    /**
     * 直接调用对象方法, 无视private/protected修饰符.
     */
    public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes,
            final Object[] parameters) {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);

        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
        }
        method.setAccessible(true);
        try {
            return method.invoke(object, parameters);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 将反射时的checked exception转换为unchecked exception.
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        return convertReflectionExceptionToUnchecked(null, e);
    }

    /**
     * 将反射时的checked exception转换为unchecked exception(重载)
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(String desc, Exception e) {
        desc = (desc == null) ? "Unexpected Checked Exception." : desc;
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException) {
            return new IllegalArgumentException(desc, e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException(desc, ((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException(desc, e);
    }

    /**
     * 循环向上转型, 获取对象的DeclaredMethod. 若向上转型到Object仍无法找到, 返回null.
     */
    protected static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
        if (null == object) {
            return null;
        }
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {// NOSONAR
                // Method不在当前类定义,继续向上转型
                continue;
            }
        }
        return null;
    }

    /**
     * 循环向上转型, 获取对象的DeclaredField. 若向上转型到Object仍无法找到, 返回null.
     */
    protected static Field getDeclaredField(final Object object, final String fieldName) {
        if (null == object || null == fieldName || fieldName.equals("")) {
            return null;
        }
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {// NOSONAR
                // Field不在当前类定义,继续向上转型
                continue;
            }
        }
        return null;
    }

    /**
     * 强行设置Field可访问
     */
    protected static void makeAccessible(final Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }

    /**
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型 若无法找到, 返回Object.class 如public UserDao
     * extends HibernateDao<User,Long>
     * 
     * @param clazz
     * @param index 父类泛型参数的索引，从0开始计算
     * @return Class 返回父类index位置的泛型参数的class
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型， 默认index等于0 若无法找到, 返回Object.class
     * 
     * @param clazz
     * @return Class 返回父类index位置的泛型参数的class
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 提取集合中的对象的某属性的属性值(通过getter函数), 组合成List.
     * 
     * @param collection 数据集合.
     * @param propertyName 要提取的属性名.
     */
    @SuppressWarnings("unchecked")
    public static List convertElementPropertyToList(final Collection collection, final String propertyName) {
        List list = new ArrayList();
        try {
            for (Object obj : collection) {
                list.add(getFieldValue(obj, propertyName));
            }
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
        return list;
    }

    /**
     * 提取集合中的对象的某属性的属性值(通过getter函数), 组合成数组.
     * 
     * @param collection 数据集合.
     * @param propertyName 要提取的属性名.
     */
    @SuppressWarnings("unchecked")
    public static Object[] convertElementPropertyToArray(final Collection collection, final String propertyName) {
        Object[] arrays = new Object[collection.size()];
        try {
            int index = 0;
            for (Object obj : collection) {
                arrays[index] = getFieldValue(obj, propertyName);
                index++;
            }
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
        return arrays;
    }

    /**
     * 提取集合中的对象的某属性的属性值(通过getter函数), 用指定的分割符分隔组成字符串.
     * 
     * @param collection 数据集合.
     * @param propertyName 要提取的属性名.
     * @param separator 分隔符.
     */
    @SuppressWarnings("unchecked")
    public static String convertElementPropertyToString(final Collection collection, final String propertyName,
            final String separator) {
        List list = convertElementPropertyToList(collection, propertyName);
        return StringUtils.join(list, separator);
    }

    /**
     * 提取集合中的对象的某属性的属性值(通过getter函数), 用指定的分割符分隔组成字符串，默认分隔符是逗号
     * 
     * @param collection 数据集合.
     * @param propertyName 要提取的属性名.
     */
    @SuppressWarnings("unchecked")
    public static String convertElementPropertyToString(final Collection collection, final String propertyName) {
        return convertElementPropertyToString(collection, propertyName, ",");
    }

    /**
     * 方法摘要：检测某对象是否包含某属性
     * 
     * @param propertyName 属性名称
     * @return boolean 是否包含
     */
    public static boolean hasThisField(Object target, String propertyName) {
        Field field = getDeclaredField(target, propertyName);
        if (null == field) {
            return false;
        }
        return true;
    }

    /**
     * 判断某个类是否实现了某个接口
     * 
     * @param clazz Clazz类型
     * @param interfaceName 接口名称
     * @return
     */
    public static boolean isInterface(Class clazz, String interfaceName) {
        Class[] face = clazz.getInterfaces();
        for (int i = 0, j = face.length; i < j; i++) {
            if (face[i].getName().equals(interfaceName)) {
                return true;
            } else {
                Class[] face1 = face[i].getInterfaces();
                for (int x = 0; x < face1.length; x++) {
                    if (face1[x].getName().equals(interfaceName)) {
                        return true;
                    } else if (isInterface(face1[x], interfaceName)) {
                        return true;
                    }
                }
            }
        }
        if (null != clazz.getSuperclass()) {
            return isInterface(clazz.getSuperclass(), interfaceName);
        }
        return false;
    }

    /**
     * 判断某类是否包含指定方法
     * 
     * @param methodName 方法名称
     * @param clazz 待检测的类型
     * @param parameterTypes 方法参数类型
     * @return
     */
    public static boolean hasThisMethod(String methodName, Class clazz, Class<?>[] parameterTypes) {
        Method method = getDeclaredMethod(clazz, methodName, parameterTypes);
        return null != method;
    }

    /**
     * 判断某类自身是否包含指定方法(不包括继承的方法)
     * 
     * @param methodName 方法名称
     * @param clazz 待检测的类型
     * @param parameterTypes 方法参数类型
     * @return
     */
    public static boolean hasThisMethodOfSelf(String methodName, Class clazz, Class<?>[] parameterTypes) {
        Method method = getDeclaredMethod(clazz, methodName, parameterTypes);
        return null != method;
    }

    /**
     * 获取当前类的父类的所有非Private非static属性
     * 
     * @param clazz 待检测类型
     * @return
     */
    public static void getParentFields(List<Field> fieldList, Class clazz) {
        if (null == clazz) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                // 只添加非Private非static属性
                if (!Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                    fieldList.add(field);
                }
            }
        }
        if (clazz.getSuperclass() == Object.class) {
            return;
        }
        getParentFields(fieldList, clazz.getSuperclass());
    }

    /**
     * 获取类的所有属性
     * 
     * @param methodList 属性目标存储集合
     * @param clazz 待检测类型
     * @param includeParent 是否包含从父类继承的属性
     */
    public static void getFields(List<Field> fieldList, Class clazz, boolean includeParent) {
        if (null == fieldList) {
            fieldList = new ArrayList<Field>();
        }
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field method : fields) {
                fieldList.add(method);
            }
        }
        if (includeParent) {
            getParentFields(fieldList, clazz.getSuperclass());
        }
    }

    /**
     * 获取父类的所有非私有非抽象且非静态方法
     * 
     * @param clazz 待检测类型
     * @return
     */
    public static void getParentMethods(List<Method> methodList, Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        if (methodList != null && methodList.size() > 0) {
            for (Method method : methods) {
                // 只添加非Private非abstract非static方法
                if (!Modifier.isPrivate(method.getModifiers()) && !Modifier.isAbstract(method.getModifiers())
                        && !Modifier.isStatic(method.getModifiers())) {
                    methodList.add(method);
                }
            }
        }
        if (clazz.getSuperclass() == Object.class) {
            return;
        }
        getParentMethods(methodList, clazz.getSuperclass());
    }

    /**
     * 获取类的所有方法
     * 
     * @param methodList 属性目标存储集合
     * @param clazz 待检测类型
     * @param includeParent 是否包含从父类继承的方法
     */
    public static void getMothds(List<Method> methodList, Class clazz, boolean includeParent) {
        if (null == methodList) {
            methodList = new ArrayList<Method>();
        }
        Method[] methods = clazz.getDeclaredMethods();
        if (methodList != null && methodList.size() > 0) {
            for (Method method : methods) {
                methodList.add(method);
            }
        }
        if (includeParent) {
            getParentMethods(methodList, clazz.getSuperclass());
        }
    }

    /**
     * 获取类中定义私有属性(不包括继承的属性)
     * 
     * @param clazz 待检测类型
     * @return
     */
    public static void getSelfPrivateField(List<Field> fieldList, Class clazz) {
        if (null == clazz) {
            return;
        }
        if (null == fieldList) {
            fieldList = new ArrayList<Field>();
        }
        getFields(fieldList, clazz, false);
        if (fieldList != null && fieldList.size() > 0) {
            for (Iterator<Field> it = fieldList.iterator(); it.hasNext();) {
                Field field = it.next();
                // 若不是private属性,从集合中删除它
                if (!Modifier.isPrivate(field.getModifiers())) {
                    it.remove();
                }
            }
        }
    }

    /**
     * 判断属性上是否有指定类型的注解
     * 
     * @param field 待检测属性
     * @param annotationClass 注解类型
     * @return
     */
    public static boolean hasThisAnnotationOfField(Field field, Class annotationClass) {
        if (null == field || null == annotationClass) {
            return false;
        }
        return field.isAnnotationPresent(annotationClass);
    }

    /**
     * 判断方法上是否有指定类型的注解
     * 
     * @param method 待检测方法
     * @param annotationClass 注解类型
     * @return
     */
    public static boolean hasThisAnnotationOfMethod(Method method, Class annotationClass) {
        if (null == method || null == annotationClass) {
            return false;
        }
        return method.isAnnotationPresent(annotationClass);
    }

    /**
     * 判断类上是否有指定类型的注解
     * 
     * @param target 待检测类型
     * @param annotationClass 注解类型
     * @return
     */
    public static boolean hasThisAnnotationOfClass(Class target, Class annotationClass) {
        if (null == target || null == annotationClass) {
            return false;
        }
        if (target.isAnnotationPresent(annotationClass)) {
            return true;
        }
        // 若指定注解没有添加@Inherited注解，即该注解不可被继承
        if (!annotationClass.isAnnotationPresent(Inherited.class)) {
            return false;
        }
        // 递归判断是否有从父类型继承了该注解，前提是该注解是可被继承的注解，即该注解被@Inherited注解(注意：只有类上定义的注解才可以被继承)
        Class parentClass = target.getSuperclass();
        return hasThisAnnotationOfClass(parentClass, annotationClass);
    }

    /**
     * @Title: loadModifier
     * @Description: 加载类的属性修饰符使用情况
     * @param field
     * @return
     */
    public static Map<Integer, Boolean> loadModifier(List<Field> fields) {
        if (null == fields || fields.isEmpty()) {
            return null;
        }
        Map<Integer, Boolean> modifierMap = new HashMap<Integer, Boolean>();
        for (Field field : fields) {
            int i = field.getModifiers();
            if (Modifier.isPublic(i)) {
                modifierMap.put(Modifier.PUBLIC, true);
            }
            if (Modifier.isPrivate(i)) {
                modifierMap.put(Modifier.PRIVATE, true);
            }
            if (Modifier.isProtected(i)) {
                modifierMap.put(Modifier.PROTECTED, true);
            }
            if (Modifier.isStatic(i)) {
                modifierMap.put(Modifier.STATIC, true);
            }
            if (Modifier.isFinal(i)) {
                modifierMap.put(Modifier.FINAL, true);
            }
            if (Modifier.isSynchronized(i)) {
                modifierMap.put(Modifier.SYNCHRONIZED, true);
            }
            if (Modifier.isVolatile(i)) {
                modifierMap.put(Modifier.VOLATILE, true);
            }
            if (Modifier.isTransient(i)) {
                modifierMap.put(Modifier.TRANSIENT, true);
            }
            if (Modifier.isNative(i)) {
                modifierMap.put(Modifier.NATIVE, true);
            }
            if (Modifier.isInterface(i)) {
                modifierMap.put(Modifier.INTERFACE, true);
            }
            if (Modifier.isAbstract(i)) {
                modifierMap.put(Modifier.ABSTRACT, true);
            }
            if (Modifier.isStrict(i)) {
                modifierMap.put(Modifier.STRICT, true);
            }
        }
        return modifierMap;
    }

    /**
     * @Title: getModifierList
     * @Description: 获取指定属性的所有修饰符
     * @param field
     * @return
     */
    public static List<String> getModifierList(Field field) {
        int i = field.getModifiers();
        List<String> list = new ArrayList<String>();
        if (Modifier.isPublic(i)) {
            list.add(Modifier.toString(Modifier.PUBLIC));
        }
        if (Modifier.isPrivate(i)) {
            list.add(Modifier.toString(Modifier.PRIVATE));
        }
        if (Modifier.isProtected(i)) {
            list.add(Modifier.toString(Modifier.PROTECTED));
        }
        if (Modifier.isStatic(i)) {
            list.add(Modifier.toString(Modifier.STATIC));
        }
        if (Modifier.isFinal(i)) {
            list.add(Modifier.toString(Modifier.FINAL));
        }
        if (Modifier.isSynchronized(i)) {
            list.add(Modifier.toString(Modifier.SYNCHRONIZED));
        }
        if (Modifier.isVolatile(i)) {
            list.add(Modifier.toString(Modifier.VOLATILE));
        }
        if (Modifier.isTransient(i)) {
            list.add(Modifier.toString(Modifier.TRANSIENT));
        }
        if (Modifier.isNative(i)) {
            list.add(Modifier.toString(Modifier.NATIVE));
        }
        if (Modifier.isInterface(i)) {
            list.add(Modifier.toString(Modifier.INTERFACE));
        }
        if (Modifier.isAbstract(i)) {
            list.add(Modifier.toString(Modifier.ABSTRACT));
        }
        if (Modifier.isStrict(i)) {
            list.add(Modifier.toString(Modifier.STRICT));
        }
        return list;
    }

    /**
     * @Title: getClassFields
     * @Description: 获取类的所有属性(包括继承过来的属性)
     * @param clazz
     * @param fieldList
     * @return
     */
    public static List<Field> getClassFields(Class clazz, List<Field> fieldList) {
        Field[] fields = clazz.getDeclaredFields();
        if (null == fields) {
            return fieldList;
        }
        if (null == fieldList) {
            fieldList = new ArrayList<Field>();
        }
        fieldList.addAll(Arrays.asList(fields));
        if (null == clazz.getSuperclass()) {
            return fieldList;
        }
        return getClassFields(clazz.getSuperclass(), fieldList);
    }
}
