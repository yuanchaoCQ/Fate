package com.wk.cpd.mvc.dao.mapper;

import com.wk.cpd.mvc.dao.model.PromotionRegularPrice;

public interface PromotionRegularPriceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_regular_change_price
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_regular_change_price
     *
     * @mbg.generated
     */
    int insert(PromotionRegularPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_regular_change_price
     *
     * @mbg.generated
     */
    int insertSelective(PromotionRegularPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_regular_change_price
     *
     * @mbg.generated
     */
    PromotionRegularPrice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_regular_change_price
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PromotionRegularPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_promotion_regular_change_price
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PromotionRegularPrice record);
}