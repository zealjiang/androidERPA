package com.jzg.erp.appraiser.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.jzg.erp.appraiser.activity.SheetTimeBaoxianAty;
import com.jzg.erp.appraiser.activity.SheetTimeDateAty;
import com.jzg.erp.appraiser.model.CarInfoModel;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.global.Constant;
import com.jzg.erp.model.IdNameValue;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.DateTimeUtils;
import com.jzg.erp.utils.MTextUtils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.widget.MActionSheet;
import com.jzg.erp.widget.RecycleViewDivider;
import com.jzg.erp.widget.XRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 配置信息
 * @author zealjiang
 * @time 2016/8/3 20:18
 */
public class ProcedureInfoFragment extends NewBaseFragment implements OnItemClickListener {
    @Bind(R.id.xrv)
    XRecyclerView xrv;
    private ItemAdapter adapter;
    private List<IdNameValue> mList;
    private View rootView;
    private Context mContext;
    /**年检到期日*/
    private final int REQUEST_NJDQR = 0;
    /**交强险到期日*/
    private final int REQUEST_JQXDQR = 1;
    /**车船税到期日*/
    private final int REQUEST_CCSDQR = 2;
    /**路桥费到期日*/
    private final int REQUEST_LQFDQR = 3;
    /**商业险到期日*/
    private final int REQUEST_SYXDQR = 4;
    private ProcedureInfoFragment mProcedureInfoFragment;
    /**通用*/
    private String[] ConfigCommonArray;
    /**新车质保*/
    private String[] QANewCar;
    /**购置税*/
    private String[] PurchaseTax;
    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add(new IdNameValue("","年检到期日",""));
        mList.add(new IdNameValue("","交强险到期日",""));
        mList.add(new IdNameValue("","过户次数",""));
        mList.add(new IdNameValue("","车船税到期日",""));
        mList.add(new IdNameValue("","4S店定期保养",""));
        mList.add(new IdNameValue("","路桥费到期日",""));
        mList.add(new IdNameValue("","商业险到期日",""));
        mList.add(new IdNameValue("","钥匙",""));
        mList.add(new IdNameValue("","新车质保",""));
        mList.add(new IdNameValue("","行驶证",""));

        mList.add(new IdNameValue("","商险金额",""));
        mList.add(new IdNameValue("","过户票",""));
        mList.add(new IdNameValue("","车辆说明书",""));
        mList.add(new IdNameValue("","登记证",""));
        mList.add(new IdNameValue("","购置税",""));
        mList.add(new IdNameValue("","新车保养手册",""));
        mList.add(new IdNameValue("","新车发票",""));

        ConfigCommonArray = getResources().getStringArray(
                R.array.config_common);

        QANewCar = getResources().getStringArray(
                R.array.QA_NEW_CAR);
        PurchaseTax = getResources().getStringArray(
                R.array.purchase_tax);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_baseinfo, container, false);
            ButterKnife.bind(this, rootView);
            EventBus.getDefault().register(this);
            mContext = this.getContext();
            mProcedureInfoFragment = this;
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

        SubmitParamWrapper.ProcedureInfo procedureInfo = JzgApp.getAppContext().getSubmitParam().getCarInfo().getProcedureInfo();
        if(null==procedureInfo){
            procedureInfo = new SubmitParamWrapper.ProcedureInfo();
            JzgApp.getAppContext().getSubmitParam().getCarInfo().setProcedureInfo(procedureInfo);
        }else{
            setInitValues(procedureInfo);
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
     * 初始化显示手续信息
     * @author zealjiang
     * @time 2016/8/11 9:58
     */
    public void setInitValues(SubmitParamWrapper.ProcedureInfo carInfoModel){

        //年检到期日
        mList.get(0).setValue(DateTimeUtils.formatMillsStrM(carInfoModel.getYearlyInspectDate(),DateTimeUtils.YYYYMMDD));

        //交强险到期日
        mList.get(1).setValue(MTextUtils.empty(DateTimeUtils.formatMillsStrM(carInfoModel.getCompulsoryInsuranceDate(),DateTimeUtils.YYYYMMDD)));

        //过户次数
        mList.get(2).setValue(carInfoModel.getRegTimes());

        //车船税到期日
        mList.get(3).setValue(MTextUtils.empty(DateTimeUtils.formatMillsStrM(carInfoModel.getTaxDate(),DateTimeUtils.YYYYMMDD)));

        //4S店定期保养
        mList.get(4).setValue(carInfoModel.getMaintain4s());

        //路桥费到期日
        mList.get(5).setValue(MTextUtils.empty(DateTimeUtils.formatMillsStrM(carInfoModel.getRoadBridgeDate(),DateTimeUtils.YYYYMMDD)));

        //商业险到期日
        mList.get(6).setValue(MTextUtils.empty(DateTimeUtils.formatMillsStrM(carInfoModel.getCommercialInsuranceDate(),DateTimeUtils.YYYYMMDD)));

        //钥匙
        mList.get(7).setValue(carInfoModel.getKey());

        //新车质保
        mList.get(8).setValue(carInfoModel.getNewCarWarranty());

        //行驶证
        mList.get(9).setValue(carInfoModel.getDriveLicense());

        //商险金额
        mList.get(10).setValue(carInfoModel.getCommercialInsuranceMoney());

        //过户票
        mList.get(11).setValue(carInfoModel.getTransferBill());

        //车辆说明书
        mList.get(12).setValue(carInfoModel.getInstruction());

        //登记证
        mList.get(13).setValue(carInfoModel.getRegistration());

        //购置税
        mList.get(14).setValue(carInfoModel.getPurchaseTax());

        //新车保养手册
        mList.get(15).setValue(carInfoModel.getMaintainManual());

        //新车发票
        mList.get(16).setValue(carInfoModel.getNewCarInvoice());

    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        Intent intent = new Intent();
        switch (position){
            case 0:
                //年检到期日
                showDatePicker(REQUEST_NJDQR);
                break;
            case 1:
                //交强险到期日
                showDateDayPicker(REQUEST_JQXDQR);
                break;
            case 2:
                //过户次数
                start_InputResult(Constant.guohu_num, "请填写过户次数", "请正确填写  单位：次",1,2,mList.get(2).getValue());
                break;
            case 3:
                //车船税到期日
                showDateDayPicker(REQUEST_CCSDQR);
                break;
            case 4:
                //4S店定期保养
                configCommon(4);
                break;
            case 5:
                //路桥费到期日
                showDateDayPicker(REQUEST_LQFDQR);
                break;
            case 6:
                //商业险到期日
                showDateDayPicker(REQUEST_SYXDQR);
                break;
            case 7:
                //钥匙
                start_InputResult(Constant.key_num, "请填写钥匙把数", "请正确填写  单位：把",1,2,mList.get(7).getValue());
                break;
            case 8:
                //新车质保
                commonSheet(8,QANewCar);
                break;
            case 9:
                //行驶证
                configCommon(9);
                break;
            case 10:
                //商险金额
                start_InputResult(Constant.shangyexiane, "请填写商险金额", "请正确填写  单位：元",5,7,mList.get(10).getValue());
                break;
            case 11:
                //过户票
                configCommon(11);
                break;
            case 12:
                //车辆说明书
                configCommon(12);
                break;
            case 13:
                //登记证
                configCommon(13);
                break;
            case 14:
                //购置税
                commonSheet(14,PurchaseTax);
                break;
            case 15:
                //新车保养手册
                configCommon(15);
                break;
            case 16:
                //新车发票
                configCommon(16);
                break;
        }
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_NJDQR:
                if (data != null) {
                    String Time_MSG = data.getStringExtra(Constant.car_Time_MSG);
                    //年检到期日
                    mList.get(0).setValue(Time_MSG);
                    refresh();

                }
                break;
            case REQUEST_JQXDQR:
                if (data != null) {
                    String Time_MSG = data.getStringExtra(Constant.car_Time_MSG);
                    //交强险到期日
                    mList.get(1).setValue(Time_MSG);
                    refresh();

                }
                break;
            case Constant.guohu_num:
                if (data != null) {
                    String guohu_num = data.getStringExtra(Constant.activity_input);
                    mList.get(2).setValue(guohu_num);
                    refresh();
                }
            break;
            case REQUEST_CCSDQR:
                if (data != null) {
                    String Time_MSG = data.getStringExtra(Constant.car_Time_MSG);
                    //车船税到期日
                    mList.get(3).setValue(Time_MSG);
                    refresh();
                }
                break;
            case REQUEST_LQFDQR:
                if (data != null) {
                    String Time_MSG = data.getStringExtra(Constant.car_Time_MSG);
                    //路桥费到期日
                    mList.get(5).setValue(Time_MSG);
                    refresh();
                }
                break;
            case REQUEST_SYXDQR:
                if (data != null) {
                    String Time_MSG = data.getStringExtra(Constant.car_Time_MSG);
                    //商业险到期日
                    mList.get(6).setValue(Time_MSG);
                    refresh();

                }
                break;
            case Constant.key_num:
                if (data != null) {
                    String key_num = data.getStringExtra(Constant.activity_input);
                    //钥匙
                    mList.get(7).setValue(key_num);
                    refresh();
                }
                break;
            case Constant.shangyexiane:
                if (data != null) {
                    String shangyexiane = data.getStringExtra(Constant.activity_input);
                    //商险金额
                    mList.get(10).setValue(shangyexiane);

                    refresh();
                }
                break;
        }
    }

    private void configCommon(final int position){
        MActionSheet.createBuilder(mContext, mProcedureInfoFragment.getChildFragmentManager())
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

    private void commonSheet(final int position,final String[] array){
        MActionSheet.createBuilder(mContext, mProcedureInfoFragment.getChildFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles(array)
                .setCancelableOnTouchOutside(true)
                .setListener(new MActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(MActionSheet actionSheet, boolean isCancel) {
                    }

                    @Override
                    public void onOtherButtonClick(MActionSheet actionSheet, int index) {
                        mList.get(position).setValue(array[index]);
                        refresh();
                    }
                }).show();
    }

    /**
     * 显示时间弹出
     */
    private void showDatePicker(int request)
    {
        //选择的车型是哪年的
        //int year = Integer.valueOf(ACache.get(mContext).getAsString("carYear"));

        final Calendar end = Calendar.getInstance();
        int curYear = end.get(Calendar.YEAR);
        int curMonth = end.get(Calendar.MONTH);

        String[] yearShow = {(curYear)+"",(curYear+1)+"",(curYear+2)+"",(curYear+3)+"",
                (curYear+4)+"",(curYear+5)+"",(curYear+6)+"",(curYear+7)+"",
                (curYear+8)+"",(curYear+9)+""};

        Intent in = new Intent(mContext, SheetTimeBaoxianAty.class);
        in.putExtra("Maxyear", curYear+10);
        in.putExtra("Minyear", curYear-20);
        in.putExtra("curYear", curYear);
        in.putExtra("curMonth", curMonth+1);
        in.putExtra("yearShow", yearShow);
        startActivityForResult(in, request);
    }


    /**
     * 显示时间弹出-年月日
     */
    private void showDateDayPicker(int request)
    {
        final Calendar start = Calendar.getInstance();
        //选择的车型是哪年的
        //int year = Integer.valueOf(ACache.get(mContext).getAsString("carYear"));

        final Calendar end = Calendar.getInstance();
        int curYear = end.get(Calendar.YEAR);
        int curMonth = end.get(Calendar.MONTH);
        int curDay = end.get(Calendar.DAY_OF_MONTH);

        String[] yearShow = {(curYear)+"",(curYear+1)+"",(curYear+2)+"",(curYear+3)+"",
                (curYear+4)+"",(curYear+5)+"",(curYear+6)+"",(curYear+7)+"",
                (curYear+8)+"",(curYear+9)+""};

        Intent in = new Intent(mContext, SheetTimeDateAty.class);
        in.putExtra("Maxyear", curYear+20);
        in.putExtra("Minyear", curYear-20);
        in.putExtra("curYear", curYear);
        in.putExtra("curMonth", curMonth+1);
        in.putExtra("curDay", curDay);
        in.putExtra("yearShow", yearShow);
        startActivityForResult(in, request);
    }

    /**
     * 刷新
     * @author zealjiang
     * @time 2016/8/4 16:18
     */
    private void refresh(){
        adapter.notifyDataSetChanged();

        int i = getNotEmptyValue();
        ((CarInfoActivity)this.getActivity()).setTabText(2,"手续信息"+" "+i+"/"+mList.size());
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
     * 手续信息必填项是否全填了
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
     * @time 2016/8/11 18:08
     */
    public void setValues(CarInfoModel carInfoModel){
        CarInfoModel.DataBean.CarinfoBean carinfoBean = carInfoModel.getData().getCarinfo();
        //年检到期日
        mList.get(0).setValue(DateTimeUtils.formatMillsStrM(carinfoBean.getCheckEndDate(),DateTimeUtils.YYYYMMDD));
        //交强险到期日
        mList.get(1).setValue(DateTimeUtils.formatMillsStrM(carinfoBean.getSecureYear(),DateTimeUtils.YYYYMMDD));
        //过户次数
        mList.get(2).setValue(carinfoBean.getTransferNum()+"");
        //车船税到期日
        mList.get(3).setValue(DateTimeUtils.formatMillsStrM(carinfoBean.getVehicleAndVesselTaxExpired(),DateTimeUtils.YYYYMMDD));
        //4S店定期保养
        mList.get(4).setValue(carinfoBean.getD4SMaintenance());
        //路桥费到期日
        mList.get(5).setValue(DateTimeUtils.formatMillsStrM(carinfoBean.getLuQiaoFeeExpired(),DateTimeUtils.YYYYMMDD));
        //商业险到期日
        mList.get(6).setValue(DateTimeUtils.formatMillsStrM(carinfoBean.getSecureYearBusiness(),DateTimeUtils.YYYYMMDD));
        //钥匙
        mList.get(7).setValue(carinfoBean.getCarKey()+"");
        //新车质保
        mList.get(8).setValue(carinfoBean.getNewCarWarranty());
        //行驶证
        mList.get(9).setValue(carinfoBean.getDrivingLicense());
        //商险金额
        mList.get(10).setValue(carinfoBean.getCommercialInsuranceAmount()+"");
        //过户票
        mList.get(11).setValue(carinfoBean.getTransferTicket());
        //车辆说明书
        mList.get(12).setValue(carinfoBean.getVehicleSpecification());
        //登记证
        mList.get(13).setValue(carinfoBean.getRegistrationLicense());
        //购置税
        mList.get(14).setValue(carinfoBean.getPurchaseTax());
        //新车保养手册
        mList.get(15).setValue(carinfoBean.getCarMaintenanceManual());
        //新车发票
        mList.get(16).setValue(carinfoBean.getNewInvoice());

        refresh();

    }

    /**
     * 保存信息
     * @author zealjiang
     * @time 2016/8/11 17:26
     */
    public void saveInfo(){
        if(null==JzgApp.getAppContext().getSubmitParam().getCarInfo()){
            SubmitParamWrapper.CarInfo carInfo = new SubmitParamWrapper.CarInfo();
            JzgApp.getAppContext().getSubmitParam().setCarInfo(carInfo);
        }
        SubmitParamWrapper.ProcedureInfo carInfoModel = JzgApp.getAppContext().getSubmitParam().getCarInfo().getProcedureInfo();
        if(null==carInfoModel){
            carInfoModel = new SubmitParamWrapper.ProcedureInfo();
            JzgApp.getAppContext().getSubmitParam().getCarInfo().setProcedureInfo(carInfoModel);
        }

        //年检到期日
        carInfoModel.setYearlyInspectDate(mList.get(0).getValue());

        //交强险到期日
        carInfoModel.setCompulsoryInsuranceDate(mList.get(1).getValue());

        //过户次数
        carInfoModel.setRegTimes(mList.get(2).getValue());

        //车船税到期日
        carInfoModel.setTaxDate(mList.get(3).getValue());

        //4S店定期保养
        carInfoModel.setMaintain4s(mList.get(4).getValue());

        //路桥费到期日
        carInfoModel.setRoadBridgeDate(mList.get(5).getValue());

        //商业险到期日
        carInfoModel.setCommercialInsuranceDate(mList.get(6).getValue());

        //钥匙
        carInfoModel.setKey(mList.get(7).getValue());

        //新车质保
        carInfoModel.setNewCarWarranty(mList.get(8).getValue());

        //行驶证
        carInfoModel.setDriveLicense(mList.get(9).getValue());

        //商险金额
        carInfoModel.setCommercialInsuranceMoney(mList.get(10).getValue());

        //过户票
        carInfoModel.setTransferBill(mList.get(11).getValue());

        //车辆说明书
        carInfoModel.setInstruction(mList.get(12).getValue());

        //登记证
        carInfoModel.setRegistration(mList.get(13).getValue());

        //购置税
        carInfoModel.setPurchaseTax(mList.get(14).getValue());

        //新车保养手册
        carInfoModel.setMaintainManual(mList.get(15).getValue());

        //新车发票
        carInfoModel.setNewCarInvoice(mList.get(16).getValue());
    }



    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventCarBaseInfo(CarInfoModel event) {
        handleVinData(event);
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
            setValues(carInfoModel);
        }
    }
}
