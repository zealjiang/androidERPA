package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.PGOrderModel;
import com.jzg.erp.global.Constant;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.view.IPGOrder;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 评估单
 * @author zealjiang
 * @time 2016/8/25 10:08
 */
public class PGOrderPresenter extends BasePresenter<IPGOrder> {
    public PGOrderPresenter(IPGOrder from) {
        super(from);
    }

    private Subscription mSubscription;


    /**
     *
     * @param id
     */
    public void getPGOrderList(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("op","PGOrder");
        params.put("id", id);
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG+"==========", UIUtils.getUrl(params));
        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getPGOrder(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<PGOrderModel>() {
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
                    public void onNext(PGOrderModel data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeedPGOrder(data.getData());
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }
}
