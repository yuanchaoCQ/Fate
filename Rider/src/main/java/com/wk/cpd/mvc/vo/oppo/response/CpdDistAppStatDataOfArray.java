package com.wk.cpd.mvc.vo.oppo.response;

/**
 * 应用分发数据
 * @author Administrator
 *
 */
public class CpdDistAppStatDataOfArray {
    
    /**应用名，请求type为2,3时有此字段
     * 1：整体数据
     *  2：推广计划数据
     **/
    private String appName;
    
    /**消费**/
    private int code;
    
    /**ctr预估**/
    private double ctr;
    
    /**下载数**/
    private int downNum;
    
    /**日期，今日:YYYYMMDDHH 多日：YYYYMMDD**/
    private int dt;
    
    /****/
    private double ecpm;
    
    /**展示数**/
    private int exposeNum;
    
    /**模块名称
     * 请求type为3时有此字段
     * 3：模块数据
     **/
    private String moduleName;
    
    /**下载单价**/
    private double price;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getCtr() {
        return ctr;
    }

    public void setCtr(double ctr) {
        this.ctr = ctr;
    }

    public int getDownNum() {
        return downNum;
    }

    public void setDownNum(int downNum) {
        this.downNum = downNum;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public double getEcpm() {
        return ecpm;
    }

    public void setEcpm(double ecpm) {
        this.ecpm = ecpm;
    }

    public int getExposeNum() {
        return exposeNum;
    }

    public void setExposeNum(int exposeNum) {
        this.exposeNum = exposeNum;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    } 
    
}
