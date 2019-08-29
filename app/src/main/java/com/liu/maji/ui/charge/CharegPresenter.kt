package com.liu.maji.ui.charge

import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.bean.charge_history.ChargeHistoryResponse
import com.liu.maji.utils.ToastUtils

class CharegPresenter : BasePresenter<ChargeView>() {
    fun getChargeHistoryData(agentId: Int, merchantId: Int, mobile:String,operateType:String,startDate: String,
                             endDate: String, currentPage: Int, pageCounts: Int, recordStart: Int, recordEnd: Int) {
        ApiClient.getInstance().getChargeHistoryData(agentId, merchantId,mobile,operateType, startDate, endDate,
                currentPage, pageCounts, recordStart, recordEnd, object : ResponseSubscriber<ChargeHistoryResponse>() {
            override fun onRealSuccess(t: ChargeHistoryResponse?) {
                if (t != null) {
                    if (t.isSuccess) {
                        view?.getChargeHistoryDataResult(t.data)
                    }else {
                        ToastUtils.showToast(t.message)
                    }
                }
            }
        })
    }
}