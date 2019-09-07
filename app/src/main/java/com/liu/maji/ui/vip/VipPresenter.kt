package com.liu.maji.ui.vip

import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.bean.vip.VipChargeInfoResponse
import com.liu.maji.utils.ToastUtils

class VipPresenter : BasePresenter<VipView>() {


    /**
     * 校验卡号
     */
    fun checkChargeInfo(agentId: Int, merchantId: Int, VIPCardNum: String,mobile:String) {
        ApiClient.getInstance().queryChargeInfo(agentId, merchantId, VIPCardNum,mobile,
                object : ResponseSubscriber<VipChargeInfoResponse>() {
                    override fun onRealSuccess(t: VipChargeInfoResponse?) {
                        if (t != null) {
                            if (t.isSuccess) {
                                if (t.data.majiangVOList != null && !t.data.majiangVOList.isEmpty()){
                                    var mobile = ""
                                    if (t.data.majiangVOList[0].mobile != null){
                                        mobile = t.data.majiangVOList[0].mobile
                                    }
                                    view?.checkChargeInfoResult(t.data.majiangVOList[0].amount,
                                            t.data.majiangVOList[0].cd,mobile)
                                }

                            } else {
                                hideProgress()
//                                ToastUtils.showToast(t.message)
                                ToastUtils.showToast("参数错误")
                            }
                        }
                    }
                })
    }
}