package com.jzg.erp.appraiser.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;
import com.jzg.erp.adapter.NameVaItemAdapter;
import com.jzg.erp.appraiser.model.ConfigInfoModel;
import com.jzg.erp.appraiser.presenter.ConfigInfoPresenter;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.model.NameValue;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.view.ICommon;
import com.jzg.erp.widget.RecycleViewDivider;
import com.jzg.erp.widget.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 配置信息
 * @author zealjiang
 * @time 2016/8/3 20:18
 */
public class ConfigInfoShowFragment extends NewBaseFragment implements ICommon {
    @Bind(R.id.xrv)
    XRecyclerView xrv;
    private NameVaItemAdapter adapter;
    private List<NameValue> mList;
    private View rootView;
    private ConfigInfoShowFragment mConfigInfoFragment;
    private Context mContext;

    private ConfigInfoPresenter presenter;
    //请求参数
    private String carId = "";//车辆ID

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add(new NameValue("防抱死制动系统",""));
        mList.add(new NameValue("安全气囊",""));
        mList.add(new NameValue("刹车辅助系统",""));
        mList.add(new NameValue("车身稳定控制系统",""));
        mList.add(new NameValue("后视镜电动调节",""));
        mList.add(new NameValue("导航",""));
        mList.add(new NameValue("CD/DVD",""));
        mList.add(new NameValue("空调",""));
        mList.add(new NameValue("中控锁",""));
        mList.add(new NameValue("遥控钥匙",""));

        mList.add(new NameValue("一键启动",""));
        mList.add(new NameValue("电动车窗",""));
        mList.add(new NameValue("倒车雷达",""));
        mList.add(new NameValue("倒车影像",""));
        mList.add(new NameValue("备用轮胎",""));
        mList.add(new NameValue("定速巡航",""));
        mList.add(new NameValue("天窗",""));
        mList.add(new NameValue("真皮座椅",""));
        mList.add(new NameValue("前座椅电动调节",""));
        mList.add(new NameValue("自动泊车入位",""));

        presenter = new ConfigInfoPresenter(this);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_baseinfo, container, false);
            ButterKnife.bind(this, rootView);
            mConfigInfoFragment = this;
            mContext = this.getContext();
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            xrv.setLayoutManager(linearLayoutManager);
            xrv.setPullRefreshEnabled(false);
            xrv.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, 6, R.color.common_gray_bg));
            adapter = new NameVaItemAdapter(context, R.layout.item_car_info, mList, xrv);
            adapter.setShowJianTou(false);
            xrv.setAdapter(adapter);
        }
        refresh();

        //获取数据
        Bundle bundle = getArguments();
        if(bundle!=null){
            carId = bundle.getString("carId");
        }else{
            MyToast.showShort("无法获取评估单号");
            return rootView;
        }

        //请求手续信息数据
        request(carId);

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
     * @time 2016/8/4 16:18
     */
    private void refresh(){
        adapter.notifyDataSetChanged();

        int i = getNotEmptyValue();
        //((ABaseInfoFragment)this.getParentFragment()).setTabText(1,"配置信息"+" "+i+"/"+mList.size());
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
     * 修改信息
     * @author zealjiang
     * @time 2016/8/11 17:56
     */
    public void setValues(List<ConfigInfoModel.DataBean> listData){

        mList.clear();
        for (int i = 0; i < listData.size(); i++) {
            ConfigInfoModel.DataBean databean = listData.get(i);
            mList.add(new NameValue(databean.getCI(),databean.getStatusStr()));
        }

/*        //防抱死制动系统
        mList.get(0).setValue(dataBean.getAbs());
        //安全气囊
        mList.get(1).setValue(dataBean.getAirBag());
        //刹车辅助系统
        mList.get(2).setValue(dataBean.getEba());
        //车身稳定控制系统
        mList.get(3).setValue(dataBean.getEsp());
        //后视镜电动调节
        mList.get(4).setValue(dataBean.getElecMirror());
        //导航
        mList.get(5).setValue(dataBean.getNavi());
        //CD/DVD
        mList.get(6).setValue(dataBean.getCdDvd());
        //空调
        mList.get(7).setValue(dataBean.getAirCondition());
        //中控锁
        mList.get(8).setValue(dataBean.getCenterControlLock());
        //遥控钥匙
        mList.get(9).setValue(dataBean.getRemoteKey());
        //一键启动
        mList.get(10).setValue(dataBean.getOneKeyStart());
        //电动车窗
        mList.get(11).setValue(dataBean.getElecWindow());
        //倒车雷达
        mList.get(12).setValue(dataBean.getBackRadar());
        //倒车影像
        mList.get(13).setValue(dataBean.getBackVideo());
        //备用轮胎
        mList.get(14).setValue(dataBean.getSpareTire());
        //定速巡航
        mList.get(15).setValue(dataBean.getConstSpeedCruise());
        //天窗
        mList.get(16).setValue(dataBean.getSkyLight());
        //真皮座椅
        mList.get(17).setValue(dataBean.getLeatherSeats());
        //前座椅电动调节
        mList.get(18).setValue(dataBean.getElecSeats());
        //自动泊车入位
        mList.get(19).setValue(dataBean.getAutoParking());*/

        refresh();

    }

    private void request(String id){
        presenter.getConfigInfoList(id);
    }

    @Override
    public void succeed(Object object) {
        List<ConfigInfoModel.DataBean> dataBean = (List<ConfigInfoModel.DataBean>)object;
        if(null!=dataBean){
            setValues(dataBean);
        }
    }
}
