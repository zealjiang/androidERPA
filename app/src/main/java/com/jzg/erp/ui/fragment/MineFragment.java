package com.jzg.erp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.ui.activity.AccountActivity;
import com.jzg.erp.ui.activity.SelectRoleActivity;
import com.jzg.erp.ui.activity.SettingActivity;
import com.jzg.erp.ui.activity.ToolActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends NewBaseFragment {

    @Bind(R.id.rl_account_inf)
    RelativeLayout mRlAccountInf;
    @Bind(R.id.rl_role)
    RelativeLayout rlRole;
    @Bind(R.id.v_line_role)
    View vLineRole;

    @Override
    protected void setView() {
        showBack(false);
        setTitle(R.string.mine);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, rootView);
        if(JzgApp.getUser().getUserType()!=3){
            rlRole.setVisibility(View.GONE);
            vLineRole.setVisibility(View.GONE);
        }else{
            rlRole.setVisibility(View.VISIBLE);
            vLineRole.setVisibility(View.VISIBLE);
        }

        return rootView;
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.rl_account_inf)
    void toAccoutActivity(View view) {
        startActivity(new Intent(context, AccountActivity.class));
    }

    @OnClick(R.id.rl_tool)
    void toToolActivity(View view) {
        startActivity(new Intent(context, ToolActivity.class));
    }

    @OnClick(R.id.rl_setting)
    void toSettingActivity(View view) {
        startActivity(new Intent(context, SettingActivity.class));
    }

    @OnClick(R.id.rl_role)
    void toSelectRoleActivity(View view) {
        changeRole();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * finish掉之前所有的Activity 跳转到新的Activity，并关闭当前Activity
     * @author zealjiang
     * @time 2016-08-27
     */
    private void changeRole() {
        //将当前Activity从要Stack中移除，防止finishAll()时程序退出
        JzgApp.getAppContext().remove(this.getActivity());
        //关掉Stack中的Activity
        JzgApp.getAppContext().finishAll();
        //跳转到登录页
        Intent intent = new Intent(context, SelectRoleActivity.class);
        startActivity(intent);
        //关掉本Activity
        this.getActivity().finish();
    }

}
