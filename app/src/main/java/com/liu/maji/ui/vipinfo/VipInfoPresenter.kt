package com.liu.maji.ui.vipinfo

import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.bean.vip.VipInfoResponse
import com.liu.maji.utils.ToastUtils

class VipInfoPresenter:BasePresenter<VipInfoView>() {


    fun queryVipInfo(agentId: Int, merchantId: Int, currentPage: Int, pageCounts: Int, recordStart: Int, recordEnd: Int){
        ApiClient.getInstance().queryAllVipInfo(merchantId,agentId,currentPage,pageCounts,recordStart,recordEnd,object : ResponseSubscriber<VipInfoResponse>(){
            override fun onRealSuccess(t: VipInfoResponse?) {
                hideProgress()
                if (t != null){
                    view?.queryVipInfoResult(t)
                }else {
                    showShortToast("查询会员信息失败")
                }

            }
        })
    }

    fun queryVipInfoByMobile(merchantId: Int , agentId:Int,cd : String,mobile:String,status:String,enabledFlag:String){
        ApiClient.getInstance().queryVipInfoByMobile(merchantId,agentId,cd,mobile,status,enabledFlag,object : ResponseSubscriber<VipInfoResponse>(){
            override fun onRealSuccess(t: VipInfoResponse?) {
                hideProgress()
                if (t != null){
                    view?.queryVipInfoByMobileResult(t.data.majiangVOList[0])
                }else {
                    ToastUtils.showToast("暂未查询到相关数据")
                }
            }
        })
    }

}