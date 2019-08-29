package com.liu.maji.ui.forget_password

import com.liu.maji.base.BaseView

interface ForgetView:BaseView {

    fun onCommitNewPasswordResult(isSuccess:Boolean)
}