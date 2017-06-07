package com.wk.cpd.mvc.vo.wk.request;

import com.wk.cpd.mvc.vo.wk.base.WkRequsetVO;

/**
 * 玩咖改价接口请求实体
 * @author Administrator
 *
 */
public class WkUpdatePriceReqVO extends WkRequsetVO {
    
    /**推广类型 
     * 1：应用分发   2：搜索推广
     * @see PromotionType
     **/
    private int type;
    
    /**出价类型
     * @see PriceType
     * **/
    private int price_type;
    
    /**出价 **/
    private String price;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlatId() {
        return platId;
    }

    public void setPlatId(int platId) {
        this.platId = platId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice_type() {
        return price_type;
    }

    public void setPrice_type(int price_type) {
        this.price_type = price_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
}
