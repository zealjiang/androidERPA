package com.jzg.erp.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.activity.AppraiserHomeActivity;
import com.jzg.erp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectRoleActivity extends BaseActivity {

    @Bind(R.id.ivLeft)
    ImageView ivLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_select_role);
        ButterKnife.bind(this);
    }

    @Override
    protected void setData() {
        setTitle(getString(R.string.select_role));
        setImageRight(R.mipmap.ic_tools);
        showBack(false);
        //ivLeft.setImageResource(R.mipmap.ic_account);

    }

    @OnClick({R.id.ivLeft, R.id.ivRight, R.id.rlAppraiser, R.id.rlSeller})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                break;
            case R.id.ivRight:
                jump(ToolActivity.class,false);
                break;
            case R.id.rlAppraiser:
                JzgApp.getUser().setCurUserType(2);
                jump(AppraiserHomeActivity.class,true);
                break;
            case R.id.rlSeller:
                JzgApp.getUser().setCurUserType(1);
                jump(HomeActivity.class,true);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
