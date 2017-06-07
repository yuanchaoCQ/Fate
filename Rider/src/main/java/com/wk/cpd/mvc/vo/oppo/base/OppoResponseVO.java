package com.wk.cpd.mvc.vo.oppo.base;

/**
 * OPPO响应实体BaseVO
 * @author Administrator
 *
 */
public class OppoResponseVO {

    /**响应码，非1001标识请求失败**/
    protected int code;
    
    /**响应码含义**/
    protected String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
