package com.jzg.erp.appraiser.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.appraiser.model.PGOrderDetailModel;
import com.jzg.erp.appraiser.model.PGOrderInfoModel;
import com.jzg.erp.appraiser.model.PGPersonModel;
import com.jzg.erp.appraiser.presenter.ABaseInfoFraPresenter;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.dialog.ShowMsgDialog;
import com.jzg.erp.event.MenuShowEvent;
import com.jzg.erp.event.VINEvent;
import com.jzg.erp.utils.DateTimeUtils;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.view.ICommon3;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基本信息浏览页面(包括基本信息、配置信息、手续信息)
 *
 * @author zealjiang
 * @time 2016/8/18 9:34
 */
public class ABaseInfoFragment extends NewBaseFragment implements ICommon3, View.OnClickListener {

    @Bind(R.id.et_vin)
    EditText etVin;
    @Bind(android.R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tv_car_level)
    TextView tvCarLevel;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_state)
    TextView tvState;


    private BaseInfoShowFragment baseInfoFragment;
    private ConfigInfoShowFragment configInfoFragment;
    private ProcedureInfoShowFragment handlingInfoFragment;

    private Context context;
    private Activity activity;

    private ABaseInfoFraPresenter pgOrderInfoPresenter;
    private final int PERMISSION_CALL = 1;

    //评估单号
    private String pingguNo = "";
    private String carId = "";
    //点击姓名
    private String name = "";
    private String shop = "";
    private String phone = "";
    //点击状态
    private String status = "";
    private String purchaseTime = "";
    private String operator = "";

    @Override
    protected void setView() {
        //3
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        baseInfoFragment = new BaseInfoShowFragment();
                        bundle.putString("carId",carId);
                        baseInfoFragment.setArguments(bundle);
                        return baseInfoFragment;
                    case 1:
                        configInfoFragment = new ConfigInfoShowFragment();
                        bundle.putString("carId",carId);
                        configInfoFragment.setArguments(bundle);
                        return configInfoFragment;
                    case 2:
                        handlingInfoFragment = new ProcedureInfoShowFragment();
                        bundle.putString("carId",carId);
                        handlingInfoFragment.setArguments(bundle);
                        return handlingInfoFragment;
                    default:
                        baseInfoFragment = new BaseInfoShowFragment();
                        bundle.putString("carId",carId);
                        baseInfoFragment.setArguments(bundle);
                        return baseInfoFragment;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "基本信息";
                    case 1:
                        return "配置信息";
                    case 2:
                        return "手续信息";
                    default:
                        return "基本信息";
                }
            }

        });
        viewPager.setOffscreenPageLimit(3);
        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(0).setCustomView(getTabView("基本信息"));
        tabs.getTabAt(1).setCustomView(getTabView("配置信息"));
        tabs.getTabAt(2).setCustomView(getTabView("手续信息"));

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_base_info, container, false);
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        context = this.getContext();
        activity = this.getActivity();

        //获取数据
        Bundle bundle = getArguments();
        if(bundle!=null){
            pingguNo = bundle.getString("pingguNo");
            carId = bundle.getString("carId");
        }else{
            MyToast.showShort("无法获取评估单号");
            return rootView;
        }

        pgOrderInfoPresenter.getPGOrderInfo(pingguNo+"");
        return rootView;
    }

    @Override
    protected void initData() {
        //1
        pgOrderInfoPresenter = new ABaseInfoFraPresenter(this);
    }


    private View getTabView(String title) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(title);
        return view;
    }


    public void setTabText(int pos, String text) {
        tabs.getTabAt(pos).setText(text);
        ((TextView) tabs.getTabAt(pos).getCustomView().findViewById(R.id.tv_title)).setText(text);
    }



    public void startActivityForResult(Activity context, Class cls, String name, int value) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(name, value);
        context.startActivityForResult(intent, value);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.tv_name, R.id.tv_state})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                ShowMsgDialog.showMDPhone(activity, name, "所属店铺：" + shop, "手机号: ", phone, Color.parseColor("#000000"), new ShowMsgDialog.DialogClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog) {
                        dialog.dismiss();
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                            checkPermission();
                        }else{
                            call();
                        }

                    }
                });
                break;
            case R.id.tv_state:
                //当状态为待申请时，不可点击申请状态
                if(status.contains("待申请")){
                    return;
                }

                String content2 = "申请收购时间："+ DateTimeUtils.formatMillsStr(purchaseTime,DateTimeUtils.YYYYMMDDHHMM)+"\n操作员："+operator;
                ShowMsgDialog.showMaterialDialogNoBtn(activity, status,content2);
                break;
        }
    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
            //申请CALL_PHONE权限
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CALL);
        }else{
            call();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == PERMISSION_CALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call();
            } else {
                MyToast.showShort("拨打电话授权被拒，请授权后重试");
            }
        }
    }

    private void call(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity).setTitle("提示").setMessage("确定拨打" + phone + "吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent);

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setCancelable(true);
        builder.create().show();
    }

    @Override
    public void succeedA(Object object) {
        //根据评估单单号找出对应的评估人、评估单状态、车况等级
        PGOrderInfoModel.DataBean dataBean = (PGOrderInfoModel.DataBean)object;
        LogUtil.e(TAG,"ShowAdd:"+dataBean.getShowAdd());
        if(dataBean.getShowAdd()==1){
            EventBus.getDefault().post(new MenuShowEvent(0,true,pingguNo));
        }else{
            EventBus.getDefault().post(new MenuShowEvent(0,false,pingguNo));
        }

        tvCarLevel.setText(dataBean.getResult());
        tvName.setText(dataBean.getPingguUserName());
        tvState.setText(dataBean.getRequestStatusStr());

        name = dataBean.getPingguUserName();
        status = dataBean.getRequestStatusStr();

        //根据姓名获取评估人的信息
        pgOrderInfoPresenter.getPGPersonInfo(dataBean.getPingguUserId()+"");

        //点击评估单的状态获取评估单的详情
        pgOrderInfoPresenter.getPGOrderDetail(pingguNo+"",dataBean.getRequestStatus()+"");
    }

    @Override
    public void succeedB(Object object) {
        //点击姓名获取评估人的信息
        PGPersonModel.DataBean dataBean = (PGPersonModel.DataBean)object;
        shop = dataBean.getStoreName();
        phone = dataBean.getTel();
    }

    @Override
    public void succeedC(Object object) {
        //点击评估单的状态获取评估单的详情
        PGOrderDetailModel.DataBean dataBean = (PGOrderDetailModel.DataBean)object;

        purchaseTime = dataBean.getApproveTime();
        operator = dataBean.getApproveUserName();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventVin(VINEvent vinEvent) {
        etVin.setText(vinEvent.getVin());
    }
}
