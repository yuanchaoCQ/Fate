package com.wk.cpd.mvc.utils;

import java.util.UUID;

/**
 * @description: 数据生成类
 */
public class GeneratorUtils {

    /**
     * @title: generateDetecting
     * @description: 监播地址生成工具
     * @param url
     * @param requestVO
     * @return
     */
//    public static String generateDetecting(String url, RequestVO requestVO, String requestId,
//            String... ipushParameters) {
//
//        String appId = requestVO.getApp().getAppid();
//        String adslotId = requestVO.getApp().getCrid();
//        String trackerUrl = url + "?request_id=" + requestId + "&app_id=" + appId + "&adslot_id=" + adslotId;
//        StringBuilder reString = new StringBuilder();
//		for(int i = 0; i < ipushParameters.length ; i++){
//			reString.append(ipushParameters[i]);
//		}
//        
//        trackerUrl += reString.toString();
//        
//        return trackerUrl;
//    }

    /**
     * @title: generateRequestId
     * @description: requestId生成工具
     * @param requestVO
     * @return
     * @throws Exception
     */
//    public static String generateRequestId(RequestVO requestVO) throws Exception {
//
//        String appId = requestVO.getApp().getAppid();
//        String adslotId = requestVO.getApp().getAppvr();
//        long timesStamp = System.currentTimeMillis();
//        String uuid = UUID.randomUUID().toString();
//        String requestId = appId + adslotId + timesStamp + uuid;
//
//        return MD5Utils.md5AsString(requestId);
//    }
}
