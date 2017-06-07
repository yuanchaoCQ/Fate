package com.wk.cpd.mvc.utils;

import org.apache.commons.lang.StringUtils;

import com.wk.cpd.mvc.Constant;
import com.wk.cpd.mvc.api.IP;

/**
 *
 * 地域工具类
 *
 */
public final class AreaUtils {

    /**
     * 根据传入的{@code ip}信息获取地域编码
     * 
     * @param ip 传入的{@code ip}地址
     * @return 返回地域编码,如果{@code ip}地址为空返回“0”
     */
    public static int getAreaCodeByIP(final String ip) {
        if (StringUtils.isBlank(ip)) {
            return 0;
        }
        if (ip.indexOf(",") == 0) { // 如果第一个位置是逗号，取第二个ip使用
            return getAreaCode(ip.split(",")[1]);
        } else if (ip.indexOf(",") > 0) { // 如果传入的ip是用逗号分隔的一个ip串，取第一个使用
            return getAreaCode(ip.split(",")[0]);
        } else {
            return getAreaCode(ip);
        }

    }

    /*********************************************************
     * 私有方法
     *********************************************************/

    private static int getAreaCode(final String ip) {
        String[] area = IP.find(ip);
        String areaCode = "";
        if (!StringUtils.isBlank(area[2])) {
            areaCode = Constant.AREA_MAP.get(area[2]);
        } else if (!StringUtils.isBlank(area[1])) {
            areaCode = Constant.AREA_MAP.get(area[1]);
        } else if (!StringUtils.isBlank(area[0])) {
            areaCode = Constant.AREA_MAP.get(area[0]);
        }

        return WKObjectUtils.toInt(areaCode);
    }
}
