package com.wk.cpd.mvc.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wk.cpd.mvc.utils.http.WKHttpUtils;
import com.wk.cpd.mvc.utils.log.WKLogManager;

/**
 *
 * 日志拦截器
 *
 */

public class LogInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LogManager.getLogger();
    private Marker bidLogMarker = MarkerManager.getMarker("BidLog");
    private Marker crashLogMarker = MarkerManager.getMarker("SdkCrashLog");
    private Marker sdkFatalLogMarker = MarkerManager.getMarker("SdkFatalLog");
    private Marker ipDomainLogMarker = MarkerManager.getMarker("IpDomainLog");

    // 单例实现初始化
    private static class LogInterceptorHolder {
        private final static LogInterceptor INSTANCE = new LogInterceptor();
    }

    private LogInterceptor() {
    }

    // 获取实例对象
    public static LogInterceptor instance() {
        return LogInterceptorHolder.INSTANCE;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String ip = WKHttpUtils.getIpAddr(request);// request.getRemoteAddr();
                                                   // //获取IP地址，正式环境用request.getHeader("X-Forwarded-For");
        String uri = request.getRequestURI();
        WKLogManager.initLogInfo(ip, uri);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        if (WKLogManager.getLOG().isIpDomainLog()) {
            // SDK崩溃日志
            logger.info(ipDomainLogMarker, WKLogManager.getLOG().ipDomainLogToString());
        } else if (WKLogManager.getLOG().getIsRBIFatal()) {
            logger.info(sdkFatalLogMarker, WKLogManager.getLOG().toString());
        } else if (WKLogManager.getLOG().getIsRBICrash()) {
            // SDK崩溃日志
            logger.info(crashLogMarker, WKLogManager.getLOG().crashToString());
        } else {
            logger.info(bidLogMarker, WKLogManager.getLOG().toString());
            // 落系统错误日志
            String sysError = WKLogManager.getLOG().sysErrorToString();
            if (!StringUtils.isBlank(sysError)) {
                logger.error(sysError);
            }
        }

        WKLogManager.destroy();
    }

}
