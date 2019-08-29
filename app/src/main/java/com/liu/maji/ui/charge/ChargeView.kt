package com.liu.maji.ui.charge

import com.liu.maji.base.BaseView
import com.liu.maji.modle.bean.charge_history.ChargeHistoryResponse

interface ChargeView:BaseView {
    fun getChargeHistoryDataResult(dataBean: ChargeHistoryResponse.DataBean)
}