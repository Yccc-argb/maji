package com.liu.maji.ui.forget_password

import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.CommonResponse
import com.liu.maji.modle.bean.login.CodeResponse

import com.liu.maji.utils.ToastUtils

class ForgetPresenter : BasePresenter<ForgetView>() {

    fun commitNewPasswordToService(phone: String) {


//        ApiClient.getInstance().getCode(phone, object : ResponseSubscriber<CommonResponse>() {
//            override fun onRealSuccess(t: CommonResponse) {
//                hideProgress()
//                if (t.isSuccess) {
//                    view?.onCommitNewPasswordResult(true)
//                } else {
//                    ToastUtils.showToast(t.message)
//                }
//            }
//        })


    }

}