package com.liu.maji.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liu.maji.modle.bean.vip.VipInfoResponse;

import java.security.KeyStore;
import java.util.List;

import cn.com.liu.maji.R;

public class VipInfoAdapter extends BaseQuickAdapter<VipInfoResponse.DataBean.MajiangVOListBean,BaseViewHolder> {
    public VipInfoAdapter(int layoutResId, @Nullable List<VipInfoResponse.DataBean.MajiangVOListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VipInfoResponse.DataBean.MajiangVOListBean item) {
        if (item.getName() == null){
            helper.setText(R.id.tv_vip_name,"游客"+item.getId());
        }else {
            helper.setText(R.id.tv_vip_name,item.getName().startsWith("游客")?item.getName()+item.getId():item.getName());
        }

        if (TextUtils.isEmpty(item.getMobile())){
            //手机号为空
            helper.setGone(R.id.tv_vip_phone,false);
        }else {
            helper.setGone(R.id.tv_vip_phone,true);
            helper.setText(R.id.tv_vip_phone,"手机号:"+item.getMobile());
        }

        helper.setText(R.id.tv_cd,"编号: "+item.getCd());
//        helper.setText(R.id.tv_total_consume,"共消费: "+"100" + "元");
        helper.setText(R.id.tv_yue,"当前点数: "+item.getAmount());

    }
}
