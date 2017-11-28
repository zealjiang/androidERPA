package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.EvalStatusListModel;
import com.jzg.erp.global.Constant;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.view.IEvalStatusListInf;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 获取 历史评估单状态 选项数据
 * @author zealjiang
 * @time 2016/8/22 15:26
 */
public class EvalStatusListPresenter extends BasePresenter<IEvalStatusListInf> {
    public EvalStatusListPresenter(IEvalStatusListInf from) {
        super(from);
    }

    private Subscription mSubscription;

    public void getEvalStatusList(String LicensePlate,String SearchEndDate,String SearchStartDate,String StoreId,String UserName) {
        Map<String, String> params = new HashMap<>();
        params.put("op","assessstatus");
        params.put("PingguUserId", JzgApp.getUser().getUserID()+"");
        params.put("LicensePlate", LicensePlate);
        params.put("SearchEndDate", SearchEndDate);
        params.put("SearchStartDate", SearchStartDate);
        params.put("StoreId", StoreId);
        params.put("UserName", UserName);
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));

        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getHistoryEvalStatus(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<EvalStatusListModel>() {
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
                    public void onNext(EvalStatusListModel data) {
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
