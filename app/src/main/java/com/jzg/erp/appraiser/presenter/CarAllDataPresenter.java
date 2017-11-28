package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarAllDataModel;
import com.jzg.erp.global.Constant;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.view.IBaseView;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 获取车辆的所有信息
 * @author zealjiang
 * @time 2016/8/28 15:45
 */
public class CarAllDataPresenter extends BasePresenter<IBaseView> {

    private OnCarAllDataListener listener;

    public CarAllDataPresenter(IBaseView from,OnCarAllDataListener listener) {
        super(from);
        this.listener = listener;
    }

    private Subscription mSubscription;



    public void getCarAllData(String vin,String pgOrderId) {
        Map<String, String> params = new HashMap<>();
        params.put("op","getcaralldata");
        params.put("vin",vin);
        params.put("pgOrderId", pgOrderId);

        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));

        LogUtil.e(TAG,UIUtils.getUrl(params));
        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getCarAllData(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<CarAllDataModel>() {
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
                    public void onNext(CarAllDataModel data) {
                        int status = data.getStatus();
                        if(status==1){
                            listener.succeedCarAllData(data.getData());
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }

    public interface OnCarAllDataListener<T>{
        void succeedCarAllData(T data);
    }
}
