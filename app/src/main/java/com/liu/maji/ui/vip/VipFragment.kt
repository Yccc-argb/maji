package com.liu.maji.ui.vip

import android.Manifest
import android.os.Bundle
import android.text.Editable
import android.text.PrecomputedText
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import cn.com.liu.maji.R
import com.liu.maji.app.Constant
import com.liu.maji.base.MySupportFragment
import com.liu.maji.ui.qr.QRFragment
import com.liu.maji.ui.vipdetails.VipDetailsFragment
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import it.dongjun.mylibrary.activity.CodeUtils
import kotlinx.android.synthetic.main.fragment_vip.*
import kotlinx.android.synthetic.main.title.*

class VipFragment : MySupportFragment<VipView, VipPresenter>(), View.OnClickListener, VipView, TextWatcher {

    var imei: String = ""

    override fun checkChargeInfoResult(amount: Int, cardNum: String, mobile: String) {
        hideProgress()
        val bundle = Bundle()
        bundle.putInt("amount", amount)
        bundle.putString("cardNum", cardNum)
        bundle.putString("mobile", mobile)
        bundle.putString("imei", imei)

        val vipDetailsFragment = VipDetailsFragment()
        vipDetailsFragment.arguments = bundle
        start(vipDetailsFragment)


    }


    override fun createPresenter(): VipPresenter {
        return VipPresenter()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("---VipFragment:onViewCreated---")
        initView()
    }

    private fun initView() {
        tv_title.text = "充点信息"
        tv_add.visibility = View.INVISIBLE
        iv_back.setOnClickListener(this)
        tv_sao.setOnClickListener(this)
        rl_btn_sure.setOnClickListener(this)
        et_phone.addTextChangedListener(this)
        et_vip.addTextChangedListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_sao
            -> startQR()
            R.id.rl_btn_sure
            -> checkPhoneAndVipNumber()
            R.id.iv_back
            -> _mActivity.onBackPressed()
        }
    }


    private fun startQR() {
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.CAMERA).subscribe { granted ->
            if (granted!!) {
                // 打开相机
                startForResult(QRFragment(), 0)
            } else {
                // 权限被拒绝
                ToastUtils.showToast("请先打开相机")
            }
        }

    }

    private fun checkPhoneAndVipNumber() {


        val phone = et_phone.text.toString().trim()
//        if (TextUtils.isEmpty(phone)) {
//            toast(getString(R.string.phone_is_empty))
//            return
//        }

        val vipNumber = et_vip.text.toString().trim()

//        if (TextUtils.isEmpty(vipNumber)) {
//            toast(getString(R.string.vipnumeber_is_empty))
//            return
//        }

        if (TextUtils.isEmpty(phone) && TextUtils.isEmpty(vipNumber)) {
            ToastUtils.showToast("请输入手机号或者卡号")
            return
        }
        showProgress(1)
        getPresenter().checkChargeInfo(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                vipNumber, phone)
    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            val vipNumberAndImei = data?.getString(CodeUtils.RESULT_STRING)
            val split = vipNumberAndImei?.split("#/#")
            if (split?.isNotEmpty() == true) {
                var vipNumber: String
                if (split.size == 2) {
                    vipNumber = split[0]    //卡号
                    imei = split[1]      //充值机号
                } else {
                    vipNumber = split[0]
                    imei = ""
                }
                if (TextUtils.isEmpty(vipNumber)) {
                    toast(getString(R.string.get_vip_number_fail))
                    return
                }
                et_vip.setText(vipNumber)
                showProgress(1)
                getPresenter().checkChargeInfo(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                        vipNumber, "")

            }


        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (et_phone.hasFocus()) {

            val phone = et_phone.text.toString().trim()
            if (!TextUtils.isEmpty(phone) && phone.length == 11) {
                showProgress(1)
                //手机号满足要求
                getPresenter().checkChargeInfo(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                        "", phone)
            }
        }
        if (et_vip.hasFocus()) {

            val cd = et_vip.text.toString().trim()
            if (!TextUtils.isEmpty(cd) && cd.length == 10) {
                showProgress(1)
                //卡号满足要求
                getPresenter().checkChargeInfo(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                        cd, "")
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun onSupportInvisible() {
        super.onSupportInvisible()
        //用户不可见
        imei = ""
        et_vip.setText("")
    }
}