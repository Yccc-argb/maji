package com.liu.maji.ui.set

import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.CommonResponse
import com.liu.maji.utils.ToastUtils

class SetPrenter : BasePresenter<SetView>() {


    fun resetPassword(password:String){
        ApiClient.getInstance().resetPassword(password,object : ResponseSubscriber<CommonResponse>(){
            override fun onRealSuccess(t: CommonResponse?) {
                hideProgress()
                ToastUtils.showToast(t?.message)
            }
        })
    }
}