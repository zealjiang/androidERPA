package com.jzg.erp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.jzg.erp.R;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.utils.MyToast;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 */
public class InputTextActivity extends BaseActivity {

    @Bind(R.id.etContent)
    EditText etContent;
    @Bind(R.id.tvTips)
    TextView tvTips;

    private String title = "";
    private String tips = "";
    private String oldData = "";
    private int maxLen = 300;
    private int inputType = -1;
    private boolean flag;



    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_text_input);
        ButterKnife.bind(this);
    }

    @Override
    protected void setData() {
        init();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etContent.getWindowToken(), 0);
        finish();
    }

    public void init() {
        title = getIntent().getStringExtra(Constant.INPUT_TITLE);
        tips = getIntent().getStringExtra(Constant.INPUT_TIPS);
        oldData = getIntent().getStringExtra(Constant.OLD_DATA);
        maxLen = getIntent().getIntExtra(Constant.INPUT_LENGTH, 300);
        inputType = getIntent().getIntExtra(Constant.INPUT_TYPE, -1);
        InputFilter[] filters = {new InputFilter.LengthFilter(maxLen)};
        etContent.setFilters(filters);
        if(inputType!=-1)
            etContent.setInputType(inputType);

        if("填写客户心理价".equals(title)){
            flag = true;
        }
        setTitle(title);
        tvTips.setText(tips);
        etContent.setText(oldData);
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>maxLen){
                    MyToast.showShort("最多只能输入"+maxLen+"个字符");
                }
            }
        });

        if(flag){
            etContent.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
            RxTextView.textChangeEvents(etContent)
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TextViewTextChangeEvent>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
                            String keyword = onTextChangeEvent.text().toString();
                            if (!TextUtils.isEmpty(keyword)) {
                                if(keyword.contains(".")){
                                    String s1 = keyword.substring(keyword.indexOf(".")+1,keyword.length());
                                    if(s1.length()>2){
                                        String result = keyword.substring(0, keyword.indexOf(".")+3);
                                        etContent.setText(result);
                                        etContent.setSelection(result.length());
                                    }
                                }else{
                                    if(keyword.length()>5){
                                        String result = keyword.substring(0, 5);
                                        etContent.setText(result);
                                        etContent.setSelection(result.length());
                                    }
                                }
                            }
                        }
                    });
        }

    }
    public void submit() {
        String input = etContent.getText().toString();
        Intent intent  = getIntent();
        intent.putExtra(Constant.ACTIVITY_INPUT,input);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etContent.getWindowToken(), 0);
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick({R.id.vin_submit})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.vin_submit:
                submit();
                break;
            default:
                break;
        }
    }

}
