package com.jzg.erp.appraiser.presenter;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.ParamOption;
import com.jzg.erp.presenter.BasePresenter;
import com.jzg.erp.utils.ACache;
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
 * @time: 2016/8/15 16:39
 * @desc:
 */
public class AppraiserHomePresenter extends BasePresenter<IBaseView> {
    public AppraiserHomePresenter(IBaseView from) {
        super(from);
    }

    /**
     * 提交的参数
     */
    private Map<String, String> getParams(String UserId) {

        Map<String, String> params = new HashMap<>();
        params.put("UserId", UserId);
        //加sign
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        LogUtil.e(TAG, UIUtils.getUrl(params));
        return params;
    }

    public void getOptionParams(String UserId){
        JzgApp.getApiServer().getParamOptions(getParams(UserId)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ParamOption>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ParamOption option) {
                if(option!=null){
                    JzgApp.setOption(option);
                    ACache.get(JzgApp.getAppContext()).put("option",option);
                }
            }
        });
    }
}
