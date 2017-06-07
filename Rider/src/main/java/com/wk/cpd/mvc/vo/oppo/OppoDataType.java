package com.wk.cpd.mvc.vo.oppo;
/**
 * @description: OPPO 数据类型
 */
public enum OppoDataType {

    SUMMARY {
        @Override
        public int value() {
            
            return 1;
        }
    },
    
    PLAN {
        @Override
        public int value() {
            
            return 2;
        }
    },
    
    CLASSIFICATION {
        @Override
        public int value() {
            
            return 3;
        }
    };
    
    public abstract int value();
}
