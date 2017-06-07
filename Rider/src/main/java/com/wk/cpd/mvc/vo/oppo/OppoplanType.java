package com.wk.cpd.mvc.vo.oppo;

import com.wk.cpd.mvc.Constant;

/**
 * @description: OPPO计划类型
 */
public enum OppoplanType {

    PROMOTION {
        @Override
        public String getIndexUrl() {

            return OppoInterfaceType.OPPO_APP_REPORT_INDEX_URL.getUrl();
        }

        @Override
        public String getDataSummaryUrl() {

            return Constant.OPPO_APP_PROMOTIONLIST_URL;
        }

        @Override
        public String getPriceUpdateUrl() {

            return Constant.OPPO_APP_PRICE_UPDATE_URL;
        }

        @Override
        public String swichURl() {

            return Constant.OPPO_APP_SWICH_URL;
        }

        @Override
        public String getDataTrendURL() {

            return Constant.OPPO_APP_PROMOTION_TREND_URL;
        }

        @Override
        public String getDataListURL() {

            return Constant.OPPO_APP_LOCALTION_LIST_URL;
        }
    },

    SEARCH

    {
        @Override
        public String getIndexUrl() {

            return Constant.OPPO_SEARCH_REPORT_INDEX_URL;
        }

        @Override
        public String getDataSummaryUrl() {

            return Constant.OPPO_SEARCH_LIST_URL;
        }

        @Override
        public String getPriceUpdateUrl() {

            return Constant.OPPO_SEARCH_PRICE_UPDATE_URL;
        }

        @Override
        public String swichURl() {

            return Constant.OPPO_SEARCH_PRICE_UPDATE_URL;
        }

        @Override
        public String getDataTrendURL() {

            return Constant.OPPO_SEARCH_TREND_URL;
        }

        @Override
        public String getDataListURL() {

            return Constant.OPPO_SEARCH_LOCALTION_LIST_URL;
        }
    };

    public abstract String getIndexUrl();

    public abstract String getDataSummaryUrl();

    public abstract String getPriceUpdateUrl();

    public abstract String swichURl();

    public abstract String getDataTrendURL();

    public abstract String getDataListURL();

}
