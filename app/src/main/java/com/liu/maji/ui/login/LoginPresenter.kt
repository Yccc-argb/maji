package com.liu.maji.ui.login

import com.liu.maji.app.Constant
import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.CommonResponse
import com.liu.maji.modle.bean.login.LoginResponse
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.ToastUtils

class LoginPresenter : BasePresenter<LoginView>() {

    /*
    服务器校验
     */
     fun login(phone:String,code:String){
        ApiClient.getInstance().login(phone, code, object : ResponseSubscriber<LoginResponse>() {
            override fun onRealSuccess(loginResponse: LoginResponse) {
                if (loginResponse.isSuccess){
                    //merchantId
                    Prefs.putInt(Constant.MERCHANT_ID,loginResponse!!.data.merchantId)
                    //agentId
                    Prefs.putInt(Constant.AGENT_ID,loginResponse!!.data.agentId)
                    //保存AgentName
                    Prefs.putString(Constant.AGENT_NAME,loginResponse!!.data.agentName)
                    view?.onLoginResult()
                }else{
                    ToastUtils.showToast(loginResponse.message)
                }

            }
        })

    }


    fun resetPassword(password:String){
        ApiClient.getInstance().resetPassword(password,object : ResponseSubscriber<CommonResponse>(){
            override fun onRealSuccess(t: CommonResponse?) {
                hideProgress()
                ToastUtils.showToast(t?.message)
            }
        })
    }

}