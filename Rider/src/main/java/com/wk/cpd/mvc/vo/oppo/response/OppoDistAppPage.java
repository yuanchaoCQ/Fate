package com.wk.cpd.mvc.vo.oppo.response;

/**
 * 分页信息
 * @author Administrator
 *
 */
public class OppoDistAppPage {
    
    /**当前页，从1开始**/
    private int page;
    
    /**行数**/
    private int rows;
    
    /**排序**/
    private String sort;
    
    /**总条数**/
    private int totalCount;
    
    /**总页数**/
    private int totalPage;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    } 
    
}
