package com.wk.cpd.mvc.vo.oppo.request;

/**
 * OPPO搜索推广出价实体VO
 * @author Administrator
 *
 */
public class OppoSearchBidReqVO {
    
    /**出价，可含有小数。单位元。请求时只同id一同为参数**/
    private String price;
    
    /**日预算，可含有小数。单位元。请求时只同id一同为参数**/
    private String dayBudget;
    
    /**获取搜索推广列表时获取**/
    private String searchBidId;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDayBudget() {
        return dayBudget;
    }

    public void setDayBudget(String dayBudget) {
        this.dayBudget = dayBudget;
    }

    public String getSearchBidId() {
        return searchBidId;
    }

    public void setSearchBidId(String searchBidId) {
        this.searchBidId = searchBidId;
    }
    
}
