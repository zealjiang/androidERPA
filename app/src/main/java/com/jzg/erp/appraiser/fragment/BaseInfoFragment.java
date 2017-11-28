package com.jzg.erp.appraiser.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.jzg.erp.R;
import com.jzg.erp.adapter.ItemAdapter;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.activity.CarBrandActivity;
import com.jzg.erp.appraiser.activity.CarInfoActivity;
import com.jzg.erp.appraiser.activity.CarModelActivity;
import com.jzg.erp.appraiser.activity.CarTypeActivity;
import com.jzg.erp.appraiser.model.CarInfoModel;
import com.jzg.erp.appraiser.model.ParamOption;
import com.jzg.erp.appraiser.presenter.GetCarConfigByStyleIdPresenter;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.global.Constant;
import com.jzg.erp.model.IdNameValue;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.ACache;
import com.jzg.erp.utils.DataUtils;
import com.jzg.erp.utils.DateTimeUtils;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MTextUtils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.view.ICommon;
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
 * 基本信息
 * @author zealjiang
 * @time 2016/8/3 20:18
 */
public class BaseInfoFragment extends NewBaseFragment implements ICommon, OnItemClickListener {
    @Bind(R.id.xrv)
    XRecyclerView xrv;
    private ItemAdapter adapter;
    private List<IdNameValue> mList;
    private View rootView;
    private Context mContext;
    String brandLogo = "";
    String brandId = "0";
    String seriesId = "0";
    String styleId = "0";
    private Calendar calendar;
    private DatePickerDialog dialog;
    private GetCarConfigByStyleIdPresenter presenter;

    private int carId = 0;

    @Override
    protected void initData() {
        //1
        mList = new ArrayList<>();
        mList.add(new IdNameValue("","品牌 *",""));
        mList.add(new IdNameValue("","车系 *",""));
        mList.add(new IdNameValue("","车型 *",""));
        mList.add(new IdNameValue("","车牌号",""));
        mList.add(new IdNameValue("","初次上牌 *",""));
        mList.add(new IdNameValue("","颜色",""));
        mList.add(new IdNameValue("","内饰",""));
        mList.add(new IdNameValue("","表显里程 *",""));
        mList.add(new IdNameValue("","出厂日期",""));
        mList.add(new IdNameValue("","新车市场价",""));

        presenter = new GetCarConfigByStyleIdPresenter(this);

    }


    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_baseinfo, container, false);
            ButterKnife.bind(this, rootView);
            EventBus.getDefault().register(this);
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

        SubmitParamWrapper.CarBaseInfo carBaseInfo = JzgApp.getAppContext().getSubmitParam().getCarInfo().getCarBaseInfo();
        if(null==carBaseInfo) {
            carBaseInfo = new SubmitParamWrapper.CarBaseInfo();
            JzgApp.getAppContext().getSubmitParam().getCarInfo().setCarBaseInfo(carBaseInfo);
        }else{
            setInitValues(carBaseInfo);
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
     * 初始化显示基本信息
     * @author zealjiang
     * @time 2016/8/11 9:58
     */
    public void setInitValues(SubmitParamWrapper.CarBaseInfo carBaseInfo){
        List<ParamOption.DataBean.KeyValueItem> list;
        //品牌
        mList.get(0).setValue(carBaseInfo.getBrand());
        //车系
        mList.get(1).setValue(carBaseInfo.getSeries());
        //车型
        mList.get(2).setValue(carBaseInfo.getStyle());
        //车牌号
        mList.get(3).setValue(carBaseInfo.getCardId());
        //初次上牌
        mList.get(4).setValue(DateTimeUtils.formatMillsStrM(carBaseInfo.getFirstRegDate(),DateTimeUtils.YYYYMMDD));
        //颜色
        list = JzgApp.getAppContext().getOption().getData().getCarColor().getData();
        String colorName = DataUtils.getParamOpNameById(list,carBaseInfo.getColor());
        mList.get(5).setValue(colorName);
        //内饰
        list = JzgApp.getAppContext().getOption().getData().getInnerColor().getData();
        String innerColorName = DataUtils.getParamOpNameById(list,carBaseInfo.getInnerDecor());
        mList.get(6).setValue(innerColorName);
        //表显里程
        mList.get(7).setValue(MTextUtils.empty(carBaseInfo.getMileage()));
        //出厂日期
        mList.get(8).setValue(DateTimeUtils.formatMillsStrM(carBaseInfo.getManufactureDate(),DateTimeUtils.YYYYMMDD));
        //新车市场价
        mList.get(9).setValue(MTextUtils.empty(carBaseInfo.getNewCarPrice()));

        refresh();

        brandId = carBaseInfo.getBrandId()+"";
        brandLogo = "";//carinfoBean.getBrandLogo();
        seriesId = carBaseInfo.getSeriesId()+"";
        styleId = carBaseInfo.getStyleId()+"";
        carId = carBaseInfo.getCarId();


    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        List<ParamOption.DataBean.KeyValueItem> list;
        Intent intent = new Intent();
        switch (position){
            case 0:
                //选择品牌
                intent.setClass(mContext, CarBrandActivity.class);
                startActivityForResult(intent, Constant.CHOOSE_CAR_BRAND);
                break;
            case 1:
                //选择车系
                if(TextUtils.isEmpty(brandId)||"0".equals(brandId)){
                    MyToast.showShort("请先选择车品牌");
                }else{
                    intent.setClass(mContext, CarModelActivity.class);
                    intent.putExtra("brandId", brandId+"");
                    intent.putExtra("brandName", mList.get(0).getValue());
                    intent.putExtra("brandLogo", brandLogo);
                    startActivityForResult(intent, Constant.CHOOSE_CAR_MODEL);
                }
                break;
            case 2:
                //选择车型
                if(TextUtils.isEmpty(seriesId)||"0".equals(seriesId)||"null".equals(seriesId.toLowerCase())){
                    MyToast.showShort("请先选择车系");
                }else{
                    intent.setClass(mContext, CarTypeActivity.class);
                    intent.putExtra("seriesId", seriesId+"");
                    startActivityForResult(intent, Constant.CHOOSE_STYLE);
                }
                break;
            case 3:
                //车牌号
                start_InputResult(mContext,Constant.CAR_NO, "请填写车牌号","",-1,10,mList.get(3).getValue());
                break;
            case 4:
                //首次上牌时间
                if("".equals(styleId)){
                    MyToast.showShort("请选择车型");
                }else{
                    calendar = Calendar.getInstance();
                    dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            boolean flag = DateTimeUtils.laterThanNow(year, monthOfYear, dayOfMonth);
                            if (!flag) {
                                String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                dialog.dismiss();
                                mList.get(4).setValue(date);

                                LogUtil.e(TAG, date);
                                refresh();
                            } else {
                                MyToast.showLong(getString(R.string.selected_date_cannot_before_than_today));
                            }
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    dialog.show();
                }
                break;
            case 5:
                //颜色
                list = JzgApp.getAppContext().getOption().getData().getCarColor().getData();
                final String[] carColor = DataUtils.getListItemNameArray(list);

                if(carColor!=null && carColor.length>0) {
                    startRadioActivity(Constant.CAR_COLOR, "请选择车辆颜色", carColor, mList.get(5).getValue());
                }else{
                    MyToast.showShort("没有可供选择的内饰颜色，请检查");
                }
                break;
            case 6:
                //内饰
                list = JzgApp.getAppContext().getOption().getData().getInnerColor().getData();
                final String[] innerDecorColor = DataUtils.getListItemNameArray(list);
                if(innerDecorColor!=null && innerDecorColor.length>0){
                    startRadioActivity(Constant.INNER_COLOR, "请选择内饰颜色", innerDecorColor, mList.get(6).getValue());
                }else{
                    MyToast.showShort("没有可供选择的内饰颜色，请检查");
                }
                break;
            case 7:
                //表显里程
                start_InputResult(Constant.XINGSHILICHENG, "请填写行驶里程", "请正确填写  单位：公里",1,6,mList.get(7).getValue());
                break;
            case 8:
                calendar = Calendar.getInstance();
                dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        boolean flag = DateTimeUtils.laterThanNow(year, monthOfYear, dayOfMonth);
                        if (!flag) {
                            String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            dialog.dismiss();
                            mList.get(8).setValue(date);
                            refresh();
                            LogUtil.e(TAG, date);
                        } else {
                            MyToast.showLong(getString(R.string.selected_date_cannot_before_than_today));
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case 9:
                //新车市场价
                start_InputResult(Constant.XINCHESHICHANGJIA, "请填写新车市场价", "请正确填写  单位：万元",2,8,mList.get(9).getValue());
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
            case Constant.CHOOSE_CAR_BRAND:
                if (data != null) {
                    String brandName = (String)data.getStringExtra("brandName");
                    brandId = data.getStringExtra("brandId");
                    brandLogo = (String)data.getStringExtra("brandLogo");
                    //设置品牌值
                    mList.get(0).setValue(brandName);
                    seriesId = "";
                    styleId = "";
                    //设置车系值
                    mList.get(1).setValue("");
                    //设置车型值
                    mList.get(2).setValue("");
                    refresh();
                }
                break;
            case Constant.CHOOSE_CAR_MODEL:
                if (data != null) {
                    String seriesName = (String)data.getStringExtra("seriesName");
                    seriesId = data.getStringExtra("seriesId");
                    //设置车系值
                    mList.get(1).setValue(seriesName);
                    //设置车型值
                    mList.get(2).setValue("");
                    refresh();

                }
                break;
            case Constant.CHOOSE_STYLE:
                if (data != null) {
                    styleId = (String)data.getStringExtra("styleId");
                    String styleName = (String)data.getStringExtra("styleName");
                    //设置车型值
                    mList.get(2).setValue(styleName);
                    refresh();

                    //获取配置信息
                    request();
                }
                break;

            case Constant.CAR_NO:
                if (data != null) {
                    String car_no = data.getStringExtra(Constant.activity_input);
                    //设置车牌号
                    mList.get(3).setValue(car_no);
                    ACache.get(mContext).put("carNO",car_no);
                    refresh();
                }
                break;
            case Constant.CAR_COLOR:
                //车身颜色
                if (data != null) {
                    String carColor = data.getStringExtra(Constant.activity_radio);
                    mList.get(5).setValue(carColor);
                    refresh();
                }
                break;
            case Constant.INNER_COLOR:
                //内饰颜色
                if (data != null) {
                    String innerColor = data.getStringExtra(Constant.activity_radio);
                    mList.get(6).setValue(innerColor);
                    refresh();
                }
                break;
            case Constant.XINGSHILICHENG:
                //表显里程
                if (data != null) {
                    String xingshilicheng = data.getStringExtra(Constant.activity_input);
                    mList.get(7).setValue(xingshilicheng);
                    refresh();
                }
                break;
            case Constant.XINCHESHICHANGJIA:
                if (data != null) {
                    String xincheshichangjia = data.getStringExtra(Constant.activity_input);
                    //新车市场价
                    mList.get(9).setValue(xincheshichangjia);
                    refresh();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 刷新
     * @author zealjiang
     * @time 2016/8/4 16:18*
     */
    private void refresh(){
        adapter.notifyDataSetChanged();
        int i = getNotEmptyValue();
        ((CarInfoActivity)this.getActivity()).setTabText(0,"基本信息"+" "+i+"/"+mList.size());
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
    public void setValues(CarInfoModel carInfoModel){
        List<ParamOption.DataBean.KeyValueItem> list;
        CarInfoModel.DataBean.CarinfoBean carinfoBean = carInfoModel.getData().getCarinfo();
        //品牌
        mList.get(0).setValue(carinfoBean.getMakeName());
        //车系
        mList.get(1).setValue(carinfoBean.getModelName());
        //车型
        mList.get(2).setValue(carinfoBean.getStyleName());
        //车牌号
        mList.get(3).setValue(carinfoBean.getLicensePlate());
        //初次上牌
        mList.get(4).setValue(DateTimeUtils.formatMillsStr(carinfoBean.getRegdate(),DateTimeUtils.YYYYMMDD));
        //颜色
        list = JzgApp.getAppContext().getOption().getData().getCarColor().getData();
        String color = DataUtils.getParamOpNameById(list,carinfoBean.getColor());
        mList.get(5).setValue(color);
        //内饰
        list = JzgApp.getAppContext().getOption().getData().getInnerColor().getData();
        String innerColor = DataUtils.getParamOpNameById(list,carinfoBean.getInnerClolor());
        mList.get(6).setValue(innerColor);
        //表显里程
        mList.get(7).setValue(MTextUtils.empty(carinfoBean.getMileage()+""));
        //出厂日期
        mList.get(8).setValue(DateTimeUtils.formatMillsStr(carinfoBean.getManufactureDate(),DateTimeUtils.YYYYMMDD));
        //新车市场价
        mList.get(9).setValue(MTextUtils.empty(carinfoBean.getNewCarPrice()+""));

        refresh();


        brandId = carinfoBean.getMakeID()+"";
        brandLogo = "";//carinfoBean.getBrandLogo();
        seriesId = carinfoBean.getModelID()+"";
        styleId = carinfoBean.getStyleID()+"";
    }


    /**
     * 基本信息必填项是否全填了
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
     * 保存信息
     * @author zealjiang
     * @time 2016/8/11 16:54
     */
    public void saveInfo(){

        if(null==JzgApp.getAppContext().getSubmitParam().getCarInfo()){
            SubmitParamWrapper.CarInfo carInfo = new SubmitParamWrapper.CarInfo();
            JzgApp.getAppContext().getSubmitParam().setCarInfo(carInfo);
        }

        SubmitParamWrapper.CarBaseInfo carBaseInfo = JzgApp.getAppContext().getSubmitParam().getCarInfo().getCarBaseInfo();
        if(null==carBaseInfo){
            carBaseInfo = new SubmitParamWrapper.CarBaseInfo();
            JzgApp.getAppContext().getSubmitParam().getCarInfo().setCarBaseInfo(carBaseInfo);
        }

        List<ParamOption.DataBean.KeyValueItem> list;
        //品牌
        carBaseInfo.setBrand(mList.get(0).getValue());
        carBaseInfo.setBrandId(brandId);
        carBaseInfo.setBrandLogo(brandLogo);
        //车系
        carBaseInfo.setSeries(mList.get(1).getValue());
        if(TextUtils.isEmpty(seriesId))seriesId="0";
        carBaseInfo.setSeriesId(seriesId);
        //车型
        carBaseInfo.setStyle(mList.get(2).getValue());
        if(TextUtils.isEmpty(styleId))styleId="0";
        carBaseInfo.setStyleId(styleId);
        //车牌号
        carBaseInfo.setCardId(mList.get(3).getValue());
        //初次上牌
        carBaseInfo.setFirstRegDate(mList.get(4).getValue());
        //颜色
        list = JzgApp.getAppContext().getOption().getData().getCarColor().getData();
        int colorId = DataUtils.getParamOpIdByName(list,mList.get(5).getValue());
        carBaseInfo.setColor(colorId);
        //内饰
        list = JzgApp.getAppContext().getOption().getData().getInnerColor().getData();
        int innerColorId = DataUtils.getParamOpIdByName(list,mList.get(6).getValue());
        carBaseInfo.setInnerDecor(innerColorId);
        //表显里程
        carBaseInfo.setMileage(mList.get(7).getValue());
        //出厂日期
        carBaseInfo.setManufactureDate(mList.get(8).getValue());
        //新车市场价
        carBaseInfo.setNewCarPrice(mList.get(9).getValue()+"");
        //车辆ID
        carBaseInfo.setCarId(carId);

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
            //车辆ID，要返给服务器
            carId = carInfoModel.getData().getCarinfo().getID();
        }else{
            //只返回品牌和车系
            /**
             * "MakeID": 8,

             "MakeName": " 大众 ",

             "ModelID": 2381,

             "ModelName": " 宝来 "

             */
            CarInfoModel.DataBean.CarinfoBean carinfoBean = carInfoModel.getData().getCarinfo();
            //品牌
            mList.get(0).setValue(carinfoBean.getMakeName());
            //车系
            mList.get(1).setValue(carinfoBean.getModelName());

            brandId = carinfoBean.getMakeID()+"";
            seriesId = carinfoBean.getModelID()+"";

            refresh();
        }
    }

    /**
     * 通过品牌、车系、车型获取配置信息
     * @author zealjiang
     * @time 2016/8/26 16:16
     */
    private void request(){
        presenter.getCarCiByStyleId(styleId);
    }

    @Override
    public void succeed(Object object) {
        CarInfoModel.DataBean dataBean = (CarInfoModel.DataBean)object;
        //修改新车市场价
        String newCarPrice = dataBean.getCarinfo().getNewCarPrice()+"";
        mList.get(9).setValue(newCarPrice);
        refresh();
        //修改配置信息
        EventBus.getDefault().post(dataBean.getCarCI());
        //车辆ID，要返给服务器
        carId = dataBean.getCarinfo().getID();
    }
}
