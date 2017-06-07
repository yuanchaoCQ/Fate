package com.wk.cpd.mvc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.protocol.HttpClientContext;

/**
 * @description: 数据存储
 */
public class DataResource {
    
    /** 账户登录上下文 
     *  key: platId_userId
     *  value: HttpClientContext **/
    public static final Map<String, HttpClientContext> PLAT_CONTEXT = new HashMap<>();
}
