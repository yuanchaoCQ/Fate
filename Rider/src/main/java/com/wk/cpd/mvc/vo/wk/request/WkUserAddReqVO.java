package com.wk.cpd.mvc.vo.wk.request;

import com.wk.cpd.mvc.vo.wk.base.WkRequsetVO;

/**
 * 玩咖账户接口请求参数实体类
 * @author Administrator
 *
 */
public class WkUserAddReqVO  extends WkRequsetVO {
    
    
    /**用户名**/
    private String uname;
    
    /**密码**/
    private String pwd;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
}
