package com.wk.cpd.mvc.vo.oppo;

import com.wk.cpd.mvc.Constant;

/**
 * @description: OPPO 接口类型
 */
public enum OppoInterfaceType {

    OPPO_APP_PROMOTIONLIST_URL {
        @Override
        public String getUrl() {

            return Constant.OPPO_APP_PROMOTIONLIST_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return String.class;
        }
    },
    
    OPPO_APP_PRICE_UPDATE_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_APP_PRICE_UPDATE_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    },
    
    OPPO_APP_SWICH_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_APP_SWICH_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    },
    
    OPPO_SEARCH_LIST_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_SEARCH_LIST_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    },
    
    OPPO_SEARCH_PRICE_UPDATE_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_SEARCH_PRICE_UPDATE_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    },
    
    OPPO_SEARCH_SWICH_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_SEARCH_SWICH_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    },
    
    OPPO_APP_REPORT_INDEX_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_APP_REPORT_INDEX_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    },
    
    OPPO_APP_PROMOTION_TREND_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_APP_PROMOTION_TREND_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    },
    
    OPPO_APP_LOCALTION_LIST_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_APP_LOCALTION_LIST_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    },
    
    OPPO_SEARCH_REPORT_INDEX_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_SEARCH_REPORT_INDEX_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    },
    
    OPPO_SEARCH_TREND_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_SEARCH_TREND_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    },
    
    OPPO_SEARCH_LOCALTION_LIST_URL {
        @Override
        public String getUrl() {
            
            return Constant.OPPO_SEARCH_LOCALTION_LIST_URL;
        }

        @Override
        public Class<? extends Object> getResponseClass() {
            
            return null;
        }
    };
    
    
    public abstract String getUrl();
    
    public abstract Class<? extends Object> getResponseClass();
}
