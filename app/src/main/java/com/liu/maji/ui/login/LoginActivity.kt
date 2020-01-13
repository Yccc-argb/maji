package com.liu.maji.ui.login


import android.text.TextUtils
import android.view.View
import cn.com.liu.maji.R
import com.liu.maji.app.Constant
import com.liu.maji.base.ActivityCollector
import com.liu.maji.base.MySupportActivity
import com.liu.maji.ui.forget_password.ForgetPasswordActivity
import com.liu.maji.ui.main.MainActivity
import com.liu.maji.utils.CommonUtils
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.ToastUtils
import com.liu.maji.wedigt.SimpleDialog
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : MySupportActivity<LoginView,LoginPresenter>(), View.OnClickListener ,LoginView, SimpleDialog.DialogClickListener {
    override fun onConfirm(type: Int) {

    }

    override fun onConfirm(type: String?) {
        val phoneNumeber = et_account.text.toString().trim()
        if (TextUtils.isEmpty(phoneNumeber) || phoneNumeber.length != 11){
            ToastUtils.showToast(getString(R.string.phone_is_error))
            return
        }
        if(dialog!= null && dialog!!.isShowing){
            dialog!!.dismiss()
            dialog = null
        }

        showProgress(1)
        getPresenter().resetPassword(phoneNumeber)
    }

    override fun onCancel() {
        if(dialog!= null && dialog!!.isShowing){
            dialog!!.dismiss()
            dialog = null

        }
    }

    var phoneNumeber = ""
    private var dialog: SimpleDialog? = null

    override fun onLoginResult() {
        //登录接口回调
        Prefs.putBoolean(Constant.IS_LOGIN,true)
        Prefs.putString(Constant.PHONE,phoneNumeber)
        hideProgress()
        startActivity(MainActivity::class.java)
        finish()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.rl_login
            -> checkPhoneAndCode()
            R.id.tv_forget_code
            -> changePassword()

        }
    }

    private fun checkPhoneAndCode(){
        phoneNumeber = et_account.text.toString().trim()
        if (TextUtils.isEmpty(phoneNumeber)){
            toast(getString(R.string.account_is_empty))
            return
        }
//        if (!CommonUtils.isMobile(phone)){
//            toast("手机号非法")
//            return
//        }

//
        val code = et_code.text.toString().trim()  //密码
        if (TextUtils.isEmpty(code)){
            toast(getString(R.string.code_is_empty))
            return
        }
        showProgress(1)
        getPresenter().login(phoneNumeber,code)


    }

    override fun init() {
        if (Prefs.getBoolean(Constant.IS_LOGIN,false)){
            startActivity(MainActivity::class.java)
            finish()
            ActivityCollector.removeActivity(this)
            return
        }
        //处理业务逻辑
        tv_forget_code.setOnClickListener(this)
        et_account.setOnClickListener(this)
        et_code.setOnClickListener(this)
        rl_login.setOnClickListener(this)

        println("输出日志-->init()")

    }

    /**
     * 重置密码
     */
    private fun changePassword(){
        dialog = SimpleDialog(mActivity, R.style.circle_dialog)
        dialog?.showChangePasswordDialog(this)
    }

    override fun createPresenter(): LoginPresenter {
        return  LoginPresenter()
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_login
    }

}
