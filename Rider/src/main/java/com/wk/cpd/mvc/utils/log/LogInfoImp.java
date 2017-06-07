package com.wk.cpd.mvc.utils.log;

import com.wk.cpd.mvc.utils.DateTimeUtils;

/**
 * 日志信息实现类
 *
 */
final class LogInfoImp extends LogInfo {

    boolean error = false;
    boolean sysError = false;
    boolean noContent = false;

    /**
     * 构造方法，必须传入ip，如果没有ip请传入"null"
     * 
     * @param ip
     *            日志的ip
     */
    public LogInfoImp(final String ip, final String uri) {
        super(ip, uri);
    }

    /**
     * 获取{@code ip}
     * 
     * @return 返回{@code ip}
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * 设置请求的id
     * 
     * @param requestId
     *            竞价请求的id
     */
//    public LogInfo addrequestId(String requestId, LogLevel level) {
//        
//        StringBuilder info = null;
//        if (LogLevel.INFO.equals(level)) {
//            info = logInfo;
//        } else if (LogLevel.SYSERROR.equals(level)) {
//            info = sysLogInfo;
//        } else {
//            return this;
//        }
//        info.append("[request_id:").append(requestId).append("]");
//        return this;
//    }

    /**
     * 增加日志信息，按照key-value形式
     * 
     * @param key
     *            主键
     * @param value
     *            值
     * @return 返回{@code LogInfo}实例
     */
    public LogInfo addInfo(final String key, final String value) {
        logInfo.append("[").append(key).append(":").append(value).append("]");
        return this;
    }

    /**
     * 在日志中添加错误信息
     * 
     * @param e
     *            错误信息{code Exception}实例
     * @return 返回{@code LogInfo}实例
     */
    public LogInfo addSysError(final Exception e) {
        sysError = true;
        String error = e.getMessage();
        StackTraceElement[] mes = e.getStackTrace();
        for (int i = 0; i < mes.length; i++) {
            error += mes[i].toString() + "\n";
        }
        sysLogInfo.append("[error_message:").append(error).append("]").append("[time:")
                .append(DateTimeUtils.getCurrentSecond()).append("]");
        return this;
    }

    /**
     * 在日志中添加错误信息
     * 
     * @param e
     *            错误信息{code Exception}实例
     * @return 返回{@code LogInfo}实例
     */
    public LogInfo addError(final String e) {
        error = true;
        logInfo.append("[is_error:1]")//
                .append("[error_message:").append(e).append("]");
        return this;
    }

    public LogInfo addNoContent(){
        noContent = true;
        logInfo.append("[is_error:6]");
        return this;
    }
    /**
     * 添加打点日志
     * 
     * @param 错误信息{code
     *            Exception}实例
     * @return 返回{@code LogInfo}实例
     */
    public LogInfo addRBIFatal(final String key, final String value) {

        if (!isRBIFatal) {
            isRBIFatal = true;
        }
        logInfo.append("[").append(key).append(":").append(value).append("]");
        return this;
    }

    /**
     * 业务错误的toString 方法
     */
    public String toString() {
        if (!error && !noContent) {
            logInfo.append("[is_error:0]");
        }
        addInfo("time", String.valueOf(System.currentTimeMillis()));
        long useTime = DateTimeUtils.getCurrentMillis() - startTime;
        addInfo("process_time", String.valueOf(useTime));
        return ip + "  " + uri + " " + logInfo.toString();
    }

    /**
     * 系统错误的toString 方法
     */
    public String sysErrorToString() {
        if (sysError) {
            return sysLogInfo.toString();
        }
        return "";
    }

    @Override
    public boolean getIsRBIFatal() {

        return isRBIFatal;
    }

    @Override
    public LogInfo addCrash(String key, String value) {
        if (!isRBICrash) {
            isRBICrash = true;
        }
        crashLoginfo.append("[").append(key).append(":").append(value).append("]");
        return this;
    }

    @Override
    public boolean getIsRBICrash() {
        
        return isRBICrash;
    }

    @Override
    public String crashToString() {
        
        if (isRBICrash) {
            return crashLoginfo.toString();
        }
        return "";
    }

    @Override
    public LogInfo addIpDomainLog(final String key, String value) {
        if (!isIpDomain) {
            isIpDomain = true;
        }
        ipDomainLoginfo.append("[").append(key).append(":").append(value).append("]");
        return this;
    }

    @Override
    public boolean isIpDomainLog() {
        
        return this.isIpDomain;
    }

    @Override
    public String ipDomainLogToString() {
        
        return ipDomainLoginfo.toString();
    }
}
