package com.liu.maji.ui.home

import com.liu.maji.base.BaseView

interface HomeView : BaseView{


    fun getAdDataResult(data:List<String>,htmlData:Map<String,String>)

    fun syncAPPVersionInfoResult(versionCode:Int,url:String)
}