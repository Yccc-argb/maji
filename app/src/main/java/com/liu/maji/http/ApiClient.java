package com.liu.maji.http;


import android.text.TextUtils;
import android.widget.TextView;

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
import com.liu.maji.utils.JsonUtils;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */

public class ApiClient {

    public static String TAG = "retrofit";
    private final int DEFAULT_TIMEOUT = 15;
    public Retrofit mRetrofit;
    public ApiService apiService;

    public ApiClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();


            CookieHandler cookieHandler = new CookieManager();
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                    .cookieJar(new JavaNetCookieJar(cookieHandler))
                    .sslSocketFactory(sslSocketFactory)
//                    .retryOnConnectionFailure(false)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            request = request.newBuilder()
                                    .addHeader("producer", android.os.Build.MANUFACTURER)
                                    .addHeader("mobileModel", android.os.Build.MODEL).build();
                            return chain.proceed(request);
                        }
                    })
//                    .addInterceptor(new SignInterceptor())
                    .addInterceptor(new LoggerInterceptor("MJ_LOGGER", true));

//            String url = Constant.BASE_URL;
            mRetrofit = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(ApiConstants.BASE_URL)
                    .build();

            apiService = mRetrofit.create(ApiService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static class SingleHolder {
        private static final ApiClient INSTANCE = new ApiClient();
    }

    public static ApiClient getInstance() {
        return SingleHolder.INSTANCE;
    }


    /**
     * 登录
     *
     * @param phone
     * @param code
     * @param subscriber
     */
    public void login(String phone, String code, ResponseSubscriber<LoginResponse> subscriber) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mobilePhone", phone);
        map.put("password", code);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);

        apiService.login(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 获取验证码
     *
     * @param phone
     * @param subscriber
     */
    public void getCode(String phone, int merchantId, int agentId, ResponseSubscriber<CommonResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("mobilePhone", phone);
        map.put("merchantId", merchantId);
        map.put("agentId", agentId);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.getLoginCode(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 获取设备信息
     *
     * @param agentId
     * @param merchantId
     * @param currentPage
     * @param pageCounts
     * @param recordStart
     * @param recordEnd
     * @param subscriber
     */

    public void getDevicesInfo(int agentId, int merchantId, int currentPage, int pageCounts, int recordStart, int recordEnd, ResponseSubscriber<DeviceInfoResponse> subscriber) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("agentId", agentId);
        map.put("merchantId", merchantId);
        map.put("page", currentPage);
        map.put("pageSize", pageCounts);
        map.put("recordStart", recordStart);
        map.put("recordEnd", recordEnd);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.getDevicesInfo(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


    /**
     * 查询充值信息
     *
     * @param agentId
     * @param merchantId
     * @param vipCardNum
     * @param subscriber
     */
    public void queryChargeInfo(int agentId, int merchantId, String vipCardNum, String mobile, ResponseSubscriber<VipChargeInfoResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("agentId", agentId);
        map.put("merchantId", merchantId);
        if (mobile.isEmpty()) {
            //手机号为空,校验卡号
            map.put("cd", vipCardNum);
        } else if (vipCardNum.isEmpty()) {
            //卡号为空,校验手机号
            map.put("mobile", mobile);
        } else {
            map.put("cd", vipCardNum);
            map.put("mobile", mobile);
        }
        map.put("status", "normal");
        map.put("enabledFlag", "Y");

        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.queryChargeInfo(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void charge(String deviceId, String agentId, String carddNum, String operateAmount, String giveAmount, String platAmount, ResponseSubscriber<CommonResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("cd", carddNum);
        map.put("operateAmount", operateAmount);
        map.put("giveAmount", giveAmount);
        map.put("platAmount", platAmount);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.charge(deviceId, agentId, requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 我的收益
     *
     * @param agentId
     * @param merchantId
     * @param subscriber
     */
    public void getIncomeData(int agentId, int merchantId, int range, ResponseSubscriber<IncomeResponse> subscriber) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("agentId", agentId);
        map.put("merchantId", merchantId);
        map.put("range", range);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.getIncomeData(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 充值记录
     *
     * @param agentId
     * @param merchantId
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageCounts
     * @param recordStart
     * @param recordEnd
     * @param subscriber
     */
    public void getChargeHistoryData(int agentId, int merchantId, String mobile, String operateType, String startDate, String endDate, int currentPage, int pageCounts, int recordStart, int recordEnd, ResponseSubscriber<ChargeHistoryResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("agentId", agentId);
        map.put("merchantId", merchantId);
        if (TextUtils.isEmpty(mobile)) {
            map.put("mobile", "");
            map.put("cardCd", "");
        } else if (mobile.length() >= 11) {
            //认为是手机号
            map.put("mobile", mobile);
            map.put("cardCd", "");
        } else {
            map.put("mobile", "");
            map.put("cardCd", mobile);
        }
        map.put("operateType", operateType);
        map.put("startTime", startDate);
        map.put("endTime", endDate);
        map.put("page", currentPage);
        map.put("pageSize", pageCounts);
        map.put("recordStart", recordStart);
        map.put("recordEnd", recordEnd);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.getChargeData(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


    /**
     * 开卡
     *
     * @param merchantId
     * @param agentId
     * @param mobile
     * @param cd
     * @param name
     * @param subscriber
     */
    public void addNewVipCard(String deviceId, int merchantId, int agentId, String mobile, String cd, String name, String verifyCode, ResponseSubscriber<CommonResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("merchantId", String.valueOf(merchantId));
        map.put("agentId", String.valueOf(agentId));
        map.put("mobilePhone", mobile);
        map.put("verifyCode", verifyCode);
        map.put("cd", cd);
        map.put("name", name);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.addNewVipCard(deviceId, requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /*
    查询所有会员信息
     */
    public void queryAllVipInfo(int agentId, int merchantId, int currentPage, int pageCounts, int recordStart, int recordEnd, ResponseSubscriber<VipInfoResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("agentId", agentId);
        map.put("merchantId", merchantId);
        map.put("page", currentPage);
        map.put("pageSize", pageCounts);
        map.put("recordStart", recordStart);
        map.put("recordEnd", recordEnd);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.queryAllVipInfo(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /*
   查询所有会员信息
    */
    public void queryVipInfoByMobile(int merchantId, int agentId, String cd, String mobilePhone,String status,String enabledFlag, ResponseSubscriber<VipInfoResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("merchantId", merchantId);
        map.put("agentId", agentId);
        map.put("mobile", mobilePhone);
        map.put("cd", cd);
        map.put("status", status);
        map.put("enabledFlag", enabledFlag);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.queryVipInfoByMobile(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /*
  查询充值机信息
   */
    public void queryChargeMechineInfo(int merchantId, int agentId, int currentPage, int pageCounts, int recordStart, int recordEnd, ResponseSubscriber<ChargeMechineResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("merchantId", merchantId);
        map.put("agentId", agentId);
        map.put("page", currentPage);
        map.put("pageSize", pageCounts);
        map.put("recordStart", recordStart);
        map.put("recordEnd", recordEnd);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.queryChargeMechineInfo(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /*
  查询计费套餐
   */
    public void queryConsumeTypeInfo(int merchantId, ResponseSubscriber<ChangeConsumeResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("merchantId", merchantId);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.queryConsumeType(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /*
    修改套餐
  */
    public void changeConsumeType(int productId, int modernProductID, int equipId, ResponseSubscriber<ChangeConsumeTypeResultResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("modernProductID", modernProductID);
        map.put("equipId", equipId);
        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.changeConsumeType(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /*
    停止刷卡盘
  */
    public void stopSwipe(int merchantId,int agentId,String diviceID,String switchFlag,String sonsumType
                          ,String consumNumber,String equipId,ResponseSubscriber<ChangeConsumeTypeResultResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("merchantId", merchantId);
        map.put("agentId", agentId);
        map.put("diviceID", diviceID);
        map.put("switchFlag", switchFlag);
        map.put("sonsumType", sonsumType);
        map.put("consumNumber", consumNumber);
        map.put("equipId", equipId);

        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.stopSwipe(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /*
    挂失卡
  */
    public void lossCard(int merchantId,int agentId,String cd,String mobile,String newCd,ResponseSubscriber<CommonResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("merchantId", merchantId);
        map.put("agentId", agentId);
        map.put("cd", cd);
        map.put("mobile", mobile);

        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.lossCard(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /*
    挂失卡
  */
    public void replaceCard(int merchantId,int agentId,String cd,String deviceId,String mobile,String newCd,ResponseSubscriber<CommonResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("merchantId", merchantId);
        map.put("agentId", agentId);
        map.put("cd", cd);
        map.put("mobile", mobile);
        map.put("newCd", newCd);

        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.replaceCard(deviceId,requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /*
    退卡
  */
    public void refundCard(int merchantId,int agentId,String cd,String mobile,ResponseSubscriber<CommonResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("merchantId", merchantId);
        map.put("agentId", agentId);
        map.put("cd", cd);
        map.put("mobile", mobile);

        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.refundCard(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /*
    批量开卡
  */
    public void batchOpenCard(int merchantId,int agentId,String onlineAmount,String offlineAmount,String giveAmount,
                              String openCardModel,String diviceID,ResponseSubscriber<CommonResponse> subscriber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("merchantId", merchantId);
        map.put("agentId", agentId);
        map.put("onlineAmount", onlineAmount);
        map.put("offlineAmount", offlineAmount);
        map.put("giveAmount", giveAmount);
        map.put("openCardModel", openCardModel);
        map.put("diviceID", diviceID);

        String params = JsonUtils.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
        apiService.batchOpenCard(requestBody).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }





//
//
//
//
//
//    /**
//     * 获取首页数据
//     *
//     * @param agentId
//     * @param merchantId
//     * @param subscriber
//     */
//    public void getTotalIncomData(int agentId, int merchantId, ResponseSubscriberTwo<MainResponse> subscriber) {
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//
//        apiService.getTotalIncom(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 获取我的设备
//     *
//     * @param agentId
//     * @param merchantId
//     * @param subscriber
//     */
//    public void getDeviceType(int agentId, int merchantId, ResponseSubscriberTwo<DeviceTypeResponse> subscriber) {
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//
//        apiService.getMyDevice(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//
//    /**
//     * 设备信息
//     *
//     * @param agentId
//     * @param merchantId
//     * @param equipTypeId
//     * @param page
//     * @param pageSize
//     * @param recordStart
//     * @param recordEnd
//     * @param subscriber
//     */
//    public void getDeviceData(int agentId, int merchantId, int equipTypeId, int page, int pageSize, int recordStart, int recordEnd, ResponseSubscriberTwo<DeviceDataResponse> subscriber) {
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        map.put("equipTypeId", equipTypeId);
//        map.put("page", page);
//        map.put("pageSize", pageSize);
//        map.put("recordStart", recordStart);
//        map.put("recordEnd", recordEnd);
//
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//
//        apiService.getDeviceData(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 产品信息查寻
//     *
//     * @param agentId
//     * @param merchantId
//     * @param name
//     * @param page
//     * @param pageSize
//     * @param recordStart
//     * @param recordEnd
//     * @param subscriber
//     */
//    public void getProductData(int agentId, int merchantId, String name, int page, int pageSize, int recordStart, int recordEnd, ResponseSubscriberTwo<ProductResponse> subscriber) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        map.put("name", name);
//        map.put("page", page);
//        map.put("pageSize", pageSize);
//        map.put("recordStart", recordStart);
//        map.put("recordEnd", recordEnd);
//
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//
//        apiService.getProductData(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 营业额报表数据查询
//     *
//     * @param agentId
//     * @param merchantId
//     * @param range
//     * @param subscriber
//     */
//    public void getBusinessData(int agentId, int merchantId, int range, ResponseSubscriberTwo<BusinessResponse> subscriber) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        map.put("range", range);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//        apiService.getBusinessData(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 营业额报表数据查询
//     *
//     * @param agentId
//     * @param merchantId
//     * @param range
//     * @param subscriber
//     */
//    public void getIncomeData(int agentId, int merchantId, int range, ResponseSubscriberTwo<BusinessResponse> subscriber) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        map.put("range", range);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//        apiService.getIncomeData(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 营业额月份
//     *
//     * @param subscriber
//     */
//    public void getMonthtData(ResponseSubscriberTwo<MothResponse> subscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), "");
//        apiService.getMonthRange(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 收益月份
//     *
//     * @param subscriber
//     */
//    public void getMonthtDataIncom(ResponseSubscriberTwo<MothResponse> subscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), "");
//        apiService.getMonthRangeIncom(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 账单首页
//     *
//     * @param subscriber
//     */
//    public void getBillTotalData(int agentId, int merchantId, ResponseSubscriberTwo<BillTotalResponse> subscriber) {
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//        apiService.getBillTotalData(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取最近账单
//     *
//     * @param subscriber
//     */
//    public void getBillRecentData(int agentId, int merchantId, ResponseSubscriberTwo<BillRecentResponse> subscriber) {
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//        apiService.getBillRecentData(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 获取账单的查询月份
//     *
//     * @param subscriber
//     */
//    public void getBillMonth(ResponseSubscriberTwo<BillMonthResponse> subscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), "");
//        apiService.getBillMoth(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 获取更多订单
//     *
//     * @param userId
//     * @param merchantId
//     * @param startTime
//     * @param status
//     * @param page
//     * @param pageSize
//     * @param recordStart
//     * @param recordEnd
//     * @param subscriber
//     */
//    public void getMoreBill(int userId, int merchantId, String startTime, String status, int page, int pageSize, int recordStart, int recordEnd, ResponseSubscriberTwo<BillMoreResponse> subscriber) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("userId", userId);
//        map.put("merchantId", merchantId);
//        map.put("startTime", startTime);
//        if (!TextUtils.isEmpty(status)) {
//            map.put("status", status);
//        }
//        map.put("page", page);
//        map.put("pageSize", pageSize);
//        map.put("recordStart", recordStart);
//        map.put("recordEnd", recordEnd);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//        apiService.getMoreBill(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//
//    /**
//     * 获取月账单统计
//     *
//     * @param agentId
//     * @param merchantId
//     * @param month
//     * @param subscriber
//     */
//    public void getMonthDataSta(int agentId, int merchantId, String month, ResponseSubscriberTwo<BillStaResponse> subscriber) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        map.put("month", month);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//        apiService.getMonthSta(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取库存
//     *
//     * @param subscriber
//     */
//    public void getStockData(int equipId, ResponseSubscriberTwo<StockResponse> subscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), "");
//        apiService.getStockData(equipId).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 获取今日账单总数据
//     *
//     * @param subscriber
//     */
//    public void getTurnoverTotal(int equipId, ResponseSubscriberTwo<TurnoverTotalResponse> subscriber) {
//
//
//        apiService.getTotalTurnover(equipId).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 获得订单分页信息
//     *
//     * @param subscriber
//     */
//    public void getOrderData(int equipId, String day,int page, int pageSize, int recordStart, int recordEnd, ResponseSubscriberTwo<OrderDetailResponse> subscriber) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("equipId", equipId);
//        map.put("startTime", day);
//        map.put("page", page);
//        map.put("pageSize", pageSize);
//        map.put("recordStart", recordStart);
//        map.put("recordEnd", recordEnd);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//        apiService.getOrderData(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    public void getMonthHistoryOrder(int agentId, int merchantId, String month, int page, int pageSize, int recordStart, int recordEnd, ResponseSubscriberTwo<it.dongjun.manager.module.bean.response.business.OrderDetailResponse> subscriber) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        map.put("month", month);
//        map.put("page", page);
//        map.put("pageSize", pageSize);
//        map.put("recordStart", recordStart);
//        map.put("recordEnd", recordEnd);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//        apiService.getHistoryOrder(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void getRecentDayIncomeSta(int agentId, int merchantId, int equipId, ResponseSubscriberTwo<RecentDayIncomeResponse> subscriber) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("agentId", agentId);
//        map.put("merchantId", merchantId);
//        map.put("equipId", equipId);
//        String params = JsonUtils.toJSONString(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text; charset=utf-8"), params);
//        apiService.getRecentOrderSta(requestBody).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
////    /**
////     * 获取版本信息
////     *
////     * @param subscriber
////     */
////    public void getVersionInfo(ResponseSubscriberTwo<GetVersionResponse> subscriber) {
////        apiService.getVersionInfo()
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * 运输费用
////     *
////     * @param recIds
////     * @param addressId
////     * @param bonusIds
////     * @param subscriber
////     */
////    public void getShippingFee(String recIds, String addressId, String bonusIds, ResponseSubscriberTwo<GetShippingFeeResponse> subscriber) {
////        apiService.getShippingFee(recIds, addressId, bonusIds)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////    /**
////     * 更新购物车
////     *
////     * @param recId
////     * @param number
////     * @param subscriber
////     */
////    public void updateCart(String recId, String number, ResponseSubscriberTwo<CommonResponse> subscriber) {
////        apiService.updateCart(recId, number)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * 验证码登录
////     *
////     * @param username
////     * @param code       ：验证码
////     * @param subscriber
////     */
////    public void codeLogin(String username, String code, Subscriber<UserLoginResponse> subscriber) {
////        String[] valueList = new String[]{code, username};
////        String clientId = "098f6bcd4621d373cade4e832627b4f6";
////        String token = Prefs.getString(SpEnum.TOKEN.name(), Constant.TOKEN_DEFAULT);
////        apiService.codeLogin(username, clientId, code)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
//////    /**
//////     * 获取商品详情
//////     *
//////     * @param id
//////     * @param subscriber
//////     */
//////    public void getGoodDetail(@NonNull String id, SubscriberResponse<GetGoodsInfoResponse> subscriber) {
//////        String[] valueList = new String[]{id};
//////        apiService.getGoodsInfo(Prefs.getString(SpEnum.TOKEN.name(), Constant.TOKEN_DEFAULT), id, SignUtils.getSignPackageString(valueList))
//////                .subscribeOn(Schedulers.io())
//////                .unsubscribeOn(Schedulers.io())
//////                .observeOn(AndroidSchedulers.mainThread())
//////                .subscribe(subscriber);
//////    }
////
////
////    /**
////     * 加入购物车
////     *
////     * @param goods_act_id
////     * @param goodId
////     * @param num
////     * @param parentId
////     * @param spec
////     * @param is_reset
////     * @param subscriber
////     */
////    public void addCart(String goods_act_id, String goodId, @Nullable String num, @Nullable String parentId, @Nullable List<String> spec, @Nullable boolean is_reset,
////                        int giftType, String ptId, String giftExchangeId,
////                        ResponseSubscriberTwo<AddCartResponse> subscriber) {
////        apiService.addCart(num, spec, goodId, is_reset, goods_act_id, giftType, ptId, giftExchangeId, 2)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * 更改用户信息
////     *
////     * @return
////     */
////    public void updateUserInfo(UserModel userModel, ResponseSubscriberTwo<GetUserInfoResponse> subscriber) {
////        String nickname = userModel.getNickname();
////        String bir = userModel.getBirthday();
////        String sex = userModel.getSex();
////        String email = userModel.getEmail();
////        String headimg = userModel.getHeadimg();
////        int defaultAddressId = userModel.getAddressId() == null ? 0 : Integer.parseInt(userModel.getAddressId());
////        Integer sex2 = 0;
////
////
////        if (!TextUtils.isEmpty(sex)) {
////            sex2 = Integer.parseInt(sex);
////        } else {
////            sex2 = null;
////        }
////
////        apiService.updateUserInfo(nickname, bir, sex2, email, headimg, defaultAddressId)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////    /**
////     * 通过支付id获取微信的预付款信息
////     *
////     * @param tradeNo
////     * @param subscriber
////     */
////
////    public void getWxPrePayInfo(String tradeNo, ResponseSubscriberTwo<GetWxPrePayInfo> subscriber) {
////        String token = Prefs.getString(SpEnum.TOKEN.name()
////                , Constant.TOKEN_DEFAULT);
////
////
////        String[] valueList = new String[]{tradeNo};
////        apiService.getWxPrePayInfo(tradeNo)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * 商品评论
////     *
////     * @param commenGoodRequestModel
////     * @param subscriber
////     */
////    public void commentGoods(CommentGoodRequestModel commenGoodRequestModel, List<String> imgPaths, ResponseSubscriberTwo<CommonResponse> subscriber) {
////
////        String rec_id = commenGoodRequestModel.getRec_id();
////        String content = commenGoodRequestModel.getContent();
////        String goods_id = commenGoodRequestModel.getGoods_id();
////        int comment_rank = commenGoodRequestModel.getComment_rank();
////        int server = commenGoodRequestModel.getServer();
////        int send = commenGoodRequestModel.getSend();
////        int shipping = commenGoodRequestModel.getShipping();
////        String order_id = commenGoodRequestModel.getOrder_id();
////        int hide_username = commenGoodRequestModel.getHide_username();
////
////        apiService.commentGoods(
////                rec_id, content, imgPaths, goods_id,
////                comment_rank, server, send,
////                shipping,
////                order_id,
////                hide_username
////        )
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
//////    /**
//////     * 提交订单
//////     *
//////     * @param orderRequest
//////     * @param map
//////     * @param subscriber
//////     */
//////    public void submitOrder(SubmitOrderRequest orderRequest, Map<String, String> map, ResponseSubscriberTwo<SubmitOrderResponse> subscriber) {
//////        String token = Prefs.getString(SpEnum.TOKEN.name()
//////                , Constant.TOKEN_DEFAULT);
//////        String orderString = JsonUtils.toJSONString(orderRequest);
//////        String[] valueList = new String[]{};
//////        apiService.submitOrder(
//////                "15307",
//////                 orderString, JsonUtils.toJSONString(map), SignUtils.getSignPackageString(valueList))
//////                .subscribeOn(Schedulers.io())
//////                .unsubscribeOn(Schedulers.io())
//////                .observeOn(AndroidSchedulers.mainThread())
//////                .subscribe(subscriber);
//////    }
////
////
////    /**
////     * 获取中国的区域列表
////     *
////     * @param type
////     * @param subscriber
////     */
////    public void getDistrictList(int type, ResponseSubscriberTwo<UserGetDistrictResponse> subscriber) {
////
////        String token = Prefs.getString(SpEnum.TOKEN.name()
////                , Constant.TOKEN_DEFAULT);
////        apiService.getDistrictList(type)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////    /**
////     * 添加地址
////     *
////     * @param address
////     * @param subscriber
////     */
////    public void addAddress(AddAddressRequest address, ResponseSubscriberTwo<CommonResponse> subscriber) {
////        String name = address.getConsignee();
////        String mobile = address.getMobile();
////        int provinceId = address.getProvince();
////        int cityId = address.getCity();
////        int districtId = address.getDistrict();
////        String addressDetail = address.getAddressDetail();
////        String zipCode = address.getZipCode();
////        String[] valueList = new String[]{addressDetail, String.valueOf(cityId), String.valueOf(districtId), mobile, name, String.valueOf(provinceId), zipCode};
////
////        apiService.addAddress(name, mobile, provinceId, cityId, districtId, addressDetail, zipCode)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * 删除地址
////     *
////     * @param ids
////     * @param subscriber
////     */
////    public void deleteAddressList(String ids, ResponseSubscriberTwo<CommonResponse> subscriber) {
////        int id = 0;
////        if (!TextUtils.isEmpty(ids)) {
////
////            id = Integer.parseInt(ids);
////        } else {
////            return;
////        }
////        int[] id2 = new int[]{id};
////
////
////        apiService.deleteAddressList(id2)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * 获取商品评论列表
////     *
////     * @param goodId
////     * @param type
////     * @param page
////     * @param subscriber
////     */
////    public void getGoodComments(String goodId, String type, String page, int end,
////                                SubscriberResponse<GetGoodCommentsResponse> subscriber) {
////        String[] valueList = new String[]{end + "" == null ? "" : end + "", goodId == null ? "" : goodId, page == null ? "" : page, type == null ? "" : type};
////        apiService.getGoodComments(page, end, goodId, type)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * 抽奖
////     *
////     * @param longitud
////     * @param latitude
////     * @param subscriber
////     */
////    public void drawLottery(String longitud, String latitude, ResponseSubscriberTwo<PrizeDrawResponse> subscriber) {
////        String[] valueList = new String[]{latitude, longitud};
////        apiService.drawLottery(longitud, latitude)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////
////    }
////
////    /**
////     * app售货机消息列表接口
////     *
////     * @param subscriber
////     */
////    public void getMessageList(ResponseSubscriberTwo<GetMessageResponse> subscriber) {
////        String[] valueList = new String[]{};
////        apiService.getMessageList()
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////    /**
////     * APP获取售货机地址列表
////     *
////     * @param longitud
////     * @param latitude
////     * @param subscriber
////     */
////
////    public void getMachineAddressList(String longitud, String latitude, ResponseSubscriberTwo<GetMachineAddressResponse> subscriber) {
////        String[] valueList = new String[]{latitude, longitud};
////        apiService.getMachineAddressList(longitud, latitude)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * app扫二维码获取信息
////     *
////     * @param machine_code
////     * @param subscriber
////     */
////    public void getGoodsInfoByCode(String machine_code, ResponseSubscriberTwo<GetQRCodeBonusResponse> subscriber) {
////        String[] valueList = new String[]{machine_code};
////
////        apiService.getGoodsInfoByCode(machine_code)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////    /**
////     * app出货请求接口
////     *
////     * @param bar
////     * @param device_no
////     * @param order_sn
////     * @param subscriber
////     */
////    public void exportGoods(String bar, String device_no, String order_sn, ResponseSubscriberTwo<GetSpitGoodsResponse> subscriber) {
////        String[] valueList = new String[]{bar, device_no, order_sn};
////        apiService.exportGoods(device_no, order_sn, bar)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////    /**
////     * app售货机评论接口
////     *
////     * @param comment_rank
////     * @param operat
////     * @param position
////     * @param speed
////     * @param category
////     * @param subscriber
////     */
////    public void commentVendor(String comment_rank, String operat, String position, String speed, String category, ResponseSubscriberTwo<GetVendorCommentResponse> subscriber) {
////        String[] valueList = new String[]{category, comment_rank, operat, position, speed};
////        apiService.commentVendor(comment_rank, operat, position, speed, category)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * 更新分享之后的状态
////     *
////     * @param status
////     * @param id
////     * @param subscriber
////     */
////    public void updateShareInfoStatus(String status, int id, ResponseSubscriberTwo<CommonResponse> subscriber) {
////        String[] valueList = new String[]{id + "", status};
////        apiService.updateShareInfoStatus(id, status)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * Java-获取短信验证码
////     * @param  ：
////     * @param mobile：手机号
////     * @param strEnsure：图片验证码
////     * @param purpose
////     * @param subscriber
////     */
//////    public void getLoginCode(String  ,String mobile,String strEnsure,String purpose, ResponseSubscriberTwo<CommonResponse> subscriber) {
//////
//////        String[] valueList = new String[]{mobile};
//////        apiService.getLoginCode(mobile, SignUtils.getSignPackageString(valueList))
//////                .subscribeOn(Schedulers.io())
//////                .unsubscribeOn(Schedulers.io())
//////                .observeOn(AndroidSchedulers.mainThread())
//////                .subscribe(subscriber);
//////    }
////
////    /*java cloud 接口*/
////
////    /**
////     * java 登录
////     *
////     * @param userName：用户名
////     * @param password：密码
////     */
////    public void login(String userName, String password, Subscriber<UserLoginResponse> subscriber) {
////        //platform：登录平台标识（1.App 2.WEBAPP 3.PC）
////        //clientId：客户端标识(eWhqcy1taWNyby1tYWxseWhqcy1taWNyby1tYWxs)
////        int platform = 1;
////        String clientId = "098f6bcd4621d373cade4e832627b4f6";
////
////        String[] valueList = new String[]{clientId, password, platform + "", userName};
////        apiService.login(userName, password, clientId)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////    /**
////     * java-注册
////     *
////     * @param mobile:手机号
////     * @param pwd:密码
////     * @param code:验证码
////     * @param type:操作类型1:注册2:修改密码
////     */
////    public void register(String mobile, String pwd, String code, int type, Subscriber<CommonResponse> subscriber) {
////        apiService.register(mobile, pwd, code, type)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////
////    }
////
////    /**
////     * 购物车列表
////     *
////     * @param page
////     * @param subscriber
////     */
////    public void getCartList(int page, SubscriberResponse<GetCartListResponse> subscriber) {
////
////        apiService.getCartList(page)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
////
////
////    /**
////     * 获取用户信息
////     *
////     * @param subscriber
////     */
////    public void getUserInfo(ResponseSubscriberTwo<GetUserInfoResponse> subscriber) {
////        apiService.getUserInfo()
////                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取订单各种类型的数量
//     *
//     * @param subscriber
//     */
//    public void getOrderCount(ResponseSubscriberTwo<UserGetOrderCountResponse> subscriber) {
//        apiService.getOrderCount()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取订单列表
//     *
//     * @param page 页数
//     * @param size 每页大小
//     * @param type 列表类型 已完成订单:finished,待评论订单:awaitComment,
//     *             待发货:awaitShip,待付款:awaitPay,退款中:payback,
//     *             已取消:canceled,待收货:awaitReceipt,已发货:shipped,
//     *             所有:all(默认all)
//     */
//
//    public void getOrderList(int page, int size, String type, ResponseSubscriberTwo<UserGetOrderListResponse> subscriber) {
//        apiService.getOrderList(page, size, type)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 获取地址信息
//     *
//     * @param subscriber
//     */
//    public void getAddressList(ResponseSubscriberTwo<UserGetAddressListResponse> subscriber) {
//        apiService.getAddressList()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 更新地址
//     *
//     * @param addressId
//     * @param mobile
//     * @param consignee     收件人
//     * @param provinceId
//     * @param cityId
//     * @param districtId
//     * @param addressDetail
//     * @param zipCode       邮政编码
//     * @param subscriber
//     */
//    public void updateAddress(String addressId, String mobile, String consignee, int provinceId, int cityId, int districtId, String addressDetail, String zipCode, ResponseSubscriberTwo<CommonResponse> subscriber) {
//        apiService.updateAddressInfo(consignee, mobile, provinceId, cityId, districtId, addressDetail, zipCode, addressId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /*获取优惠券列表---未使用*/
//    public void getBonusList(int isUsed, int page, String supplierId, String goodIds, double payfee, ResponseSubscriberTwo<GetBonusListResponse> subscriber) {
//        int supplierId2 = 0;
//        if (!TextUtils.isEmpty(supplierId)) {
//            supplierId2 = Integer.parseInt(supplierId);
//        }
//        String[] goodIds2 = new String[]{goodIds};
//        apiService.getBonusList(goodIds2, isUsed, page, 10, payfee)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /*获取优惠券列表--已使用*/
//    public void getBonusList(int isUsed, int page, ResponseSubscriberTwo<GetBonusListResponse> subscriber) {
//        String[] ss = new String[]{};
//        apiService.getBonusList(ss, isUsed, page, 10, 0.0)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 领取优惠券
//     *
//     * @param bonusSn
//     * @param subscriber
//     */
//    public void receiveBonus(String bonusSn, ResponseSubscriberTwo<CommonResponse> subscriber) {
//
//        apiService.receiveBonus(bonusSn)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 分类列表
//     *
//     * @param subscriber
//     */
//    public void getIndexCateList(ResponseSubscriberTwo<GetIndexCateListResponse> subscriber) {
//
//        apiService.getIndexCateList()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 获取订单详情
//     *
//     * @param orderId
//     * @param subscriber
//     */
//    public void getOrderDetail(String orderId, ResponseSubscriberTwo<UserGetOrderDetailResponse> subscriber) {
//        int orderId2 = 0;
//        if (!TextUtils.isEmpty(orderId)) {
//            orderId2 = Integer.parseInt(orderId);
//        }
//
//        apiService.getOrderDetail(orderId2)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 根据已选择的购物车的部分商品的id获取确认订单页面的信息
//     *
//     * @param recIds
//     * @param addressId
//     * @param subscriber
//     */
//    public void getEnsureOrderInfo(String recIds, String addressId, ResponseSubscriberTwo<GetEnsureOrderResponse> subscriber) {
//
//        int addressId2 = 0;
//        if (!TextUtils.isEmpty(addressId)) {
//            addressId2 = Integer.parseInt(addressId);
//        }
//        apiService.getEnsureOrderInfo(recIds, addressId2)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 确认收货
//     *
//     * @param orderId
//     * @param subscriber
//     */
//    public void confirmReceiveGood(String orderId, ResponseSubscriberTwo<CommonResponse> subscriber) {
//
//        int orderId2 = 0;
//        if (!TextUtils.isEmpty(orderId)) {
//            orderId2 = Integer.parseInt(orderId);
//        }
//        apiService.confirmReceiveGood(orderId2)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 取消订单
//     *
//     * @param orderId
//     * @param subscriber
//     */
//    public void cancelOrder(String orderId, ResponseSubscriberTwo<CommonResponse> subscriber) {
//        int orderId2 = 0;
//        if (!TextUtils.isEmpty(orderId)) {
//            orderId2 = Integer.parseInt(orderId);
//        }
//        apiService.cancelOrder(orderId2)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 申请退款
//     *
//     * @param orderId
//     * @param goodStatus
//     * @param reason
//     * @param imgs
//     * @param name
//     * @param mobile
//     * @param subscriber
//     */
//    public void applyReturnMoney(int orderId, int goodStatus, String reason, String detail, String imgs, String name, String mobile, ResponseSubscriberTwo<CommonResponse> subscriber) {
//
//        apiService.applyReturnMoney(orderId, goodStatus, reason, detail, imgs, name, mobile)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取收藏商品列表
//     *
//     * @param page
//     * @param subscriber
//     */
//    public void getCollectGoods(int page, ResponseSubscriberTwo<GetCollectGoodsResponse> subscriber) {
//
//        apiService.getCollectGoods(page)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 收藏
//     *
//     * @param type
//     * @param goodId
//     * @param subscriber
//     */
//    public void collect(int type, String goodId, ResponseSubscriberTwo<CommonResponse> subscriber) {
//
//        apiService.collectGood(type, goodId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 删除购物车商品
//     *
//     * @param recId
//     * @param subscriber
//     */
//    public void deleteCart(String recId, ResponseSubscriberTwo<CommonResponse> subscriber) {
//
//        apiService.deleteCart(recId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /****************************************11.28对接接口******************************************************/
//    public void getPreSearchList(String keywords, ResponseSubscriberTwo<GetPreSearchListResponse> subscriber) {
//
//        apiService.getPreSearchList(keywords)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 商品列表
//     *
//     * @param page       页码
//     * @param brandId    品牌id
//     * @param cateId     分类id
//     * @param orderBy    排列顺序：排序:默认0综合排序, 1按照销量降序，2按照价格升序，3按照价格降序
//     * @param keyword    关键字
//     * @param topid
//     * @param subscriber
//     */
//    public void getGoodsList(@Nullable Integer page, @Nullable Integer brandId, @Nullable Integer cateId,
//                             @Nullable Integer orderBy, @Nullable String keyword, @Nullable Integer topid, SubscriberResponse<GetGoodsListResponse> subscriber) {
//
//        apiService.getGoodsList(page, brandId, cateId, orderBy, keyword)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    //搜索历史记录及热门搜索列表
//    public void getSearchInfo(boolean clean, SubscriberResponse<GetSearchInfoResponse> subscriber) {
//
//        apiService.getSearchInfo(clean)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    //清空搜索历史记录
//    public void cleanSearchHistory(boolean clean, SubscriberResponse<GetSearchHistoryResponse> subscriber) {
//
//        apiService.cleanSearchHistory(clean)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 注册新接口
//     *
//     * @param mobile : 11位手机号码
//     * @param code   : 6位验证码
//     */
//    public void newdo_reg(String mobile, String code, ResponseSubscriberTwo<UserLoginResponse> subscriber) {
//
//        apiService.newdo_reg(mobile, code)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 绑定手机号接口
//     *
//     * @param unionid 微信unionid
//     * @param mobile  : 11位手机号码
//     * @param code    : 6位验证码
//     */
//    public void blandPhone(String unionid, String mobile, String code, ResponseSubscriberTwo<UserLoginResponse> subscriber) {
//        apiService.bindPhone(unionid, mobile, code)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 校验短信验证码
//     *
//     * @param mobile 手机号
//     * @param code   验证码
//     * @return
//     */
//    public void checkSmsCode(String mobile, String code, ResponseSubscriberTwo<GetPasswordNewResponse> subscriber) {
//        apiService.checkSmsCode(mobile, code)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 更新密码接口
//     *
//     * @param mobile : 11位手机号码
//     * @param pwd    : 8-16位密码
//     */
//    public void updatePwd(String mobile, String pwd, String code, ResponseSubscriberTwo<GetModifyPasswordResponse> subscriber) {
//        apiService.updatePwd(mobile, pwd, code)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 第三方微信登录接口
//     *
//     * @param code 微信授权登录code
//     * @return
//     */
//    public void openLogin(String code, ResponseSubscriberTwo<UserLoginResponse> subscriber) {
//        apiService.wechatLogin(code)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 快递信息接口
//     *
//     * @param orderId : 用户订单号
//     */
//    public void getShippingInfo(String orderId, ResponseSubscriberTwo<GetDeliveryInfoResponse> subscriber) {
//
//        apiService.getShippingInfo(orderId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取注册验证码，绑定手机号注册码
//     *
//     * @param mobile  : 11位手机号码
//     * @param purpose [1->绑定手机号] [2->忘记密码] [3->注册] [4->给绑定的手机号发送短信]
//     */
//    public void sendCode(String mobile, String purpose, ResponseSubscriberTwo<CommonResponse> subscriber) {
//        apiService.sendCode(mobile, purpose)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 推荐客服接口
//     *
//     * @param supplier_id 商品供应商id
//     * @param source      1表示我的页面客服的请求，2表示商品详情页面，3表示订单详情页面,0表示未知
//     * @param isConnect   IM是否在线 1:表示连接，0:表示未连接
//     */
//    public void recommendHuanXinCustomer(String supplier_id, String source, String isConnect, ResponseSubscriberTwo<GetHuanXinCustomerResponse> subscriber) {
//
//        apiService.recommendHuanXinCustomer(supplier_id, source, isConnect)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取环信好友的列表接口
//     *
//     * @param source
//     */
//    public void getHuanXinUserList(String HXusername, String source, ResponseSubscriberTwo<GetImContactResponse> subscriber) {
//
//        apiService.getHuanXinUserList(HXusername, source)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取广告页
//     */
//    public void getAppAds(ResponseSubscriberTwo<GetAppAdsResponse> subscriber) {
//        apiService.getAppAds()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 问卷调查
//     */
//    public void getQuestionnairContent(ResponseSubscriberTwo<GetQuestionnairResponse> subscriber) {
//
//        apiService.getQuestionnairContent()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 统计问卷调查的结果
//     *
//     * @param questionAnswerList 用户的答案
//     */
//    public void statisticQuestionnairResult(String questionAnswerList, ResponseSubscriberTwo<GetSubmitQuestionnairResponse> subscriber) {
//        apiService.statisticQuestionnairResult(questionAnswerList)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 充值
//     *
//     * @param recharge_no 充值卡号
//     */
//    public void rechargeWithBalance(String recharge_no, String passwd, ResponseSubscriberTwo<GetRechargeWithBalanceResponse> subscriber) {
//        apiService.rechargeWithBalance(recharge_no, passwd)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 余额支付
//     *
//     * @param isBalanceCheck 0表示未选中余额支付，1表示选中余额支付
//     * @param type           1表示支付宝支付，2表示微信支付，3表示余额支付0表示其他或未知
//     * @param out_trade_no   交易号
//     */
//    public void payConfirm(int out_trade_no, int isBalanceCheck, int type, ResponseSubscriberTwo<GetPayConfirmResponse> subscriber) {
//
//        apiService.payConfirm(out_trade_no, isBalanceCheck, type)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 签到获取积分
//     *
//     * @param checkIn
//     * @param subscriber
//     */
//    public void checkInPoints(boolean checkIn, ResponseSubscriberTwo<CheckInPointsResponse> subscriber) {
//        apiService.checkInPoints(checkIn)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 签到获取积分
//     *
//     * @param subscriber
//     */
//    public void getPointsDetail(int page, int end, ResponseSubscriberTwo<GetPointsDetailResponse> subscriber) {
//        apiService.getPointsDetail(page, end)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 获取商品详情
//     *
//     * @param id
//     * @param subscriber
//     */
//    public void getGoodDetail(@NonNull String id, String ptId, String actId, String giftExchangeId, SubscriberResponse<GetGoodsDetailResponse> subscriber) {
//        apiService.getGoodsInfo(id, ptId, actId, giftExchangeId, 2)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void getHomePage(ResponseSubscriberTwo<GetHomePageResponse> subscriber) {
//        apiService.getHomePage(2)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 提交订单
//     *
//     * @param orderRequest
//     * @param map
//     * @param subscriber
//     */
//    public void submitOrder(String orderRequest, Map<String, String> map, String ptId, ResponseSubscriberTwo<SubmitOrderResponse> subscriber) {
//        apiService.submitOrder(
//                orderRequest, JsonUtils.toJSONString(map), ptId, "")
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void getPinTuanActivityList(int page, int end, ResponseSubscriberTwo<GetPinTuanActivityListResponse> subscriber) {
//        apiService.getPinTuanActivityList(page, end)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//
//    /**
//     * 上传身份证正反面照片
//     *
//     * @param localImgPaths
//     * @param subscriber
//     */
//    public void uploadImgs(List<String> localImgPaths, ResponseSubscriberTwo<UploadImgsResponse> subscriber) {
//
//        if (localImgPaths == null) return;
//        if (localImgPaths.size() == 0) return;
//        List<MultipartBody.Part> partList = new ArrayList<>();
//        MultipartBody.Part[] partArray = new MultipartBody.Part[localImgPaths.size()];
//        for (int i = 0; i < localImgPaths.size(); i++) {
//            File file = new File(localImgPaths.get(i));
//            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
//            partList.add(part);
//            partArray[i] = part;
//            Log.i(TAG, partArray[i] + "");
//        }
//        int size = partList.size();
//        if (size > 9) size = 9;
//
//
//        apiService.uploadImgs(size > 0 ? partList.get(0) : null, size > 1 ? partList.get(1) : null, size > 2 ? partList.get(2) : null,
//                size > 3 ? partList.get(3) : null, size > 4 ? partList.get(4) : null, size > 5 ? partList.get(5) : null,
//                size > 6 ? partList.get(6) : null, size > 7 ? partList.get(7) : null, size > 9 ? partList.get(9) : null)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void getIdentifyStatus(ResponseSubscriberTwo<CommonResponse> subscriber) {
//        apiService.getIdentifyStatus()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 实名认证
//     *
//     * @param realName
//     * @param identifyId
//     * @param faceCard
//     * @param backCard
//     * @param subscriber
//     */
//    public void identify(String realName, String identifyId, String faceCard, String backCard,
//                         ResponseSubscriberTwo<CommonResponse> subscriber) {
//
//        File file = new File(faceCard);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part part = MultipartBody.Part.createFormData("faceCardFile", file.getName(), requestBody);
//
//        File file2 = new File(backCard);
//        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
//        MultipartBody.Part part2 = MultipartBody.Part.createFormData("backCardFile", file2.getName(), requestBody2);
//
//        apiService.identify(
//                realName, identifyId, part, part2)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * app未读消息
//     *
//     * @param subscriber
//     */
//    public void getNoReadMessage(ResponseSubscriberTwo<GetReadMessageResponse> subscriber) {
//
//        apiService.getNoReadMessage()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void addPackageToCart(String packageId, String num, String goodsAttr, ResponseSubscriberTwo<AddCartResponse> subscriber) {
//        String token = Prefs.getString(SpEnum.TOKEN.name()
//                , Constant.TOKEN_DEFAULT);
//
//        Log.e(TAG, goodsAttr);
//        try {
//            goodsAttr = URLEncoder.encode(goodsAttr, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            Log.e(TAG, e.getMessage());
//        }
//        apiService.addPackageToCart(packageId, num, goodsAttr)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void getGiftList(int page, int pageSize, ResponseSubscriberTwo<GetIntegralGiftListResponse> subscriber) {
//        apiService.getGiftList(page, pageSize)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 加入购物车
//     *
//     * @param goods_act_id
//     * @param goodId
//     * @param num
//     * @param spec
//     * @param is_reset
//     * @param subscriber
//     */
//    public void launchPinTuan(String goods_act_id, String goodId, @Nullable String num, @Nullable List<String> spec, @Nullable boolean is_reset,
//                              ResponseSubscriberTwo<GetLauchPinTuanResponse> subscriber) {
//        apiService.launchPinTuan(num, spec, goodId, is_reset, goods_act_id, 2)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void getPinTuanShare(String ptId, ResponseSubscriberTwo<GetPinTuanShareResponse> subscriber) {
//        String token = Prefs.getString(SpEnum.TOKEN.name(), null);
//        apiService.getPinTuanShare(ptId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//
//    }
//
//    public void getFlashSale(int page, int size, String date, boolean isToday, ResponseSubscriberTwo<GetFlashSaleResponse> subscriber){
//        String token = Prefs.getString(SpEnum.TOKEN.name(), null);
//        int mIsToday=0;
//        if (isToday){
//            mIsToday=1;
//        }else{
//            mIsToday=0;
//        }
//        apiService.getFlashSale(2,page+"",size+"",date,mIsToday+"")
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void getSubjectGoodsList(String subjectId, ResponseSubscriberTwo<GetSubjectGoodsListResponse> subscriber) {
//        apiService.getSubjectGoodsList(subjectId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    public void getGroupByGoodsId(String goodsId, String type, ResponseSubscriberTwo<GetGroupPeopleListResponse> subscriber) {
//        apiService.getGroupByGoodsId(goodsId, type)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void getAgreementDescription(int type, ResponseSubscriberTwo<GetDescriptionResponse> subscriber) {
//        apiService.getAgreementDescription(type)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取支付宝回调地址
//     *
//     * @param subscriber
//     */
//
//    public void alipayCallBackUrl(ResponseSubscriberTwo<GetAliPayCallBackUrlResponse> subscriber) {
//        apiService.alipayCallBackUrl()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取商品的套餐信息
//     *
//     * @param goodsId
//     * @param subscriber
//     */
//    public void getPackageGoods(String goodsId, ResponseSubscriberTwo<GetPackageGoodsResponse> subscriber) {
//        apiService.getPackageGoods(goodsId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 支付校验
//     *
//     * @param payLogId   交易号
//     * @param subscriber
//     */
//
//    public void payBeforeCheck(int payLogId, ResponseSubscriberTwo<CommonResponse> subscriber) {
//        apiService.payBeforeCheck(payLogId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void newerVipInfo(ResponseSubscriberTwo<GetNewerVipResponse> subscriber) {
//        apiService.newerVipInfo()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//
//    }
//
//    public void receiveBonusType(int bonusTypeId, ResponseSubscriberTwo<CommonResponse> subscriber) {
//        apiService.receiveBonusType(bonusTypeId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void addJPushAlias(String alias, ResponseSubscriberTwo<CommonResponse> subscriber) {
//        apiService.addJPushAlias(alias)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    public void getPushMessage(int noticeType, int page, int end, ResponseSubscriberTwo<GetPushMessageResponse> subscriber) {
//        apiService.getPushMessage(noticeType, page, end)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    public void updateMessageStatus(String messageId, ResponseSubscriberTwo<CommonResponse> subscriber) {
//
//        apiService.updateMessageStatus(messageId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    public void getFlashSaleDate(ResponseSubscriberTwo<GetFlashSaleDateResponse> subscriber){
//        apiService.getFlashSaleDate(2)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    /**
//     * 获取自助机列表接口（合伙人）
//     *
//     * @param longitude
//     * @param latitude
//     * @param page
//     * @param end
//     * @param subscriber
//     */
//
//    public void getMachineList(String longitude, String latitude, int page, int end, ResponseSubscriberTwo<GetVendorMachineListResponse> subscriber) {
//
//        apiService.getMachineList(longitude, latitude, page, end)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 自助机获奖名单
//     *
//     * @param count
//     * @return
//     */
//    public void getMachineWinners(int count, ResponseSubscriberTwo<GetMachineWinnersResponse> subscriber) {
//
//        apiService.getMachineWinners(count)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 获取合伙人利润接口（合伙人）
//     *
//     * @return
//     */
//    public void getPartnerProfit(ResponseSubscriberTwo<GetPartnerProfitResponse> subscriber) {
//
//        apiService.getPartnerProfit()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 选择合伙机器
//     *
//     * @param joinType 合伙类型 1：待合伙 2：已合伙 默认为1
//     * @param page
//     * @param end
//     * @return
//     */
//    public void choiceMachineList(int joinType, int page, int end, ResponseSubscriberTwo<GetchoiceMachineListResponse> subscriber) {
//
//        apiService.choiceMachineList(joinType, page, end)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 合伙人分享
//     *
//     * @param positionId
//     * @return
//     */
//
//    public void getCopartnerShareUrl(int positionId, ResponseSubscriberTwo<GetCoparnerShareUrlResponse> subscriber) {
//        apiService.shareUrl(positionId)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     *  确认合伙，添加自助机订单
//     * @param machineId
//     * @param share
//     * @param referee
//     * @param subscriber
//     */
//
//    public void confirmJoin(String machineId, int share, String referee, ResponseSubscriberTwo<GetConfirmJoinResponse> subscriber) {
//        apiService.confirmJoin(machineId, share,referee)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 合伙人验证是否实名
//     * @param subscriber
//     */
//
//    public void authentication( ResponseSubscriberTwo<GetAuthenticationResponse> subscriber) {
//        apiService.authentication()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 获取合伙人体验利润
//     * @param subscriber
//     */
//
//
//    public void getProfitInfo( ResponseSubscriberTwo<GetCopartnerProfitInfoResponse> subscriber) {
//        apiService.getProfitInfo()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }


}
