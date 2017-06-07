package com.wk.cpd.mvc.dao.model;

import java.math.BigDecimal;

public class PromotionDailyHistory {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_promotion_daily_report.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_promotion_daily_report.plat_id
     *
     * @mbg.generated
     */
    private Integer platId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_promotion_daily_report.plat_promotion_id
     *
     * @mbg.generated
     */
    private String platPromotionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_promotion_daily_report.type
     *
     * @mbg.generated
     */
    private Byte type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_promotion_daily_report.date
     *
     * @mbg.generated
     */
    private Integer date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_promotion_daily_report.position
     *
     * @mbg.generated
     */
    private String position;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_promotion_daily_report.imp_cnt
     *
     * @mbg.generated
     */
    private Integer impCnt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_promotion_daily_report.download_cnt
     *
     * @mbg.generated
     */
    private Integer downloadCnt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_promotion_daily_report.cost
     *
     * @mbg.generated
     */
    private BigDecimal cost;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_promotion_daily_report.id
     *
     * @return the value of tb_promotion_daily_report.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_promotion_daily_report.id
     *
     * @param id the value for tb_promotion_daily_report.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_promotion_daily_report.plat_id
     *
     * @return the value of tb_promotion_daily_report.plat_id
     *
     * @mbg.generated
     */
    public Integer getPlatId() {
        return platId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_promotion_daily_report.plat_id
     *
     * @param platId the value for tb_promotion_daily_report.plat_id
     *
     * @mbg.generated
     */
    public void setPlatId(Integer platId) {
        this.platId = platId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_promotion_daily_report.plat_promotion_id
     *
     * @return the value of tb_promotion_daily_report.plat_promotion_id
     *
     * @mbg.generated
     */
    public String getPlatPromotionId() {
        return platPromotionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_promotion_daily_report.plat_promotion_id
     *
     * @param platPromotionId the value for tb_promotion_daily_report.plat_promotion_id
     *
     * @mbg.generated
     */
    public void setPlatPromotionId(String platPromotionId) {
        this.platPromotionId = platPromotionId == null ? null : platPromotionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_promotion_daily_report.type
     *
     * @return the value of tb_promotion_daily_report.type
     *
     * @mbg.generated
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_promotion_daily_report.type
     *
     * @param type the value for tb_promotion_daily_report.type
     *
     * @mbg.generated
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_promotion_daily_report.date
     *
     * @return the value of tb_promotion_daily_report.date
     *
     * @mbg.generated
     */
    public Integer getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_promotion_daily_report.date
     *
     * @param date the value for tb_promotion_daily_report.date
     *
     * @mbg.generated
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_promotion_daily_report.position
     *
     * @return the value of tb_promotion_daily_report.position
     *
     * @mbg.generated
     */
    public String getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_promotion_daily_report.position
     *
     * @param position the value for tb_promotion_daily_report.position
     *
     * @mbg.generated
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_promotion_daily_report.imp_cnt
     *
     * @return the value of tb_promotion_daily_report.imp_cnt
     *
     * @mbg.generated
     */
    public Integer getImpCnt() {
        return impCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_promotion_daily_report.imp_cnt
     *
     * @param impCnt the value for tb_promotion_daily_report.imp_cnt
     *
     * @mbg.generated
     */
    public void setImpCnt(Integer impCnt) {
        this.impCnt = impCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_promotion_daily_report.download_cnt
     *
     * @return the value of tb_promotion_daily_report.download_cnt
     *
     * @mbg.generated
     */
    public Integer getDownloadCnt() {
        return downloadCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_promotion_daily_report.download_cnt
     *
     * @param downloadCnt the value for tb_promotion_daily_report.download_cnt
     *
     * @mbg.generated
     */
    public void setDownloadCnt(Integer downloadCnt) {
        this.downloadCnt = downloadCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_promotion_daily_report.cost
     *
     * @return the value of tb_promotion_daily_report.cost
     *
     * @mbg.generated
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_promotion_daily_report.cost
     *
     * @param cost the value for tb_promotion_daily_report.cost
     *
     * @mbg.generated
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}