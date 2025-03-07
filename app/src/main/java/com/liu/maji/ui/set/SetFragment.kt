package com.liu.maji.ui.set

import android.content.Intent
import android.os.Bundle
import android.os.TestLooperManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.liu.maji.R
import com.liu.maji.app.Constant
import com.liu.maji.base.MySupportFragment
import com.liu.maji.ui.forget_password.ForgetPasswordActivity
import com.liu.maji.utils.CommonUtils
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.ToastUtils
import com.liu.maji.wedigt.SimpleDialog
import kotlinx.android.synthetic.main.fragment_set.*
import kotlinx.android.synthetic.main.title.*

class SetFragment : MySupportFragment<SetView,SetPrenter>(), View.OnClickListener {



    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.iv_back
                -> _mActivity.onBackPressed()
            R.id.rl_exit
                -> exit()
            R . id .rl_change_password
            -> turn2ForgetPassword()
        }

    }

    override fun createPresenter(): SetPrenter {
        return SetPrenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_set, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        tv_add.visibility = View.INVISIBLE
        rl_exit.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        rl_change_password.setOnClickListener(this)
        tv_version.text = CommonUtils.getVersionName()
    }

    private fun exit(){
//        Prefs.clear()
        Prefs.putBoolean(Constant.IS_LOGIN,false)
        Prefs.putInt(Constant.MERCHANT_ID,0)
        Prefs.putInt(Constant.AGENT_ID,0)
        Prefs.putString(Constant.AGENT_NAME,"")
        Prefs.putString(Constant.PHONE,"")
        this._mActivity.finish()
//        System.exit(0)
    }

    private fun turn2ForgetPassword(){
        val intent = Intent(mActivity, ForgetPasswordActivity::class.java)
        startActivity(intent)
    }
}