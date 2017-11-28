package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.PGOrderDetailModel;
import com.jzg.erp.appraiser.model.PGOrderInfoModel;
import com.jzg.erp.appraiser.model.PGPersonModel;
import com.jzg.erp.global.Constant;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.view.ICommon3;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zealjiang on 2016/8/23 20:04.
 * Email: zealjiang@126.com
 */
public class ABaseInfoFraPresenter extends BasePresenter<ICommon3> {
    public ABaseInfoFraPresenter(ICommon3 from) {
        super(from);
    }

    private Subscription mSubscription;


    /**
     * 根据评估单单号找出对应的评估人、评估单状态、车况等级
     * @param pingguNo
     */
    public void getPGOrderInfo(String pingguNo) {
        Map<String, String> params = new HashMap<>();
        params.put("op","PGOrderNo");
        params.put("pingguNo", pingguNo);
        params.put("userId", JzgApp.getUser().getUserID()+"");
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG, UIUtils.getUrl(params));
        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getPGOrderInfo(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<PGOrderInfoModel>() {
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
                    public void onNext(PGOrderInfoModel data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeedA(data.getData().get(0));
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }

    /**
     * 点击姓名获取评估人的信息
     * @param pingguUserId
     */
    public void getPGPersonInfo(String pingguUserId) {
        Map<String, String> params = new HashMap<>();
        params.put("op","PGOrderNoOwnerInfo");
        params.put("pingguUserId", pingguUserId);

        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getPGPersonInfo(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<PGPersonModel>() {
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
                    public void onNext(PGPersonModel data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeedB(data.getData().get(0));
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }

    /**
     * 点击评估单的状态获取评估单的详情
     * @param pingguNo
     * @param requestStatus
     */
    public void getPGOrderDetail(String pingguNo,String requestStatus) {
        Map<String, String> params = new HashMap<>();
        params.put("op","PGOrderNoDetail");
        params.put("pingguNo", pingguNo);
        params.put("requestStatus", requestStatus);

        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        baseView.showDialog();
        mSubscription = JzgApp.getApiServer().getPGOrderDetail(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<PGOrderDetailModel>() {
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
                    public void onNext(PGOrderDetailModel data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeedC(data.getData().get(0));
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }
}
