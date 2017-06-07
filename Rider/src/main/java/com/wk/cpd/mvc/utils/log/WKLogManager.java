package com.wk.cpd.mvc.utils.log;

/**
 * 用来存储Log信息
 */
public final class WKLogManager {

    private static ThreadLocal<LogInfo> LOG = new ThreadLocal<LogInfo>();

    public static LogInfo initLogInfo(final String ip, final String uri) {
        LogInfo info = new LogInfoImp(ip, uri);
        LOG.set(info);
        return info;
    }

    public static LogInfo getLOG() {
        return LOG.get();
    }

    /**
     * 数据销毁
     */
    public static void destroy() {
        LOG.remove();
    }
}
