package com.liu.maji.ui.home

import com.liu.maji.app.Constant
import com.liu.maji.base.BasePresenter
import com.liu.maji.http.ApiClient
import com.liu.maji.http.ApiConstants
import com.liu.maji.http.ResponseSubscriber
import com.liu.maji.modle.CommonResponse
import com.liu.maji.modle.bean.AdResponse
import com.liu.maji.modle.bean.AppVersionResponse
import kotlin.math.absoluteValue

class HomePresenter : BasePresenter<HomeView>() {

    /**
     * 获取广告数据
     * @param merchantId 商户id
     */
    fun getAdData(merchantId: String) {
        ApiClient.getInstance().getAdData(merchantId, object : ResponseSubscriber<AdResponse>() {
            override fun onRealSuccess(t: AdResponse?) {
                hideProgress()
                if (t?.isSuccess == true) {
                    val list = arrayListOf<String>()
                    val htmlMap = mutableMapOf<String, String>()
                    for (element in t.data) {
                        list.add(ApiConstants.IMAGE_BASE_URL + element.imgUrl)
                        if (element.addUrl.isNotEmpty()){
                            htmlMap[ApiConstants.IMAGE_BASE_URL + element.imgUrl] = element.addUrl
                        }
                    }
//                    if (list.isEmpty()){
//                        list.add("https://qa-upload.poseidong.com/image/87/20181112/9678C06D34CC4A439F4A620E76F48BB81987569868.png")
//                        list.add("https://qa-upload.poseidong.com/image/87/20181112/0336BC6D4FCA42DB9204F56B03FE3B1D-1390341075.png")
//                    }
//                    htmlList.add("http://www.baidu.com")
//                    htmlList.add("http://www.dongjun.org/")
                    view?.getAdDataResult(list,htmlMap)
                } else {
                    showShortToast(t?.message)
                }

            }
        })
    }


    /**
     * 同步app版本信息
     */
    fun syncAPPVersionInfo() {
        ApiClient.getInstance().syncAppVersion(object : ResponseSubscriber<AppVersionResponse>() {
            override fun onRealSuccess(t: AppVersionResponse?) {
                hideProgress()
                if (t?.isSuccess == true) {
                    view?.syncAPPVersionInfoResult(t?.data.version.toInt(), t?.data.url)
                }
            }
        })
    }
}