//package com.wk.feed.mvc.utils;
//
//import com.google.openrtb.OpenRtb.BidRequest;
//import com.wk.feed.mvc.utils.local.ThreadLocalManager;
//
///**
// * 宏替换工具类
// */
//public final class MacrosubstituionUtils {
//
//    /**
//     * 传入要进行宏替换的{@code url}和相关参数
//     * 
//     * @param url 要进行宏替换的{@code url}
//     * @param id {@code ADX}生成的竞价{@code id}
//     * @param bidid {@code DSP}生成的竞价{@code id}
//     * @param price 中标价格
//     * @param impId 曝光{@code id}
//     * @return 返回宏替换后的{@code url}
//     */
//    public static String macrosubstitutionNurl(final String url, final String id, final String bidid,
//            final String price, final String impId) {
//        String result = url;
//        result = result.replaceAll("\\$\\{AUCTION_ID\\}", id)// 替换ADX生成的竞价id
//                .replaceAll("\\$\\{AUCTION_BID_ID\\}", bidid)// 替换DSP生成的竞价id
//                .replaceAll("\\$\\{AUCTION_PRICE\\}", price)// 替换中标价格，价格是加密的
//                .replaceAll("\\$\\{AUCTION_IMP_ID\\}", impId)// 曝光id
//                .replaceAll("\\$\\{AUCTION_TIMESTAMP\\}", DateTimeUtils.getCurrentSecond() + "");// 竞价操作时间
//        return result;
//    }
//
//    /**
//     * 传入要进行宏替换的{@code url}和相关参数
//     * 
//     * @param url 要进行宏替换的{@code url}
//     * @param id {@code ADX}生成的竞价{@code id}
//     * @param bidid {@code DSP}生成的竞价{@code id}
//     * @param price 中标价格
//     * @param impId 曝光{@code id}
//     * @return 返回宏替换后的{@code url}
//     */
//    public static String macrosubstitutionTrackersWithPrice(final String url, final String id, final String bidid,
//            final String price, final String impId) {
//        String result = url;
//        result = result.replaceAll("\\$\\{AUCTION_ID\\}", id)// 替换ADX生成的竞价id
//                .replaceAll("\\$\\{AUCTION_BID_ID\\}", bidid)// 替换DSP生成的竞价id
//                .replaceAll("\\$\\{AUCTION_PRICE\\}", price)// 替换中标价格，价格是加密的
//                .replaceAll("\\$\\{AUCTION_IMP_ID\\}", impId);// 曝光id
//        return result;
//    }
//
//    /**
//     * 传入要进行宏替换的{@code url}和相关参数
//     * 
//     * @param url 要进行宏替换的{@code url}
//     * @param id {@code ADX}生成的竞价{@code id}
//     * @param bidid {@code DSP}生成的竞价{@code id}
//     * @param impId 曝光{@code id}
//     * @return 返回宏替换后的{@code url}
//     */
//    public static String macrosubstitutionTrackers(final String url, final String id, final String bidid,
//            final String impId) {
//        String result = url;
//        result = result.replaceAll("\\$\\{AUCTION_ID\\}", id)// 替换ADX生成的竞价id
//                .replaceAll("\\$\\{AUCTION_BID_ID\\}", bidid)// 替换DSP生成的竞价id
//                .replaceAll("\\$\\{AUCTION_IMP_ID\\}", impId);// 曝光id
//        return result;
//    }
//
//    /**
//     * 替换点触达需要的参数
//     * 
//     * @param url 要进行宏替换的{@code url}
//     * @param bidRequest 请求信息
//     * @return 返回宏替换后的{@code url}
//     */
//    public static String macrosubstitutionDCD(final String url, final BidRequest bidRequest) {
//        String result = url;
//        result = result.replaceAll("\\$\\{request_id\\}", bidRequest.getId())// 替换成请求id
//                .replaceAll("\\$\\{app_id\\}", ThreadLocalManager.getLocal().getAppId())// 应用id
//                .replaceAll("\\$\\{app_version\\}", bidRequest.getApp().getVer())// 应用版本
//                .replaceAll("\\$\\{package_name\\}", bidRequest.getApp().getBundle())// 包名
//                .replaceAll("\\$\\{device_type\\}", bidRequest.getDevice().getDevicetype().getNumber() + "")// 设备类型
//                .replaceAll("\\$\\{os_type\\}", bidRequest.getDevice().getOs())// 操作系统类型
//                .replaceAll("\\$\\{os_version\\}", bidRequest.getDevice().getOsv())// 操作系统版本
//                .replaceAll("\\$\\{vendor\\}", bidRequest.getDevice().getMake())// 设备厂商
//                .replaceAll("\\$\\{model\\}", bidRequest.getDevice().getModel())// 设备型号
//                .replaceAll("\\$\\{android_id\\}", ThreadLocalManager.getLocal().getAndroidId()) // androidId
//                .replaceAll("\\$\\{imei\\}", ThreadLocalManager.getLocal().getImei())// imei
//                .replaceAll("\\$\\{mac\\}", bidRequest.getDevice().getMacmd5())// mac号
//                .replaceAll("\\$\\{w\\}", bidRequest.getDevice().getW() + "")// 设备宽度
//                .replaceAll("\\$\\{h\\}", bidRequest.getDevice().getH() + "")// 设备高度
//                .replaceAll("\\$\\{connect_type\\}", bidRequest.getDevice().getConnectiontype().getNumber() + "")// 设备连接类型
//                .replaceAll("\\$\\{carrier\\}", bidRequest.getDevice().getCarrier())// 运营商
//                .replaceAll("\\$\\{bss_id\\}", "0")
//                .replaceAll("\\$\\{adslot_id\\}", bidRequest.getImp(0).getTagid());// 基站信息
//        if (bidRequest.getImp(0).hasBanner()) {
//
//            if (bidRequest.getImp(0).getExtension(WkAdxExt.isSplashScreen) == 1) {
//                result = result.replaceAll("\\$\\{ad_type\\}", "3");// 广告类型--开屏
//            } else if (bidRequest.getImp(0).getInstl() == 1) {
//                result = result.replaceAll("\\$\\{ad_type\\}", "2");// 广告类型--插屏
//            } else {
//                result = result.replaceAll("\\$\\{ad_type\\}", "1");// 广告类型--横幅
//            }
//
//        } else if (bidRequest.getImp(0).hasNative()) {
//            result = result.replaceAll("\\$\\{ad_type\\}", "4");// 广告类型--原生
//        } else {
//            result = result.replaceAll("\\$\\{ad_type\\}", "");// 广告类型--空
//        }
//        return result;
//    }
//
//}
