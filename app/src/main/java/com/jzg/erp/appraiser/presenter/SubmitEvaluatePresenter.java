package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.view.ISubmitView;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.NetworkExceptionUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/26 21:46
 * @desc:
 */
public class SubmitEvaluatePresenter extends BasePresenter<ISubmitView> {
    public SubmitEvaluatePresenter(ISubmitView from) {
        super(from);
    }

    /***
     *op=
     */
    public void submitEvaluate(String json){
        baseView.showDialog();
        Map<String,String> params = new HashMap<>();
        params.put("op","add");
        params.put("jsonCar",json);
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));

        JzgApp.getApiServer().submitEvaluate(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<com.jzg.erp.base.BaseObject>() {
            @Override
            public void onCompleted() {
                baseView.dismissDialog();
            }
            @Override
            public void onError(Throwable e) {
                baseView.dismissDialog();
                String info = NetworkExceptionUtils.getErrorByException(e);
                LogUtil.e(TAG,"error info:"+info);
                baseView.showError(info);
            }
            @Override
            public void onNext(com.jzg.erp.base.BaseObject data) {
                int status = data.getStatus();
                if(status==1){
                    baseView.succeed();
                }else if(status>=-5&&status<=-1){
                    baseView.unusual(data);
                }else{
                    onError(new RuntimeException(data.getMessage()));
                }
            }
        });
    }
}
