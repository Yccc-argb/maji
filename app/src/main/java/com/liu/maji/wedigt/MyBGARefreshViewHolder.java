package com.liu.maji.wedigt;

import android.content.Context;
import android.view.View;


import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

public class MyBGARefreshViewHolder extends BGARefreshViewHolder {
    /**
     * @param context
     * @param isLoadingMoreEnabled 上拉加载更多是否可用
     */
    public MyBGARefreshViewHolder(Context context, boolean isLoadingMoreEnabled) {
        super(context, isLoadingMoreEnabled);
    }

    @Override
    public View getRefreshHeaderView() {
        return null;
    }

    @Override
    public void handleScale(float scale, int moveYDistance) {

    }

    @Override
    public void changeToIdle() {

    }

    @Override
    public void changeToPullDown() {

    }

    @Override
    public void changeToReleaseRefresh() {

    }

    @Override
    public void changeToRefreshing() {

    }

    @Override
    public void onEndRefreshing() {

    }
}
