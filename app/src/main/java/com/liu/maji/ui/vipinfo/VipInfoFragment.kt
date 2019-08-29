package com.liu.maji.ui.vipinfo

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.com.liu.maji.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liu.maji.app.Constant
import com.liu.maji.base.MySupportFragment
import com.liu.maji.modle.bean.vip.VipInfoResponse
import com.liu.maji.ui.adapter.VipInfoAdapter
import com.liu.maji.ui.registervip.RegisterVipFragment
import com.liu.maji.ui.vip.VipFragment
import com.liu.maji.ui.vipdetails.VipDetailsFragment
import com.liu.maji.utils.JsonUtils
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_vip_info.*
import kotlinx.android.synthetic.main.title.*

class VipInfoFragment : MySupportFragment<VipInfoView,VipInfoPresenter>(), View.OnClickListener ,VipInfoView, BaseQuickAdapter.OnItemClickListener {


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val item = vipInfoAdapter?.getItem(position)
        val bundle = Bundle()
        bundle.putInt("amount", item!!.amount.toInt())
        bundle.putString("cardNum", item!!.cd)
        bundle.putString("mobile", item!!.mobile)
        val vipDetailsFragment = VipDetailsFragment()
        vipDetailsFragment.arguments = bundle
        start(vipDetailsFragment)

    }


    private var vipInfoAdapter : VipInfoAdapter?=null
    private var majiangVOList: MutableList<VipInfoResponse.DataBean.MajiangVOListBean> = ArrayList()
    private var type : Int= 0

    private var currentPage: Int = 1
    private val pageCounts: Int = 10

    private var refreshLayout: BGARefreshLayout?= null

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_add
                    ->start(RegisterVipFragment())
            R.id.iv_search
                    ->searchVipByPhoneNUmber()
            R.id.rl_btn_sure
                    ->start(VipFragment())
            R.id.iv_back
            ->_mActivity.onBackPressed()
        }
    }


    override fun queryVipInfoByMobileResult(t: VipInfoResponse.DataBean.MajiangVOListBean?) {
        //手机号查询会员信息结果
        if (t!=null){
            majiangVOList?.clear()
            majiangVOList.add(t)
            vipInfoAdapter?.notifyDataSetChanged()
        }else {
            toast("暂未查询到相关信息")
        }

    }



    override fun queryVipInfoResult(vipInfo: VipInfoResponse) {
        if (type == 0){
            refreshLayout?.endRefreshing()
        }else{
            refreshLayout?.endLoadingMore()
        }
        val item = vipInfo.data.majiangVOList
        if (item.isEmpty()){
            ToastUtils.showToast("没有更多数据了")
        }else {
            //初始化会员信息
            ++currentPage
            val amount = vipInfo.data.majiangVOListSize
            tv_vip_tall.text = amount.toString()
            majiangVOList.addAll(item)
            if (vipInfoAdapter == null){
                vipInfoAdapter = VipInfoAdapter(R.layout.item_vip_info, majiangVOList)
                val linearLayoutManager = LinearLayoutManager(_mActivity)
                rlv_vipinfo.layoutManager = linearLayoutManager
                rlv_vipinfo.adapter = vipInfoAdapter
                vipInfoAdapter?.setOnItemClickListener(this)

            }else {
                vipInfoAdapter?.setNewData(majiangVOList)
            }
        }

    }



    override fun createPresenter(): VipInfoPresenter {
        return VipInfoPresenter()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vip_info,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        tv_title.text = "会员信息"
        tv_add.text = "添加会员"
        tv_add.setOnClickListener(this)
        rl_btn_sure.setOnClickListener(this)
        iv_search.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        initBGRefreshLayout(bg_refreshlayout)
        initVipInfo()

    }

    private fun initVipInfo(){
        showProgress(1)
        getPresenter().queryVipInfo(Prefs.getInt(Constant.MERCHANT_ID, 0), Prefs.getInt(Constant.AGENT_ID, 0),
                currentPage, pageCounts, (currentPage - 1)*10, currentPage*10)


    }


    private fun searchVipByPhoneNUmber(){
        val phone = et_phone_search.text.toString().trim()
        if (TextUtils.isEmpty(phone)){
            ToastUtils.showToast((R.string.condition_is_empty))
            return
        }

        //通过手机号搜索会员账户
        showProgress(1)
        getPresenter().queryVipInfoByMobile(Prefs.getInt(Constant.MERCHANT_ID,0)
                ,Prefs.getInt(Constant.AGENT_ID,0),phone,phone,"normal","Y")

    }


    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        //加载更多
        type = 1
        this.refreshLayout = refreshLayout
        println("回调上拉加载更多")
        showProgress(1)

        this.currentPage = 1
        getPresenter().queryVipInfo(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                currentPage, pageCounts, (currentPage - 1)*10, currentPage*10)
        return true
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        //下拉刷新
        type = 0
        this.refreshLayout = refreshLayout
        println("回调上拉加载更多")
        showProgress(1)
        majiangVOList?.clear()
        getPresenter().queryVipInfo(Prefs.getInt(Constant.MERCHANT_ID, 0), Prefs.getInt(Constant.AGENT_ID, 0),
                currentPage, pageCounts, (currentPage - 1)*10, currentPage*10)
    }



}