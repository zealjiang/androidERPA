/**
 * Project Name:JZGBJXD
 * File Name:HomeActivity.java
 * Package Name:com.gc.jzgpgswl.ui
 * Date:2014-11-19上午10:15:36
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.text.method.ReplacementTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jzg.erp.R;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.utils.MyToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ClassName:HomeActivity <br/>
 * Function: 录入. <br/>
 * Date: 2014-8-19 上午10:10:36 <br/>
 * 
 * @author 郑有权
 * @version
 * @since JDK 1.6
 * @see
 */
public class InputActivity extends BaseActivity {

	protected static final String TAG = InputActivity.class.getSimpleName();

	@Bind(R.id.input_edit)
	EditText input_edit;
	@Bind(R.id.input_text_show)
	TextView input_text_show;
	
	int requestCode = 0;
	String  title = "";
	String  hint = "";
	int  input_type = -1; //-1默认，0代表VIN，1代表数字int,2代表可以输入小数,3代表email，4代表发动机：数字字母横线
	int input_length = 5000;
	String showText = "";


	@Override
	protected void initViews(Bundle savedInstanceState) {
		setContentView(R.layout.activity_input_no_button);

		ButterKnife.bind(this);
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
				input_submit();
				break;
		}
	}


	public void init(){
		setTextRight("保存");
		requestCode = getIntent().getIntExtra(Constant.INPUT_VIN, 0);
		title = getIntent().getStringExtra(Constant.input_title);
		hint = getIntent().getStringExtra(Constant.input_hint);
		showText = getIntent().getStringExtra(Constant.showText);
		input_type = getIntent().getIntExtra(Constant.input_type,-1);
		input_length = getIntent().getIntExtra(Constant.input_length,5000);

		setTitle(title);
		input_text_show.setText(hint);
		input_edit.setText(showText);
		
		int inputType = 1;
		if(input_type == 0){
			//vin
			input_edit.setFocusable(true);
			input_edit.setFocusableInTouchMode(true);
			input_edit.requestFocus();
			
			input_edit.setKeyListener(new NumberKeyListener() {
				
				
				@Override
				protected char[] getAcceptedChars() {
					return new char[] { '1', '2', '3', '4', '5', '6', '7', '8','9', '0',
					'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
					'r','s','t','u','v','w','x','y','z',
					'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
					'R','S','T','U','V','W','X','Y','Z'};
				}

				@Override
				public int getInputType() {
					return 1;
				}
			});
			input_edit.setTransformationMethod(new InputLowerToUpper());
			
		}else if(input_type == 1){
			//数字
			inputType = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL;
			input_edit.setInputType(inputType);
		}else if(input_type == 2){
			//数字可以输入小数
			inputType = InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL;
			input_edit.setInputType(inputType);
			priceTextWatcher(input_edit);
		}else if(input_type == 4){
			input_edit.setFocusable(true);
			input_edit.setFocusableInTouchMode(true);
			input_edit.requestFocus();
			
			input_edit.setKeyListener(new NumberKeyListener() {
				
				
				@Override
				protected char[] getAcceptedChars() {
					return new char[] { '1', '2', '3', '4', '5', '6', '7', '8','9', '0',
					'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
					'r','s','t','u','v','w','x','y','z',
					'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
					'R','S','T','U','V','W','X','Y','Z','_'};
				}

				@Override
				public int getInputType() {
					return 1;
				}
			});
			
		}else if(input_type == 5){
			//数字可以输入小数
			inputType = InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL;
			input_edit.setInputType(inputType);
			priceTextWatcherCommon(input_edit,2);
		}
		else{
			inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL;
			input_edit.setInputType(inputType);
		}
		
		
		input_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(input_length)});
		
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
	 * 保存
	  * vin_submit
	  *
	  * @Title: vin_submit
	  * @param     
	  * @return void
	  * @throws
	 */
	public void input_submit(){
		
		String intput = input_edit.getText().toString();
		if(TextUtils.isEmpty(intput)){
			MyToast.showShort("输入不能为空");
		}else{
			Intent intent = new Intent();
			if(input_type == 1){
				intent.putExtra(Constant.activity_input, intput);
			}else if(input_type == 0){
				if(intput.length() != 17){
					MyToast.showShort("Vin只能为17位");
					return;
				}else{
					intent.putExtra(Constant.activity_input, intput.toUpperCase());
				}
			}else if(input_type == 3){
				if(isEmail(intput)){
					intent.putExtra(Constant.activity_input, intput);
				}else{
					MyToast.showShort("邮箱输入不正确");
					return;
				}
			}
			else{
				intent.putExtra(Constant.activity_input, intput);
			}
			
			setResult(requestCode, intent); 
			finish();
		}
	}
	

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}



	public void priceTextWatcher(final EditText editText){
		
		editText.addTextChangedListener(new TextWatcher(){
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				if (s.length() == 0)
				{
					return;
				}

				if (s.length() > 4 && !s.toString().contains("."))
				{
					editText.setText(s.toString().subSequence(0,
							s.length() - 1));
					editText.setSelection(s.length() - 1);
					Toast.makeText(InputActivity.this, "请输入小数点", Toast.LENGTH_SHORT).show();
				} else if (s.toString().contains(".")
						&& s.toString()
								.substring(s.toString().lastIndexOf("."))
								.length() > 3)
				{
					editText.setText(s.toString().substring(0,
							s.toString().lastIndexOf("."))
							+ s.toString()
									.substring(s.toString().lastIndexOf("."))
									.subSequence(0, 3));
					editText.setSelection(s.length() - 1);
					Toast.makeText(InputActivity.this, "只能输入小数点后两位", Toast.LENGTH_SHORT).show();
				} else if ("0".equals(String.valueOf(s.charAt(0)))
						&& !s.toString().contains("."))
				{
					if (s.length() == 2
							&& "0".equals(String.valueOf(s.charAt(0))))
					{
						editText.setText("0");
						editText.setSelection(1);
					}
					Toast.makeText(InputActivity.this, "请输入小数点", Toast.LENGTH_SHORT).show();
				} else if (".".equals(String.valueOf(s.charAt(0))))
				{
					editText.setText("");
					Toast.makeText(InputActivity.this, "第一位不能为小数点", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{

			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});
	}

	public void priceTextWatcherCommon(final EditText editText,int decimalLenth){

		editText.addTextChangedListener(new TextWatcher(){
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count)
			{
				if (s.length() == 0)
				{
					return;
				}

				if (s.toString().contains(".")
						&& s.toString()
						.substring(s.toString().lastIndexOf("."))
						.length() > 3)
				{
					editText.setText(s.toString().substring(0,
							s.toString().lastIndexOf("."))
							+ s.toString()
							.substring(s.toString().lastIndexOf("."))
							.subSequence(0, 3));
					editText.setSelection(s.length() - 1);
					Toast.makeText(InputActivity.this, "只能输入小数点后两位", Toast.LENGTH_SHORT).show();
				} else if ("0".equals(String.valueOf(s.charAt(0)))
						&& !s.toString().contains("."))
				{
					if (s.length() == 2
							&& "0".equals(String.valueOf(s.charAt(0))))
					{
						editText.setText("0");
						editText.setSelection(1);
					}
					Toast.makeText(InputActivity.this, "请输入小数点", Toast.LENGTH_SHORT).show();
				} else if (".".equals(String.valueOf(s.charAt(0))))
				{
					editText.setText("");
					Toast.makeText(InputActivity.this, "第一位不能为小数点", Toast.LENGTH_SHORT).show();
				}else if(s.length()==input_length&&".".equals(String.valueOf(s.charAt(s.length()-1)))){
					editText.setText(s.subSequence(0,s.length() - 1));
					editText.setSelection(s.length() - 1);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after)
			{

			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});
	}

	public boolean isEmail(String str) {
		boolean tag = true;  
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
        final Pattern pattern = Pattern.compile(pattern1);  
        final Matcher mat = pattern.matcher(str);  
        if (!mat.find()) {  
            tag = false;  
        }  
        return tag;
	}

}
