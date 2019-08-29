package com.liu.maji.ui.home

import android.os.Bundle
import android.os.ConditionVariable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.liu.maji.R
import com.liu.maji.app.Constant
import kotlinx.android.synthetic.main.fragment_home.*
import com.liu.maji.base.MySupportFragment
import com.liu.maji.ui.charge.ChargeFragment
import com.liu.maji.ui.device.DevcieFragment
import com.liu.maji.ui.income.IncomeFragment
import com.liu.maji.ui.invest.InvestFragment
import com.liu.maji.ui.set.SetFragment
import com.liu.maji.ui.vip.VipFragment
import com.liu.maji.ui.vipinfo.VipInfoFragment
import com.liu.maji.utils.Prefs

class HomeFragment : MySupportFragment<HomeView, HomePresenter>(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_my_device
            -> start(DevcieFragment())
            R.id.ll_my_member
            -> toast("我的员工")
            R.id.ll_vip
            -> start(VipInfoFragment())
            R.id.ll_my_data
            -> start(IncomeFragment())
            R.id.ll_pay_history
            -> start(ChargeFragment())
            R.id.ll_taocan
            -> startToInvestOrOpptions(1)
            R.id.ll_opinion
            -> startToInvestOrOpptions(2)
            R.id.iv_set
            -> start(SetFragment())
        }

    }

    private fun startToInvestOrOpptions(i: Int) {
        val bundle = Bundle()
        bundle.putInt("type",i)
        val investFragment = InvestFragment()
        investFragment.arguments = bundle
        start(investFragment)
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ll_my_shop.setOnClickListener(this)
        ll_my_device.setOnClickListener(this)
        ll_my_member.setOnClickListener(this)
        ll_my_data.setOnClickListener(this)
        ll_pay_history.setOnClickListener(this)
        ll_taocan.setOnClickListener(this)
        ll_opinion.setOnClickListener(this)
        ll_vip.setOnClickListener(this)
        iv_set.setOnClickListener(this)
        initView()
    }

    private fun initView() {
        tv_name.text = Prefs.getString(Constant.AGENT_NAME,"")
        tv_phone.text = Prefs.getString(Constant.PHONE,"")

    }

}