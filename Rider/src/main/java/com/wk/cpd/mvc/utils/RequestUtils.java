package com.wk.cpd.mvc.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 请求工具类，获取请求中相应数据
 */
public class RequestUtils {

    /**
     * 用于获取真实的ip地址
     * 
     * @param request 请求
     * @return 返回真实的ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        int index = ip.indexOf(",");
        if (index > 0) {
            ip = ip.substring(0, index);
        }
        return ip;
    }

    /**
     * 获取请求的uri地址，例如 {@code /adx/v1.0/request?parameter=value}
     * 
     * @param request 请求
     * @return 返回uri地址
     */
    public static String getUri(HttpServletRequest request) {
        String queryString = request.getQueryString();
        String uri = request.getRequestURI() + (StringUtils.isBlank(queryString) ? "" : ("?" + queryString));
        return uri;
    }
}
