package com.wk.cpd.mvc.dao.mapper;

import com.wk.cpd.mvc.dao.model.PromotionDailyHistory;

public interface PromotionDailyHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_daily_report
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_daily_report
     *
     * @mbg.generated
     */
    int insert(PromotionDailyHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_daily_report
     *
     * @mbg.generated
     */
    int insertSelective(PromotionDailyHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_daily_report
     *
     * @mbg.generated
     */
    PromotionDailyHistory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_daily_report
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PromotionDailyHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_daily_report
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PromotionDailyHistory record);
}