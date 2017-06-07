package com.wk.cpd.mvc.utils.local;

/**
 * 存放本地线程数据的对象
 *
 */
public class LocalInfo {

    /** 请求ID **/
    private String requestId;
    
    /** 用户唯一ID **/
    private String uid;
    
    /** 是否更新token **/
    private boolean updateToken;
    
    /** 头条请求的新闻类型 **/
    private String toutiaoCategory;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isUpdateToken() {
        return updateToken;
    }

    public void setUpdateToken(boolean updateToken) {
        this.updateToken = updateToken;
    }

    public String getToutiaoCategory() {
        return toutiaoCategory;
    }

    public void setToutiaoCategory(String toutiaoCategory) {
        this.toutiaoCategory = toutiaoCategory;
    }
    
}
