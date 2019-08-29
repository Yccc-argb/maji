package com.liu.maji.wedigt;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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


    public interface DialogClickListener {
        //确认
        void onConfirm(int type);

        //取消
        void onCancel();
    }

}
