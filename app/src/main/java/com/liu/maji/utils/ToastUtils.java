package com.liu.maji.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.liu.maji.app.APP;
import com.liu.maji.http.ApiClient;
import com.liu.maji.http.ResponseSubscriber;
import com.liu.maji.modle.bean.login.LoginResponse;


/**
 * toast工具类
 * Created by teikasei on 2017/1/10.
 */
public class ToastUtils {

    /**
     * 弹出短暂的toast
     *
     * @param msg
     */
    public static void showToast(String msg) {
        Toast.makeText(APP.getContext(), msg, Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * 弹出短暂的toast
     *
     * @param stringId 要发出的信息的 stringId
     */
    public static void showToast(@StringRes int stringId) {
        Toast.makeText(APP.getContext(), stringId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出长时间的toast
     *
     * @param msg
     */
    public static void showLongToast(String msg) {
        Toast.makeText(APP.getContext(), msg, Toast.LENGTH_LONG)
                .show();
    }
}
