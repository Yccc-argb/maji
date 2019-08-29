package com.liu.maji.ui.charge

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.com.liu.maji.R
import com.liu.maji.app.Constant

import com.liu.maji.base.MySupportFragment
import com.liu.maji.modle.bean.charge_history.ChargeHistoryResponse
import com.liu.maji.ui.adapter.ChargeAdapter
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.ToastUtils


import kotlinx.android.synthetic.main.fragment_charge.*

import kotlinx.android.synthetic.main.title.*


class ChargeFragment : MySupportFragment<ChargeView, CharegPresenter>(), View.OnClickListener, ChargeView {




    override fun getChargeHistoryDataResult(dataBean: ChargeHistoryResponse.DataBean) {
        //查询充值记录回调
        if (type == 0){
            refreshLayout?.endRefreshing()
        }else{
            refreshLayout?.endLoadingMore()
        }
        currentPage = dataBean.page    //当前页数
        if (dataBean.records != null && !dataBean.records.isEmpty()){
            //还有更多数据
            if (chargeAdapter == null){
                chargeAdapter = ChargeAdapter(R.layout.item_charge, dataBean.records)
                val linearLayoutManager = LinearLayoutManager(_mActivity)
                rlv_charge.layoutManager = linearLayoutManager
                rlv_charge.adapter = chargeAdapter
            }else{
                this.dataBean?.addAll(dataBean.records)
                chargeAdapter?.setNewData(this.dataBean)
            }
        }else{
            ToastUtils.showToast("暂无更多数据")
        }

    }


    private var currentPage = 1
    private val pageCounts = 10
    private var dataBean: MutableList<ChargeHistoryResponse.DataBean.RecordsBean> = ArrayList()
    private var type = 1
    private var refreshLayout:BGARefreshLayout?=null
    private var chargeAdapter : ChargeAdapter ? = null

    override fun createPresenter(): CharegPresenter {
        return CharegPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_charge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tv_title.text = "充点记录"
        tv_add.visibility = View.INVISIBLE
        iv_back.setOnClickListener(this)
        initBGRefreshLayout(bg_refreshlayout)
        initDeviceData()
        iv_search.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back
            -> _mActivity.onBackPressed()
            R.id.iv_search
                    ->searchChargeByPhone()
        }

    }

    private fun searchChargeByPhone(){

        val phone = et_phone_search.text.toString().trim()   //手机号
        if (TextUtils.isEmpty(phone)){
            ToastUtils.showToast(getString(R.string.condition_is_empty))
            return
        }
        showProgress(1)
        currentPage = 1
        this.dataBean?.clear()
        getPresenter().getChargeHistoryData(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0)
                , phone,"recharge_offline", "","", currentPage, pageCounts, 1, 10)

    }

    private fun initDeviceData() {
        showProgress(1)
        getPresenter().getChargeHistoryData(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0)
                , "","recharge_offline", "","", currentPage, pageCounts, 1, 10)


//        val chargeAdapter = ChargeAdapter(R.layout.item_charge, dataList)
//        val linearLayoutManager = LinearLayoutManager(_mActivity)
//        rlv_charge.layoutManager = linearLayoutManager
//        rlv_charge.adapter = chargeAdapter

    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        //上拉加载更多
        this.refreshLayout = refreshLayout
        type = 1
        println("回调上拉加载更多")
        showProgress(1)
        val phone = et_phone_search.text.toString().trim()   //手机号
        ++currentPage
        getPresenter().getChargeHistoryData(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0)
                , phone,"recharge_offline", "", "",currentPage, pageCounts, (currentPage-1)*10 +1, currentPage*10)
        return true
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
//        super.onBGARefreshLayoutBeginRefreshing(refreshLayout)
        this.refreshLayout = refreshLayout
        type = 0
        //下拉刷新
        println("回掉下拉刷新")
        showProgress(1)
        val phone = et_phone_search.text.toString().trim()   //手机号
        currentPage = 1
        this.dataBean?.clear()
        getPresenter().getChargeHistoryData(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0)
                , phone,"recharge_offline","","",currentPage,pageCounts,(currentPage-1)*10 +1,currentPage*10)


    }


}