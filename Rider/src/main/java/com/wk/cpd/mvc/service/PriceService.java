package com.wk.cpd.mvc.service;

import com.wk.cpd.mvc.vo.wk.request.WkUpdatePriceReqVO;

/**
 * @description: 改价服务
 */
public interface PriceService {

    /**
     * @description: 变更出价
     * @param planType
     * @param planId
     * @param price
     * @return
     * @throws Exception
     */
    public boolean updatePrice(WkUpdatePriceReqVO priceVO) throws Exception;
    
    /**
     * @description: 变更日预算
     * @param planType
     * @param planId
     * @param price
     * @return
     * @throws Exception
     */
    public boolean updateBudget(WkUpdatePriceReqVO budgetVO) throws Exception;
}
