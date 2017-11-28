package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarInfoModel;
import com.jzg.erp.global.Constant;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.view.ICommon;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 通过车型查询车辆配置
 * @author zealjiang
 * @time 2016/8/26 15:59
 */
public class GetCarConfigByStyleIdPresenter extends BasePresenter<ICommon> {
    public GetCarConfigByStyleIdPresenter(ICommon from) {
        super(from);
    }

    private Subscription mSubscription;


    /**
     *
     * @param styleID
     */
    public void getCarCiByStyleId(String styleID) {
        Map<String, String> params = new HashMap<>();
        params.put("op","GetCarCiByStyleId");
        params.put("styleID", styleID);

        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG, UIUtils.getUrl(params));
        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getConfigInfoBySeries(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<CarInfoModel>() {
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
                    public void onNext(CarInfoModel data) {
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
