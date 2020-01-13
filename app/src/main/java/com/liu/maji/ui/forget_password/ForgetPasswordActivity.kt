package com.liu.maji.ui.forget_password


import android.content.Intent
import android.os.Process
import android.text.TextUtils
import android.view.View
import cn.com.liu.maji.R
import com.liu.maji.base.ActivityCollector
import com.liu.maji.base.MySupportActivity
import com.liu.maji.ui.login.LoginActivity
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.RxTimeUtils
import kotlinx.android.synthetic.main.activity_forget_code.*
import kotlinx.android.synthetic.main.title.*
import rx.Subscriber
import rx.Subscription


import java.util.concurrent.TimeUnit

class ForgetPasswordActivity : MySupportActivity<ForgetView, ForgetPresenter>(), View.OnClickListener, ForgetView {


    private var mSubscritionp: Subscription? = null


    override fun onCommitNewPasswordResult(isSuccess: Boolean?) {
        toast("密码修改成功")
        Prefs.clear()
        val intent = Intent(this, LoginActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        for (item in ActivityCollector.activityList){
            item.finish()
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_send_vertif
            -> sendVertify()

            R.id.rl_btn_sure
            -> checkCondition()

            R.id.iv_back
            -> this.finish()

        }
    }

    override fun init() {

        tv_title.text = "修改密码"
        tv_add.visibility = View.INVISIBLE
        iv_back.setOnClickListener(this)
        //设置点击事件
        tv_send_vertif.setOnClickListener(this)
        rl_btn_sure.setOnClickListener(this)


    }

    override fun createPresenter(): ForgetPresenter {
        return ForgetPresenter()
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_forget_code
    }


    private fun sendVertify() {
        tv_send_vertif.setBackgroundResource(R.drawable.bg_get_code_send)
        tv_send_vertif.isEnabled = false
        mSubscritionp = RxTimeUtils.timeCountDown(60, TimeUnit.SECONDS)
                .subscribe(object : Subscriber<Int>() {
                    override fun onNext(t: Int?) {
                        tv_send_vertif.text = (t.toString() + "s后重新发送")
                    }

                    override fun onCompleted() {
                        setSendVerfityNormal()
                        this.unsubscribe()
                    }

                    override fun onError(e: Throwable?) {
                        setSendVerfityNormal()
                        this.unsubscribe()
                    }
                })


//        RxTimeUtils.timeCountDown(60, TimeUnit.SECONDS)
//                .subscribe(object : Subscriber<Long> {
//                    override fun onSubscribe(s: Subscription?) {
//                        //设置点击的
//                        tv_send_vertif.isEnabled = false
//                        tv_send_vertif.setBackgroundResource(R.drawable.bg_get_code_send)
//                        mSubscritionp = s
//                        s?.request(Long.MAX_VALUE)//设置请求事件的数量，重要，必须调用
//                    }
//
//                    override fun onNext(aLong: Long?) {
//                        tv_send_vertif.text = (aLong.toString() + "s后重新发送")
//                    }
//
//                    override fun onComplete() {
//                        tv_send_vertif.isEnabled = true
//                        tv_send_vertif.text = "点击重发"
//                        tv_send_vertif.setBackgroundResource(R.drawable.bg_get_code)
//                        mSubscritionp?.cancel()
//                    }
//
//                    override fun onError(t: Throwable?) {
//                        t?.printStackTrace()
//                    }
//                })
    }

    fun setSendVerfityNormal() {
        tv_send_vertif.isEnabled = true
        tv_send_vertif.text = "点击重发"
        tv_send_vertif.setBackgroundResource(R.drawable.bg_get_code)
    }


    /**
     * 输入信息校验
     */
    private fun checkCondition() {
        //手机号
        val phone = et_phone.text.toString().trim()
        if (TextUtils.isEmpty(phone) || phone.length != 11) {
            toast(getString(R.string.phone_is_error))
            return
        }

//        if (!CommonUtils.isMobile(phone)) {
//            toast("手机号非法")
//            return
//        }
//        val code = et_code.text.toString().trim()
//
//        if (TextUtils.isEmpty(code)) {
//            toast(getString(R.string.code_is_empty))
//            return
//        }

        val oldPassword = et_password_old.text.toString().trim()
        if (TextUtils.isEmpty(oldPassword)) {
            toast("原密码不能为空")
            return
        }
        val password = et_password.text.toString().trim()
        if (TextUtils.isEmpty(password)) {
            toast(getString(R.string.input_new_password))
            return
        }
        val passwordNew = et_new_password.text.toString().trim()

        if (TextUtils.isEmpty(passwordNew)) {
            toast(getString(R.string.confirm_new_password))
            return
        }

        if (!TextUtils.equals(password, passwordNew)) {
            toast(getString(R.string.is_not_same))
            return
        }

        //服务器请求
        showProgress(1)
        getPresenter().commitNewPasswordToService(phone, oldPassword, passwordNew)
    }


    override fun onDestroy() {
        println("ForgetPasswordActivity销毁")
        mSubscritionp?.unsubscribe()
        mSubscritionp = null
        super.onDestroy()

    }
}