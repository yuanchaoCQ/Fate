package com.wk.cpd.mvc.vo.oppo.request;

public class OppoTrendReqVO {
    
    /**日期区间，yyyy-mm-dd~yyyy-mm-dd区间最大为30天**/
    private String dateRange;

    /**应用ID，查看全部时，为””**/
    private String appId;
    
    /**
     * 报表类型，1：整体数据 2：推广计划数据 3：模块数据
     * @see OppoReportType
     * **/
    private int type;
    
    /**应用分发，传""**/
    private String moduleId;

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
    
}
