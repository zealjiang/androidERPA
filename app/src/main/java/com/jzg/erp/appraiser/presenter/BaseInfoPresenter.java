package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.BaseInfoModel;
import com.jzg.erp.global.Constant;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.view.ICommon;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 配置信息
 * @author zealjiang
 * @time 2016/8/22 11:57
 */
public class BaseInfoPresenter extends BasePresenter<ICommon> {
    public BaseInfoPresenter(ICommon from) {
        super(from);
    }


    public void getBaseInfoList(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("op","CarbaseInfo");
        params.put("id", id);
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG, UIUtils.getUrl(params));

        baseView.showDialog();
        JzgApp.getApiServer().getBaseInfo(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<BaseInfoModel>() {
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
                    public void onNext(BaseInfoModel data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeed(data.getData().get(0));
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }
}
