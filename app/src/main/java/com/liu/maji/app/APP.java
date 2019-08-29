package com.liu.maji.app;

import android.app.Application;
import android.content.Context;

import com.liu.maji.utils.Prefs;


/**
 * Created by Yangyanyan on 2018/11/8 0008.
 */

public class APP extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        initSp();
    }


    private void initSp() {
        new Prefs.Builder().setContext(this).setPrefsName("maji").setUseDefaultSharedPreference(true).build();
    }

    public static Context getContext() {
        return mContext;
    }
}
