package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.ConfigInfoModel;
import com.jzg.erp.global.Constant;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.view.ICommon;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 配置信息
 * @author zealjiang
 * @time 2016/8/22 11:57
 */
public class ConfigInfoPresenter extends BasePresenter<ICommon> {
    public ConfigInfoPresenter(ICommon from) {
        super(from);
    }

    private Subscription mSubscription;

    public void getConfigInfoList(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("op","CarConfigInfo");
        params.put("id", id);
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));

        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getConfigInfo(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<ConfigInfoModel>() {
                    @Override
                    public void onCompleted() {
                        baseView.dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        baseView.dismissDialog();
                        if (e != null && baseView != null) {
                            baseView.showError(Constant.ERROR_FORNET);
                        }
                    }

                    @Override
                    public void onNext(ConfigInfoModel data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeed(data.getData());
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }
}
