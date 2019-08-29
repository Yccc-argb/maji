package com.liu.maji.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liu.maji.modle.bean.device.ChangeConsumeResponse;

import java.util.List;

import cn.com.liu.maji.R;

public class ChangeConsumeAdapter extends BaseQuickAdapter<ChangeConsumeResponse.DataBean,BaseViewHolder> {

    public ChangeConsumeAdapter(int layoutResId, @Nullable List<ChangeConsumeResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChangeConsumeResponse.DataBean item) {



        helper.setVisible(R.id.tv_charge_machine_name,false);
        ((TextView)helper.getView(R.id.tv_charge_num)).setTextSize(18);
        helper.setTextColor(R.id.tv_charge_num,"局".equals(item.getProductUnit())?Color.parseColor("#A4BBDA")
        :Color.parseColor("#DAA4A4"));
        helper.setText(R.id.tv_charge_num,item.getName());
    }
}
