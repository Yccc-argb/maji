package com.liu.maji.ui.registervip

import com.liu.maji.base.BaseView
import com.liu.maji.modle.bean.vip.ChargeMechineResponse

interface RegisterVipView:BaseView {

    fun addNewVipResult()

    fun getCodeResult()
    fun queryChargeMechineInfoResult(t: ChargeMechineResponse)
    fun changeBatchModleResult(b: Boolean)
}