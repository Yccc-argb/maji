package com.liu.maji.ui.vipinfo

import com.liu.maji.base.BaseView
import com.liu.maji.modle.bean.vip.VipInfoResponse

interface VipInfoView : BaseView {

    fun queryVipInfoResult(vipInfo:VipInfoResponse)
    fun queryVipInfoByMobileResult(t: VipInfoResponse.DataBean.MajiangVOListBean?)
}