package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.global.IntentKey;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.MTextUtils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.widget.MActionSheet;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCompanyCustomerActivity extends BaseActivity {


    @Bind(R.id.tvCompanyNameRight)
    TextView tvCompanyNameRight;
    @Bind(R.id.tvPhoneValue)
    TextView tvPhoneValue;
    @Bind(R.id.tvContactNameValue)
    TextView tvContactNameValue;
    @Bind(R.id.tvGenderValue)
    TextView tvGenderValue;
    @Bind(R.id.tvWantPriceValue)
    TextView tvWantPriceValue;
    @Bind(R.id.tvWantPriceKey)
    TextView tvWantPriceKey;
    @Bind(R.id.tvWantLevelValue)
    TextView tvWantLevelValue;
    @Bind(R.id.tvCarHostSourceValue)
    TextView tvCarHostSourceValue;
    @Bind(R.id.tvCustomerAddressValue)
    TextView tvCustomerAddressValue;
    @Bind(R.id.tvProvinceCityValue)
    TextView tvProvinceCityValue;
    @Bind(R.id.tvPreSellDateValue)
    TextView tvPreSellDateValue;
    @Bind(R.id.tvCompanyNameLeft)
    TextView tvCompanyNameLeft;
    @Bind(R.id.tvPhoneKey)
    TextView tvPhoneKey;
    @Bind(R.id.tvContactNameKey)
    TextView tvContactNameKey;
    private SubmitParamWrapper.CustomerInfo customerInfo;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_company_customer);
        ButterKnife.bind(this);
        customerInfo = JzgApp.getSubmitParam().getCustomerInfo();
    }

    @Override
    protected void setData() {
        setTitle("填写公司客户资料");
        setTextRight("保存");
        tvCompanyNameLeft.setText(UIUtils.getSpannableText("公司名称*", 4));
        tvContactNameKey.setText(UIUtils.getSpannableText("联系人*",3));
        tvPhoneKey.setText(UIUtils.getSpannableText("联系电话*",4));
        tvWantPriceKey.setText(UIUtils.getSpannableText("客户心理价*",5));
        if(customerInfo!=null && customerInfo.getType()==2){
            tvCompanyNameRight.setText(customerInfo.getName());
            tvPhoneValue.setText(customerInfo.getPhone());
            tvContactNameValue.setText(customerInfo.getContact());
            String address = customerInfo.getAddress();
            if(!TextUtils.isEmpty(address)){
                String[] split = address.split(" ");
                if(split!=null){
                    if(split.length==1){
                        String[] arr = MTextUtils.split(address);
                        tvProvinceCityValue.setText(arr[0]);
                        tvCustomerAddressValue.setText(arr[1]);
                    }else if(split.length==2){
                        tvProvinceCityValue.setText(split[0]);
                        tvCustomerAddressValue.setText(split[1]);
                    }
                }
            }
            tvWantLevelValue.setText(customerInfo.getWantLevel());
            tvCarHostSourceValue.setText(customerInfo.getCarHostSource());
            tvGenderValue.setText(customerInfo.getGender());
            tvWantPriceValue.setText(customerInfo.getWantPrice());
            tvPreSellDateValue.setText(customerInfo.getPreSellDate());
        }
    }

    private void checkInput(){
        String companyName = tvCompanyNameRight.getText().toString().trim();
        String phone = tvPhoneValue.getText().toString().trim();
        String contactName = tvContactNameValue.getText().toString().trim();
        String pc = tvProvinceCityValue.getText().toString().trim();
        String address = tvCustomerAddressValue.getText().toString().trim();
        address = address.replaceAll("\\s*", "");
        String wantLevel = tvWantLevelValue.getText().toString().trim();
        String carHostSource = tvCarHostSourceValue.getText().toString().trim();
        String gender = tvGenderValue.getText().toString().trim();
        String wantPrice = tvWantPriceValue.getText().toString().trim();
        String preSellDate = tvPreSellDateValue.getText().toString().trim();
        if(TextUtils.isEmpty(companyName)){
            MyToast.showShort("请填写公司名称");
            return;
        }
        if(TextUtils.isEmpty(phone)){
            MyToast.showShort("请填写联系电话");
            return;
        }
        if(TextUtils.isEmpty(contactName)){
            MyToast.showShort("请填写联系人");
            return;
        }
        if(TextUtils.isEmpty(wantPrice)){
            MyToast.showShort("请填客户心理价");
            return;
        }

        SubmitParamWrapper.CustomerInfo customerInfo =new SubmitParamWrapper.CustomerInfo(2,contactName,companyName,phone,"",gender,wantPrice,wantLevel,carHostSource,pc+" "+address,preSellDate);
        customerInfo.setContact(contactName);
        JzgApp.getAppContext().getSubmitParam().setCustomerInfo(customerInfo);
        finish();

    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.rlCompanyName, R.id.rlPhone, R.id.rlContactName, R.id.rlGender, R.id.rlWantPrice, R.id.rlWantLevel, R.id.rlCarHostSource, R.id.rlCustomerAddress, R.id.rlPreSellDate,R.id.rlProvinceCity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                checkInput();
                break;
            case R.id.rlCompanyName:
                startInputTextActivity(IntentKey.REQ_CODE_COMPANY_NAME,"填写公司名称","",tvCompanyNameRight.getText().toString()+"",20,-1);
                break;
            case R.id.rlPhone:
                startInputTextActivity(IntentKey.REQ_CODE_PHONE,"填写电话","",tvPhoneValue.getText().toString()+"",11, InputType.TYPE_CLASS_PHONE);
                break;
            case R.id.rlContactName:
                startInputTextActivity(IntentKey.REQ_CODE_CONTACT_NAME,"填写联系人","",tvContactNameValue.getText().toString()+"",20,-1);
                break;
            case R.id.rlGender:
                MActionSheet.createBuilder(AddCompanyCustomerActivity.this, getSupportFragmentManager())
                        .setCancelButtonTitle("取消")
                        .setOtherButtonTitles(Constant.GENDER)
                        .setCancelableOnTouchOutside(true)
                        .setListener(new MActionSheet.ActionSheetListener() {
                            @Override
                            public void onDismiss(MActionSheet actionSheet, boolean isCancel) {
                            }

                            @Override
                            public void onOtherButtonClick(MActionSheet actionSheet, int index) {
                                tvGenderValue.setText(Constant.GENDER[index]);
                            }
                        }).show();
                break;
            case R.id.rlWantPrice:
                startInputTextActivity(IntentKey.REQ_CODE_WANT_PRICE,"填写客户心理价","单位：万，精确到小数点后两位",tvWantPriceValue.getText().toString()+"",8,InputType.TYPE_CLASS_NUMBER |InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case R.id.rlWantLevel:
                MActionSheet.createBuilder(AddCompanyCustomerActivity.this, getSupportFragmentManager())
                        .setCancelButtonTitle("取消")
                        .setOtherButtonTitles(MTextUtils.set2Array(JzgApp.getOption().getData().getCustomerLevel().getCustomerLevelMap().keySet()))
                        .setCancelableOnTouchOutside(true)
                        .setListener(new MActionSheet.ActionSheetListener() {
                            @Override
                            public void onDismiss(MActionSheet actionSheet, boolean isCancel) {
                            }

                            @Override
                            public void onOtherButtonClick(MActionSheet actionSheet, int index) {
                                tvWantLevelValue.setText(MTextUtils.set2Array(JzgApp.getOption().getData().getCustomerLevel().getCustomerLevelMap().keySet())[index]);
                            }
                        }).show();
                break;
            case R.id.rlCarHostSource:
                MActionSheet.createBuilder(AddCompanyCustomerActivity.this, getSupportFragmentManager())
                        .setCancelButtonTitle("取消")
                        .setOtherButtonTitles(MTextUtils.set2Array(JzgApp.getOption().getData().getCustomerFrom().getCustomerFromMap().keySet()))
                        .setCancelableOnTouchOutside(true)
                        .setListener(new MActionSheet.ActionSheetListener() {
                            @Override
                            public void onDismiss(MActionSheet actionSheet, boolean isCancel) {
                            }

                            @Override
                            public void onOtherButtonClick(MActionSheet actionSheet, int index) {
                                tvCarHostSourceValue.setText(MTextUtils.set2Array(JzgApp.getOption().getData().getCustomerFrom().getCustomerFromMap().keySet())[index]);
                            }
                        }).show();
                break;
            case R.id.rlCustomerAddress:
                startInputTextActivity(IntentKey.REQ_CODE_CUSTOMER_ADDRESS,"填写通讯地址","",tvCustomerAddressValue.getText().toString()+"",50,-1);
                break;
            case R.id.rlProvinceCity:
                Intent intent = new Intent(this,SelectProviceCity.class);
                intent.putExtra("cityId",0);
                intent.putExtra("step",1);
                intent.putExtra("cityName","");
                startActivityForResult(intent,IntentKey.REQ_CODE_SELECT_CITY);
                break;
            case R.id.rlPreSellDate:
                MActionSheet.createBuilder(AddCompanyCustomerActivity.this, getSupportFragmentManager())
                        .setCancelButtonTitle("取消")
                        .setOtherButtonTitles(MTextUtils.set2Array(JzgApp.getOption().getData().getPreSellDate().getPreSellDateMap().keySet()))
                        .setCancelableOnTouchOutside(true)
                        .setListener(new MActionSheet.ActionSheetListener() {
                            @Override
                            public void onDismiss(MActionSheet actionSheet, boolean isCancel) {
                            }

                            @Override
                            public void onOtherButtonClick(MActionSheet actionSheet, int index) {
                                tvPreSellDateValue.setText(MTextUtils.set2Array(JzgApp.getOption().getData().getPreSellDate().getPreSellDateMap().keySet())[index]);
                            }
                        }).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && data != null) {
            String result = data.getStringExtra(Constant.ACTIVITY_INPUT);
            switch (requestCode){
                case IntentKey.REQ_CODE_COMPANY_NAME:
                    tvCompanyNameRight.setText(result);
                    break;
                case IntentKey.REQ_CODE_CONTACT_NAME:
                    tvContactNameValue.setText(result);
                    break;
                case IntentKey.REQ_CODE_PHONE:
                    tvPhoneValue.setText(result);
                    break;
                case IntentKey.REQ_CODE_WANT_PRICE:
                    tvWantPriceValue.setText(result);
                    break;
                case IntentKey.REQ_CODE_CUSTOMER_ADDRESS:
                    tvCustomerAddressValue.setText(result);
                    break;
                case IntentKey.REQ_CODE_SELECT_CITY:
                    tvProvinceCityValue.setText(result);
                    break;
            }
        }
    }
}
