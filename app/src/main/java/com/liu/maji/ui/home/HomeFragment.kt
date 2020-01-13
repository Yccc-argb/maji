package com.liu.maji.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.liu.maji.R
import com.liu.maji.app.Constant
import kotlinx.android.synthetic.main.fragment_home.*
import com.liu.maji.base.MySupportFragment
import com.liu.maji.service.DownLoadService
import com.liu.maji.ui.charge.ChargeFragment
import com.liu.maji.ui.device.DevcieFragment
import com.liu.maji.ui.income.IncomeFragment
import com.liu.maji.ui.invest.InvestFragment
import com.liu.maji.ui.set.SetFragment
import com.liu.maji.ui.vipinfo.VipInfoFragment
import com.liu.maji.ui.webview.WebViewActivity
import com.liu.maji.utils.GlideImageLoader
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.UIUtil
import com.liu.maji.wedigt.SimpleDialog
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener

class HomeFragment : MySupportFragment<HomeView, HomePresenter>(), View.OnClickListener, HomeView, SimpleDialog.DialogClickListener, OnBannerListener {

    private var htmlData: Map<String, String> = mutableMapOf()
    private var imageUrl: List<String>? = null
    override fun OnBannerClick(position: Int) {
        //banner点击事件
        toast("点击banner了")
//        val intent = Intent(mActivity,WebViewActivity::class.java)
//        intent.putExtra("htmlUrl","http://www.baidu.com")
//        startActivity(intent)
        if (imageUrl?.get(position)?.isNotEmpty() == true) {
            val html = htmlData[imageUrl?.get(position) ?: ""]
            if (!html.isNullOrEmpty()) {
                //跳转html
                val intent = Intent(mActivity, WebViewActivity::class.java)
                intent.putExtra("htmlUrl", html)
                startActivity(intent)
            }
        }

    }

    override fun onConfirm(type: Int) {

    }

    override fun onConfirm(type: String?) {
        //开始升级
        if (type != null) {
            if (simpleDialog?.isShowing == true) {
                simpleDialog?.dismiss()
                simpleDialog = null
            }
            startToDown(type)
        }
    }

    override fun onCancel() {

    }

    private fun startToDown(url: String) {
        val intent = Intent(UIUtil.geContext(), DownLoadService::class.java)
        intent.putExtra("DOWNLOAD_URL", url)
        mActivity.startService(intent)
    }


    override fun syncAPPVersionInfoResult(versionCode: Int, url: String) {

        val localVersionCode = UIUtil.geContext().packageManager.getPackageInfo(UIUtil.getPackageName(), 0).versionCode
        if (versionCode > localVersionCode) {
            //升级
            simpleDialog = SimpleDialog(mActivity, R.style.circle_dialog)
            simpleDialog?.showUpdateDialog(url, this)
        }
    }


    override fun getAdDataResult(data: List<String>, html: Map<String, String>) {
        if (data.isNotEmpty()) {
            this.imageUrl = data
            this.htmlData = html
            //有广告数据
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                    .setIndicatorGravity(BannerConfig.CENTER)
                    .setImageLoader(GlideImageLoader())
                    .setImages(data)
                    .setDelayTime(5 * 1000)
                    .start()

        } else {
            //没有广告数据
        }
    }

    override fun onSupportInvisible() {
        //用户不可见
        banner.stopAutoPlay()
    }

    override fun onSupportVisible() {
        //用户可见
        banner.startAutoPlay()
    }

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
        bundle.putInt("type", i)
        val investFragment = InvestFragment()
        investFragment.arguments = bundle
        start(investFragment)
    }


    private var simpleDialog: SimpleDialog? = null
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
        showProgress(1)
        getPresenter().syncAPPVersionInfo()
        getPresenter().getAdData((Prefs.getInt(Constant.MERCHANT_ID, 0)).toString())
        banner.setOnBannerListener(this)
    }

    private fun initView() {
        tv_name.text = Prefs.getString(Constant.AGENT_NAME, "")
        tv_phone.text = Prefs.getString(Constant.PHONE, "")

    }

    override fun onDestroy() {
        banner.stopAutoPlay()
        banner.releaseBanner()
        super.onDestroy()
    }

}