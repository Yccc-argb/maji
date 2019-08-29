package com.liu.maji.ui.income

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.liu.maji.R
import com.liu.maji.app.Constant
import com.liu.maji.base.MySupportFragment
import com.liu.maji.utils.Prefs
import kotlinx.android.synthetic.main.fragment_income.*
import kotlinx.android.synthetic.main.my_camera.*
import kotlinx.android.synthetic.main.title.*

class IncomeFragment : MySupportFragment<IncomeView, IncomePresenter>(), View.OnClickListener, IncomeView {
    override fun getIncomeDataResult(totalIncome: Double?, yestIncome: Double?, todIncome: Double?) {
        hideProgress()
        tv_total_income.text = Html.fromHtml("<big>" + totalIncome.toString() + "</big>(点)")
        tv_yesterday_total_income.text = Html.fromHtml("<big>" + yestIncome.toString() + "</big><br>昨日指数(点)")
        tv_today_total_income.text = Html.fromHtml("<big>" + todIncome.toString() + "</big><br>今日指数(点)")
    }


    override fun createPresenter(): IncomePresenter {
        return IncomePresenter()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_income, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initData() {
        showProgress(1)
        getPresenter().getInComeData(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0), 1)
    }

    private fun initView() {
        tv_title.text = "我的收益"
        tv_add.visibility = View.INVISIBLE
        iv_back.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        //返回首页
        _mActivity.onBackPressed()
    }
}