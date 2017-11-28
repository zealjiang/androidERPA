package com.jzg.erp.appraiser.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;
import com.jzg.erp.adapter.ItemAdapter;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.activity.CarInfoActivity;
import com.jzg.erp.appraiser.model.CarInfoModel;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.model.IdNameValue;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.widget.MActionSheet;
import com.jzg.erp.widget.RecycleViewDivider;
import com.jzg.erp.widget.XRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 配置信息
 * @author zealjiang
 * @time 2016/8/3 20:18
 */
public class ConfigInfoFragment extends NewBaseFragment implements OnItemClickListener {
    @Bind(R.id.xrv)
    XRecyclerView xrv;
    private ItemAdapter adapter;
    private List<IdNameValue> mList;
    private View rootView;
    private ConfigInfoFragment mConfigInfoFragment;
    private Context mContext;
    /**通用*/
    private String[] ConfigCommonArray;
    /**加装*/
    private String[] ConfigAddArray;

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add(new IdNameValue("","防抱死制动系统",""));
        mList.add(new IdNameValue("","安全气囊",""));
        mList.add(new IdNameValue("","刹车辅助系统",""));
        mList.add(new IdNameValue("","车身稳定控制系统",""));
        mList.add(new IdNameValue("","后视镜电动调节",""));
        mList.add(new IdNameValue("","导航",""));
        mList.add(new IdNameValue("","CD/DVD",""));
        mList.add(new IdNameValue("","空调",""));
        mList.add(new IdNameValue("","中控锁",""));
        mList.add(new IdNameValue("","遥控钥匙",""));

        mList.add(new IdNameValue("","一键启动",""));
        mList.add(new IdNameValue("","电动车窗",""));
        mList.add(new IdNameValue("","倒车雷达",""));
        mList.add(new IdNameValue("","倒车影像",""));
        mList.add(new IdNameValue("","备用轮胎",""));
        mList.add(new IdNameValue("","定速巡航",""));
        mList.add(new IdNameValue("","天窗",""));
        mList.add(new IdNameValue("","真皮座椅",""));
        mList.add(new IdNameValue("","前座椅电动调节",""));
        mList.add(new IdNameValue("","自动泊车入位",""));

        ConfigCommonArray = getResources().getStringArray(R.array.config_common);
        ConfigAddArray = getResources().getStringArray(R.array.config_add);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_baseinfo, container, false);
            ButterKnife.bind(this, rootView);
            EventBus.getDefault().register(this);
            mConfigInfoFragment = this;
            mContext = this.getContext();
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            xrv.setLayoutManager(linearLayoutManager);
            xrv.setPullRefreshEnabled(false);
            xrv.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, 6, R.color.common_gray_bg));
            adapter = new ItemAdapter(context, R.layout.item_car_info, mList, xrv);
            adapter.setOnItemClickListener(this);
            xrv.setAdapter(adapter);
        }

        //设置上次保存的值
        if(null==JzgApp.getAppContext().getSubmitParam().getCarInfo()){
            SubmitParamWrapper.CarInfo carInfo = new SubmitParamWrapper.CarInfo();
            JzgApp.getAppContext().getSubmitParam().setCarInfo(carInfo);
        }

        ArrayList<String> allocInfoList = JzgApp.getAppContext().getSubmitParam().getCarInfo().getAllocInfoList();
        if(null== allocInfoList){
            allocInfoList = new ArrayList<String>();
            JzgApp.getAppContext().getSubmitParam().getCarInfo().setAllocInfoList(allocInfoList);
        }else{
            setInitValues(allocInfoList);

        }

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
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化显示配置信息
     * @author zealjiang
     * @time 2016/8/11 9:58
     */
    public void setInitValues(ArrayList<String> allocInfoList){

        //1, 有 ;2. 无 ;3. 加装 ， 0. 未选择
        //是否加装:1,有;2.无;3.加装  20个配置项是按顺序来的

        for (int i = 0; i < allocInfoList.size(); i++) {
            String[] array = allocInfoList.get(i).split(",");
            if(array.length==1){
                mList.get(i).setValue("");
            }else{
                mList.get(i).setValue(idToName(array[1]));
            }

        }
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        switch (position){
            case 0:
                //防抱死制动系统
                configCommon(0);
                break;
            case 1:
                //安全气囊
                configCommon(1);
                break;
            case 2:
                //刹车铺助系统
                configCommon(2);
                break;
            case 3:
                //车身稳定控制系统
                configCommon(3);
                break;
            case 4:
                //后视镜电动调节
                configCommon(4);
                break;
            case 5:
                //导航
                configAdd(5);
                break;
            case 6:
                //CD/DVD
                configAdd(6);
                break;
            case 7:
                //空调
                configAdd(7);
                break;
            case 8:
                //中控锁
                configAdd(8);
                break;
            case 9:
                //遥控钥匙
                configAdd(9);
                break;
            case 10:
                //一键启动
                configAdd(10);
                break;
            case 11:
                //电动车窗
                configAdd(11);
                break;
            case 12:
                //倒车雷达
                configAdd(12);
                break;
            case 13:
                //倒车影像
                configAdd(13);
                break;
            case 14:
                //备用轮胎
                configAdd(14);
                break;
            case 15:
                //定速巡航
                configAdd(15);
                break;
            case 16:
                //天窗
                configAdd(16);
                break;
            case 17:
                //真皮座椅
                configAdd(17);
                break;
            case 18:
                //前座椅电动调节
                configAdd(18);
                break;
            case 19:
                //自动泊车入位
                configAdd(19);
                break;
        }
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

    private void configCommon(final int position){
        MActionSheet.createBuilder(mContext,mConfigInfoFragment.getChildFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles(ConfigCommonArray)
                .setCancelableOnTouchOutside(true)
                .setListener(new MActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(MActionSheet actionSheet, boolean isCancel) {
                    }

                    @Override
                    public void onOtherButtonClick(MActionSheet actionSheet, int index) {
                        mList.get(position).setValue(ConfigCommonArray[index]);
                        refresh();
                    }
                }).show();
    }

    private void configAdd(final int position){
        MActionSheet.createBuilder(mContext,mConfigInfoFragment.getChildFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles(ConfigAddArray)
                .setCancelableOnTouchOutside(true)
                .setListener(new MActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(MActionSheet actionSheet, boolean isCancel) {
                    }

                    @Override
                    public void onOtherButtonClick(MActionSheet actionSheet, int index) {
                        mList.get(position).setValue(ConfigAddArray[index]);
                        refresh();
                    }
                }).show();
    }

    /**
     * 刷新
     * @author zealjiang
     * @time 2016/8/4 16:18
     */
    private void refresh(){
        adapter.notifyDataSetChanged();

        int i = getNotEmptyValue();
        ((CarInfoActivity)this.getActivity()).setTabText(1,"配置信息"+" "+i+"/"+mList.size());
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
     * 配置信息必填项是否全填了
     * @author zealjiang
     * @time 2016/8/11 13:55
     */
    public boolean isFillOut(){
        for (int i = 0; i < mList.size(); i++) {
            if(mList.get(i).getName().endsWith("*")){
                if(TextUtils.isEmpty(mList.get(i).getValue())){
                    String name = mList.get(i).getName();
                    if(name.endsWith("*")){
                        name = name.substring(0,name.length()-1);
                    }
                    MyToast.showShort("请填写"+name);
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 修改信息
     * @author zealjiang
     * @time 2016/8/11 17:56
     */
    public void setValues(CarInfoModel.DataBean.CarCIBean carCIBean){
        //1, 有 ;2. 无 ;3. 加装 ， 0. 未选择

        //防抱死制动系统
        mList.get(0).setValue(idToName(carCIBean.getAbs()));
        //安全气囊
        mList.get(1).setValue(idToName(carCIBean.getAirBag()));
        //刹车辅助系统
        mList.get(2).setValue(idToName(carCIBean.getEba()));
        //车身稳定控制系统
        mList.get(3).setValue(idToName(carCIBean.getEsp()));
        //后视镜电动调节
        mList.get(4).setValue(idToName(carCIBean.getElecMirror()));
        //导航
        mList.get(5).setValue(idToName(carCIBean.getNavi()));
        //CD/DVD
        mList.get(6).setValue(idToName(carCIBean.getCdDvd()));
        //空调
        mList.get(7).setValue(idToName(carCIBean.getAirCondition()));
        //中控锁
        mList.get(8).setValue(idToName(carCIBean.getCenterControlLock()));
        //遥控钥匙
        mList.get(9).setValue(idToName(carCIBean.getRemoteKey()));
        //一键启动
        mList.get(10).setValue(idToName(carCIBean.getOneKeyStart()));
        //电动车窗
        mList.get(11).setValue(idToName(carCIBean.getElecWindow()));
        //倒车雷达
        mList.get(12).setValue(idToName(carCIBean.getBackRadar()));
        //倒车影像
        mList.get(13).setValue(idToName(carCIBean.getBackVideo()));
        //备用轮胎
        mList.get(14).setValue(idToName(carCIBean.getSpareTire()));
        //定速巡航
        mList.get(15).setValue(idToName(carCIBean.getConstSpeedCruise()));
        //天窗
        mList.get(16).setValue(idToName(carCIBean.getSkyLight()));
        //真皮座椅
        mList.get(17).setValue(idToName(carCIBean.getLeatherSeats()));
        //前座椅电动调节
        mList.get(18).setValue(idToName(carCIBean.getElecSeats()));
        //自动泊车入位
        mList.get(19).setValue(idToName(carCIBean.getAutoParking()));

        refresh();

    }

    /**
     * 将vin码返回的配置信息ID转成对应的名字
     * @author zealjiang
     * @time 2016/8/25 14:33
     */
    private String idToName(String id){
        if(TextUtils.isEmpty(id)){
            return "";
        }
        //1, 有 ;2. 无 ;3. 加装 ， 0. 未选择
        switch (id){
            case "0":
                return "";
            case "1":
                return "有";
            case "2":
                return "无";
            case "3":
                return "加装";
        }
        return "";
    }

    /**
     * 保存信息
     * @author zealjiang
     * @time 2016/8/11 17:08
     */
    public void saveInfo(){
        if(null== JzgApp.getAppContext().getSubmitParam().getCarInfo()){
            SubmitParamWrapper.CarInfo carInfo = new SubmitParamWrapper.CarInfo();
            JzgApp.getAppContext().getSubmitParam().setCarInfo(carInfo);
        }
        ArrayList<String> allocInfoList = JzgApp.getAppContext().getSubmitParam().getCarInfo().getAllocInfoList();
        if(null== allocInfoList){
            allocInfoList = new ArrayList<>();
            JzgApp.getAppContext().getSubmitParam().getCarInfo().setAllocInfoList(allocInfoList);
        }

        //内容 :1, 有 ;2. 无 ;3. 加装 ， 0. 未选择
        allocInfoList.clear();
        //防抱死制动系统
        allocInfoList.add("1,"+valueToId(mList.get(0).getValue()));
        //安全气囊
        allocInfoList.add("2,"+valueToId(mList.get(1).getValue()));
        //刹车辅助系统
        allocInfoList.add("3,"+valueToId(mList.get(2).getValue()));
        //车身稳定控制系统
        allocInfoList.add("4,"+valueToId(mList.get(3).getValue()));
        //后视镜电动调节
        allocInfoList.add("5,"+valueToId(mList.get(4).getValue()));
        //导航
        allocInfoList.add("6,"+valueToId(mList.get(5).getValue()));
        //CD/DVD
        allocInfoList.add("7,"+valueToId(mList.get(6).getValue()));
        //空调
        allocInfoList.add("8,"+valueToId(mList.get(7).getValue()));
        //中控锁
        allocInfoList.add("9,"+valueToId(mList.get(8).getValue()));
        //遥控钥匙
        allocInfoList.add("10,"+valueToId(mList.get(9).getValue()));
        //一键启动
        allocInfoList.add("11,"+valueToId(mList.get(10).getValue()));
        //电动车窗
        allocInfoList.add("12,"+valueToId(mList.get(11).getValue()));
        //倒车雷达
        allocInfoList.add("13,"+valueToId(mList.get(12).getValue()));
        //倒车影像
        allocInfoList.add("14,"+valueToId(mList.get(13).getValue()));
        //备用轮胎
        allocInfoList.add("15,"+valueToId(mList.get(14).getValue()));
        //定速巡航
        allocInfoList.add("16,"+valueToId(mList.get(15).getValue()));
        //天窗
        allocInfoList.add("17,"+valueToId(mList.get(16).getValue()));
        //真皮座椅
        allocInfoList.add("18,"+valueToId(mList.get(17).getValue()));
        //前座椅电动调节
        allocInfoList.add("19,"+valueToId(mList.get(18).getValue()));
        //自动泊车入位
        allocInfoList.add("20,"+valueToId(mList.get(19).getValue()));

    }


    //内容 :1, 有 ;2. 无 ;3. 加装 ， 0. 未选择
    private String valueToId(String value){
        switch (value){
            case "":
                return "0";
            case "有":
                return "1";
            case "无":
                return "2";
            case "加装":
                return "3";
            default:
                return "0";

        }
    }


    /**
     * 处理vin码解析返回的数据
     * @author zealjiang
     * @time 2016/8/25 11:53
     */
    private void handleVinData(CarInfoModel carInfoModel){
        boolean isDataFull = carInfoModel.getData().isIsExist();
        if(isDataFull){
            //覆盖所有数据
            CarInfoModel.DataBean.CarCIBean carCIBean = carInfoModel.getData().getCarCI();
            setValues(carCIBean);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventConfigInfo(CarInfoModel.DataBean.CarCIBean dataBean) {
        //车辆资料-配置信息
        setValues(dataBean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventCarBaseInfo(CarInfoModel event) {
        //所有信息
        handleVinData(event);
    }
}
