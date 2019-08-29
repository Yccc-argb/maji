package com.liu.maji.ui.vip

import com.liu.maji.base.BaseView
import java.time.temporal.TemporalAmount

interface VipView : BaseView{
    fun checkChargeInfoResult(amount: Int,cardNum:String,mobile:String)
}