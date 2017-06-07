package com.wk.cpd.mvc.vo.oppo.response;

/**
 * 应用分发模块数据响应VO
 * @author Administrator
 *
 */
public class OppoDistAppResVO {
    
    /**响应码，非1001错误**/
    private String code;
    
    /**应用分发数据
     * @see CpdDistAppStatDataOfArray
     * **/
    private CpdDistAppStatDataOfArray data;
    
    /**分页信息
     *  @see OppoDistAppPage
     * **/
    private OppoDistAppPage page;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CpdDistAppStatDataOfArray getData() {
        return data;
    }

    public void setData(CpdDistAppStatDataOfArray data) {
        this.data = data;
    }

    public OppoDistAppPage getPage() {
        return page;
    }

    public void setPage(OppoDistAppPage page) {
        this.page = page;
    }
    
}
