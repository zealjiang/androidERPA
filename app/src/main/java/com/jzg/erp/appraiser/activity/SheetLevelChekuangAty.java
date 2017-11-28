package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jzg.erp.R;
import com.jzg.erp.global.Constant;
import com.jzg.erp.widget.NumberPicker;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SheetLevelChekuangAty extends FragmentActivity {

	
	int win_width ;
    int win_height;
    @Bind(R.id.np_car_level)
	NumberPicker carLevelNP;
    @Bind(R.id.np_cost_level)
	NumberPicker costLevelNP;
    
    private int mFirstColumnShowMax;
    private int mFirstColumnShowMin;
    
    private int mSecondColumnShowMax ;
    private int mSecondColumnShowMin ;
    
    private int mFirstColumnShowDefault;
    private int mSecondColumnShowDefault;
    
    private String[] mFirstColumnShowStr = null;
    private String[] mSecondColumnShowStr = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setFinishOnTouchOutside(true);

		setContentView(R.layout.activity_sheetlevel_chekuang);
		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
		WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
		Point size = new Point();
		d.getSize(size);
		win_width = size.x;
		win_height = size.y;
		p.gravity = Gravity.BOTTOM;
		p.height = (int) (win_height * 0.5); // 高度设置为屏幕的0.6
		p.width = (int) (win_width * 1); // 宽度设置为屏幕的1
		p.alpha = 1.0f;// 设置透明度
				
		mFirstColumnShowStr = getIntent().getStringArrayExtra(Constant.FIRST_COLUMN_SHOW);
		mSecondColumnShowStr = getIntent().getStringArrayExtra(Constant.SECOND_COLUMN_SHOW);
		mFirstColumnShowMax = getIntent().getIntExtra(Constant.FIRST_COLUMN_SHOW_MAX, 0);
		mFirstColumnShowMin = getIntent().getIntExtra(Constant.FIRST_COLUMN_SHOW_MIM, 0);
		mSecondColumnShowMax = getIntent().getIntExtra(Constant.SECOND_COLUMN_SHOW_MAX, 0);
		mSecondColumnShowMin = getIntent().getIntExtra(Constant.SECOND_COLUMN_SHOW_MIN, 0);
		mFirstColumnShowDefault = getIntent().getIntExtra(Constant.FIRST_COLUMN_SHOW_DEFAULT, 0);
		mSecondColumnShowDefault = getIntent().getIntExtra(Constant.SECOND_COLUMN_SHOW_DEFAULT, 0);
		
		this.getWindow().setAttributes(p);
		
		
		ButterKnife.bind(this);
		carLevelNP.setDisplayedValues(mFirstColumnShowStr);
		carLevelNP.setMaxValue(mFirstColumnShowMax);
		carLevelNP.setMinValue(mFirstColumnShowMin);
		carLevelNP.setValue(mFirstColumnShowDefault);

		costLevelNP.setDisplayedValues(mSecondColumnShowStr);
		costLevelNP.setMaxValue(mSecondColumnShowMax);
		costLevelNP.setMinValue(mSecondColumnShowMin);
		costLevelNP.setValue(mSecondColumnShowDefault);

		//使NumberPicker不可编辑
		carLevelNP.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		costLevelNP.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		carLevelNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				int carLevel  = picker.getValue();
				mFirstColumnShowDefault = carLevel;
			}
		});
		costLevelNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				int cost  = picker.getValue();
				mSecondColumnShowDefault = cost;
			}
		});


	}

	
	public void time_done(View v){
		back_data();
		this.finish();
	}
	
	
	public void back_data(){
		Intent in = new Intent();
		if(carLevelNP.getValue() == 0){
			in.putExtra(Constant.FIRST_COLUMN_CHOOSE_RESULT, mFirstColumnShowStr[carLevelNP.getValue()]+"");
			in.putExtra(Constant.SECOND_COLUMN_CHOOSE_RESULT,"");
		}else{
			in.putExtra(Constant.FIRST_COLUMN_CHOOSE_RESULT, mFirstColumnShowStr[carLevelNP.getValue()]);
			in.putExtra(Constant.SECOND_COLUMN_CHOOSE_RESULT, mSecondColumnShowStr[costLevelNP.getValue()].trim());
		}
		
		setResult(Constant.SHEET_TWO_COLUMN_MSG, in);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
	}
}
