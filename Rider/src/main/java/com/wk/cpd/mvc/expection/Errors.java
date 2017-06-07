package com.wk.cpd.mvc.expection;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础错误类型
 */
public class Errors {

    protected static Map<Integer, String> ERROR_MESSAGE;
    protected static Map<Integer, Integer> HTTP_CODE;

    public static int NO_ERROR = 0;
    public static int INTERNAL_ERROR = 1;
    public static int PARAM_ERROR = 2;
    public static int API_UNSUPPORTED = 3;
    public static int ABOLITION_API = 4;
    public static int VERIFICATION_FAILURE = 5;
    public static int NO_CONTENT = 6; // 无内容

    static {
        ERROR_MESSAGE = new HashMap<Integer, String>();
        ERROR_MESSAGE.put(NO_ERROR, "no error");
        ERROR_MESSAGE.put(API_UNSUPPORTED, "Unsupported api");
        ERROR_MESSAGE.put(PARAM_ERROR, "param error");
        ERROR_MESSAGE.put(ABOLITION_API, "abolished api");
        ERROR_MESSAGE.put(VERIFICATION_FAILURE, "verification failure");
        ERROR_MESSAGE.put(NO_CONTENT, "no content");
        ERROR_MESSAGE.put(INTERNAL_ERROR, "internal error");

        HTTP_CODE = new HashMap<Integer, Integer>();
        HTTP_CODE.put(INTERNAL_ERROR, 500);
        HTTP_CODE.put(API_UNSUPPORTED, 403);
        HTTP_CODE.put(PARAM_ERROR, 403);
        HTTP_CODE.put(ABOLITION_API, 403);
        HTTP_CODE.put(VERIFICATION_FAILURE, 403);
        HTTP_CODE.put(NO_CONTENT, 204);
        HTTP_CODE.put(NO_ERROR, 200);
    }

    /**
     * 根据错误类型的{@code code}获取对应的信息
     * @param code 错误类型的{@code code}
     * @return 返回对应的信息
     */
    public static String getErrorMessageViaErrorCode(int code) {
        return ERROR_MESSAGE.get(code);
    }

    /**
     * 根据错误类型的{@code code}获取对应的{@code HTTP_CODE}
     * @param code 错误类型的{@code code}
     * @return 返回对应的{@code HTTP_CODE}
     */
    public static int getHTTP_CODEViaErrorCode(int code) {
        return HTTP_CODE.get(code);
    }
}
