package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.fragment.BaseInfoFragment;
import com.jzg.erp.appraiser.fragment.ConfigInfoFragment;
import com.jzg.erp.appraiser.fragment.ProcedureInfoFragment;
import com.jzg.erp.appraiser.model.CarInfoModel;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.MyToast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Function: 基本信息编辑界面 <br/>
 * Date: 2015-8-19 16:59:36 <br/>
 * 
 * @author zealjiang
 * @version
 * @since JDK 1.6
 * @see
 */
public class CarInfoActivity extends BaseActivity implements View.OnClickListener{

	@Bind(R.id.et_vin)
	EditText etVin;
	@Bind(R.id.tv_vin_length)
	TextView tvVinLength;
	@Bind(android.R.id.tabs)
	TabLayout tabs;
	@Bind(R.id.viewPager)
	ViewPager viewPager;

	private BaseInfoFragment baseInfoFragment;
	private ConfigInfoFragment configInfoFragment;
	private ProcedureInfoFragment procedureInfoFragment;
	private CarInfoModel carInfoModel;
	private boolean flag = false;

	@Override
	protected void initViews(Bundle savedInstanceState) {
		setContentView(R.layout.activity_register_base_info);
		ButterKnife.bind(this);
		viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return 3;
			}

			@Override
			public Fragment getItem(int position) {
				switch (position) {
					case 0:
						baseInfoFragment = new BaseInfoFragment();
						return baseInfoFragment;
					case 1:
						configInfoFragment = new ConfigInfoFragment();
						return configInfoFragment;
					case 2:
						procedureInfoFragment = new ProcedureInfoFragment();
						return procedureInfoFragment;
					default:
						baseInfoFragment = new BaseInfoFragment();
						return baseInfoFragment;
				}
			}

			@Override
			public CharSequence getPageTitle(int position) {
				switch (position) {
					case 0:
						return "基本信息";
					case 1:
						return "配置信息";
					case 2:
						return "手续信息";
					default:
						return "基本信息";
				}
			}

		});


		viewPager.setOffscreenPageLimit(3);
		tabs.setupWithViewPager(viewPager);
		tabs.getTabAt(0).setCustomView(getTabView("基本信息"));
		tabs.getTabAt(1).setCustomView(getTabView("配置信息"));
		tabs.getTabAt(2).setCustomView(getTabView("手续信息"));


		//初始化显示VIN车架号
		//设置上次保存的值
		SubmitParamWrapper.CarInfo carInfo = JzgApp.getAppContext().getSubmitParam().getCarInfo();
		if(null==carInfo){
			carInfo = new SubmitParamWrapper.CarInfo();
			JzgApp.getAppContext().getSubmitParam().setCarInfo(carInfo);
		}

		etVin.setText(carInfo.getVin());
		tvVinLength.setText(etVin.getText().toString().length()+"/17");

	}

	private View getTabView(String title) {
		View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
		TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_title.setText(title);
		return view;
	}


	public void setTabText(int pos,String text){
		tabs.getTabAt(pos).setText(text);
		((TextView)tabs.getTabAt(pos).getCustomView().findViewById(R.id.tv_title)).setText(text);
	};

	@Override
	protected void setData() {
		setTitle("车辆资料");
		setTextRight("保存");
		init();
	}

	public void init(){
		mTvRight.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	

	@OnClick({ R.id.ivLeft, R.id.tvRight})
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
			case R.id.tvRight:
				save();
				break;
			case R.id.ivLeft:
				this.finish();
				break;
		}
	}


	/**
	 * 保存
	 * @author zealjiang
	 * @time 2016/8/10 11:37
	 */
	private void save(){
		//检查VIN码是否填写
		String vin = etVin.getText().toString();
		if(TextUtils.isEmpty(vin)){
			MyToast.showShort("请输入车架号");
			return;
		}

		//检查基本信息必填项是否全部填写
		boolean isFillOutBaseInfo = baseInfoFragment.isFillOut();
		if(!isFillOutBaseInfo){
			return;
		}
		//检查配置信息必填项是否全部填写
		boolean isFillOutConfigInfo = configInfoFragment.isFillOut();
		if(!isFillOutConfigInfo){
			return;
		}
		//检查手续信息必填项是否全部填写
		boolean isFillOutHandlingInfo = procedureInfoFragment.isFillOut();
		if(!isFillOutHandlingInfo){
			return;
		}
		//保存所有车辆资料
		SubmitParamWrapper.CarInfo carInfo = JzgApp.getAppContext().getSubmitParam().getCarInfo();
		if(null==carInfo){
			carInfo = new SubmitParamWrapper.CarInfo();
			JzgApp.getAppContext().getSubmitParam().setCarInfo(carInfo);
		}
		//保存VIN码
		carInfo.setVin(vin);
		//保存基本信息
		baseInfoFragment.saveInfo();
		//保存配置信息
		configInfoFragment.saveInfo();
		//保存手续信息
		procedureInfoFragment.saveInfo();

		List<SubmitParamWrapper.PhotoItem> list = new ArrayList<>();
		if(carInfoModel!=null ){
			List<CarInfoModel.DataBean.CarPicBean> carPicList = carInfoModel.getData().getCarPic();
			for(CarInfoModel.DataBean.CarPicBean pic:carPicList){
				SubmitParamWrapper.PhotoItem item = new SubmitParamWrapper.PhotoItem(pic.getDictID(), pic.getPicPath(),pic.getViewUrl(), pic.getImgText());
				item.setViewUrl(pic.getViewUrl());
				list.add(item);
			}
		}
		if(flag)
			JzgApp.getSubmitParam().setPhotoItems(list);
		finish();

	}



	@OnClick(R.id.et_vin)
	public void base_info_layout_click(View v) {
		switch (v.getId()) {
		case R.id.et_vin:
			Intent intent = new Intent(CarInfoActivity.this, InputVinActivity.class);
			intent.putExtra("VIN", etVin.getText().toString());
			startActivityForResult(intent,Constant.INPUT_VIN_ID);
			break;

		default:
			break;
		}
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode){
			case Constant.INPUT_VIN_ID:
				if(data!=null&&data.getStringExtra(Constant.INPUT_VIN).length()==17){
					flag = true;
					//显示VIN码
					String vin = data.getStringExtra(Constant.INPUT_VIN);
					etVin.setText(vin);
					tvVinLength.setText("17/17");
					//得到vin码解析的数据对象
					carInfoModel = (CarInfoModel)data.getSerializableExtra("CarInfoModel");

					//将消息发送到基本信息、配置信息、手续信息
					EventBus.getDefault().post(carInfoModel);
				}
				break;
		}
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
	}
}
