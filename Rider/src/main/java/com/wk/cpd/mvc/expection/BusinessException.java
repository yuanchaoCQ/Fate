package com.wk.cpd.mvc.expection;

/**
 * 权限异常，当权限校验不通过的时候抛出
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1095797921201166225L;

    public BusinessException(int code) {
        super(code + "");
    }
}
