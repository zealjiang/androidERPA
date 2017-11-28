package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarStyle;
import com.jzg.erp.http.CommonServer;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.NetworkExceptionUtils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.view.ICommon;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 车型
 * @author zealjiang
 * @time 2016/8/4 10:09
 */
public class CarStylePresenter extends BasePresenter<ICommon> {
    public CarStylePresenter(ICommon from) {
        super(from);
    }

    /**
     * 提交的参数
     */
    private Map<String, String> getParams(String modelid,String InSale) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("op", "GetStyle");
        params.put("ModelId", modelid);
        params.put("InSale", InSale);

        Map<String, Object> md = new HashMap<String, Object>();
        md.put("op", "GetStyle");
        md.put("ModelId", modelid);
        md.put("InSale", InSale);
        String PRIVATE_KEY = "2CB3147B-D93C-964B-47AE-EEE448C84E3C".toLowerCase();
        params.put("sign", MD5Utils.getMD5Sign(md,PRIVATE_KEY));
        return params;
    }

    public void request(String modelid,String InSale){

        Map<String, String> params = getParams(modelid,InSale);
        LogUtil.e(TAG, CommonServer.BASE_URL+ UIUtils.getUrl(params));
        baseView.showDialog();
        ((Observable<CarStyle>) JzgApp.getAppContext().initOtherServer("http://ptvapi.guchewang.com").
                getCarStyle(params)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CarStyle>() {
            @Override
            public void onCompleted() {
                baseView.dismissDialog();
            }

            @Override
            public void onError(Throwable e) {
                baseView.dismissDialog();
                String error =  NetworkExceptionUtils.getErrorByException(e);
                baseView.showError(error);

            }

            @Override
            public void onNext(CarStyle carStyle) {
                boolean success = carStyle.isSuccess();
                if(success){
                    baseView.succeed(carStyle);
                }else{
                    baseView.showError(carStyle.getMsg());
                }
            }
        });
    }
}
