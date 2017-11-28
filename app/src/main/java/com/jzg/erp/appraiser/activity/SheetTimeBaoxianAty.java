package com.jzg.erp.appraiser.activity;

import android.annotation.SuppressLint;
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

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 
 * 
 * @author 作者 E-mail:
 * @version 创建时间：
 */
@SuppressLint("NewApi")
public class SheetTimeBaoxianAty extends FragmentActivity {

	
	int win_width ;
    int win_height;
    @Bind(R.id.yearNumberPicker)
	NumberPicker yearNumberPicker;
    @Bind(R.id.monthNumberPicker)
	NumberPicker monthNumberPicker;
    
    
    private int maxYear;
    private int minYear;
    private int maxMonth = 12;
    private int minMonth = 1;

    private int currentYear;
    private int currentMonth;
    
    String[] yearShow = null;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setFinishOnTouchOutside(true);

		setContentView(R.layout.aty_sheettime_baoxiao);
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

		Calendar calendar = Calendar.getInstance();
		maxYear = getIntent().getIntExtra("Maxyear",calendar.get(Calendar.YEAR)+20);
		minYear = getIntent().getIntExtra("Minyear", calendar.get(Calendar.YEAR)-20);
		currentYear = getIntent().getIntExtra("curYear", calendar.get(Calendar.YEAR));
		currentMonth = getIntent().getIntExtra("curMonth", 1);
		yearShow = getIntent().getStringArrayExtra("yearShow");
		
		this.getWindow().setAttributes(p);
		
		
		ButterKnife.bind(this);
		//yearNumberPicker.setDisplayedValues(yearShow);
		yearNumberPicker.setMaxValue(maxYear);
		yearNumberPicker.setMinValue(minYear);
		yearNumberPicker.setValue(currentYear);
		
		monthNumberPicker.setMaxValue(maxMonth);
		monthNumberPicker.setMinValue(minMonth);
		monthNumberPicker.setValue(currentMonth);
		
		yearNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		monthNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		yearNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				int year  = picker.getValue();
				currentYear = year;

			}
		});
		monthNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				int month  = picker.getValue();
				currentMonth = month;
			}
		});
	}
	

	public void time_done(View v){
		back_data();
		this.finish();
	}
	
	
	public void back_data(){
		Intent in = new Intent();
		if(yearNumberPicker.getValue() == 0){
			in.putExtra(Constant.car_Time_MSG,yearNumberPicker.getValue());
		}else{
			in.putExtra(Constant.car_Time_MSG,yearNumberPicker.getValue()+"-"+monthNumberPicker.getValue());
		}
		
		setResult(Constant.Time_MSG, in);
	}

}
