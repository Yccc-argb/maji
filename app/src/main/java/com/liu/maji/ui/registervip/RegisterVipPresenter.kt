package com.liu.maji.ui.registervip

import com.liu.maji.app.Constant
import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.CommonResponse
import com.liu.maji.modle.bean.vip.ChargeMechineResponse
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.ToastUtils

class RegisterVipPresenter:BasePresenter<RegisterVipView>() {


    fun  addNewVipCard(deviceId : String ,merchantId:Int,agentId:Int ,mobile:String ,cd: String ,name:String ,verifyCode:String){
        ApiClient.getInstance().addNewVipCard(deviceId,merchantId,agentId,mobile,cd,name,verifyCode,object : ResponseSubscriber<CommonResponse>(){
            override fun onRealSuccess(t: CommonResponse?) {
                //开卡成功
                if (t?.isSuccess!!){
                    view?.addNewVipResult()
                }
            }
        })
    }


    fun getVertifyCode(phone:String,merchantId:Int,agentId:Int){
        ApiClient.getInstance().getCode(phone,merchantId,agentId,object : ResponseSubscriber<CommonResponse>(){
            override fun onRealSuccess(t: CommonResponse?) {
                hideProgress()
                view!!.getCodeResult()
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


    fun changeBatchModle(agentId:Int ,merchantId:Int,onlineAmount:String,offlineAmount:String,giveAmount:String,openCardModel:String,diviceID:String,diviceName:String){
        ApiClient.getInstance().batchOpenCard(merchantId,agentId,onlineAmount,offlineAmount,giveAmount,
                        openCardModel,diviceID,object : ResponseSubscriber<CommonResponse>(){
            override fun onRealSuccess(t: CommonResponse?) {
                hideProgress()
                if (t != null){
                    if (t.isSuccess){
                        //保存当前的充点机
                        if (!diviceName.isEmpty()){
                            Prefs.putBoolean(Constant.BATRCH_MODLE,true)
                            Prefs.putString("deviceId",diviceID)
                            Prefs.putString("deviceName",diviceName)
                            ToastUtils.showToast("批量模式开启成功")
//                            view?.changeBatchModleResult(true)

                        }else {
                            Prefs.putBoolean(Constant.BATRCH_MODLE,false)
                            Prefs.putString("deviceId","")
                            Prefs.putString("deviceName","")
                            ToastUtils.showToast("批量模式关闭成功")
//                            view?.changeBatchModleResult(false)
                        }

                    }else{
                        ToastUtils.showToast(t.message)
                    }
                }
            }
        })
    }


}