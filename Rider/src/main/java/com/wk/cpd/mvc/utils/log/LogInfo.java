package com.wk.cpd.mvc.utils.log;

import com.wk.cpd.mvc.utils.DateTimeUtils;

/**
 * 日志信息存储类
 *
 */
public abstract class LogInfo {

    final StringBuilder logInfo = new StringBuilder("ALERT:");
    final StringBuilder sysLogInfo = new StringBuilder("ALERT:");
    final StringBuilder crashLoginfo = new StringBuilder("ALERT");
    final StringBuilder ipDomainLoginfo = new StringBuilder("ALERT");
    String ip = "";
    String uri = "";
    long startTime = 0;
    boolean isRBIFatal = false; //是否为打点日志
    boolean isRBICrash = false; // 是否为崩溃日志
    boolean isIpDomain = false; // 是否为请求IP和domain接口日志

    /**
     * 构造方法，必须传入ip，如果没有ip请传入"null"
     * 
     * @param ip 日志的ip
     */
    LogInfo(final String ip, final String uri) {
        this.ip = ip;
        this.uri = uri;
        this.startTime = DateTimeUtils.getCurrentMillis();
    }

    /**
     * 获取{@code ip}
     * 
     * @return 返回{@code ip}
     */
    public abstract String getIp();

/**
     * 获取{@code ip}
     * @return 返回{@code ip}
     */
    public abstract boolean getIsRBIFatal();

    /**
     * 设置向日志中添加请求的id
     * 
     * @param requestId 请求的id
     */
//    public abstract LogInfo addrequestId(String requestId, LogLevel level);

    /**
     * 增加日志信息，按照key-value形式
     * 
     * @param key 主键
     * @param value 值
     * @return 返回{@code LogInfo}实例
     */
    public abstract LogInfo addInfo(final String key, final String value);

    /**
     * 在日志中添加错误信息(系统异常)
     * 
     * @param e 错误信息{code Exception}实例
     * @return 返回{@code LogInfo}实例
     */
    public abstract LogInfo addSysError(final Exception e);

    /**
     * 在日志中添加错误信息（业务异常）
     * 
     * @param e 错误信息
     * @return 返回{@code LogInfo}实例
     */
    public abstract LogInfo addError(final String e);
    
    /**
     * @title: addNoContent
     * @description: 添加无内容返回标记
     * @return
     */
    public abstract LogInfo addNoContent();

    /**
     * 增加崩溃日志信息，按照key-value形式
     * 
     * @param key 主键
     * @param value 值
     * @return 返回{@code LogInfo}实例
     */
    public abstract LogInfo addCrash(final String key, final String value);

    /**
     * 在日志中添加打点信息
     * @param e 打点信息
     * @return 返回{@code LogInfo}实例
     */
    public abstract LogInfo addRBIFatal (final String key, final String value);
    
    /**
     * 获取{@code ip}
     * @return 返回{@code ip}
     */
    public abstract boolean getIsRBICrash();

    /**
     * 业务错误的toString 方法
     */
    public abstract String toString();

    /**
     * 系统错误的toString 方法
     */
    public abstract String sysErrorToString();
    
    /**
     * SDK崩溃日志的toString 方法
     */
    public abstract String crashToString();
    
    /**
     * @description: 添加IP和domain对应接口日志
     * @param value
     * @return
     */
    public abstract LogInfo addIpDomainLog (final String key, final String value);
    
    /**
     * @description: 是否是IP和domain对应接口的请求日志
     * @return
     */
    public abstract boolean isIpDomainLog();
    
    /**
     * @description: IP与domain请求接口日志转字符串形式
     * @return
     */
    public abstract String ipDomainLogToString();
}
