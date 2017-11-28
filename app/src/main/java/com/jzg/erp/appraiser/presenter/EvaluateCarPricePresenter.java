package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarEvaluateInfo;
import com.jzg.erp.appraiser.view.IEvaluatePrice;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.NetworkExceptionUtils;
import com.jzg.erp.utils.UIUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/23 20:44
 * @desc:
 */
public class EvaluateCarPricePresenter extends BasePresenter<IEvaluatePrice> {
    public EvaluateCarPricePresenter(IEvaluatePrice from) {
        super(from);
    }


    /***
     userid ( 非必填，预留 )
     * @param styleid 111887 ( 必填 )
     * @param cityId ( 必填，默认为201，北京 )
     * @param mileage ( 单位：万。必填 )
     * @param registerDate 2013/03/01 ( 必填 )
     */
    public void evaluateCarPrice(String styleid,String cityId,String mileage,String registerDate){
        Map<String,String> params =  new HashMap<>();
        params.put("op","valuation");
        params.put("styleid",styleid);
        params.put("cityId",cityId);
        params.put("mileage",mileage);
        params.put("registerDate",registerDate);
        params = MD5Utils.encryptParams(params);
        LogUtil.i(TAG, UIUtils.getUrl(params));
        JzgApp.getApiServer().carValuatePrice(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<CarEvaluateInfo>() {
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
            public void onNext(CarEvaluateInfo info) {
                if(info.getStatus()==1){
                    baseView.showData(info);
                }else{
                    onError(new RuntimeException(info.getMessage()));
                }
            }
        });
    }
}
