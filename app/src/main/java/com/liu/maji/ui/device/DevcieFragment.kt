package com.liu.maji.ui.device

import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.bingoogolapple.refreshlayout.BGARefreshLayout

import cn.com.liu.maji.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liu.maji.app.Constant
import com.liu.maji.base.MySupportFragment
import com.liu.maji.modle.bean.device.ChangeConsumeResponse
import com.liu.maji.modle.bean.device.ChangeConsumeTypeResultResponse
import com.liu.maji.modle.bean.device.DeviceInfoResponse
import com.liu.maji.ui.adapter.ChangeConsumeAdapter
import com.liu.maji.ui.adapter.DeviceAdapter
import com.liu.maji.utils.Prefs
import com.liu.maji.utils.ToastUtils
import com.liu.maji.wedigt.SimpleDialog


import kotlinx.android.synthetic.main.fragment_device.*
import kotlinx.android.synthetic.main.title.*


class DevcieFragment : MySupportFragment<DeviceView, DevicePresenter>(), BGARefreshLayout.BGARefreshLayoutDelegate, View.OnClickListener, DeviceView, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener, SimpleDialog.DialogClickListener {


    override fun onCancel() {
        if (dialog != null) {
            dialog!!.dismiss()
            dialog = null
        }
    }

    override fun onConfirm(type: Int) {
        if (dialog != null) {
            dialog!!.dismiss()
            dialog = null
        }

        showProgress(1)
        getPresenter().stopCurrentSwipe(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                deviceId, "OFF", "0", consumNumber.toString(), equipId.toString())
//        toast("结束当前对局")
    }


    private var currentPage: Int = 1
    private val pageCounts: Int = 10
    private var devcieList: MutableList<DeviceInfoResponse.DataBean.RecordsBean>? = ArrayList()
    private var deviceAdapter: DeviceAdapter? = null
    private var refreshLayout: BGARefreshLayout? = null
    private var type: Int? = 0
    private var dialog: SimpleDialog? = null


    private var chargeConsumeAdapter: ChangeConsumeAdapter? = null

    private var bottomSheetDialog: BottomSheetDialog? = null

    private var consumeTypeData: MutableList<ChangeConsumeResponse.DataBean>? = null

    private var previousId: Int = 0
    private var equipId: Int = 0
    private var deviceId: String = ""
    private var consumNumber: Int = 0


    override fun changeConsumeTypeResult(t: ChangeConsumeTypeResultResponse) {
        if (t.isSuccess) {
//            ToastUtils.showToast(t.message)
            ToastUtils.showToast("修改成功")
            //刷新界面
            type = 0
//            this.refreshLayout = refreshLayout
            println("回掉下拉刷新")
            showProgress(1)
            devcieList?.clear()
            this.currentPage = 1
            getPresenter().getDevices(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                    currentPage, pageCounts, (currentPage - 1) * 10 + 1, currentPage * 10)
        } else {
            ToastUtils.showToast(t.message)
        }
    }

    override fun getConsumeTypeResult(changeConsumeResponse: ChangeConsumeResponse) {
        //查询消费套餐
        consumeTypeData = changeConsumeResponse?.data

        //消费类型弹窗
        showChangeConsumeType()
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        if (view?.id == R.id.tv_change_consume) {
            val item = deviceAdapter?.getItem(position)
            if (item != null && !item.isIsOnline) {
                ToastUtils.showToast("设备离线")
                return
            }

            if (item!!.timingDeadLine - System.currentTimeMillis() > 0) {
                ToastUtils.showToast("当前对局未结束,不支持修改")
                return
            }


            previousId = item?.taoCanId!!    //原套餐id
            equipId = item.id                //设备id
            if (consumeTypeData == null) {
                showProgress(1)
                getPresenter().getConsumeType(Prefs.getInt(Constant.MERCHANT_ID, 0))
                return
            }

            if (consumeTypeData!!.isEmpty()) {
                ToastUtils.showToast("未查询到计费套餐")
                return
            }

            //ConsumeType非未null并且有数据的时候
            showChangeConsumeType()
        } else {
            //结束对局
            val item = deviceAdapter?.getItem(position)
            if (item!!.timingDeadLine - System.currentTimeMillis() > 0 && item.isIsOnline) {
                deviceId = item?.cd    //设备号
                consumNumber = item?.productNumber
                equipId = item?.id
                dialog = SimpleDialog(mActivity, R.style.circle_dialog)
                dialog?.showCommonDialog(2, "确定结束当前对局", this)
            }


        }

    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
//        ToastUtils.showToast("修改计费方式了")
        bottomSheetDialog?.dismiss()

        dialog = SimpleDialog(mActivity, R.style.circle_dialog)
        dialog?.showCommonDialog(1, "确定更改当前刷卡盘配置", object : SimpleDialog.DialogClickListener {
            override fun onConfirm(type: Int) {
                dialog?.dismiss()
                dialog = null
                val item = chargeConsumeAdapter?.getItem(position)
                val id = item?.id!!  //新套餐id
                showProgress(1)
                getPresenter().changeConsumeType(previousId, id, equipId)
            }

            override fun onCancel() {
                dialog?.dismiss()
                dialog = null
            }
        })



    }

    override fun createPresenter(): DevicePresenter {
        return DevicePresenter()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_device, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        tv_title.text = "我的设备"
        tv_add.visibility = View.INVISIBLE
        iv_back.setOnClickListener(this)
        initBGRefreshLayout(mRefreshLayout)
        initDeviceData()
    }


    private fun initDeviceData() {
        showProgress(1)
        getPresenter().getDevices(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                currentPage, pageCounts, (currentPage - 1) * 10, currentPage * 10)

//        val dataList = ArrayList<String>()
//        for (i in 1..10) {
//            println(i)
//            dataList.add("" + i)
//        }
    }


    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {

        //上拉加载更多
        type = 1
        this.refreshLayout = refreshLayout
        println("回调上拉加载更多")
        showProgress(1)
        getPresenter().getDevices(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                currentPage, pageCounts, (currentPage - 1) * 10 + 1, currentPage * 10)
        return true
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        //下拉刷新
        type = 0
        this.refreshLayout = refreshLayout
        println("回掉下拉刷新")
        showProgress(1)
        devcieList?.clear()
        this.currentPage = 1
        getPresenter().getDevices(Prefs.getInt(Constant.AGENT_ID, 0), Prefs.getInt(Constant.MERCHANT_ID, 0),
                currentPage, pageCounts, (currentPage - 1) * 10 + 1, currentPage * 10)


    }


    override fun getDevicesResult(deviceInfoBean: DeviceInfoResponse) {
        //查询设备信息接口
        if (type == 0) {
            refreshLayout?.endRefreshing()
        } else {
            refreshLayout?.endLoadingMore()
        }
        val records = deviceInfoBean.data.records
        if (records.isEmpty()) {
            ToastUtils.showToast("没有更多数据了")
        } else {
            devcieList?.addAll(records)
            currentPage = deviceInfoBean.data.page + 1  //当前页数
            val totalNum = deviceInfoBean.totalNum   //总设备
            val onlineNum = deviceInfoBean.onlineNum
            val offlineNum = deviceInfoBean.offlineNum
            tv_total_device.text = Html.fromHtml("<big><big>$totalNum</big></big>台<br/>激活总设备")
            tv_online_device.text = Html.fromHtml("<big><big>$onlineNum</big></big>台<br/>在线")
            tv_offline_device.text = Html.fromHtml("<big><big>$offlineNum</big></big>台<br/>离线")
            if (deviceAdapter == null) {
                deviceAdapter = DeviceAdapter(R.layout.item_device, devcieList)
                val linearLayoutManager = LinearLayoutManager(_mActivity)
                rlv_device.layoutManager = linearLayoutManager
                rlv_device.adapter = deviceAdapter
                deviceAdapter?.setOnItemChildClickListener(this)

            } else {
                deviceAdapter?.setNewData(devcieList)


            }
        }

        hideProgress()

    }

    private fun showChangeConsumeType() {

        if (consumeTypeData != null && (!consumeTypeData!!.isEmpty())) {
            bottomSheetDialog = BottomSheetDialog(_mActivity)
            bottomSheetDialog?.setContentView(R.layout.dialog_select_charge_mechine)
            bottomSheetDialog?.show()
            var linearLayoutManager = LinearLayoutManager(_mActivity)
            chargeConsumeAdapter = ChangeConsumeAdapter(R.layout.item_charge_machine, consumeTypeData)
            val rlvChargeMachine = bottomSheetDialog?.findViewById<RecyclerView>(R.id.rlv_charge_machine)
            val title = bottomSheetDialog?.findViewById<TextView>(R.id.tv_bottom_title)
            title?.text = "请选择计费方式"
            rlvChargeMachine?.layoutManager = linearLayoutManager
            rlvChargeMachine?.adapter = chargeConsumeAdapter
            chargeConsumeAdapter?.onItemClickListener = this
        } else {
            ToastUtils.showToast("暂未查询到消费套餐信息")
        }


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.iv_back
            -> _mActivity.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (deviceAdapter != null) {
            deviceAdapter?.cancelAllTimers()
            deviceAdapter = null
        }
    }

}