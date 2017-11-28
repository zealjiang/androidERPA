package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarInfoModel;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.ACache;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.NetworkExceptionUtils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.view.IVinSearch;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * @author zealjiang
 * @time 2016/8/4 10:09
 */
public class VINPresenter extends BasePresenter<IVinSearch> {
    public VINPresenter(IVinSearch from) {
        super(from);
    }

    /**
     * 提交的参数
     */
    public Map<String, String> getParams(String vin,String StoreId) {

        // 在这里设置需要post的参数

        Map<String, String> params = new HashMap<>();
        params.put("op","getentity");
        params.put("vin", vin);
        params.put("StoreId", StoreId);
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG, UIUtils.getUrl(params));
        return params;
    }

    public void request(String vin,String StoreId){
        Map<String, String> params = getParams(vin,StoreId);

        baseView.showDialog();
        JzgApp.getApiServer().queryCarInfoByVin(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CarInfoModel>() {
            @Override
            public void onCompleted() {
                baseView.dismissDialog();
            }

            @Override
            public void onError(Throwable e) {
                if(e==null)return;
                baseView.dismissDialog();
                String error =  NetworkExceptionUtils.getErrorByException(e);
                baseView.showError(error);

            }

            @Override
            public void onNext(CarInfoModel carInfoModel) {
                int status = carInfoModel.getStatus();
                /**
                 * 错误信息说明 ：

                 Message = " 该车辆已被收购 ， 无法保存并创建新的评估单 ";

                 Status = -1;

                 Message = " 该车辆已存在处于批准收购状态的评估单 ， 无法保存并创建新的评估单 ";

                 Status = -2;

                 Message = " 该车辆已存在本店铺创建的处于待审核状态的评估单，无法保存并创建新的评估单 ";

                 Status = -3;

                 Message = " 该车辆已存在本店铺创建的处于待申请状态的评估单，若创建新的评估单，则之前创建的评估单会被自动删除，您是否还要创建新的评估单 ?";

                 Status = -4;

                 Message = " 该车辆已存在其它店铺创建的评估单，您是否需要创建新的评估单 ?";

                 Status = -5;

                 */

                if(status==1){
                    ACache.get(JzgApp.getAppContext()).put("CarInfoModel",carInfoModel);
                    baseView.succeed(carInfoModel);
                }else if(status>=-5&&status<=-1){
                    baseView.unusual(carInfoModel);
                }else{
                    baseView.showError(carInfoModel.getMessage());
                }
            }
        });
    }
}
