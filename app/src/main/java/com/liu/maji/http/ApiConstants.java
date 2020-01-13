/**
 * DongJun.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.liu.maji.http;


public final class ApiConstants {

    /**
     * http header - appid
     */
    public static final String HEADER_APPID = "app_id";

    public static final String HEADER_APPID_CONTENT = "201701110019oddmAPg";

    public static final String PARAMS_API = "api";

    /**
     * http header - timestamp
     */
    public static final String HEADER_TIMESTAMP = "psd_entry";

    /**
     * 默认编码方式UTF-8
     */
    public static final String DEFAULT_ENCODE = "UTF-8";

    /**
     * 默认接口版本号
     */
    public static final String DEFAULT_VERSION = "1.0";

    /**
     * 调用次数 - 无限制
     */
    public static final int UNLIMIT = -1;

    /**
     * 参数中的签名字段名
     */
    public static final String PARAM_KEY_SIGN = "sign";

    /**
     * API provider在bean容器中的注册名前缀
     */
    public static final String BEAN_NAME_PROVIDER_PREFIX = "api-provider-";

    /**
     * 时间误差最大允许毫秒数, 10分钟
     */
    public static final long TIME_TOLERATE_MILLS = 10L * 60L * 1000L;

    /**
     * 单商户随机串缓存最大数量
     */
    public static final int NONCE_CACHE_MAX = 1024;

    /**
     * 随机串长度16
     */
    public static final int NONCE_LENGTH = 16;

    /**
     * 异步通知成功标识
     */
    public static final String NOTIFY_SUCCESS = "SUCCESS";

    /**
     * 异步通知失败标识
     */
    public static final String NOTIFY_FAIL = "FAIL";

    /**
     * 异步通知延迟属性key
     */
    public static final String NOTIFY_DELAY_KEY = "NOTIFY_DELAY";

    public static final String PARAMS_NONCE = "nonce";
    public final static String API_LOGIN_CODE = "app.consumer.sms.verifycode.send";
    public final static String API_LOGIN = "app.consumer.login";


    public final static String LOCAL_URL =  "http://1z853v1704.iok.la:54527/web-wechart/mj/api/android/";
    public final static String TEST_URL =  "http://120.77.181.129:8080/web-wechart/mj/api/android/";
    public final static String BASE_URL =  "http://qa-merchant.fork.red/web-wechart/mj/api/android/";
    public final static String IMAGE_BASE_URL =  "http://qa-user.fork.red";   //加载图片主路径

}
