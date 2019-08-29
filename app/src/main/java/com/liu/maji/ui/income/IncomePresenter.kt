package com.liu.maji.ui.income

import com.liu.maji.base.BasePresenter
import com.liu.maji.base.BaseView
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.bean.income.IncomeResponse
import com.liu.maji.utils.ToastUtils
import java.util.*

class IncomePresenter : BasePresenter<IncomeView>() {


    /**
     * 查询当前收益
     */
    fun getInComeData(agentId: Int, merchantId: Int, range:Int) {
        ApiClient.getInstance().getIncomeData(agentId, merchantId, range,object : ResponseSubscriber<IncomeResponse>() {
            override fun onRealSuccess(t: IncomeResponse?) {
                if (t != null) {
                    if (t.isSuccess){
                        view?.getIncomeDataResult(t.data.totalSum,t.data.yesterdayTotalSum,t.data.totalSum)
                    }else {
                        ToastUtils.showToast(t.message)
                    }
                }
            }
        })
    }
}