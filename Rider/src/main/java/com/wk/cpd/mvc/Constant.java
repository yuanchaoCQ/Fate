package com.wk.cpd.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 公共信息
 */
public class Constant {

    /** 请求feed等待时间 **/
    public static long TIMEOUT = 3000;

    /** 设置字符集 **/
    public static String CHARSET = "UTF-8";

    /** 存储地域信息的{@link Map} **/
    public final static Map<String, String> AREA_MAP = new HashMap<String, String>();

    /** feed日志版本号 **/
    public final static String LOG_VERSION = "1.0";
    
    /****************************************************************
     * 请求分发配置
     ****************************************************************/

    /** 整个池子的大小 **/
    public static int MAX_TOTAL = 100;
    /** 路由数 **/
    public static int DEFAULT_MAX_PER_ROUTE = 50;
    
    /****************************************************************
     *  OPPO投放平台接口常量
     ****************************************************************/

    /** 登录页接口 **/
    public static String OPPO_LOGIN_PAGE_URL = "http://e.oppomobile.com/login_token";
    /** 登录接口 **/
    public static String oppo_login_URL = "http://e.oppomobile.com/login";
    /** 验证码接口 **/
    public static String oppo_captcha_URL = "http://e.oppomobile.com/loginCaptcha";
    
    /** 推广页接口 **/
    /** 推广页接口-应用分发页面接口 抓取html **/
    public static String OPPO_APP_PROMOTIONLIST_URL = "http://e.oppomobile.com/bid/list";
    /** 推广页接口-应用分发-改价，日预算接口 **/
    public static String OPPO_APP_PRICE_UPDATE_URL = "http://e.oppomobile.com/bid/update";
    /** 推广页接口-应用分发-开关 **/
    public static String OPPO_APP_SWICH_URL = "";
    /** 推广页接口-搜索推广页面 抓取html **/
    public static String OPPO_SEARCH_LIST_URL = "http://e.oppomobile.com/searchBid/list";
    /** 推广页接口-搜索推广-改价，日预算接口 **/
    public static String OPPO_SEARCH_PRICE_UPDATE_URL = "http://e.oppomobile.com/searchBid/update";
    /** 推广页接口-搜索推广-开关接口 **/
    public static String OPPO_SEARCH_SWICH_URL = "";
    
    /** 报表页接口 **/
    /** 报表页接口-应用分发索引页 **/
    public static String OPPO_APP_REPORT_INDEX_URL = "http://e.oppomobile.com/cpdStat/index";
    /** 报表页接口-应用分发-趋势图接口 **/
    public static String OPPO_APP_PROMOTION_TREND_URL = "http://e.oppomobile.com/cpdStat/trend";
    /** 报表页接口-应用分发-列表接口 **/
    public static String OPPO_APP_LOCALTION_LIST_URL = "http://e.oppomobile.com/cpdStat/list";
    
    /** 报表页接口-搜索推广 **/
    /** 报表页接口-搜索推广索引页 **/
    public static String OPPO_SEARCH_REPORT_INDEX_URL = "http://e.oppomobile.com/searchBid/index";
    /** 报表页接口-搜索推广-趋势图接口 **/
    public static String OPPO_SEARCH_TREND_URL = "http://e.oppomobile.com/searchBid/trend";
    /** 报表页接口-搜索推广-列表接口 **/
    public static String OPPO_SEARCH_LOCALTION_LIST_URL = "http://e.oppomobile.com/searchBid/list";
}
