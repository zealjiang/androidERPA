package com.jzg.erp.appraiser.fragment;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.activity.AddCustomerInfoActivity;
import com.jzg.erp.appraiser.activity.CarDescActivity;
import com.jzg.erp.appraiser.activity.CarInfoActivity;
import com.jzg.erp.appraiser.activity.MineActivity;
import com.jzg.erp.appraiser.activity.PriceInfoActivity;
import com.jzg.erp.appraiser.activity.UploadPhotoActivity;
import com.jzg.erp.appraiser.model.SubmitFinalJson;
import com.jzg.erp.appraiser.presenter.SubmitEvaluatePresenter;
import com.jzg.erp.appraiser.view.ISubmitView;
import com.jzg.erp.base.BaseObject;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.dialog.ShowMsgDialog;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.model.UserWrapper;
import com.jzg.erp.utils.MyToast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends NewBaseFragment implements ISubmitView {
    @Bind(R.id.ivCheckCustomerInfo)
    ImageView ivCheckCustomerInfo;
    @Bind(R.id.ivBaseInfo)
    ImageView ivBaseInfo;
    @Bind(R.id.ivCheckBaseInfo)
    ImageView ivCheckBaseInfo;
    @Bind(R.id.ivCarDesc)
    ImageView ivCarDesc;
    @Bind(R.id.ivCheckCarDesc)
    ImageView ivCheckCarDesc;
    @Bind(R.id.ivUploadPhoto)
    ImageView ivUploadPhoto;
    @Bind(R.id.ivCheckUploadPhoto)
    ImageView ivCheckUploadPhoto;
    @Bind(R.id.ivEvaluatePrice)
    ImageView ivEvaluatePrice;
    @Bind(R.id.ivCheckEvaluatePrice)
    ImageView ivCheckEvaluatePrice;
    private SubmitParamWrapper param;
    private SubmitEvaluatePresenter presenter;
    private Activity activity;
    private SubmitFinalJson submitFinalJson;

    public RegisterFragment() {
    }
    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }
    private void refresh(){
        param = JzgApp.getAppContext().getSubmitParam();
        SubmitParamWrapper.CustomerInfo customerInfo = param.getCustomerInfo();
        if(customerInfo!=null && customerInfo.isChecked()){
            ivCheckCustomerInfo.setVisibility(View.VISIBLE);
        }else{
            ivCheckCustomerInfo.setVisibility(View.GONE);
        }

        SubmitParamWrapper.CarInfo carInfo = param.getCarInfo();
        if(carInfo!=null && carInfo.isChecked()){
            ivCheckBaseInfo.setVisibility(View.VISIBLE);
        }else{
            ivCheckBaseInfo.setVisibility(View.GONE);
        }

        if(carInfo!=null && carInfo.isChecked()){
            ivUploadPhoto.setImageResource(R.mipmap.shangchuantupian);
            ivCarDesc.setImageResource(R.mipmap.ckms_def);
            ivEvaluatePrice.setImageResource(R.mipmap.jiagexinxi);
            ivCheckBaseInfo.setVisibility(View.VISIBLE);
        }else{
            ivUploadPhoto.setImageResource(R.mipmap.shangchuantupian_dis);
            ivCarDesc.setImageResource(R.mipmap.ckms_dis);
            ivEvaluatePrice.setImageResource(R.mipmap.jiagexinxi_dis);
            ivCheckBaseInfo.setVisibility(View.GONE);
        }

        SubmitParamWrapper.CarCondition carCondition = param.getCarCondition();
        if(carCondition!=null && carCondition.isChecked()){
            ivCheckCarDesc.setVisibility(View.VISIBLE);
        }else{
            ivCheckCarDesc.setVisibility(View.GONE);
        }

        List<SubmitParamWrapper.PhotoItem> photoItems = param.getPhotoItems();
        if(photoItems!=null && photoItems.size()>0&& photoItems.get(0).getDictID()==349){
            ivCheckUploadPhoto.setVisibility(View.VISIBLE);
        }else{
            ivCheckUploadPhoto.setVisibility(View.GONE);
        }

        SubmitParamWrapper.PriceEvaluation priceEvaluation = param.getPriceEvaluation();
        if(priceEvaluation!=null && priceEvaluation.isChecked()){
            ivCheckEvaluatePrice.setVisibility(View.VISIBLE);
        }else{
            ivCheckEvaluatePrice.setVisibility(View.GONE);
        }
    }



    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, rootView);
        activity = this.getActivity();
        presenter = new SubmitEvaluatePresenter(this);
        return rootView;
    }
    @Override
    protected void setView() {
        setTitle("评估");
        setImageLeft(R.mipmap.ic_account);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rlCustomerInfo, R.id.rlBaseInfo, R.id.rlCarDesc, R.id.rlUploadPhoto, R.id.rlEvaluatePrice, R.id.btnConfirm,R.id.ivLeft})
    public void onClick(View view) {
        boolean canStartActivity = false;
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rlCustomerInfo:
                intent.setClass(getActivity(), AddCustomerInfoActivity.class);
                canStartActivity = true;
                break;
            case R.id.rlBaseInfo:
                intent.setClass(getActivity(), CarInfoActivity.class);
                canStartActivity = true;
                break;
            case R.id.rlCarDesc:
                //判断基本信息是否填写完成
                if(ivCheckBaseInfo.getVisibility()==View.VISIBLE){
                    intent.setClass(getActivity(), CarDescActivity.class);
                    canStartActivity = true;
                }else{
                    MyToast.showShort("请填写基本信息");
                }
                break;
            case R.id.rlUploadPhoto:
                //判断车况描述是否填写完成
                if(ivCheckBaseInfo.getVisibility()==View.VISIBLE){
                    intent.setClass(getActivity(), UploadPhotoActivity.class);
                    canStartActivity = true;
                }else{
                    MyToast.showShort("请填写车况描述");
                }
                break;
            case R.id.rlEvaluatePrice:
                //判断上传图片是否填写完成
                if(ivCheckBaseInfo.getVisibility()==View.VISIBLE){
                    intent.setClass(getActivity(), PriceInfoActivity.class);
                    canStartActivity = true;
                }else{
                    MyToast.showShort("请上传图片");
                }
                break;
            case R.id.btnConfirm:
                checkAndSubmit();
                break;
            case R.id.ivLeft:
                canStartActivity = true;
                intent.setClass(getActivity(), MineActivity.class);
                break;
        }

        if(view.getId()!=R.id.btnConfirm){
            if(canStartActivity){
                startActivity(intent);
            }
        }
    }

    private void checkAndSubmit(){
        SubmitParamWrapper.CustomerInfo customerInfo = param.getCustomerInfo();
        SubmitParamWrapper.CarInfo carInfo = param.getCarInfo();
        SubmitParamWrapper.CarCondition carCondition = param.getCarCondition();
        List<SubmitParamWrapper.PhotoItem> photoItems = param.getPhotoItems();
        SubmitParamWrapper.PriceEvaluation priceEvaluation = param.getPriceEvaluation();
        if(customerInfo==null || !customerInfo.isChecked()){
            MyToast.showShort("客户信息不全，不能提交");
            return ;
        }
        if(carInfo==null || !carInfo.isChecked()){
            MyToast.showShort("车辆信息不全，不能提交");
            return ;
        }
        if(carCondition==null || !carCondition.isChecked()){
            MyToast.showShort("车况信息不全，不能提交");
            return ;
        }

        if(photoItems==null || photoItems.size()==0 || photoItems.get(0).getViewUrl() == null||!photoItems.get(0).getViewUrl().startsWith("http")){
            MyToast.showShort("车辆照片不全，不能提交");
            return ;
        }
        if(priceEvaluation==null || !priceEvaluation.isChecked()){
            MyToast.showShort("价格信息不全，不能提交");
            return ;
        }

        //转换客户信息
        SubmitFinalJson.DataBean.Customer customer = new SubmitFinalJson.DataBean.Customer();//客户信息
        int type = customerInfo.getType();
        customer.setAddress(customerInfo.getAddress());
        if(type==1){
            customer.setCustomerType(String.valueOf(1));
            customer.setCustomerName(customerInfo.getName());
            customer.setReplaceStyle(customerInfo.getReplaceCar());
        }else{
            customer.setCustomerType(String.valueOf(2));
            customer.setCustomerName(customerInfo.getCompanyName());
            customer.setContact(customerInfo.getName());
        }
        UserWrapper.User user = JzgApp.getUser();
        customer.setCreateUserName(user.getTrueName());
        customer.setCustomerSource(customerInfo.getCarHostValue());
        customer.setCustomerWantPrice(customerInfo.getWantPrice());
        customer.setGender(customerInfo.getGender());
        customer.setPresellTime(customerInfo.getPreSellDateValue());
        customer.setSellerId(customerInfo.getCustomerId());
        customer.setSellcarLevel(customerInfo.getWantLevelValue());
        customer.setMobile(customerInfo.getPhone());
        customer.setSaleID(priceEvaluation.getSaleID()+"");
        customer.setSaleName(priceEvaluation.getSaleName());
        customer.setID(customerInfo.getCustomerId());

        SubmitFinalJson.DataBean.CarInfo carInfoFinal = new SubmitFinalJson.DataBean.CarInfo();//车辆信息
        //vin码
        carInfoFinal.setVIN(carInfo.getVin());
        //基本信息
        //车辆ID
        carInfoFinal.setID(carInfo.getCarBaseInfo().getCarId());
        //品牌ID
        carInfoFinal.setMakeID(Integer.valueOf(carInfo.getCarBaseInfo().getBrandId()));
        //品牌名称
        carInfoFinal.setMakeName(carInfo.getCarBaseInfo().getBrand());
        //车系ID
        carInfoFinal.setModelID(Integer.valueOf(carInfo.getCarBaseInfo().getSeriesId()));
        //车系名称
        carInfoFinal.setModelName(String.valueOf(carInfo.getCarBaseInfo().getSeries()));
        //车型ID
        carInfoFinal.setStyleID(Integer.valueOf(carInfo.getCarBaseInfo().getStyleId()));
        //车型名称
        carInfoFinal.setStyleName(carInfo.getCarBaseInfo().getStyle());
        //车牌号
        carInfoFinal.setLicensePlate(carInfo.getCarBaseInfo().getCardId());
        //初次上牌
        carInfoFinal.setRegdate(carInfo.getCarBaseInfo().getFirstRegDate());
        //颜色
        carInfoFinal.setColor(carInfo.getCarBaseInfo().getColor());
        //内饰
        carInfoFinal.setInnerClolor(carInfo.getCarBaseInfo().getInnerDecor());
        //表显里程
        carInfoFinal.setMileage(carInfo.getCarBaseInfo().getMileage());
        //出厂日期
        carInfoFinal.setManufactureDate(carInfo.getCarBaseInfo().getManufactureDate());
        //新车市场价
        carInfoFinal.setNewCarPrice(String.valueOf(carInfo.getCarBaseInfo().getNewCarPrice()));

        //手续信息
        //年检到期日
        carInfoFinal.setCheckEndDate(carInfo.getProcedureInfo().getYearlyInspectDate());
        //交强险到期日
        carInfoFinal.setSecureYear(carInfo.getProcedureInfo().getCompulsoryInsuranceDate());
        //过户次数
        carInfoFinal.setTransferNum(carInfo.getProcedureInfo().getRegTimes());
        //车船税到期日
        carInfoFinal.setVehicleAndVesselTaxExpired(carInfo.getProcedureInfo().getTaxDate());
        //4S店定期保养
        carInfoFinal.setD4SMaintenance(carInfo.getProcedureInfo().getMaintain4s());
        //路桥费到期日
        carInfoFinal.setLuQiaoFeeExpired(carInfo.getProcedureInfo().getRoadBridgeDate());
        //商业险到期日
        carInfoFinal.setSecureYearBusiness(carInfo.getProcedureInfo().getCommercialInsuranceDate());
        //钥匙
        carInfoFinal.setCarKey(carInfo.getProcedureInfo().getKey());
        //新车质保
        carInfoFinal.setNewCarWarranty(carInfo.getProcedureInfo().getNewCarWarranty());
        //行驶证
        carInfoFinal.setDrivingLicense(carInfo.getProcedureInfo().getDriveLicense());
        //商险金额
        carInfoFinal.setCommercialInsuranceAmount(carInfo.getProcedureInfo().getCommercialInsuranceMoney());
        //过户票
        carInfoFinal.setTransferTicket(carInfo.getProcedureInfo().getTransferBill());
        //车辆说明书
        carInfoFinal.setVehicleSpecification(carInfo.getProcedureInfo().getInstruction());
        //登记证
        carInfoFinal.setRegistrationLicense(carInfo.getProcedureInfo().getRegistration());
        //购置税
        carInfoFinal.setPurchaseTax(carInfo.getProcedureInfo().getPurchaseTax());
        //新车保养手册
        carInfoFinal.setCarMaintenanceManual(carInfo.getProcedureInfo().getMaintainManual());
        //新车发票
        carInfoFinal.setNewInvoice(carInfo.getProcedureInfo().getNewCarInvoice());

        SubmitFinalJson.DataBean.CarPrice price = new SubmitFinalJson.DataBean.CarPrice();
        price.setCreateUserId(String.valueOf(user.getUserID()));
        price.setCreateUserName(user.getTrueName());
        price.setPingguPriceMax(String.valueOf(priceEvaluation.getPingguPriceMax()));
        price.setPingguPriceMin(String.valueOf(priceEvaluation.getPingguPriceMin()));
        price.setPingguUserId(String.valueOf(user.getUserID()));
        price.setPingguUserName(user.getTrueName());
        price.setRemark(priceEvaluation.getRemark());
        price.setStoreId(String.valueOf(user.getStoreId()));
        price.setUpdateUserName(user.getTrueName());
        price.setStoreName(user.getStoreName());



        SubmitFinalJson.DataBean jsonData = new SubmitFinalJson.DataBean(customer,carInfoFinal,price,carInfo.getAllocInfoList(),param.getPhotoItems(),param.getCarCondition().getData());
        submitFinalJson = new SubmitFinalJson();
        submitFinalJson.setIsUpdate(0);
        submitFinalJson.setData(jsonData);
        Gson gson = new Gson();
        String jsonCar = gson.toJson(submitFinalJson);
        presenter.submitEvaluate(jsonCar);
    }

    @Override
    public void succeed() {
        MyToast.showShort("创建评估单成功");
        param.clearAllData();
        submitFinalJson = null;
        refresh();
    }

    @Override
    public void unusual(BaseObject baseObject) {
        //检查VIN码的各种情况
        int status = baseObject.getStatus();
        showDialog(status,baseObject.getMessage());
    }

    private void showDialog(int state,String content){
        String title;
        switch (state){
            case -1://该车辆已被收购 ， 无法保存并创建新的评估单
                title = getResources().getString(R.string.dialog_title);
                //content = getResources().getString(R.string.dialog_exist_other);
                ShowMsgDialog.showMaterialDialogNoBtn(activity, title,content);

                break;
            case -2://该车辆已存在处于批准收购状态的评估单 ， 无法保存并创建新的评估单
                title = getResources().getString(R.string.dialog_title);
                ShowMsgDialog.showMaterialDialogNoBtn(activity, title,content);
                break;
            case -3://该车辆已存在本店铺创建的处于待审核状态的评估单，无法保存并创建新的评估单
                title = getResources().getString(R.string.dialog_title);
                ShowMsgDialog.showMaterialDialogNoBtn(activity, title,content);
                break;
            case -4://该车辆已存在本店铺创建的处于待申请状态的评估单，若创建新的评估单，则之前创建的评估单会被自动删除，您是否还要创建新的评估单 ?
                title = getResources().getString(R.string.dialog_title);
                ShowMsgDialog.showMaterialDialog2Btn(activity, title,content, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        submitFinalJson.setIsUpdate(1);
                        Gson gson = new Gson();
                        String jsonCar = gson.toJson(submitFinalJson);
                        presenter.submitEvaluate(jsonCar);
                    }
                });
                break;
            case -5://该车辆已存在其它店铺创建的评估单，您是否需要创建新的评估单 ?
                title = getResources().getString(R.string.dialog_title);
                ShowMsgDialog.showMaterialDialog2Btn(activity, title,content, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        submitFinalJson.setIsUpdate(1);
                        Gson gson = new Gson();
                        String jsonCar = gson.toJson(submitFinalJson);
                        presenter.submitEvaluate(jsonCar);
                    }
                });
                break;

        }
    }
}
