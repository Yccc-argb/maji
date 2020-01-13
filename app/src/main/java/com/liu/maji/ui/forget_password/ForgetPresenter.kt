package com.liu.maji.ui.forget_password

import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.CommonResponse
import com.liu.maji.modle.bean.login.CodeResponse

import com.liu.maji.utils.ToastUtils

class ForgetPresenter : BasePresenter<ForgetView>() {


    /**
     * 修改密码
     * @param phone 手机号
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    fun commitNewPasswordToService(phone: String, oldPassword: String, newPassword: String) {
        ApiClient.getInstance().changePassword(
                phone,
                oldPassword,
                newPassword,
                object : ResponseSubscriber<CommonResponse>() {
                    override fun onRealSuccess(t: CommonResponse?) {
                        hideProgress()
                        if (t?.isSuccess == true) {
                            view?.onCommitNewPasswordResult(t?.isSuccess)
                        } else {
                            ToastUtils.showToast(t?.message)
                        }

                    }
                }
        )
    }

}