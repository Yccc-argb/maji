package com.liu.maji.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liu.maji.modle.bean.charge_history.ChargeHistoryResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.com.liu.maji.R;

public class ChargeAdapter extends BaseQuickAdapter<ChargeHistoryResponse.DataBean.RecordsBean,BaseViewHolder> {
    public ChargeAdapter(int layoutResId, @Nullable List<ChargeHistoryResponse.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChargeHistoryResponse.DataBean.RecordsBean item) {
        helper.setText(R.id.tv_charge_money,"充点 "+item.getOperateAmount());
        if (item.getMobile() != null && !item.getMobile().isEmpty()){
            helper.setVisible(R.id.tv_phone_number,true);
            helper.setText(R.id.tv_phone_number,"手机号: "+item.getMobile());
        }else {
            helper.setGone(R.id.tv_phone_number,false);
        }

        helper.setText(R.id.tv_vip_card_number,"会员卡号: " + item.getCardCd());
        Date date = new Date(item.getUpdateDate());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        helper.setText(R.id.tv_charge_time,"会员卡号: " + item.getCardCd());
        helper.setText(R.id.tv_charge_time,"充点时间: " + format);
        helper.setText(R.id.tv_add_money,"+ " + item.getOperateAmount());

    }
}
