package com.wk.cpd.mvc.vo.wk.base;

/**
 * 玩咖请求实体baseVO
 * @author Administrator
 *
 */
public class WkRequsetVO {
    
    /**用户ID**/
    protected int userId;
    
    /**平台ID**/
    protected int platId;

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
    
}
