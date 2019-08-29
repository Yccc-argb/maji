package com.liu.maji.utils

import java.util.regex.Pattern

object CommonUtils {

    fun isMobile(phone:String) :Boolean{
        val regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$"
        val compile = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        return compile.matcher(phone).matches()
    }
}