package com.liu.maji.http;


import com.liu.maji.modle.CommonResponse;
import com.liu.maji.modle.bean.charge_history.ChargeHistoryResponse;
import com.liu.maji.modle.bean.device.ChangeConsumeResponse;
import com.liu.maji.modle.bean.device.ChangeConsumeTypeResultResponse;
import com.liu.maji.modle.bean.device.DeviceInfoResponse;
import com.liu.maji.modle.bean.income.IncomeResponse;
import com.liu.maji.modle.bean.login.CodeResponse;
import com.liu.maji.modle.bean.login.LoginResponse;
import com.liu.maji.modle.bean.vip.ChargeMechineResponse;
import com.liu.maji.modle.bean.vip.VipChargeInfoResponse;
import com.liu.maji.modle.bean.vip.VipInfoResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 *
 */
public interface ApiService {


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("login")
    Observable<LoginResponse> login(@Body RequestBody body);


    //开卡发送验证码
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("card/openSwipeVerify")
    Observable<CommonResponse> getLoginCode(@Body RequestBody body);


    //获取设备信息
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("equip/load")
    Observable<DeviceInfoResponse> getDevicesInfo(@Body RequestBody body);

    //查询卡
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("card/findCardOne")
    Observable<VipChargeInfoResponse> queryChargeInfo(@Body RequestBody body);

    /*
    充值
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("recharge/execute/{deviceNo}/{agentId}")
    Observable<CommonResponse> charge(@Path("deviceNo") String deviceNo, @Path("agentId") String agentId, @Body RequestBody body);

    //收益查询
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("rpt/turnover/load")
    Observable<IncomeResponse> getIncomeData(@Body RequestBody body);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("recharge/load")
    Observable<ChargeHistoryResponse> getChargeData(@Body RequestBody body);


    /*
    开卡
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("card/openSwipe/{deviceId}")
    Observable<CommonResponse> addNewVipCard(@Path("deviceId") String deviceId, @Body RequestBody body);


    /*
    查询会员信息
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("card/findAllCard")
    Observable<VipInfoResponse> queryAllVipInfo(@Body RequestBody body);


    /*
   通过手机号查询会员
    */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("card/findCard")
    Observable<VipInfoResponse> queryVipInfoByMobile(@Body RequestBody body);


    /*
   查询充值机信息
    */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("equip/listczj")
    Observable<ChargeMechineResponse> queryChargeMechineInfo(@Body RequestBody body);


    /*
   查询充值机信息
    */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("equip/queryProduct")
    Observable<ChangeConsumeResponse> queryConsumeType(@Body RequestBody body);


    /*
   修改套餐
    */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("equip/changeProductTaocan")
    Observable<ChangeConsumeTypeResultResponse> changeConsumeType(@Body RequestBody body);


    /*
   停止刷卡盘
    */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("equip/startOrStopMjByAndroid")
    Observable<ChangeConsumeTypeResultResponse> stopSwipe(@Body RequestBody body);/*



    /*
    挂失
     */

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("card/lossCard")
    Observable<CommonResponse> lossCard(@Body RequestBody body);

    /*
    补卡
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("card/repairCard/{deviceId}")
    Observable<CommonResponse> replaceCard(@Path("deviceId") String deviceId, @Body RequestBody body);


    /*
    退卡
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("card/cancelCard")
    Observable<CommonResponse> refundCard(@Body RequestBody body);


    /*
    批量开卡
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("equip/openCardquickly")
    Observable<CommonResponse> batchOpenCard(@Body RequestBody body);


    /*
    投资加盟或者建议
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("advice/addAdvice")
    Observable<CommonResponse> investOrSuggestion(@Body RequestBody body);
















//
//
//
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/index")
//    Observable<MainResponse> getTotalIncom(@Body RequestBody body);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/equip/list")
//    Observable<DeviceTypeResponse> getMyDevice(@Body RequestBody body);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/equip/load")
//    Observable<DeviceDataResponse> getDeviceData(@Body RequestBody body);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/product/load")
//    Observable<ProductResponse> getProductData(@Body RequestBody body);
//
//    /**
//     * 查询营业额统计数据
//     *
//     * @param body
//     * @return
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/rpt/turnover/load")
//    Observable<BusinessResponse> getBusinessData(@Body RequestBody body);
//
//    /**
//     * 查询收益统计数据
//     *
//     * @param body
//     * @return
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/rpt/income/load")
//    Observable<BusinessResponse> getIncomeData(@Body RequestBody body);
//
//    /**
//     * 查询营业额月份
//     *
//     * @param body
//     * @return
//     */
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/rpt/turnover")
//    Observable<MothResponse> getMonthRange(@Body RequestBody body);
//
//    /**
//     * 查询收益月份
//     *
//     * @param body
//     * @return
//     */
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/rpt/income")
//    Observable<MothResponse> getMonthRangeIncom(@Body RequestBody body);
//
//
//    /**
//     * 获取账单首页
//     *
//     * @param body
//     * @return
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/settle/index")
//    Observable<BillTotalResponse> getBillTotalData(@Body RequestBody body);
//
//    /**
//     * 获取最近账单
//     *
//     * @param body
//     * @return
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/settle/index/load")
//    Observable<BillRecentResponse> getBillRecentData(@Body RequestBody body);
//
//    /**
//     * 获取账单查询的月份
//     *
//     * @param body
//     * @return
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/settle/more")
//    Observable<BillMonthResponse> getBillMoth(@Body RequestBody body);
//
//
//    /**
//     * 获取更多账单
//     *
//     * @param body
//     * @return
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/settle/more/load")
//    Observable<BillMoreResponse> getMoreBill(@Body RequestBody body);
//
//    /**
//     * 获取月统计数据
//     *
//     * @param body
//     * @return
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/settle/more/statistics")
//    Observable<BillStaResponse> getMonthSta(@Body RequestBody body);
//
//    /**
//     * 获取库存数据
//     *
//     * @return
//     */
//
//    @POST("api/ivem/equip/stock/{equipId}")
//    Observable<StockResponse> getStockData(@Path("equipId") int equipId);
//
//    /**
//     * 获取总订单金额
//     *
//     * @return
//     */
//
//    @POST("api/ivem/equip/bill/{equipId}")
//    Observable<TurnoverTotalResponse> getTotalTurnover(@Path("equipId") int equipId);
//
//    /**
//     * 获得订单分页信息
//     *
//     * @param body
//     * @return
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/equip/bill/load2")
//    Observable<OrderDetailResponse> getOrderData(@Body RequestBody body);
//
//    /**
//     * 获取近7天的设备订单统计数据
//     *
//     * @param body
//     * @return
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/equip/dayRpt")
//    Observable<RecentDayIncomeResponse> getRecentOrderSta(@Body RequestBody body);
//
//    /**
//     * 每个月数据加载
//     *
//     * @param body
//     * @return
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/ivem/rpt/month/load")
//    Observable<it.dongjun.manager.module.bean.response.business.OrderDetailResponse> getHistoryOrder(@Body RequestBody body);
//
//
}