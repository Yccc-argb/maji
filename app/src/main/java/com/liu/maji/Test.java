package com.liu.maji;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;

import com.liu.maji.utils.TimeCalculateTools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

    @SuppressLint("SimpleDateFormat")
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.set(2019,6,21,23,55,0);
        long mills = instance.getTimeInMillis();
        System.out.println(mills);
        System.out.println(System.currentTimeMillis());
        System.out.println(mills - System.currentTimeMillis());
        System.out.println("------------------");
        Date date = new Date(mills);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));

        System.out.println("-------------------");
        System.out.println(simpleDateFormat.format(new Date(1563693653000l)));




    }
}
