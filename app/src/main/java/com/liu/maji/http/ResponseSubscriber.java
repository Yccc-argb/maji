package com.liu.maji.http;



import com.liu.maji.base.ActivityCollector;
import com.liu.maji.base.FragmentCollector;
import com.liu.maji.modle.CommonResponse;
import com.liu.maji.utils.ToastUtils;

import rx.Subscriber;



public abstract class ResponseSubscriber<T extends CommonResponse> extends Subscriber<T> {

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {

            ToastUtils.showToast("请求出错");
            if (ActivityCollector.getTopActivity()!=null){
                ActivityCollector.getTopActivity().hideProgress();
            }
         ActivityCollector.getTopActivity().hideProgress();
        FragmentCollector.hideProgress();


    }

    @Override
    public void onNext(T t) {
//        ToastUtils.showToast("请求成功");
        if(ActivityCollector.getTopActivity()!=null) {
            ActivityCollector.getTopActivity().hideProgress();
        }
        FragmentCollector.hideProgress();

        if (t.isSuccess()) {
            onRealSuccess(t);
        }else{

            ToastUtils.showToast(t.getMessage());
        }
    }

    protected abstract void onRealSuccess(T t);

    protected void onOtherError(T t){
//        if(t.getMessage().equals("参数错误")){
//            return;
//        }
//        ToastUtils.showToast(t.getMessage());
    }


}
