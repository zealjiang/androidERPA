package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jzg.erp.R;
import com.jzg.erp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCustomerInfoActivity extends BaseActivity {


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_customer_info);
        ButterKnife.bind(this);
    }

    @Override
    protected void setData() {
        setTitle("客户资料");
    }

    @OnClick({R.id.ivLeft, R.id.tvpersonalCustomer, R.id.tvCompanyCustomer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvpersonalCustomer:
                startP();
                break;
            case R.id.tvCompanyCustomer:
                startC();
                break;
        }
    }

    public void startP() {
        Intent intent = new Intent(AddCustomerInfoActivity.this, AddPersonalCustomerActivity.class);
        startActivity(intent);
    }


    public void startC() {
        Intent intent = new Intent(AddCustomerInfoActivity.this, AddCompanyCustomerActivity.class);
        startActivity(intent);
    }
}
