package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.jzg.erp.R;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.utils.ScreenUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RadioSpecActivity extends BaseActivity {
    int requestCode;

    @Bind(R.id.radioGroup_traddition)
    RadioGroup radioGroup_traddition;

    private String[] options;
    private String currentCheck;
    private String currentTitle;
    private int checkedPos = -1;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_radio);
        ButterKnife.bind(this);
        requestCode = getIntent().getIntExtra("requestCode", 0);
        currentCheck = getIntent().getStringExtra(Constant.CHECKED_ITEM);
        options = getIntent().getStringArrayExtra(Constant.activity_radioGroup);
        currentTitle = getIntent().getStringExtra(Constant.activity_radio_title);
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(currentCheck)) {
                checkedPos = i;
                break;
            }
        }
        setRadio();

    }

    @Override
    protected void setData() {
        setTitle(currentTitle);
    }

    public void setRadio() {
        for (int i = 0; i < options.length; i++) {
            Drawable drawable = this.getResources().getDrawable(R.drawable.radio);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            RadioButton radio = new RadioButton(getApplicationContext());
            radio.setBackgroundResource(R.drawable.radio_bj);
            if (i == checkedPos) {
                radio.setChecked(true);
            }
            radio.setText(options[i]);
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            radio.setTextAppearance(this, R.style.clickable_layout_key);
            int left = ScreenUtils.dip2px(this, 15);
            radio.setPadding(left, 0, 50, 0);
            radio.setButtonDrawable(android.R.color.transparent);
            radio.setCompoundDrawables(null, null, drawable, null);
            radio.setId(i);
            radioGroup_traddition.addView(radio);
        }
        radioGroup_traddition.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                currentCheck = options[checkedId];
                Intent mIntent = new Intent();
                mIntent.putExtra(Constant.activity_radio, currentCheck);
                setResult(requestCode, mIntent);
                finish();
            }

        });
    }

}
