package com.jzg.erp.appraiser.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;
import com.jzg.erp.adapter.NameVaItemAdapter;
import com.jzg.erp.appraiser.model.BaseInfoModel;
import com.jzg.erp.appraiser.model.ParamOption;
import com.jzg.erp.appraiser.presenter.BaseInfoPresenter;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.event.VINEvent;
import com.jzg.erp.model.NameValue;
import com.jzg.erp.utils.DateTimeUtils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.view.ICommon;
import com.jzg.erp.widget.RecycleViewDivider;
import com.jzg.erp.widget.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 基本信息
 * @author zealjiang
 * @time 2016/8/3 20:18
 */
public class BaseInfoShowFragment extends NewBaseFragment implements ICommon {
    @Bind(R.id.xrv)
    XRecyclerView xrv;
    private NameVaItemAdapter adapter;
    private List<NameValue> mList;
    private View rootView;

    private BaseInfoPresenter presenter;
    private String carId = "";//车辆ID

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add(new NameValue("品牌",""));
        mList.add(new NameValue("车系",""));
        mList.add(new NameValue("车型",""));
        mList.add(new NameValue("车牌号",""));
        mList.add(new NameValue("初次上牌",""));
        mList.add(new NameValue("颜色",""));
        mList.add(new NameValue("内饰",""));
        mList.add(new NameValue("表显里程",""));
        mList.add(new NameValue("出厂日期",""));
        mList.add(new NameValue("新车市场价",""));

        presenter = new BaseInfoPresenter(this);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_baseinfo, container, false);
            ButterKnife.bind(this, rootView);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            xrv.setLayoutManager(linearLayoutManager);
            xrv.setPullRefreshEnabled(false);
            xrv.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, 6, R.color.common_gray_bg));
            adapter = new NameVaItemAdapter(context, R.layout.item_car_info, mList, xrv);
            adapter.setShowJianTou(false);
            xrv.setAdapter(adapter);
        }

        //获取数据
        Bundle bundle = getArguments();
        if(bundle!=null){
            carId = bundle.getString("carId");
        }else{
            MyToast.showShort("无法获取评估单号");
            return rootView;
        }

        //请求基本信息数据
        request(carId);

        refresh();
        return rootView;
    }

    @Override
    protected void setView() {

    }


    @Override
    protected void setTextLeft(int res) {
        super.setTextLeft(res);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /**
     * 刷新
     * @author zealjiang
     * @time 2016/8/4 16:18*
     */
    private void refresh(){
        adapter.notifyDataSetChanged();
        int i = getNotEmptyValue();
        //((ABaseInfoFragment)this.getParentFragment()).setTabText(0,"基本信息"+" "+i+"/"+mList.size());
    }

    private int getNotEmptyValue(){
        int count = 0;
        for (int i = 0; i < mList.size(); i++) {
            if(!TextUtils.isEmpty(mList.get(i).getValue())){
                count++;
            }
        }
        return count;
    }

    /**
     * 修改基本信息
     * @author zealjiang
     * @time 2016/8/11 9:58
     */
    public void setValues(BaseInfoModel.DataBean dataBean){
        List<ParamOption.DataBean.KeyValueItem> list;
        //品牌
        mList.get(0).setValue(dataBean.getMakeName());
        //车系
        mList.get(1).setValue(dataBean.getModelName());
        //车型
        mList.get(2).setValue(dataBean.getStyleName());
        //车牌号
        mList.get(3).setValue(dataBean.getLicensePlate());
        //初次上牌
        mList.get(4).setValue(DateTimeUtils.formatMillsStr(dataBean.getRegdate(),DateTimeUtils.YYYYMMDD));
        //颜色
        mList.get(5).setValue(dataBean.getColorDicSubValue());
        //内饰
        mList.get(6).setValue(dataBean.getInnerClolorDicSubValue());
        //表显里程
        mList.get(7).setValue(dataBean.getMileage()+" 公里");
        //出厂日期
        mList.get(8).setValue(DateTimeUtils.formatMillsStr(dataBean.getManufactureDate(),DateTimeUtils.YYYYMMDD));
        //新车市场价
        mList.get(9).setValue(dataBean.getNewCarPrice()+" 万元");

        refresh();
    }

    private void request(String id){
        presenter.getBaseInfoList(id);
    }

    @Override
    public void succeed(Object object) {
        BaseInfoModel.DataBean dataBean = (BaseInfoModel.DataBean)object;
        if(null!=dataBean){
            setValues(dataBean);
            String vin = dataBean.getVIN();
            VINEvent vinEvent = new VINEvent();
            vinEvent.setVin(vin);
            EventBus.getDefault().post(vinEvent);
        }
    }

}
