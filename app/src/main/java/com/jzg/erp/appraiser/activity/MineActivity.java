package com.jzg.erp.appraiser.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.ui.activity.AccountActivity;
import com.jzg.erp.ui.activity.SelectRoleActivity;
import com.jzg.erp.ui.activity.SettingActivity;
import com.jzg.erp.ui.activity.ToolActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zealjiang on 2016/8/17 16:38.
 * Email: zealjiang@126.com
 */
public class MineActivity extends BaseActivity {


    @Bind(R.id.rl_account_inf)
    RelativeLayout rlAccountInf;
    @Bind(R.id.rl_tool)
    RelativeLayout rlTool;
    @Bind(R.id.rl_history)
    RelativeLayout rlHistory;
    @Bind(R.id.rl_setting)
    RelativeLayout rlSetting;
    @Bind(R.id.rl_role)
    RelativeLayout rlRole;
    @Bind(R.id.v_line_role)
    View vLineRole;
    private Context context;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine);
        context = this;
        ButterKnife.bind(this);
        if(JzgApp.getUser().getUserType()!=3){
            rlRole.setVisibility(View.GONE);
            vLineRole.setVisibility(View.GONE);
        }else{
            rlRole.setVisibility(View.VISIBLE);
            vLineRole.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void setData() {
        setTitle(R.string.mine);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.rl_account_inf, R.id.rl_tool, R.id.rl_history, R.id.rl_setting, R.id.rl_role})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rl_account_inf:
                startActivity(new Intent(context, AccountActivity.class));
                break;
            case R.id.rl_tool:
                startActivity(new Intent(context, ToolActivity.class));
                break;
            case R.id.rl_history:
                startActivity(new Intent(context, HistorySearchActivity.class));
                break;
            case R.id.rl_setting:
                startActivity(new Intent(context, SettingActivity.class));
                break;
            case R.id.rl_role:
                changeRole(SelectRoleActivity.class);
                break;
        }
    }

    /**
     * finish掉之前所有的Activity 跳转到新的Activity，并关闭当前Activity
     * @author zealjiang
     * @time 2016-08-27
     */
    private void changeRole(Class classs) {
        //将当前Activity从要Stack中移除，防止finishAll()时程序退出
        JzgApp.getAppContext().remove(this);
        //关掉Stack中的Activity
        JzgApp.getAppContext().finishAll();
        //跳转到登录页
        Intent intent = new Intent(this, classs);
        startActivity(intent);
        //关掉本Activity
        finish();
    }

}
