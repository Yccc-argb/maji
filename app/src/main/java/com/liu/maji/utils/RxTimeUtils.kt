package com.liu.maji.utils


import rx.Observable

import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import java.util.concurrent.TimeUnit
import javax.security.auth.Subject

/**
 * 倒计时工具类
 */
object RxTimeUtils {

    fun timeCountDown(time:Int,timeUnit:TimeUnit) :Observable<Int>{
        return Observable.interval(0, 1, timeUnit)
                .map { aLong -> time - aLong!!.toInt() }
                .take(time + 1)
                .observeOn(AndroidSchedulers.mainThread())

//        return Observer.interval(0,1,timeUnit)
//                .onBackpressureBuffer()
//                .take(time.toLong())
//                .map{
//                    time.toLong() -  it }
//                .observeOn(AndroidSchedulers.mainThread())

    }
}