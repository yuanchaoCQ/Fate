package com.wk.cpd.mvc.vo.oppo.response;

/**
 * 应用分发数据
 * @author Administrator
 *
 */
public class CpdTrendStatDataOfArray {
    
    /**消费**/
    private int cost;
    
    /**ctr预估**/
    private double ctr;
    
    /**下载书**/
    private int downNum;
    
    /**日期,今日:YYYYMMDDHH 多日：YYYYMMDD**/
    private int dt;
    
    /****/
    private double ecpm;
    
    /**展示数**/
    private int exposeNum;
    
    /**下载单价**/
    private double price;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
