package com.liu.maji.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.liu.maji.wedigt.MyBGARefreshViewHolder;
import com.liu.maji.wedigt.SimpleDialog;

import butterknife.ButterKnife;
import cn.com.liu.maji.R;

/**
 * 基本的activity
 * Created by teikasei on 2017/1/6.
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>>
        extends MvpActivity<V, P> implements BaseView {

    protected BaseActivity mActivity;
    protected KProgressHUD kProgressHUD;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                Window window = getWindow();
//                WindowManager.LayoutParams attributes = window.getAttributes();
//                attributes.flags |= flagTranslucentNavigation;
//                window.setAttributes(attributes);
//                getWindow().setStatusBarColor(Color.TRANSPARENT);
//            } else {
//                Window window = getWindow();
//                WindowManager.LayoutParams attributes = window.getAttributes();
//                attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
//                window.setAttributes(attributes);
//            }
//        }

//        View titleView = android.view.View.inflate(this, R.layout.title_bar, null);
//        ButterKnife.bind(this);
        mActivity = this;
        ActivityCollector.addActivity(mActivity);
        setupToolbar();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

        ActivityCollector.removeActivity(mActivity);
        if (kProgressHUD != null) {
            kProgressHUD.dismiss();
        }

    }

    /**
     * 获取Activity的布局id
     *
     * @return
     */
    protected abstract int getContentViewResId();

    /**
     * 初始化toolbar
     */
    protected void setupToolbar() {
    }

    /**
     * 初始化
     */
    protected abstract void init();

    @Override
    public void showProgress(int ProgressType) {
        if (kProgressHUD == null) {
            kProgressHUD = KProgressHUD.create(mActivity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setWindowColor(getResources()
                    .getColor(
                            R.color.icon_select_color)).setAnimationSpeed(1);
        }
        kProgressHUD.show();

    }

    @Override
    public void showProgress(int ProgressType, String content, Boolean canceledOnTouchOutside) {

    }

    @Override
    public void hideProgress() {
        if (kProgressHUD != null) {
            if (kProgressHUD.isShowing()) {
                kProgressHUD.dismiss();
            }
        }
    }

    public void toast(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showShortToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//        StyleableToast styleableToast = new StyleableToast(mActivity, message, Toast.LENGTH_SHORT);
//        styleableToast.setCornerRadius(99);
//        styleableToast.show();
    }

    public void startActivity(Class<?> tClass) {
        startActivity(new Intent(mActivity, tClass));
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    public void back(View view) {
        mActivity.finish();
    }




}
