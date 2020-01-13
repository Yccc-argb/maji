package com.liu.maji.ui.device

import com.liu.maji.base.BaseView
import com.liu.maji.modle.bean.device.ChangeConsumeResponse
import com.liu.maji.modle.bean.device.ChangeConsumeTypeResultResponse
import com.liu.maji.modle.bean.device.DeviceInfoResponse

interface DeviceView :BaseView{
    fun getDevicesResult(deviceInfoBean: DeviceInfoResponse)
    fun getConsumeTypeResult(changeConsumeResponse: ChangeConsumeResponse)
    fun changeConsumeTypeResult(t: ChangeConsumeTypeResultResponse)
    fun changeDeviceNameResult(b: Boolean)
}