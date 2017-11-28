/**
 * Project Name:JZGBJXD
 * File Name:HomeActivity.java
 * Package Name:com.gc.jzgpgswl.ui
 * Date:2014-11-19上午10:15:36
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.erp.appraiser.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ReplacementTransformationMethod;
import android.view.View;
import android.widget.EditText;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarInfoModel;
import com.jzg.erp.appraiser.presenter.VINPresenter;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.dialog.ShowMsgDialog;
import com.jzg.erp.global.Constant;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.view.IVinSearch;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * VIN码录入
 * @author zealjiang
 * @time 2016/8/11 18:20
 */
public class InputVinActivity extends BaseActivity implements IVinSearch {

	protected static final String TAG = InputVinActivity.class.getSimpleName();

	@Bind(R.id.vin_input_edit)
	EditText vin_input_edit;
	
	int requestCode = 0;
	
	private String requestVin = "";
	
	private String vin = "";

	private VINPresenter mVinPresenter;

	@Override
	protected void initViews(Bundle savedInstanceState) {
		setContentView(R.layout.activity_input_vin);
		ButterKnife.bind(this);

		//获取用户信息
		mVinPresenter = new VINPresenter(this);
	}

	@Override
	protected void setData() {
		init();
	}

	@OnClick({R.id.ivLeft,R.id.tvRight})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.ivLeft:
				finish();
				break;
			case R.id.tvRight:
				vin_submit();
				break;
		}
	}

	
	public void init(){
		setTitle("填写车架号");
		setTextRight("保存");
		requestCode = getIntent().getIntExtra(Constant.INPUT_VIN, 0);
		vin = getIntent().getStringExtra("VIN");
		
		vin_input_edit.setText(vin);
		vin_input_edit.setFocusable(true);
		vin_input_edit.setFocusableInTouchMode(true);
		vin_input_edit.requestFocus();
		vin_input_edit.setTransformationMethod(new InputLowerToUpper());

	}


	public class InputLowerToUpper extends ReplacementTransformationMethod{
	    @Override
	    protected char[] getOriginal() {
	        char[] lower = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };
	        return lower;
	    }
	 
	    @Override
	    protected char[] getReplacement() {
	        char[] upper = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
	        return upper;
	    }
	 
	}

	
	/**
	 * 提交vin
	  * vin_submit
	  *
	  * @Title: vin_submit
	  * @Description:
	  * @param     
	  * @return void
	  * @throws
	 */
	public void vin_submit(){
		requestVin = vin_input_edit.getText().toString().toUpperCase();
		if(TextUtils.isEmpty(requestVin)){
			MyToast.showShort("Vin不能为空");
		}else{
			if(requestVin.toString().length() != 17){
				MyToast.showShort("Vin只能为17位");
			}else{
				//网络请求
				mVinPresenter.request(requestVin, JzgApp.getUser().getStoreId()+"");
			}
		}
	}

	@Override
	public void succeed(CarInfoModel carInfoModel) {

		//如果vin码解析正常
		Intent intent = new Intent();
		//保存VIN码
		intent.putExtra(Constant.INPUT_VIN,requestVin);
		intent.putExtra("CarInfoModel",carInfoModel);
		setResult(requestCode,intent);
		finish();

	}

	@Override
	public void unusual(CarInfoModel carInfoModel) {
		//检查VIN码的各种情况
		int status = carInfoModel.getStatus();//
		showDialog(status,carInfoModel.getMessage(),carInfoModel);
	}

	private void showDialog(int state,String content,final CarInfoModel carInfoModel){
		String title;
		switch (state){
			case -1://该车辆已被收购 ， 无法保存并创建新的评估单
				title = getResources().getString(R.string.dialog_title);
				//content = getResources().getString(R.string.dialog_exist_other);
				ShowMsgDialog.showMaterialDialogNoBtn(this, title,content);

				break;
			case -2://该车辆已存在处于批准收购状态的评估单 ， 无法保存并创建新的评估单
				title = getResources().getString(R.string.dialog_title);
				ShowMsgDialog.showMaterialDialogNoBtn(this, title,content);
				break;
			case -3://该车辆已存在本店铺创建的处于待审核状态的评估单，无法保存并创建新的评估单
				title = getResources().getString(R.string.dialog_title);
				ShowMsgDialog.showMaterialDialogNoBtn(this, title,content);
				break;
			case -4://该车辆已存在本店铺创建的处于待申请状态的评估单，若创建新的评估单，则之前创建的评估单会被自动删除，您是否还要创建新的评估单 ?
				title = getResources().getString(R.string.dialog_title);
				ShowMsgDialog.showMaterialDialog2Btn(this, title,content, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						//如果vin码解析正常
						Intent intent = new Intent();
						//保存VIN码
						intent.putExtra(Constant.INPUT_VIN,requestVin);
						intent.putExtra("CarInfoModel",carInfoModel);
						setResult(requestCode,intent);
						finish();
					}
				});
				break;
			case -5://该车辆已存在其它店铺创建的评估单，您是否需要创建新的评估单 ?
				title = getResources().getString(R.string.dialog_title);
				ShowMsgDialog.showMaterialDialog2Btn(this, title,content, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						//如果vin码解析正常
						Intent intent = new Intent();
						//保存VIN码
						intent.putExtra(Constant.INPUT_VIN,requestVin);
						intent.putExtra("CarInfoModel",carInfoModel);
						setResult(requestCode,intent);
						finish();
					}
				});
				break;

		}
	}
}
