package com.liu.maji.ui.vipdetails

import android.Manifest
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import cn.com.liu.maji.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liu.maji.app.Constant
import com.liu.maji.base.MySupportFragment
import com.liu.maji.modle.bean.vip.ChargeMechineResponse
import com.liu.maji.ui.adapter.ChargeMechineAdapter
import com.liu.maji.ui.home.HomeFragment
import com.liu.maji.ui.qr.QRFragment
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import it.dongjun.mylibrary.activity.CodeUtils
import kotlinx.android.synthetic.main.fragment_vipdetails.*
import kotlinx.android.synthetic.main.title.*
import me.yokeyword.fragmentation.ISupportFragment

class VipDetailsFragment : MySupportFragment<VipDetailsView, VipDetailsPresenter>(), View.OnClickListener, VipDetailsView, BaseQuickAdapter.OnItemClickListener {
    override fun operateCardResult() {
        start(HomeFragment(), ISupportFragment.SINGLETASK)
    }


    override fun queryChargeMechineInfoResult(t: ChargeMechineResponse) {
        chargeMechineData = t.data
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val item = chargeMechineAdapter?.getItem(position)
        et_charge_num.setText(item?.equipTypeName)
        devcieNumCharge = item?.cd.toString()
        bottomSheetDialog?.dismiss()
    }

    private var amount: Int = 0
    private var cardNum: String = ""
    private var mobile : String = ""
    private var bottomSheetDialog: BottomSheetDialog? = null
    private var chargeMechineData: MutableList<ChargeMechineResponse.DataBean>? = null
    private var chargeMechineAdapter: ChargeMechineAdapter? = null

    private var devcieNumCharge: String = ""


    override fun chargeResult() {
        toast("充点成功")
        start(HomeFragment(), ISupportFragment.SINGLETASK)
    }


    override fun createPresenter(): VipDetailsPresenter {
        return VipDetailsPresenter()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vipdetails, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        tv_title.text = "会员卡充点"
        tv_add.visibility = View.INVISIBLE
        iv_back.setOnClickListener(this)
        rl_btn_sure.setOnClickListener(this)
        tv_xiala.setOnClickListener(this)
        tv_business_xiala.setOnClickListener(this)
        tv_business_sao.setOnClickListener(this)
        this.amount = arguments?.getInt("amount") ?: 0//余额
        this.cardNum = arguments?.getString("cardNum") ?: "" //卡编号
        this.mobile = arguments?.getString("mobile") ?: ""
        tv_balance.text = "当前点数: " + amount?.toString()
        tv_vip_number.text = "会员卡: $cardNum"
        showProgress(1)
        getPresenter().queryChargeMechineInfo(Prefs.getInt(Constant.MERCHANT_ID, 0), Prefs.getInt(Constant.AGENT_ID, 0)
                , 1, 10, 1, 10)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back
            -> _mActivity.onBackPressed()
            R.id.rl_btn_sure
            -> checkChargeMoney()
//            R.id.iv_close
//            -> bottomSheetDialog?.dismiss()
            R.id.tv_xiala
            -> showChargeMechineType(1)
            R.id.tv_business_xiala
            -> showChargeMechineType(2)
            R.id.tv_business_sao
            -> startQR()


        }
    }

    private fun checkChargeMoney() {
//        val chargeMechineNum = et_charge_num.text.toString().trim()

        val businessType = et_business_type.text.toString().trim()   //业务类型
        if (TextUtils.isEmpty(businessType)){
            ToastUtils.showToast("请先选择业务类型")
            return
        }

        if (businessType == "充值"){
            val chargeMoney = et_charge_money.text.toString().trim()

            if (TextUtils.isEmpty(chargeMoney)) {
                toast(getString(R.string.charge_money_is_empty))
                return
            }
            var chargeMoneyInt = 0
            try {
                chargeMoneyInt = chargeMoney.toInt()
            } catch (e: NumberFormatException) {
                ToastUtils.showToast("请输入正确的充值点数")
                return
            }
            if (chargeMoneyInt <= 0) {
                ToastUtils.showToast("充值点数必须大于0")
            }
            if (TextUtils.isEmpty(devcieNumCharge)) {
                toast(getString(R.string.charge_is_empty))
                return
            }
            showProgress(1)
            getPresenter().charge(devcieNumCharge, Prefs.getInt(Constant.AGENT_ID, 0),
                    cardNum, chargeMoneyInt, 0, (chargeMoneyInt + amount).toDouble())
        }else if (businessType == "挂失"){
            //
            if (TextUtils.isEmpty(mobile)){
                toast("该卡未绑定手机号,不能挂失")
                return
            }
            getPresenter().lossCard(Prefs.getInt(Constant.MERCHANT_ID,0),Prefs.getInt(Constant.AGENT_ID,0),
                    cardNum,mobile,"")
        }else if (businessType == "补卡"){
            //获取新的卡号
            if (TextUtils.isEmpty(mobile)){
                toast("该卡未绑定手机号,不能补卡")
                return
            }
            if (TextUtils.isEmpty(devcieNumCharge)) {
                toast(getString(R.string.charge_is_empty))
                return
            }
            val newCd = et_newCd.text.toString().trim()
            if (TextUtils.isEmpty(newCd)) {
                toast(getString(R.string.charge_is_empty))
                return
            }

            getPresenter().replaceCard(Prefs.getInt(Constant.MERCHANT_ID,0),Prefs.getInt(Constant.AGENT_ID,0),
                    cardNum,devcieNumCharge,mobile,newCd)
        }else if (businessType == "退卡"){
            getPresenter().refundCard(Prefs.getInt(Constant.MERCHANT_ID,0),Prefs.getInt(Constant.AGENT_ID,0),
                    cardNum,mobile)
        }




    }

    private fun showChargeMechineType(type: Int) {
        bottomSheetDialog = BottomSheetDialog(_mActivity)
        if (type == 1) {
            if (chargeMechineData != null && (!chargeMechineData!!.isEmpty())) {
                bottomSheetDialog?.setContentView(R.layout.dialog_select_charge_mechine)
                var linearLayoutManager = LinearLayoutManager(_mActivity)
                chargeMechineAdapter = ChargeMechineAdapter(R.layout.item_charge_machine, chargeMechineData)
                val rlvChargeMachine = bottomSheetDialog?.findViewById<RecyclerView>(R.id.rlv_charge_machine)
                rlvChargeMachine?.layoutManager = linearLayoutManager
                rlvChargeMachine?.adapter = chargeMechineAdapter
                bottomSheetDialog?.findViewById<ImageView>(R.id.iv_close)?.setOnClickListener {
                    bottomSheetDialog?.dismiss()
                }
                chargeMechineAdapter?.onItemClickListener = this
                bottomSheetDialog?.show()

            } else {
                ToastUtils.showToast("暂未查询到充点机信息")
            }
        } else {
            //弹出业务选择窗口
            bottomSheetDialog?.setContentView(R.layout.dialog_select_business_type)
            bottomSheetDialog?.findViewById<ImageView>(R.id.iv_close)?.setOnClickListener {
                bottomSheetDialog?.dismiss()
            }
            bottomSheetDialog?.findViewById<RelativeLayout>(R.id.rl_charge)?.setOnClickListener {
                et_business_type.setText("充值")
                bottomSheetDialog?.dismiss()
                rl_charge_mechine.visibility = View.VISIBLE
                rl_charge_num.visibility = View.VISIBLE
                rl_newCd.visibility = View.GONE
            }
            bottomSheetDialog?.findViewById<RelativeLayout>(R.id.rl_guashi)?.setOnClickListener {
                et_business_type.setText("挂失")
                bottomSheetDialog?.dismiss()
                rl_charge_mechine.visibility = View.GONE
                rl_charge_num.visibility = View.GONE
                rl_newCd.visibility = View.GONE
            }
            bottomSheetDialog?.findViewById<RelativeLayout>(R.id.rl_buka)?.setOnClickListener {
                et_business_type.setText("补卡")
                bottomSheetDialog?.dismiss()
                rl_charge_mechine.visibility = View.VISIBLE
                rl_charge_num.visibility = View.GONE
                rl_newCd.visibility = View.VISIBLE
            }
            bottomSheetDialog?.findViewById<RelativeLayout>(R.id.rl_tuika)?.setOnClickListener {
                et_business_type.setText("退卡")
                bottomSheetDialog?.dismiss()
                rl_charge_mechine.visibility = View.GONE
                rl_charge_num.visibility = View.GONE
                rl_newCd.visibility =View.GONE
            }
            bottomSheetDialog?.show()

        }
    }

    private fun startQR(){
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

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val vipNumber = data?.getString(CodeUtils.RESULT_STRING)
            if (TextUtils.isEmpty(vipNumber)) {
                toast(getString(R.string.get_vip_number_fail))
                return
            }
            et_newCd.setText(data?.getString(CodeUtils.RESULT_STRING))
        }
    }

}