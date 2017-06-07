package com.wk.cpd.mvc.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

import com.wk.cpd.mvc.utils.log.WKLogManager;

/**
 *
 * {@code Object}工具类
 *
 */
public final class WKObjectUtils {

    /**
     * 将{@link String}类型的版本号格式化成{@link int}类型的版本号
     * 
     * @param {@link String}类型的版本号
     * @return 返回{@link int}类型的版本号
     */
    public static int formatString2Int4Version(String osv) {
        if (StringUtils.isBlank(osv)) {
            return 0;
        }
        String[] osvs = StringUtils.split(osv, "\\.");

        if (osvs.length == 1) {
            return toInt(osvs[0]) * 10000;
        } else if(osvs.length == 3){
          return toInt(osvs[0]) * 10000 + toInt(osvs[1]) * 100 + toInt(osvs[2]);  
        } else {
            return toInt(osvs[0]) * 10000 + toInt(osvs[1]) * 100;
        }
    }

    /**
     * 将{@link String}类型字符串转化成{@link int}类型
     * 
     * @param str {@link String}类型字符串
     * @return 返回@link int}类型
     */
    public static int toInt(String str) {
        
        int num = 0;
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        try {
            num = Integer.parseInt(str);
        } catch (Exception e) {
            // 异常情况：线上出现[0-9].[0-9].[0-9][?&]*[a-z]的异常版本号情况，特殊处理
            // 版本发布小版本号最大为2位，所以只判断前两位
            if (str.substring(0, 1).matches("[0-9]+.*")) {
                if (str.substring(1, 2).matches("[0-9]+.*")) {
                    num = Integer.parseInt(str.substring(0, 2));
                } else {
                    num = Integer.parseInt(str.substring(0, 1));
                }
            }
            
            WKLogManager.getLOG().addSysError(new Exception("sdk version number format error. Please view request log based on bidrequestid"));
        }
        
        return num;
    }

    /**
     * 将{@link Object}类型字符串转化成{@link int}类型
     * 
     * @param str {@link Object}类型字符串
     * @return 返回@link int}类型
     */
    public static int toInt(Object ob) {
        if (ObjectUtils.isEmpty(ob)) {
            return 0;
        }

        return Integer.parseInt(ob.toString());
    }

    /**
     * 将{@link String}类型字符串转化成{@link Double}类型
     * 
     * @param str {@link String}类型字符串
     * @return 返回@link double}类型
     */
    public static double toDouble(String str) {
        if (StringUtils.isBlank(str)) {
            return 0D;
        }
        return Double.parseDouble(str);
    }

    /**
     * 将{@link Object}类型字符串转化成{@link Double}类型
     * 
     * @param str {@link Object}类型字符串
     * @return 返回@link double}类型
     */
    public static double toDouble(Object ob) {
        if (ObjectUtils.isEmpty(ob)) {
            return 0D;
        }

        return Double.parseDouble(ob.toString());
    }
}
