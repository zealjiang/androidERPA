package com.jzg.erp.appraiser.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.fragment.CarsFragment;
import com.jzg.erp.appraiser.fragment.RegisterFragment;
import com.jzg.erp.appraiser.presenter.AppraiserHomePresenter;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.view.IBaseView;
import com.jzg.erp.widget.bottombar.BottomTab;
import com.jzg.erp.widget.bottombar.BottomTabGroup;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AppraiserHomeActivity extends BaseActivity implements BottomTabGroup.OnCheckedChangeListener , IBaseView {
    @Bind(R.id.bottom_bar_root)
    BottomTabGroup bottomBarRoot;
    private CarsFragment carsFragment;
    private RegisterFragment evaluateFragment;
    private FragmentTransaction transaction;
    private Fragment fg ;
    private AppraiserHomePresenter presenter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_appraiser_home);
        ButterKnife.bind(this);
        BottomTab tab01 = (BottomTab) bottomBarRoot.getChildAt(0);
        tab01.setChecked(true);
        bottomBarRoot.setOnCheckedChangeListener(this);
        presenter = new AppraiserHomePresenter(this);
//        if(JzgApp.networkAvailable){
//            UpdateManager.getUpdateManager().checkAppUpdate(this, false);
//        }
        presenter.getOptionParams(JzgApp.getUser().getUserID()+"");
    }

    @Override
    protected void setData() {
        transaction =getSupportFragmentManager().beginTransaction();
        if(evaluateFragment==null){
            evaluateFragment = new RegisterFragment();
        }
        transaction.add(R.id.rlContainer,evaluateFragment);
        transaction.commit();
        fg = evaluateFragment;

    }

    @Override
    public void onCheckedChanged(BottomTabGroup root, int checkedId) {
        switch (checkedId) {
            case R.id.tab_05:
                if(evaluateFragment==null){
                    evaluateFragment = new RegisterFragment();
                }
                changeTab(evaluateFragment);
                break;
            case R.id.tab_06:
                if(carsFragment==null){
                    carsFragment = new CarsFragment();
                }
                changeTab(carsFragment);
                break;
        }
    }

    private void changeTab(Fragment fragment){
        fg = fragment;
        transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.rlContainer,fragment);
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
/*        if(fg instanceof CarsFragment){
            if(carsFragment.onKeyDown(keyCode, event)){
                return true;
            }
        }*/

        exitBy2Click();
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
