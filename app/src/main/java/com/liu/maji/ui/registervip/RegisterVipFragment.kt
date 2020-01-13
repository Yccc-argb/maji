package com.liu.maji.ui.registervip

import android.Manifest
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.liu.maji.R
import com.chad.library.adapter.base.BaseQuickAdapter

import com.liu.maji.app.Constant
import com.liu.maji.base.MySupportFragment
import com.liu.maji.modle.bean.vip.ChargeMechineResponse
import com.liu.maji.modle.bean.vip.ChargeMechineResponse.DataBean
import com.liu.maji.ui.adapter.ChargeMechineAdapter
import com.liu.maji.ui.home.HomeFragment
import com.liu.maji.ui.qr.QRFragment
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.RxTimeUtils
import com.liu.maji.utils.ToastUtils
import com.liu.maji.wedigt.SimpleDialog
import com.tbruyelle.rxpermissions2.RxPermissions

import it.dongjun.mylibrary.activity.CodeUtils

import kotlinx.android.synthetic.main.fragment_register_vip.*
import kotlinx.android.synthetic.main.title.*
import me.yokeyword.fragmentation.ISupportFragment
import rx.Subscriber
import rx.Subscription
import java.util.concurrent.TimeUnit

class RegisterVipFragment : MySupportFragment<RegisterVipView, RegisterVipPresenter>(), View.OnClickListener, RegisterVipView, BaseQuickAdapter.OnItemClickListener, SimpleDialog.DialogClickListener {
    override fun onConfirm(type: String?) {

    }

    override fun changeBatchModleResult(b: Boolean) {
//       if (b){
//          //开启
//       }else {
//           //关闭
//       }
    }


    override fun onConfirm(type: Int) {

        //确认
        if (dialog != null) {
            dialog!!.dismiss()
            dialog = null

            if (type == 1) {
                showProgress(1)
                getPresenter().addNewVipCard(devcieNumCharge, Prefs.getInt(Constant.MERCHANT_ID, 0)
                        , Prefs.getInt(Constant.AGENT_ID, 0), "", vipNumber, "", "")
            } else if (type == 2) {
                //开启批量模
                tv_piliang.setBackgroundResource(R.drawable.kai)
                rl_vip_num.visibility = View.GONE
                rl_charge_mechine_num.visibility = View.VISIBLE
                rl_vip_phone.visibility = View.GONE
                rl_vertify_code.visibility = View.GONE
                tv_name.setText("开卡点数")
                et_charge_num.setText("")
                et_name.setText("")

            } else {
                tv_piliang.setBackgroundResource(R.drawable.close)
                rl_vip_num.visibility = View.VISIBLE
                rl_charge_mechine_num.visibility = View.VISIBLE
                rl_vip_phone.visibility = View.VISIBLE
                rl_vertify_code.visibility = View.VISIBLE
                tv_name.setText("姓名    ")
                et_charge_num.setText("")
                et_name.setText("")
                if (!Prefs.getBoolean(Constant.BATRCH_MODLE,false)){
                    toast("批量开卡模式关闭成功")
                    return
                }

                if (!TextUtils.isEmpty(Prefs.getString("deviceId",""))){
                    showProgress(1)
                    getPresenter().changeBatchModle(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                            "0", "0", "0", "0", Prefs.getString("deviceId",""),"")
                }



            }

        }
    }

    override fun onCancel() {
        //取消
        if (dialog != null) {
            dialog!!.dismiss()
            dialog = null
        }

    }


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val item = chargeMechineAdapter?.getItem(position)
//        et_charge_num.setText(item?.equipTypeName)
        devcieNumCharge = item?.cd.toString()
        devcieName = item?.name.toString()
        et_charge_num.setText(devcieName)
        bottomSheetDialog?.dismiss()
    }


    private var chargeMechineAdapter: ChargeMechineAdapter? = null

    private var bottomSheetDialog: BottomSheetDialog? = null

    private var chargeMechineData: MutableList<DataBean>? = null

    private var devcieNumCharge: String = ""
    private var devcieName: String = ""
    private var vipNumber: String = ""

    private var dialog: SimpleDialog? = null

    private var batchModle: Boolean = false


    override fun queryChargeMechineInfoResult(t: ChargeMechineResponse) {
        chargeMechineData = t.data
    }


    override fun getCodeResult() {
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
        ToastUtils.showToast("验证码发送成功")
    }


    private var mSubscritionp: Subscription? = null

    override fun addNewVipResult() {
        toast("开卡成功")
        start(HomeFragment(), ISupportFragment.SINGLETASK)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_sao
            -> startQR()
            R.id.rl_btn_sure
            -> checkPhoneAndVipNumber()
            R.id.tv_send_vertif
            -> sendVertifyCode()
            R.id.iv_back
            -> _mActivity.onBackPressed()

            R.id.tv_xiala
            -> showChargeMechineType()
            R.id.tv_piliang
            -> changeBatchModel()
        }
    }


    /**
     * 更改注册模式
     */
    private fun changeBatchModel() {
        batchModle = !batchModle
        dialog = SimpleDialog(mActivity, R.style.circle_dialog)
        if (batchModle) {
            dialog?.showCommonDialog(2, "确定开启批量新增模式", this)

        } else {
            dialog?.showCommonDialog(3, "确定关闭批量新增模式", this)
        }

    }


    private fun startQR() {
        val rxPermissions = RxPermissions(this)

        rxPermissions.request(Manifest.permission.CAMERA).subscribe { granted ->
            if (granted!!) {
                // 打开相机
                startForResult(QRFragment(), 1)
            } else {
                // 权限被拒绝
                ToastUtils.showToast("请先打开相机")
            }
        }

    }

    private fun showChargeMechineType() {
        if (chargeMechineData != null && (!chargeMechineData!!.isEmpty())) {
            bottomSheetDialog = BottomSheetDialog(_mActivity)
            bottomSheetDialog?.setContentView(R.layout.dialog_select_charge_mechine)
            bottomSheetDialog?.show()

            var linearLayoutManager = LinearLayoutManager(_mActivity)
            chargeMechineAdapter = ChargeMechineAdapter(R.layout.item_charge_machine, chargeMechineData)
            val rlvChargeMachine = bottomSheetDialog?.findViewById<RecyclerView>(R.id.rlv_charge_machine)
            rlvChargeMachine?.layoutManager = linearLayoutManager
            rlvChargeMachine?.adapter = chargeMechineAdapter

            chargeMechineAdapter?.onItemClickListener = this
        } else {
            ToastUtils.showToast("暂未查询到充点机信息")
        }


    }

    private fun sendVertifyCode() {
        //会员卡号
        vipNumber = et_vip.text.toString().trim()
        if (TextUtils.isEmpty(vipNumber)) {
            toast(getString(R.string.vipnumeber_is_empty))
            return
        }

//        val chargeMechineNum = et_charge_num.text.toString().trim()
        if (TextUtils.isEmpty(devcieNumCharge)) {
            toast(getString(R.string.charge_is_empty))
            return
        }

        //姓名
        val vipName = et_name.text.toString().trim()
        if (TextUtils.isEmpty(vipName)) {
            toast(getString(R.string.name_is_empty))
            return
        }

        //手机号
        val phone = et_phone.text.toString().trim()
        if (TextUtils.isEmpty(phone)) {
            toast(getString(R.string.phone_is_empty))
            return
        }


        showProgress(1)
        getPresenter().getVertifyCode(phone, Prefs.getInt(Constant.MERCHANT_ID, 0),
                Prefs.getInt(Constant.AGENT_ID, 0))
    }

    fun setSendVerfityNormal() {
        tv_send_vertif.isEnabled = true
        tv_send_vertif.text = "点击重发"
        tv_send_vertif.setBackgroundResource(R.drawable.bg_get_code)
    }

    private fun checkPhoneAndVipNumber() {

        if (batchModle) {
            //批量模式

            if (TextUtils.isEmpty(devcieNumCharge)) {
                toast(getString(R.string.charge_is_empty))
                return
            }

            val vipAmount = et_name.text.toString().trim()
            if (TextUtils.isEmpty(vipAmount)) {
                toast("请输入开卡点数")
                return
            }

            try {
                vipAmount.toInt()
            } catch (e: NumberFormatException) {
                ToastUtils.showToast("请输入正确的充值点数")
                return
            }

            showProgress(1)
            if (TextUtils.isEmpty(devcieName)){
                devcieName = Prefs.getString("deviceName","")
            }
            getPresenter().changeBatchModle(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                    "0", vipAmount, "0", "1", devcieNumCharge, devcieName)

        } else {
            //会员卡号
            vipNumber = et_vip.text.toString().trim()
            if (TextUtils.isEmpty(vipNumber)) {
                toast(getString(R.string.vipnumeber_is_empty))
                return
            }

//        val chargeMechineNum = et_charge_num.text.toString().trim()
            if (TextUtils.isEmpty(devcieNumCharge)) {
                toast(getString(R.string.charge_is_empty))
                return
            }


            //姓名
            val vipName = et_name.text.toString().trim()
//        if (TextUtils.isEmpty(vipName)) {
//            toast(getString(R.string.name_is_empty))
//            return
//        }


            //手机号
            val phone = et_phone.text.toString().trim()
            //验证码
            val code = et_code.text.toString().trim()

            if (TextUtils.isEmpty(vipName) || TextUtils.isEmpty(phone)) {
                //姓名,手机号和验证码不填的时候提示弹窗
                dialog = SimpleDialog(mActivity, R.style.circle_dialog)
                dialog?.showCommonDialog(1, "无手机号验证,卡片丢失后将无法补卡", this)
                return
            }


            //手机号和姓名不为空,校验验证码
            if (TextUtils.isEmpty(code)) {
                toast(getString(R.string.vertify_code_is_empty))
                return
            }

            //提交手机号和验证码校验
            showProgress(1)
            getPresenter().addNewVipCard(devcieNumCharge, Prefs.getInt(Constant.MERCHANT_ID, 0)
                    , Prefs.getInt(Constant.AGENT_ID, 0), phone, vipNumber, vipName, code)
        }


    }

    override fun createPresenter(): RegisterVipPresenter {
        return RegisterVipPresenter()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register_vip, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        batchModle = Prefs.getBoolean(Constant.BATRCH_MODLE, false)
        if (Prefs.getBoolean(Constant.BATRCH_MODLE, false)) {
            tv_piliang.setBackgroundResource(R.drawable.kai)
            rl_vip_num.visibility = View.GONE
            rl_charge_mechine_num.visibility = View.VISIBLE
            rl_vip_phone.visibility = View.GONE
            rl_vertify_code.visibility = View.GONE
            tv_name.text = "开卡点数"
            et_charge_num.setText("")
            et_name.setText("")
            if (!TextUtils.isEmpty(Prefs.getString("deviceName",""))){
                et_charge_num.setText(Prefs.getString("deviceName",""))   //获取设备名称
                devcieNumCharge = Prefs.getString("deviceId","")          //获取设备ID
            }
        } else {
            tv_piliang.setBackgroundResource(R.drawable.close)
            rl_vip_num.visibility = View.VISIBLE
            rl_charge_mechine_num.visibility = View.VISIBLE
            rl_vip_phone.visibility = View.VISIBLE
            rl_vertify_code.visibility = View.VISIBLE
            tv_name.setText("姓名    ")
            et_charge_num.setText("")
            et_name.setText("")
        }

    }

    private fun initView() {
        tv_title.text = "新增会员"
        tv_add.visibility = View.INVISIBLE
        tv_sao.setOnClickListener(this)
        rl_btn_sure.setOnClickListener(this)
        tv_send_vertif.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        tv_xiala.setOnClickListener(this)
        tv_piliang.setOnClickListener(this)
        showProgress(1)
        getPresenter().queryChargeMechineInfo(Prefs.getInt(Constant.MERCHANT_ID, 0), Prefs.getInt(Constant.AGENT_ID, 0)
                , 1, 10, 1, 10)

    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val vipNumberAndImei = data?.getString(CodeUtils.RESULT_STRING)
            val split = vipNumberAndImei?.split("#/#")
            if (split?.isNotEmpty() == true) {
                if (split.size == 2){
                    et_vip.setText(split[0])
                    et_charge_num.setText(split[1])
                    devcieNumCharge = split[1]
                }else {
                    et_vip.setText(split[0])
                }
            }else {
                toast(getString(R.string.get_vip_number_fail))
                return
            }
//            val vipNumber = data?.getString(CodeUtils.RESULT_STRING)
//            if (TextUtils.isEmpty(vipNumber)) {
//
//            }
//            et_vip.setText(data?.getString(CodeUtils.RESULT_STRING))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSubscritionp?.unsubscribe()

    }
}