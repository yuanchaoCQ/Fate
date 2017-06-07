package com.wk.cpd.mvc.utils.local;

/**
 * 用来管理本地本地线程的数据
 */
public class ThreadLocalManager {

    private static ThreadLocal<LocalInfo> LOCAL = new ThreadLocal<LocalInfo>();

    /**
     * 初始化
     * 
     * @param ip
     * @param uri
     */
    public static void initLocalInfo() {
        LocalInfo info = new LocalInfo();
        LOCAL.set(info);
    }

    /** 获取本地线程信息存储的实例 **/
    public static LocalInfo getLocal() {
        return LOCAL.get();
    }

    /**
     * 数据销毁
     */
    public static void destroy() {
        LOCAL.remove();
    }
}
