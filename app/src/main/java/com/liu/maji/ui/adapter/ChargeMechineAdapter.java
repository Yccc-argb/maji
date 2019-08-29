package com.liu.maji.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liu.maji.modle.bean.vip.ChargeMechineResponse;

import java.util.List;

import cn.com.liu.maji.R;

public class ChargeMechineAdapter extends BaseQuickAdapter<ChargeMechineResponse.DataBean,BaseViewHolder> {
    public ChargeMechineAdapter(int layoutResId, @Nullable List<ChargeMechineResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChargeMechineResponse.DataBean item) {
//        helper.setText(R.id.tv_charge_machine_name,item.getEquipTypeName().replaceAll("值","点"));
        helper.setText(R.id.tv_charge_machine_name,item.getName());
        helper.setText(R.id.tv_charge_num,item.getCd());
    }
}
