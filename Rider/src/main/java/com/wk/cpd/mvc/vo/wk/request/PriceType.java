package com.wk.cpd.mvc.vo.wk.request;

/**
 * 出价类型
 * @author Administrator
 *
 */
public enum PriceType {
    
    BIDDEN(1, "出价"),
    
    DAILY_BUDGET(2, "日预算");
    
    private int code;
    
    private String name;
    
    private PriceType(int code, String name) {
        this.code = code;
        this.name= name;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
}
