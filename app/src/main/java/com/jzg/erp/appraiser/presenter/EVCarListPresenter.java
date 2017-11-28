package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.EVCarList;
import com.jzg.erp.global.Constant;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.view.ICommon;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 获取评估师端车辆列表
 * @author zealjiang
 * @time 2016/8/16 17:23
 */
public class EVCarListPresenter extends BasePresenter<ICommon> {
    public EVCarListPresenter(ICommon from) {
        super(from);
    }

    /**
     * 无条件下查车辆列表
     * @author zealjiang
     * @time 2016/8/23 15:07
     */
    public void getCarList(String userId,String pageIndex) {
        Map<String, String> params = new HashMap<>();
        params.put("op","CarSource");
        params.put("userId", userId);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Constant.PAGECOUNT+"");
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG, UIUtils.getUrl(params));
        baseView.showDialog();
        JzgApp.getApiServer().getCarList(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<EVCarList>() {
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
                    public void onNext(EVCarList data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeed(data.getData());
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }

    /**
     * 通过车型查车辆列表
     * @author zealjiang
     * @time 2016/8/23 15:07
     */
    public void getCarListByStyleName(String userId,String pageIndex,String makeName,String modelName,String styleName) {
        Map<String, String> params = new HashMap<>();
        params.put("op","StyleName");
        params.put("userId", userId);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Constant.PAGECOUNT+"");
        params.put("makeName", makeName);
        params.put("modelName", modelName);
        params.put("styleName", styleName);
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG,UIUtils.getUrl(params));
        baseView.showDialog();
        JzgApp.getApiServer().getCarListByStyleName(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<EVCarList>() {
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
                    public void onNext(EVCarList data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeed(data.getData());
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }





    /**
     * 通过车牌号查车辆列表
     * @author zealjiang
     * @time 2016/8/23 15:07
     */
    public void getCarListByLicense(String userId,String license,String pageIndex) {
        Map<String, String> params = new HashMap<>();
        params.put("op","LicensePlate");
        params.put("userId", userId);
        params.put("license", license);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Constant.PAGECOUNT+"");
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG,UIUtils.getUrl(params));
        baseView.showDialog();
        JzgApp.getApiServer().getCarListByLicense(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<EVCarList>() {
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
                    public void onNext(EVCarList data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeed(data.getData());
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }

    /**
     * 通过手机号查车辆列表
     * @author zealjiang
     * @time 2016/8/23 15:07
     */
    public void getCarListByPhone(String userId,String phone,String pageIndex) {
        Map<String, String> params = new HashMap<>();
        params.put("op","Phone");
        params.put("userId", userId);
        params.put("telphone", phone);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Constant.PAGECOUNT+"");
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG,UIUtils.getUrl(params));
        baseView.showDialog();
        JzgApp.getApiServer().getCarListByPhone(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<EVCarList>() {
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
                    public void onNext(EVCarList data) {
                        int status = data.getStatus();
                        if(status==1){
                            baseView.succeed(data.getData());
                        }else{
                            baseView.showError(data.getMessage());
                        }
                    }
                });
    }


    /**
     * 通过VIN查车辆列表
     * @author zealjiang
     * @time 2016/8/23 15:07
     */
    public void getCarListByVIN(String userId,String vinCode,String pageIndex) {
        Map<String, String> params = new HashMap<>();
        params.put("op","VIN");
        params.put("userId", userId);
        params.put("vinCode", vinCode);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Constant.PAGECOUNT+"");
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG,UIUtils.getUrl(params));
        baseView.showDialog();
        JzgApp.getApiServer().getCarListByVin(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<EVCarList>() {
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
                    public void onNext(EVCarList data) {
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
