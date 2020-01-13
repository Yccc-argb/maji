package com.liu.maji.ui.device

import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.CommonResponse
import com.liu.maji.modle.bean.device.ChangeConsumeResponse
import com.liu.maji.modle.bean.device.ChangeConsumeTypeResultResponse
import com.liu.maji.modle.bean.device.DeviceInfoResponse
import com.liu.maji.utils.ToastUtils

class DevicePresenter : BasePresenter<DeviceView>() {
    fun getDevices(agentId: Int, merchantId: Int, currentPage: Int, pageCounts: Int, recordStart: Int, recordEnd: Int) {
        ApiClient.getInstance().getDevicesInfo(
                agentId,
                merchantId,
                currentPage,
                pageCounts,
                recordStart,
                recordEnd,
                object : ResponseSubscriber<DeviceInfoResponse>() {
                    override fun onRealSuccess(t: DeviceInfoResponse?) {
                        if (t?.isSuccess == true) {
                            view?.getDevicesResult(t)
                        } else {
                            ToastUtils.showToast(t!!.message)
                        }
                    }
                }
        )
    }


    /*
    查询消费套餐
     */
    fun getConsumeType(merchantId: Int) {
        ApiClient.getInstance().queryConsumeTypeInfo(
                merchantId,
                object : ResponseSubscriber<ChangeConsumeResponse>() {
                    override fun onRealSuccess(t: ChangeConsumeResponse?) {
                        hideProgress()
                        if (t != null) {
                            view?.getConsumeTypeResult(t)
                        }else {
                            ToastUtils.showToast("未查询到消费套餐")
                        }
                    }
                }
        )
    }

    fun changeConsumeType(productId: Int,modernProductId:Int,equipId:Int){
        ApiClient.getInstance().changeConsumeType(productId,modernProductId,equipId,
                object : ResponseSubscriber<ChangeConsumeTypeResultResponse>(){
                    override fun onRealSuccess(t: ChangeConsumeTypeResultResponse?) {
                        hideProgress()
                        if (t != null){
                            view?.changeConsumeTypeResult(t)
                        }else {
                            ToastUtils.showToast("修改套餐失败")
                        }
                    }
                })
    }


    /**
     * 停止刷卡盘
     */
    fun stopCurrentSwipe(agentId: Int, merchantId: Int,diviceID:String,switchFlag:String,sonsumType:String,
                         consumNumber:String,equipId:String){
        ApiClient.getInstance().stopSwipe(merchantId,agentId,diviceID,switchFlag,sonsumType,consumNumber,equipId,
                object : ResponseSubscriber<ChangeConsumeTypeResultResponse>(){
                    override fun onRealSuccess(t: ChangeConsumeTypeResultResponse?) {
                        hideProgress()
                        if (t != null){
                            view?.changeConsumeTypeResult(t)
                        }else {
                            ToastUtils.showToast("操作失败")
                        }

                    }
                })
    }

    fun changeDeviceName(cd : String,newName:String){
        ApiClient.getInstance().changeDeviceName(cd,newName,object : ResponseSubscriber<CommonResponse>(){
            override fun onRealSuccess(t: CommonResponse?) {
                hideProgress()
                if (t?.isSuccess == true){
                    view?.changeDeviceNameResult(true)
                }else {
                    ToastUtils.showToast(t?.message)
                }
            }
        })
    }
}