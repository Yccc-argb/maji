package com.liu.maji.ui.invest

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.liu.maji.R
import com.liu.maji.base.MySupportFragment
import com.liu.maji.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_invest.*
import kotlinx.android.synthetic.main.title.*

class InvestFragment : MySupportFragment<InvestView,InvestPresenter>(), View.OnClickListener, TextWatcher {
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
       if (et_remarks.text.toString().trim().length > 100){
           ToastUtils.showToast("最多输入100个字")
           return
       }
    }


    var type = 0


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back
                    ->_mActivity.onBackPressed()
            R . id . rl_btn_sure
            -> upLoadOptions()

        }
    }



    private fun upLoadOptions() {
        val name = et_name.text.toString().trim()
        if (TextUtils.isEmpty(name)){
            ToastUtils.showToast("请输入姓名")
            return
        }

        val phone = et_phone.text.toString().trim()

        if (TextUtils.isEmpty(phone) || phone.length != 11){
            ToastUtils.showToast("请输入正确的手机号")
            return
        }

//        showProgress(1)
        ToastUtils.showToast("提交服务器")

    }


    override fun createPresenter(): InvestPresenter {
        return InvestPresenter()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_invest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        type = arguments!!.getInt("type")
        tv_add.visibility = View.INVISIBLE
        if (type == 1){
            //投资加盟
            tv_title.text = "投资加盟"
        }else {
           //意见反馈
            tv_title.text = "意见反馈"
        }
        iv_back.setOnClickListener(this)
        rl_btn_sure.setOnClickListener(this)
        et_remarks.addTextChangedListener(this)
    }
}