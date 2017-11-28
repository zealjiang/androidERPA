package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarCondInfoShowModel;
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
 * 根据车辆列表中的 CarInfoId 来获取车况： ( 车况 )
 * @author zealjiang
 * @time 2016/8/24 16:12
 */
public class CarCondInfoShowPresenter extends BasePresenter<ICommon> {
    public CarCondInfoShowPresenter(ICommon from) {
        super(from);
    }

    private Subscription mSubscription;


    /**
     * 车辆ID
     * @param id
     */
    public void getCarConditionInfo(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("op","PGCarCase");
        params.put("pingguId", id);
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getCarConditionInfo(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<CarCondInfoShowModel>() {
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
                    public void onNext(CarCondInfoShowModel data) {
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
