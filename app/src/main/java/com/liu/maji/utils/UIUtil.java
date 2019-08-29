package com.liu.maji.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.liu.maji.app.APP;


/**
 * Created by Yangyanyan on 2018/1/11.
 */

public class UIUtil {
    /**
     * 获取上下文
     *
     * @return Context
     */
    public static Context geContext() {
        return APP.getContext();
    }

    /**
     * 获取Resource对象
     *
     * @return Resources
     */
    public static Resources getResources() {
        return geContext().getResources();
    }

    /**
     * 获取String.xml中的字符串的信息
     *
     * @param resId
     * @return String
     */
    public static String getString(int resId) {
        return geContext().getResources().getString(resId);
    }

    /**
     * 获取Color.xml中颜色的信息
     *
     * @param resId
     * @return int
     */
    public static int getColor(int resId) {
        return geContext().getResources().getColor(resId);
    }

    /**
     * 获取String.xml中字符串数组的信息
     *
     * @param resId
     * @return
     */
    public static String[] getStringArray(int resId) {
        return geContext().getResources().getStringArray(resId);
    }

    /**
     * 获取当前应用程序的包名
     *
     * @param resId
     * @return
     */
    public static String getPackageName(int resId) {
        return geContext().getPackageName();
    }

    /**
     * dip-->px
     *
     * @param dip
     * @return
     */
    public static int dip2px(int dip) {
        //px/dip = density(手机屏幕的密度)
        //获取当前手机屏幕的密度
        float density = getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }

    public static int px2dip(int px) {
        float density = getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + .5f);
        return dip;
    }
    public static int[] getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return new int[] {outMetrics.widthPixels,outMetrics.heightPixels};
    }
}
