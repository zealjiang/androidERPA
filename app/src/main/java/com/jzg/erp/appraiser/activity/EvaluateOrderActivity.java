package com.jzg.erp.appraiser.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.fragment.EvaluateOrderFragment;
import com.jzg.erp.appraiser.model.BaseInfoModel;
import com.jzg.erp.appraiser.model.CarAllDataModel;
import com.jzg.erp.appraiser.model.PGOrderModel;
import com.jzg.erp.appraiser.presenter.BaseInfoPresenter;
import com.jzg.erp.appraiser.presenter.CarAllDataPresenter;
import com.jzg.erp.appraiser.presenter.QueryCarConcernPresenter;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.base.BaseObject;
import com.jzg.erp.event.MenuShowEvent;
import com.jzg.erp.global.IntentKey;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.utils.ScreenUtils;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.view.IBaseView;
import com.jzg.erp.view.ICommon;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EvaluateOrderActivity extends BaseActivity implements QueryCarConcernPresenter.OnDataSucceedListener<BaseObject>,CarAllDataPresenter.OnCarAllDataListener<CarAllDataModel.DataBean>,ICommon,IBaseView {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private List<PGOrderModel.DataBean> dataList;
    private String[] titles;
    private String carId = "";
    private String currPingguNo;
    private QueryCarConcernPresenter presenter;
    private CarAllDataPresenter carAllDataPresenter;
    private int currPos = 0;
    private Map<String,Boolean> showMap;
    
    //请求参数
    private String vin = "";
    private String pgOrderId = "";

    //为了获取VIN码
    private BaseInfoPresenter baseInfoPresenter;
    private boolean isNeedCreateNewPGOrder = false;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_evaluate_order);
        showMap = new HashMap<>();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        presenter = new QueryCarConcernPresenter(this,this);
        carAllDataPresenter = new CarAllDataPresenter(this,this);
        baseInfoPresenter = new BaseInfoPresenter(this);
        Bundle bundle = getIntent().getExtras();
        dataList = bundle.getParcelableArrayList("pgOrderArray");
        carId = bundle.getString("carId");

        baseInfoPresenter.getBaseInfoList(carId);

        titles = new String[dataList.size()];
        if(dataList!=null && dataList.size()>0){
            for(int i=0;i<dataList.size();i++){
                titles[i]=dataList.get(i).getPingguNo();
                showMap.put(dataList.get(i).getPingguNo(),false);
            }
        }
        currPingguNo = dataList.get(0).getPingguNo();
        pgOrderId = dataList.get(0).getID()+"";


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public Fragment getItem(int position) {
                pgOrderId = dataList.get(position).getID()+"";
                return new EvaluateOrderFragment(titles[position],carId,pgOrderId);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                currPos = position;
                pgOrderId = dataList.get(position).getID()+"";
                setTitle(titles[position]+"("+(position+1)+"/"+titles.length+")");
                currPingguNo = dataList.get(position).getPingguNo();
                showIvRight(showMap.get(currPingguNo));
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Subscribe
    public void onEventMainThread(MenuShowEvent event){
        boolean show = event.isShow();
        String pingguNo = event.getPingguNo();
        LogUtil.e(TAG,"收到消息,"+pingguNo+":"+ show);
        showMap.put(pingguNo,show);
        showIvRight(showMap.get(currPingguNo));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setData() {
        setTitle(titles[0]);
        setTitle(titles[0]+"("+1+"/"+titles.length+")");
        setImageRight(R.mipmap.tianjia01);
        showIvRight(false);

    }

    private PopupWindow window;

    private void showPop(View v) {
        if (window == null) {
            View menu = UIUtils.inflate(R.layout.layout_menu);
            window = new PopupWindow(menu, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            TextView tvToBuy = (TextView) menu.findViewById(R.id.tvToBuy);
            TextView tvNewEvaluate = (TextView) menu.findViewById(R.id.tvNewEvaluate);
            TextView tvDelete = (TextView) menu.findViewById(R.id.tvDelete);
            tvToBuy.setOnClickListener(listener);
            tvNewEvaluate.setOnClickListener(listener);
            tvDelete.setOnClickListener(listener);
            window.setContentView(menu);
            window.setFocusable(true);
            window.setOutsideTouchable(true);
            window.setBackgroundDrawable(new BitmapDrawable());
            window.setAnimationStyle(R.style.AnimAlpha);
            menu.setOnClickListener(listener);
        }
        window.showAsDropDown(v, 0, -(ScreenUtils.dip2px(this,40)));
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (window != null && window.isShowing()) {
                window.dismiss();
            }
            switch (v.getId()) {
                case R.id.tvToBuy:
                    Intent intent = new Intent(EvaluateOrderActivity.this,BuyApplyActivity.class);
                    intent.putExtra("pingguNo",currPingguNo);
                    intent.putExtra("carId",carId);
                    startActivityForResult(intent, IntentKey.REQ_CODE_BUY_CAR);
                    break;
                case R.id.tvNewEvaluate:
                    new AlertDialog.Builder(EvaluateOrderActivity.this).setMessage("新建评估单确认")
                            .setMessage("您确认要创建新的评估单么？创建新的评估单并提交成功后，系统将自动删除您所创建的旧的评估单！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if(TextUtils.isEmpty(vin)){//Vin码可能获取失败
                                baseInfoPresenter.getBaseInfoList(carId);
                                isNeedCreateNewPGOrder = true;
                            }else{
                                isNeedCreateNewPGOrder = false;
                                requestCarAllData();
                            }

                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                    break;
                case R.id.tvDelete:
                    new AlertDialog.Builder(EvaluateOrderActivity.this).setMessage("确认要删除此评估单吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            confirm();

                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                    break;
                case R.id.rlContainer:
                    window.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IntentKey.REQ_CODE_BUY_CAR && resultCode==RESULT_OK){
            finish();
        }
    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.ivRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                showPop(view);
                break;
            case R.id.ivRight:
                showPop(view);
                break;
        }
    }

    private void confirm(){
       new AlertDialog.Builder(this).setTitle("删除确认").setMessage("您确认要删除该评估单么？删除后该评估单将作废！").setCancelable(true)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        presenter.deleteOrder(currPingguNo);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    @Override
    public void showData(BaseObject data) {
        if(data.getStatus()==1){
            finish();
        }else{
            MyToast.showShort("删除评估单失败");
        }
    }

    /**
     * 获取车辆的所有信息
     * @author zealjiang
     * @time 2016/8/28 16:17
     */
    private void requestCarAllData(){
        carAllDataPresenter.getCarAllData(vin,pgOrderId);
    }

    @Override
    public void succeedCarAllData(CarAllDataModel.DataBean data) {
        //取到所有的值后，为SubmitParamWrapper赋值
        setAllData(data);
        //回到评估界面，并关闭之前所有的Activity
        //将本Activity从要Stack中移除，防止finishAll()时程序退出
        JzgApp.getAppContext().remove(this);
        //关掉Stack中的Activity
        JzgApp.getAppContext().finishAll();
        //跳转到评估页评估Fragment
        Intent intent = new Intent(this, AppraiserHomeActivity.class);
        startActivity(intent);
        //关掉本Activity
        finish();
    }

    @Override
    public void succeed(Object object) {//基本信息
        BaseInfoModel.DataBean dataBean = (BaseInfoModel.DataBean)object;
        if(null!=dataBean){
            vin = dataBean.getVIN();
            if(isNeedCreateNewPGOrder){
                requestCarAllData();
            }
        }
    }

    /**
     * 赋值
     * @author zealjiang
     * @time 2016/8/28 17:29
     */
    private void setAllData(CarAllDataModel.DataBean data){

        //车辆
        SubmitParamWrapper.CarInfo carInfo = JzgApp.getAppContext().getSubmitParam().getCarInfo();
        if(null== carInfo){
            carInfo = new SubmitParamWrapper.CarInfo();
            JzgApp.getAppContext().getSubmitParam().setCarInfo(carInfo);
        }

        //基本信息
        SubmitParamWrapper.CarBaseInfo carBaseInfo = JzgApp.getAppContext().getSubmitParam().getCarInfo().getCarBaseInfo();
        if(null==carBaseInfo){
            carBaseInfo = new SubmitParamWrapper.CarBaseInfo();
            JzgApp.getAppContext().getSubmitParam().getCarInfo().setCarBaseInfo(carBaseInfo);
        }

        //配置信息
        ArrayList<String> allocInfoList = JzgApp.getAppContext().getSubmitParam().getCarInfo().getAllocInfoList();
        if(null== allocInfoList){
            allocInfoList = new ArrayList<String>();
            JzgApp.getAppContext().getSubmitParam().getCarInfo().setAllocInfoList(allocInfoList);
        }
        allocInfoList.clear();

        //手续信息
        SubmitParamWrapper.ProcedureInfo procedureInfo = JzgApp.getAppContext().getSubmitParam().getCarInfo().getProcedureInfo();
        if(null==procedureInfo){
            procedureInfo = new SubmitParamWrapper.ProcedureInfo();
            JzgApp.getAppContext().getSubmitParam().getCarInfo().setProcedureInfo(procedureInfo);
        }

        //车况描述
        SubmitParamWrapper.CarCondition carCondition = JzgApp.getAppContext().getSubmitParam().getCarCondition();
        if(null==carCondition){
            carCondition = new SubmitParamWrapper.CarCondition();
            JzgApp.getAppContext().getSubmitParam().setCarCondition(carCondition);
        }

        //vin码
        carInfo.setVin(data.getCarinfo().getVIN());

        //基本信息
        CarAllDataModel.DataBean.CarinfoBean carinfoBean =  data.getCarinfo();
        carBaseInfo.setBrand(carinfoBean.getMakeName());
        carBaseInfo.setBrandId(carinfoBean.getMakeID()+"");
        carBaseInfo.setSeries(carinfoBean.getModelName());
        carBaseInfo.setSeriesId(carinfoBean.getModelID()+"");
        carBaseInfo.setStyle(carinfoBean.getStyleName());
        carBaseInfo.setStyleId(carinfoBean.getStyleID()+"");
        carBaseInfo.setCardId(carinfoBean.getLicensePlate());
        carBaseInfo.setFirstRegDate(carinfoBean.getRegdate());
        carBaseInfo.setColor(carinfoBean.getColor());
        carBaseInfo.setInnerDecor(carinfoBean.getInnerClolor());
        carBaseInfo.setMileage(carinfoBean.getMileage()+"");
        carBaseInfo.setManufactureDate(carinfoBean.getManufactureDate());
        carBaseInfo.setNewCarPrice(carinfoBean.getNewCarPrice());
        carBaseInfo.setCarId(carinfoBean.getID());


        //配置信息
        CarAllDataModel.DataBean.CarCIBean carCIBean =  data.getCarCI();
        allocInfoList.add("1,"+carCIBean.getAbs());
        allocInfoList.add("2,"+carCIBean.getAirBag());
        allocInfoList.add("3,"+carCIBean.getEba());
        allocInfoList.add("4,"+carCIBean.getEsp());
        allocInfoList.add("5,"+carCIBean.getElecMirror());
        allocInfoList.add("6,"+carCIBean.getNavi());
        allocInfoList.add("7,"+carCIBean.getCdDvd());
        allocInfoList.add("8,"+carCIBean.getAirCondition());
        allocInfoList.add("9,"+carCIBean.getCenterControlLock());
        allocInfoList.add("10,"+carCIBean.getRemoteKey());
        allocInfoList.add("11,"+carCIBean.getOneKeyStart());
        allocInfoList.add("12,"+carCIBean.getElecWindow());
        allocInfoList.add("13,"+carCIBean.getBackRadar());
        allocInfoList.add("14,"+carCIBean.getBackVideo());
        allocInfoList.add("15,"+carCIBean.getSpareTire());
        allocInfoList.add("16,"+carCIBean.getConstSpeedCruise());
        allocInfoList.add("17,"+carCIBean.getSkyLight());
        allocInfoList.add("18,"+carCIBean.getLeatherSeats());
        allocInfoList.add("19,"+carCIBean.getElecSeats());
        allocInfoList.add("20,"+carCIBean.getAutoParking());


        //手续信息
        procedureInfo.setYearlyInspectDate(carinfoBean.getCheckEndDate());
        procedureInfo.setCompulsoryInsuranceDate(carinfoBean.getSecureYear()+"");
        procedureInfo.setRegTimes(carinfoBean.getTransferNum()+"");
        procedureInfo.setTaxDate(carinfoBean.getVehicleAndVesselTaxExpired()+"");
        procedureInfo.setMaintain4s(carinfoBean.getD4SMaintenance());
        procedureInfo.setRoadBridgeDate(carinfoBean.getLuQiaoFeeExpired()+"");
        procedureInfo.setCommercialInsuranceDate(carinfoBean.getSecureYearBusiness());
        procedureInfo.setKey(carinfoBean.getCarKey()+"");
        procedureInfo.setNewCarWarranty(carinfoBean.getNewCarWarranty());
        procedureInfo.setDriveLicense(carinfoBean.getDrivingLicense());
        procedureInfo.setCommercialInsuranceMoney(carinfoBean.getCommercialInsuranceAmount()+"");
        procedureInfo.setTransferBill(carinfoBean.getTransferTicket());
        procedureInfo.setInstruction(carinfoBean.getVehicleSpecification());
        procedureInfo.setRegistration(carinfoBean.getRegistrationLicense());
        procedureInfo.setPurchaseTax(carinfoBean.getPurchaseTax());
        procedureInfo.setMaintainManual(carinfoBean.getCarMaintenanceManual());
        procedureInfo.setNewCarInvoice(carinfoBean.getNewInvoice());


        //车况描述
        List<CarAllDataModel.DataBean.CarCheckBean> carCheckList =  data.getCarCheck();
        List<String> listString = new ArrayList<String>();
        for (int i = 0; i < carCheckList.size(); i++) {
            listString.add(carCheckList.get(i).getId()+","+carCheckList.get(i).getResult());
        }
        carCondition.setData(listString);

        //客户资料
        CarAllDataModel.DataBean.CustomerBean customerBean =  data.getCustomer();
        SubmitParamWrapper.CustomerInfo  customerInfo= new SubmitParamWrapper.CustomerInfo();
        customerInfo.setType(customerBean.getCustomerType());
        customerInfo.setName(customerBean.getCustomerName());
        customerInfo.setCompanyName(customerBean.getCustomerName());
        customerInfo.setPhone(customerBean.getMobile());
        customerInfo.setReplaceCar(customerBean.getReplaceStyle());
        customerInfo.setGender(customerBean.getGender());
        customerInfo.setWantPrice(customerBean.getCustomerWantPrice()+"");
        customerInfo.setWantLevelValue(customerBean.getSellcarLevel());
        customerInfo.setCarHostValue(customerBean.getCustomerSource());
        customerInfo.setPreSellDateValue(customerBean.getPresellTime());
        customerInfo.setWantLevel(customerBean.getSellcarLevelName());
        customerInfo.setCarHostSource(customerBean.getCustomerSourceName());
        customerInfo.setPreSellDate(customerBean.getPresellTimeName());
        customerInfo.setAddress(customerBean.getAddress());
        customerInfo.setCreateUserName(customerBean.getCreateUser());
        customerInfo.setSaleID(customerBean.getSaleID()+"");
        customerInfo.setSaleName(customerBean.getSaleName());
        customerInfo.setCustomerNo(customerBean.getCustomerNo());
        customerInfo.setContact(customerBean.getContact());
        customerInfo.setCustomerId(customerBean.getID());
        JzgApp.getAppContext().getSubmitParam().setCustomerInfo(customerInfo);

        //车型照片
        JzgApp.getAppContext().getSubmitParam().setPhotoItems(data.getCarPic());
        //评估价格
        JzgApp.getAppContext().getSubmitParam().setPriceEvaluation(data.getCarPrice());
    }
}
