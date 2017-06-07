package com.wk.cpd.mvc.vo.wk.request;

/**
 * 推广类型
 * @author Administrator
 *
 */
public enum PromotionType {

    DIST_APP(1, "应用分发"),
    
    SEARCH_PROMOTION(2, "搜索推广");
    
    private int code;
    
    private String name;
    
    private PromotionType(int code, String name) {
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
