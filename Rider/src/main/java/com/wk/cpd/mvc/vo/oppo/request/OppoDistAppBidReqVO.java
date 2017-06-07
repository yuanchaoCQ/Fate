package com.wk.cpd.mvc.vo.oppo.request;

/**
 * 应用分发出价请求VO
 * @author Administrator
 *
 */
public class OppoDistAppBidReqVO {

    /**出价,可含有小数。单位元。请求时只同id一同为参数**/
    private String price;
    
    /**日预算,可含有小数。单位元。请求时只同id一同为参数**/
    private String dayBudget;
    
    /**获取应用推广列表时获取**/
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
