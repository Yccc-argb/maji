package com.liu.maji.ui.invest

import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.CommonResponse
import com.liu.maji.utils.ToastUtils

class InvestPresenter : BasePresenter<InvestView>(){


    fun investOrSuggestion(merchantId:Int,agentId:Int, merchantName :String,consumerName:String,consumerPhone:String, message:String){
        ApiClient.getInstance().investOrSuggestion(merchantId,agentId,merchantName,consumerName,consumerPhone,message,
                object: ResponseSubscriber<CommonResponse>(){
                    override fun onRealSuccess(t: CommonResponse?) {
                        hideProgress()
                        ToastUtils.showToast(t?.message)
                    }
                })
    }
}