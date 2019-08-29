package com.liu.maji.ui.vipdetails

import com.liu.maji.base.BaseView
import com.liu.maji.modle.bean.vip.ChargeMechineResponse

interface VipDetailsView : BaseView{
    fun chargeResult()
    fun queryChargeMechineInfoResult(t: ChargeMechineResponse)
    fun operateCardResult()


}