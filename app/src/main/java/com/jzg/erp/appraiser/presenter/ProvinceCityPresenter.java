package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.ProvinceCity;
import com.jzg.erp.appraiser.view.IProvinceCity;
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
 * @time: 2016/8/23 10:31
 * @desc:
 */
public class ProvinceCityPresenter extends BasePresenter<IProvinceCity>{

    public ProvinceCityPresenter(IProvinceCity from) {
        super(from);
    }

    /***
     *
     * @param provinceId 可选，空着则取省，有值取市
     */
    public void getProviceOfCity(int provinceId){
        baseView.showDialog();
        Map<String,String> params = new HashMap<>();
        params.put("op","areacity");
        if(provinceId>0){
            params.put("provinceId",String.valueOf(provinceId));
        }
        params = MD5Utils.encryptParams(params);
        JzgApp.getApiServer().getProvinceCity(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<ProvinceCity>() {
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
            public void onNext(ProvinceCity provinceCity) {
                if(provinceCity.getStatus()==1){
                    baseView.showData(provinceCity.getData());
                }else{
                    onError(new RuntimeException(provinceCity.getMessage()));
                }
            }
        });
    }
}
