package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarPhoto;
import com.jzg.erp.appraiser.model.EvaluateHistory;
import com.jzg.erp.appraiser.model.EvaluatePrice;
import com.jzg.erp.appraiser.model.OnlineSale;
import com.jzg.erp.appraiser.model.PingguCustomer;
import com.jzg.erp.appraiser.model.Seller;
import com.jzg.erp.appraiser.model.TradeHistory;
import com.jzg.erp.appraiser.model.TradeRecord;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.view.IBaseView;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/23 08:22
 * @desc:
 */
public class QueryCarConcernPresenter extends BasePresenter<IBaseView> {
    private OnDataSucceedListener listener;
    public QueryCarConcernPresenter(IBaseView from,OnDataSucceedListener listener) {
        super(from);
        this.listener = listener;
    }

    public void getTradeHistoryListByModelId(String op, String styleId ){
        Map<String,String> params = new HashMap<>();
        params.put("op",op);
        params.put("modelId",styleId);
        params = MD5Utils.encryptParams(params);
        print(params);
        JzgApp.getApiServer().getTradeHistory(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<TradeHistory>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TradeHistory tradeHistory) {
                if(tradeHistory.getStatus()==1){
                    listener.showData(tradeHistory);
                }else{
                    onError(new RuntimeException(tradeHistory.getMessage()));
                }
            }
        });
    }

    /***
     * ?op=listByCar&carId=3482
     * @param op
     * @param carId
     */
    public void getEvaluateHistoryListByStyleId(String op,String carId){
        Map<String,String> params = new HashMap<>();
        params.put("op",op);
        params.put("carId",carId);
        params = MD5Utils.encryptParams(params);
        print(params);
        JzgApp.getApiServer().getEvaluateHistory(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<EvaluateHistory>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(EvaluateHistory evaluateHistory) {
                if(evaluateHistory.getStatus()==1){
                    listener.showData(evaluateHistory);
                }else{
                    onError(new RuntimeException(evaluateHistory.getMessage()));
                }
            }
        });
    }

    /***
     * 获取某个车型45天内的销售记录
     * @param styleid  ( 必填 )
     * @param registerDate  ( 必填 )
     * @param mileage  ( 单位：万。必填 )
     * @param cityId ( 必填，默认为201，北京 )
     */
    public void getTradeRecord(String styleid,String registerDate,String mileage,String cityId){
        Map<String,String> params = new HashMap<>();
        params.put("op","historytransaction");
        params.put("styleid",styleid);
        params.put("cityId",cityId);
        params.put("mileage",mileage);
        params.put("registerDate",registerDate);
        params = MD5Utils.encryptParams(params);
        print(params);
        JzgApp.getApiServer().getTradeRecord(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<TradeRecord>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG,e.getMessage());
            }

            @Override
            public void onNext(TradeRecord record) {
                if(record.getStatus()==1){
                    listener.showData(record);
                }else{
                    onError(new RuntimeException(record.getMessage()));
                }
            }
        });
    }

    /***
     * @param styleid ( 必填 )   车型id
     * @param cityId ( 必填 )
     */
    public void getOnlineSales(String styleid,String cityId){
        Map<String,String> params = new HashMap<>();
        params.put("op","publishcarsource");
        params.put("styleid",styleid);
        params.put("cityId",cityId);
        params = MD5Utils.encryptParams(params);
        print(params);
        JzgApp.getApiServer().getOnlineSale(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<OnlineSale>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG,e.getMessage());
            }

            @Override
            public void onNext(OnlineSale record) {
                if(record.getStatus()==1){
                    listener.showData(record);
                }else{
                    onError(new RuntimeException(record.getMessage()));
                }
            }
        });
    }


    /***
     * 根据评估单号获取被评估车辆的照片
     * @param pingguNo 评估单号
     */
    public void getCarPhoto(String pingguNo){
        Map<String,String> params = new HashMap<>();
        params.put("op","PGCarPicpath");
        params.put("id",pingguNo);
        params = MD5Utils.encryptParams(params);
        print(params);
        JzgApp.getApiServer().getCarPhoto(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<CarPhoto>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG,e.getMessage());
            }

            @Override
            public void onNext(CarPhoto record) {
                if(record.getStatus()==1){
                    listener.showData(record);
                }else{
                    onError(new RuntimeException(record.getMessage()));
                }
            }
        });
    }

    /***
     * 根据评估单号获取被评估车辆的评估价格
     * @param pingguNo
     */
    public void getEvaluatePrice(String pingguNo){
        Map<String,String> params = new HashMap<>();
        params.put("op","PGCarPrice");
        params.put("pingguNo",pingguNo);
        params = MD5Utils.encryptParams(params);
        print(params);
        JzgApp.getApiServer().getEvaluatePrice(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<EvaluatePrice>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG,e.getMessage());
            }

            @Override
            public void onNext(EvaluatePrice price) {
                if(price.getStatus()==1){
                    listener.showData(price);
                }else{
                    onError(new RuntimeException(price.getMessage()));
                }
            }
        });
    }


    /***
     * 根据评估单号获取售车客户信息
     * @param pingguNo
     */
    public void getCustomerInfo(String pingguNo){
        Map<String,String> params = new HashMap<>();
        params.put("op","PGOrderNoCarOwnerInfo");
        params.put("pingguNo",pingguNo);
        print(params);
        JzgApp.getApiServer().getCustomerInfo(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<PingguCustomer>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG,e.getMessage());
            }

            @Override
            public void onNext(PingguCustomer price) {
                if(price.getStatus()==1){
                    listener.showData(price);
                }else{
                    onError(new RuntimeException(price.getMessage()));
                }
            }
        });
    }


    /***
     *
     * @param storeid 69
     */
    public void getStoreSellers(String storeid){
        baseView.showDialog();
        Map<String,String> params = new HashMap<>();
        params.put("op","saleoptions");
        params.put("storeid",storeid);
        params = MD5Utils.encryptParams(params);
        print(params);
        JzgApp.getApiServer().getStoreSellers(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<Seller>() {
            @Override
            public void onCompleted() {
                baseView.dismissDialog();
            }

            @Override
            public void onError(Throwable e) {
                baseView.dismissDialog();
                LogUtil.e(TAG,e.getMessage());
            }

            @Override
            public void onNext(Seller seller) {
                if(seller.getStatus()==1){
                    listener.showData(seller);
                }else{
                    onError(new RuntimeException(seller.getMessage()));
                }
            }
        });
    }

    private void print ( Map<String,String> params){
        LogUtil.e(TAG, UIUtils.getUrl(params));
    }

    /**
     *
     * @param pingguNo
     */
    public void deleteOrder(String pingguNo){
        baseView.showDialog();
        Map<String,String> params = new HashMap<>();
        params.put("op","DelPingguOrder");
        params.put("pingguNo",pingguNo);
        params = MD5Utils.encryptParams(params);
        JzgApp.getApiServer().deleteOrder(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<com.jzg.erp.base.BaseObject>() {
            @Override
            public void onCompleted() {
                baseView.dismissDialog();
            }

            @Override
            public void onError(Throwable e) {
                baseView.dismissDialog();
                LogUtil.e(TAG,e.getMessage());
            }

            @Override
            public void onNext(com.jzg.erp.base.BaseObject data) {
                if(data.getStatus()==1){
                    listener.showData(data);
                }else{
                    onError(new RuntimeException(data.getMessage()));
                }
            }
        });
    }



    public static interface OnDataSucceedListener<T>{
        void showData(T data);
    }

}
