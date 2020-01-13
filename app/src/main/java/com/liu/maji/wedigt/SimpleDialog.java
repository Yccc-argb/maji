package com.liu.maji.wedigt;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liu.maji.utils.ToastUtils;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import cn.com.liu.maji.R;

public class SimpleDialog extends Dialog {


    public SimpleDialog(Context context) {
        super(context);
    }

    public SimpleDialog(Context context, int themeResId) {
        super(context, themeResId);

    }

    protected SimpleDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    public void showCommonDialog(final int type, String notice, final DialogClickListener dialogClickListener) {
        View inflate = this.getLayoutInflater().inflate(R.layout.dialog_common, null);
        TextView tv_cancel = inflate.findViewById(R.id.tv_cancel);
        TextView tv_sure = inflate.findViewById(R.id.tv_sure);
        TextView tv_notice = inflate.findViewById(R.id.tv_notice);
        tv_notice.setText(notice);
//        if (type == 1){
//            tv_sure.setTextColor(Color.RED);
//            tv_notice.setTextColor(Color.RED);
//        }
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogClickListener != null)
                    dialogClickListener.onCancel();
            }
        });

        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogClickListener != null)
                    dialogClickListener.onConfirm(type);
            }
        });
        this.setContentView(inflate);
        this.show();
    }

    public void showChangePasswordDialog(final DialogClickListener dialogClickListene) {
        View inflate = this.getLayoutInflater().inflate(R.layout.dialog_change_password, null);
//        final TextView et_password = inflate.findViewById(R.id.tv_password);
        TextView tv_cancel = inflate.findViewById(R.id.tv_cancel);
        TextView tv_sure = inflate.findViewById(R.id.tv_sure);


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogClickListene != null)
                    dialogClickListene.onCancel();
            }
        });

        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogClickListene != null){
//                    String phone = et_password.getText().toString().trim();
                    dialogClickListene.onConfirm("");
                }

            }
        });
        this.setContentView(inflate);
        this.show();
    }


    /**
     * 升级弹窗
     * @param url
     * @param dialogClickListener
     */
    public void showUpdateDialog(final String url,final DialogClickListener dialogClickListener){
        View inflate = this.getLayoutInflater().inflate(R.layout.dialog_update, null);
        Button btSure = inflate.findViewById(R.id.bt_sure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogClickListener != null)
                    dialogClickListener.onConfirm(url);
            }
        });
        this.setCancelable(false);
        this.setContentView(inflate);
        this.show();
    }

    public void showChangeDeviceNameDialog(final DialogClickListener dialogClickListener){
        View inflate = this.getLayoutInflater().inflate(R.layout.dialog_change_device_name, null);
        final EditText et_new_device_name = inflate.findViewById(R.id.et_new_device_name);
        TextView btSure = inflate.findViewById(R.id.tv_sure);
        TextView btnCancel = inflate.findViewById(R.id.tv_cancel);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogClickListener != null){
                    String trim = et_new_device_name.getText().toString().trim();
                    if (TextUtils.isEmpty(trim)){
                        ToastUtils.showToast("设备号不能为空");
                        return;
                    }
                    dialogClickListener.onConfirm(trim);
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogClickListener != null)
                    dialogClickListener.onCancel();
            }
        });
        this.setContentView(inflate);
        this.show();
    }


    public interface DialogClickListener {
        //确认
        void onConfirm(int type);

        void onConfirm(String type);

        //取消
        void onCancel();
    }

}
