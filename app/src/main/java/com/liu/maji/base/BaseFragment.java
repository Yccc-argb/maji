package com.liu.maji.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.kaopiz.kprogresshud.KProgressHUD;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.com.liu.maji.R;


/**
 * Created by admin on 2017/2/9.
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>>
        extends MvpFragment<V, P> implements BaseView, BGARefreshLayout.BGARefreshLayoutDelegate {

    protected BaseActivity mActivity;
    private KProgressHUD kProgressHUD;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        FragmentCollector.addFragment(this);
    }

    @Override
    public void showProgress(int ProgressType) {
        if (kProgressHUD == null) {
            kProgressHUD = KProgressHUD.create(mActivity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setWindowColor(getResources()
                    .getColor(R.color.icon_select_color)).setAnimationSpeed(2);
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
//        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
//        StyleableToast styleableToast = new StyleableToast(mActivity, message, Toast.LENGTH_SHORT);
//        styleableToast.setCornerRadius(99);
//        styleableToast.show();
    }

    @Override
    public void startActivity(Class<?> tClass) {
        startActivity(new Intent(mActivity, tClass));
    }

    @Override
    public void finish() {
        mActivity.finish();
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(this.getClass().getSimpleName()); //统计页面，"MainScreen"为页面名称，可自定义
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }

    public void back(View view) {
        mActivity.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
        FragmentCollector.removeFragment(this);
    }

    protected void initBGRefreshLayout(BGARefreshLayout mRefreshLayout){
        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
//        val refreshViewHolder = MyBGARefreshViewHolder(mActivity, true)
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, true);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);


        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时不显示加载更多控件
//        mRefreshLayout.setIsShowLoadingMoreView(false)
        // 设置正在加载更多时的文本
        refreshViewHolder.setLoadingMoreText("加载中...");
        // 设置整个加载更多控件的背景颜色资源 id
//        refreshViewHolder.setLoadMoreBackgroundColorRes(loadMoreBackgroundColorRes)
//        // 设置整个加载更多控件的背景 drawable 资源 id
//        refreshViewHolder.setLoadMoreBackgroundDrawableRes(loadMoreBackgroundDrawableRes)
//        // 设置下拉刷新控件的背景颜色资源 id
//        refreshViewHolder.setRefreshViewBackgroundColorRes(refreshViewBackgroundColorRes)
//        // 设置下拉刷新控件的背景 drawable 资源 id
//        refreshViewHolder.setRefreshViewBackgroundDrawableRes(refreshViewBackgroundDrawableRes)
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
