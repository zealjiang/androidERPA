package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarCondOptionsModel;
import com.jzg.erp.global.Constant;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.view.ICommon;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 获取所有车况检测的选项数据
 * @author zealjiang
 * @time 2016/8/25 17:04
 */
public class CarCondOptionsPresenter extends BasePresenter<ICommon> {
    public CarCondOptionsPresenter(ICommon from) {
        super(from);
    }


    /**
     */
    public void getCarCondOptions() {
        Map<String, String> params = new HashMap<>();
        params.put("op","getcarcheck");
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        baseView.showDialog();
        JzgApp.getApiServer().getCarCondOptions(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<CarCondOptionsModel>() {
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
                    public void onNext(CarCondOptionsModel data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeed(data);
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }
}
