package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.HistoryEvaListModel;
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
 * 获取历史评估单
 * @author zealjiang
 * @time 2016/8/19 14:49
 */
public class HistoryEvalListPresenter extends BasePresenter<ICommon> {
    public HistoryEvalListPresenter(ICommon from) {
        super(from);
    }

    private Subscription mSubscription;


    /**
     *
     * @param pageindex
     * @param LicensePlate
     * @param RequestStatus
     * @param SearchStartDate
     * @param SearchEndDate
     * @param StoreId
     * @param UserName
     */
    public void getHistoryEvalList(String pageindex,String LicensePlate,String RequestStatus,
                          String SearchStartDate,String SearchEndDate,String StoreId,String UserName) {
        Map<String, String> params = new HashMap<>();
        params.put("op","list");
        params.put("UserId",JzgApp.getUser().getUserID()+"");
        params.put("pageindex", pageindex);
        params.put("pagesize", Constant.PAGECOUNT+"");
        params.put("LicensePlate", LicensePlate);
        params.put("RequestStatus", RequestStatus);
        params.put("SearchStartDate", SearchStartDate);
        params.put("SearchEndDate", SearchEndDate);
        params.put("StoreId", StoreId);
        params.put("UserName", UserName);
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG,UIUtils.getUrl(params));
        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getHistoryEvalList(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<HistoryEvaListModel>() {
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
                    public void onNext(HistoryEvaListModel data) {
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
