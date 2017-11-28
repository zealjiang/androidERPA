package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.ProcedureInfoModel;
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
 * 手续信息
 * @author zealjiang
 * @time 2016/8/22 10:51
 */
public class ProcedureInfoPresenter extends BasePresenter<ICommon> {
    public ProcedureInfoPresenter(ICommon from) {
        super(from);
    }

    private Subscription mSubscription;

    public void getProcedureInfoList(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("op","ProcedureInfo");
        params.put("id", id);
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));

        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getProcedureInfo(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<ProcedureInfoModel>() {
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
                    public void onNext(ProcedureInfoModel data) {
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
