package com.wk.cpd.mvc.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * @description: 将对象转换为http请求参数串
 */
public class URLParamUtils {

	private static final String SERIAL_VERSION_UID = "serialVersionUID";
	
	public static String String2UrlParams(Object obj){
	    return String2UrlParams(obj, "?");
	}
	
	public static String String2UrlParams(Object obj, String firstChar){
		
		StringBuilder params = null;
		Class<? extends Object> clazz = obj.getClass();
		List<java.lang.reflect.Field> fieldList = new ArrayList<>();
		ReflectionUtils.getClassFields(clazz, fieldList);
		
		for(java.lang.reflect.Field field : fieldList){
			
			//排除java对象因实现Serializable接口添加的serialVersionUID属性
			if(field.getName().equals(SERIAL_VERSION_UID)) {
				continue;
			}
			String fieldName = field.getName();
			Object fieldValue = ReflectionUtils.getFieldValue(obj, fieldName);
			
			if(null != fieldValue){
				
				if(null == params){
					params = new StringBuilder(firstChar);
					params.append(fieldName).append("=");
					params.append(fieldValue);
				} else {
					params.append("&").append(fieldName).append("=").append(fieldValue);
				}
			}
		}
		
		if(null == params){
			return "";
		} else {
			return params.toString();
		}
	}
}
