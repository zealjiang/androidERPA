package com.jzg.erp.appraiser.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.widget.MyScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 车况等级规则介绍
 * @author zealjiang
 * @time 2016/8/15 15:20
 */
public class CarLevelDescActivity extends BaseActivity implements MyScrollView.OnScrollListener {

    @Bind(R.id.myscrollview)
    MyScrollView myscrollview;
    @Bind(R.id.chengben_text)
    TextView chengben_text;
    @Bind(R.id.zonghe_text)
    TextView zonghe_text;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_car_level_desc);
        ButterKnife.bind(this);
    }


    @Override
    protected void setData() {
        setTitle("车况等级规则介绍");
        myscrollview.setOnScrollListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onScroll(int scrollY) {
        if(scrollY>chengben_text.getTop()){
            zonghe_text.setText("整备成本等级");
        }else{
            zonghe_text.setText("综合车况等级");
        }
    }
}
