package com.liu.maji.ui.vipdetails

import android.text.TextUtils
import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.CommonResponse
import com.liu.maji.modle.bean.vip.ChargeMechineResponse
import com.liu.maji.utils.ToastUtils

class VipDetailsPresenter :BasePresenter<VipDetailsView>() {

    /**
     * 充值
     *
     */
    fun charge(deviceId:String,agentId: Int,  cardNum: String, chargeMoneyInt: Int, giveAmount: Int,platAmount:Double) {
        ApiClient.getInstance().charge(deviceId, agentId.toString(),cardNum,chargeMoneyInt.toString(),giveAmount.toString(),platAmount.toString()
                ,object : ResponseSubscriber<CommonResponse>(){
                    override fun onRealSuccess(t: CommonResponse?) {
                        if (t != null){
                            if (t.isSuccess){
                                view?.chargeResult()
                            }else {
                                ToastUtils.showToast(t.message)
                            }
                        }
                    }
                })
    }


    fun queryChargeMechineInfo(agentId:Int ,merchantId:Int,currentPage: Int, pageCounts: Int, recordStart: Int, recordEnd: Int){
        ApiClient.getInstance().queryChargeMechineInfo(agentId,merchantId,currentPage,pageCounts,recordStart,recordEnd,object : ResponseSubscriber<ChargeMechineResponse>(){
            override fun onRealSuccess(t: ChargeMechineResponse?) {
                hideProgress()
                if (t!=null){
                    view?.queryChargeMechineInfoResult(t)
                }else {
                    showShortToast("未查询到充值机信息")
                }

            }

        })
    }

    /*
    挂失
     */
    fun lossCard(merchantId:Int,agentId:Int,cd:String,mobile:String,newCd:String){
        ApiClient.getInstance().lossCard(merchantId,agentId,cd,mobile,newCd,object : ResponseSubscriber<CommonResponse>(){
            override fun onRealSuccess(t: CommonResponse?) {
                hideProgress()
                if (TextUtils.isEmpty(newCd)){
                    //挂失
                    if (t != null){
                        if (t.isSuccess){
                            ToastUtils.showToast("挂失成功")
                            view?.operateCardResult()
                        }else {
                            ToastUtils.showToast(t.message)
                        }

                    }
                }
            }
        })
    }

    /*
    补卡
     */
    fun replaceCard(merchantId:Int,agentId:Int,cd:String,deviceId:String,mobile:String,newCd:String){
        ApiClient.getInstance().replaceCard(merchantId,agentId,cd,deviceId,mobile,newCd,object : ResponseSubscriber<CommonResponse>(){
            override fun onRealSuccess(t: CommonResponse?) {
                hideProgress()
                if (TextUtils.isEmpty(newCd)){
                    //补卡
                    if (t != null){
                        if (t.isSuccess){
                            ToastUtils.showToast("补卡成功")
                            view?.operateCardResult()
                        }else {
                            ToastUtils.showToast(t.message)
                        }

                    }
                }
            }
        })
    }

    /*
    退卡
     */
    fun refundCard (merchantId:Int,agentId:Int,cd:String,mobile:String){
        ApiClient.getInstance().refundCard(merchantId,agentId,cd,mobile,object : ResponseSubscriber<CommonResponse>(){
            override fun onRealSuccess(t: CommonResponse?) {
                hideProgress()
                if (t != null){
                    if (t.isSuccess){
                        ToastUtils.showToast("退卡成功")
                        view?.operateCardResult()
                    }else {
                        ToastUtils.showToast(t.message)
                    }
                }
            }
        })
    }
}