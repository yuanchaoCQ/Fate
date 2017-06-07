package com.wk.cpd.mvc.vo.oppo.base;

public enum OppoReportType {
    
    ENTIRE_DATA(1, "整体数据"),
    
    PROMOTION_PLAN_DATA(2, "推广计划数据"),
    
    MODULE_DATA(3, "模块数据");
    
    private int code;
    
    private String name;
    
    private OppoReportType(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
}
