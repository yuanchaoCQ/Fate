//package com.wk.feed.mvc.utils;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.util.ObjectUtils;
//
//import com.google.openrtb.OpenRtb.BidRequest;
//import com.google.openrtb.OpenRtb.BidResponse;
//import com.google.openrtb.OpenRtb.BidResponse.SeatBid;
//import com.google.openrtb.OpenRtb.BidResponse.SeatBid.Bid;
//import com.wk.adx.mvc.valueobject.DspResultVO;
//import com.wk.adx.mvc.valueobject.dsp.DSPType;
//import com.wk.adx.mvc.valueobject.dsp.SDSP;
//import com.wk.adx.mvc.valueobject.flowstrategy.Target;
//
///**
// * 结果排序工具
// *
// */
//public final class ResultUtils {
//
//    /**
//     * <p>
//     * 通过排序取出中标的{@code Dspid}和结算价格{@code price}<br>
//     * 按照竞标的价格降序排序，然后取第一名中标。<br>
//     * 中标的结算价格取第二的价格<br>
//     * <p>
//     * 
//     * @param map 参与排序的数据
//     * @param impid 要排序的曝光id
//     * @return 返回结果，第一个数据是中标的{@code DspId}，第二个数据是中标的结算价格, 第三个数据是中标的{@code Bid}实例
//     */
//    public static Object[] getIdAndPriceViaRank(final Map<Integer, DspResultVO> map, final String impid,
//            final double bidFloor) {
//        Integer dspid = -1;
//        double price = 0d;
//        double maxPrice = 0d;
//        Bid winBid = null;
//        Object[] result = null;
//
//        if (map.size() > 0) {
//            List<Integer> keyList = new ArrayList<Integer>(map.keySet());
//
//            // 打乱排序
//            Collections.shuffle(keyList);
//
//            // 循环遍历map
//            for (Integer key : keyList) {
//                BidResponse response = map.get(key).getResponse();
//                List<SeatBid> seatBids = response.getSeatbidList();
//                if (seatBids.isEmpty()) {
//                    continue;
//                }
//                SeatBid seatBid = seatBids.get(0);
//
//                Bid bid = getBidFromSeatBidViaImpId(seatBid, impid); // 根据曝光id取出参与竞价排序的Bid实例
//                if (bid == null) {
//                    continue;
//                }
//
//                double bidPrice = bid.getPrice();
//                // 竞价价格如果小于底价，不进行比价
//                if (bidPrice < Math.abs(bidFloor)) {
//                    continue;
//                }
//
//                if (bidPrice > maxPrice) {
//                    price = maxPrice; // 结算价格是以第二价格做结算的，所以当出闲比当前最大价格大的值时，
//                    // 当前最大价格成为第二价格，放入结算价格中
//                    maxPrice = bidPrice; // 保存最大价格
//                    dspid = key; // 保存出价最高的dsp的id
//                    winBid = bid;
//                } else if (bidPrice > price) {
//                    price = bidPrice; // 获取第二价格
//                }
//            }
//
//            if (price == 0) {
//                price = maxPrice; // 如果只有一家DSP参与竞价，那个计算价格就是当前价格
//            }
//
//            if (winBid == null) {
//                return null;
//            }
//
//            // XXX 暂时将结算价格默认成第一价格
//            price = maxPrice;
//
//            result = new Object[] { dspid, price, winBid };
//        }
//        return result;
//    }
//
//    /**
//     * @description: DSP返回结果(避免超时返回影响流程)
//     * @param resultMap DSP返回结果集
//     * @param dspSendList DSP发送列表map
//     */
//    public static void DSPReturn(Map<Integer, DspResultVO> resultMap, Map<Integer, DspResultVO> dspSendList) {
//
//        if (ObjectUtils.isEmpty(dspSendList)) {
//            return;
//        }
//
//        for (Integer key : dspSendList.keySet()) {
//            resultMap.put(key, (DspResultVO) dspSendList.get(key).clone());
//        }
//    }
//
//    /**
//     * 第三方平台信息检测
//     * 
//     * @param dspId DSP的id
//     * @param response 响应信息
//     * @param bidRequest 竞价请求
//     * @param currTarget 当前切量信息
//     * @return
//     * @throws Exception
//     */
//    public static boolean thirdPlatResultValidate(int dspId, BidResponse response, BidRequest bidRequest,
//            Target currTarget) throws Exception {
//
//        boolean canDownload = 0 == currTarget.getSptdl() ? false : true;
//        List<SeatBid> seatBids = response.getSeatbidList();
//
//        DSPType dspType = WKObjectUtils.geDspType(dspId + "");
//        SDSP sdsp = dspType.getSdsp();
//        // 检查渠道信息是否正确
//        if (sdsp != null && sdsp.checkInfo(seatBids.get(0), bidRequest, canDownload)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    /*********************************************************************
//     * 私有方法
//     *********************************************************************/
//
//    /**
//     * 根据传入的{@code impid}获取{@link SeatBid}中对应的{@link Bid}实例
//     * 
//     * @param seatBid {@link SeatBid}实例
//     * @param impid 曝光id
//     * @return 返回{@link Bid}实例，如果没有结果返回null
//     */
//    private static Bid getBidFromSeatBidViaImpId(SeatBid seatBid, String impid) {
//        List<Bid> bidList = seatBid.getBidList();
//        for (Bid bid : bidList) {
//            if (impid.equals(bid.getImpid())) {
//                return bid;
//            }
//        }
//        return null;
//    }
//
//}
