package com.liu.maji.ui.adapter;

import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liu.maji.modle.bean.device.DeviceInfoResponse;
import com.liu.maji.utils.TimeCalculateTools;
import com.liu.maji.utils.UIUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.com.liu.maji.R;


public class DeviceAdapter extends BaseQuickAdapter<DeviceInfoResponse.DataBean.RecordsBean,BaseViewHolder> {

    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownCounters ;
    SimpleDateFormat simpleDateFormat;
    private String TAG = "DeviceAdapter";

    public DeviceAdapter(int layoutResId, @Nullable List<DeviceInfoResponse.DataBean.RecordsBean> data) {
        super(layoutResId, data);
        this.countDownCounters = new SparseArray<>();
        this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    protected void convert(final BaseViewHolder helper, DeviceInfoResponse.DataBean.RecordsBean item) {
        helper.addOnClickListener(R.id.tv_change_consume);
        helper.addOnClickListener(R.id.tv_time);
        helper.addOnLongClickListener(R.id.tv_device_number);
        helper.setText(R.id.tv_store_number,"门店编号: "+String.valueOf(item.getAgentId()));
        helper.setText(R.id.tv_device_number,"设备名称: "+String.valueOf(item.getName()));
        helper.setText(R.id.tv_hardware_number,"硬件编号: "+String.valueOf(item.getCd()));
        helper.setText(R.id.tv_device_type,"设备类型: "+("mj_recharge".equals(item.getBaseType())?"充点机":"刷卡盘"));
        helper.setText(R.id.tv_mechine_status,item.isIsOnline()?"运行中 | ":"离线");
        //
        helper.setVisible(R.id.tv_time,"mj_recharge".equals(item.getBaseType())?false:true);
        if (item.isIsOnline()){
            //在线
            helper.setVisible(R.id.iv_signal,true);
            helper.setVisible(R.id.tv_signal,true);
            Glide.with(UIUtil.geContext()).load(item.getSigIntensityPath()).into((ImageView) helper.getView(R.id.iv_signal));
            helper.setText(R.id.tv_signal," | "+item.getSig());
        }else{
            //离线
            helper.setVisible(R.id.iv_signal,false);
            helper.setVisible(R.id.tv_signal,false);
        }

        if (item.getTaocanName() != null && !item.getTaocanName().isEmpty()){

            helper.setText(R.id.tv_consume_type,"局".equals(item.getProductUnit())?
            "计局/" + item.getTaocanName().replaceAll("元","点"):
                    "计时/" + item.getTaocanName().replaceAll("元","点"));
        }else {
            helper.setVisible(R.id.tv_consume_type,false);
        }
        if (!"局".equals(item.getProductUnit())){
            helper.setVisible(R.id.tv_time,true);

            //倒计时
            CountDownTimer countDownTimer = countDownCounters.get((helper.getView(R.id.tv_time)).hashCode());

            if (countDownTimer != null){
                countDownTimer.cancel();
            }

            long remainTime = item.getTimingDeadLine() - System.currentTimeMillis();//获取剩余时间
            //剩余时间大于0并且设备在线
            if (remainTime > 0 && item.isIsOnline()){
                countDownTimer = new CountDownTimer(remainTime, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
//                    Log.i(TAG, "millisUntilFinished:" + millisUntilFinished);
//                    Date date = new Date(millisUntilFinished);
                        helper.setText(R.id.tv_time,TimeCalculateTools.getCountTimeByLong(millisUntilFinished));
//                    Log.i(TAG, "millisUntilFinished:" + simpleDateFormat.format(date));
                    }

                    @Override
                    public void onFinish() {
                        //倒计时结束
                        helper.setText(R.id.tv_time,"00:00:00");
                    }
                }.start();

                countDownCounters.put((helper.getView(R.id.tv_time)).hashCode(),countDownTimer);
            }else {
                helper.setText(R.id.tv_time,"00:00:00");
            }

        }else {
            helper.setVisible(R.id.tv_time,false);
        }

    }

    /**
     * 清空当前 CountTimeDown 资源
     */
    public void cancelAllTimers(){
        if (countDownCounters == null) {
            return;
        }
        for (int i = 0, length = countDownCounters.size(); i < length; i++) {
            CountDownTimer cdt = countDownCounters.get(countDownCounters.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }
}
