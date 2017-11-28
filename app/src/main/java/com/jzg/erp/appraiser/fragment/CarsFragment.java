package com.jzg.erp.appraiser.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.activity.EvaluateOrderActivity;
import com.jzg.erp.appraiser.adapter.EVCarListAdapter;
import com.jzg.erp.appraiser.model.EVCarList;
import com.jzg.erp.appraiser.model.PGOrderModel;
import com.jzg.erp.appraiser.presenter.EVCarListPresenter;
import com.jzg.erp.appraiser.presenter.PGOrderPresenter;
import com.jzg.erp.appraiser.widget.RlCarStyleDrawer;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.global.Constant;
import com.jzg.erp.model.CarSourceData;
import com.jzg.erp.utils.MTextUtils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.view.ICommon;
import com.jzg.erp.view.IPGOrder;
import com.jzg.erp.widget.EmptyView;
import com.jzg.erp.widget.ErrorView;
import com.jzg.erp.widget.RecycleViewDivider;
import com.jzg.erp.widget.XRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zealjiang on 2016/8/9 14:28.
 * Email: zealjiang@126.com
 */
public class CarsFragment extends NewBaseFragment implements ICommon,IPGOrder,XRecyclerView.LoadingListener, PopupWindow.OnDismissListener {


    @Bind(R.id.iv_car_type)
    ImageView ivCarType;
    @Bind(R.id.ll_car_type)
    LinearLayout llCarType;
    @Bind(R.id.iv_car_licensePN)
    ImageView ivCarLicensePN;
    @Bind(R.id.ll_car_licensePN)
    LinearLayout llCarLicensePN;
    @Bind(R.id.iv_car_phone)
    ImageView ivCarPhone;
    @Bind(R.id.ll_car_phone)
    LinearLayout llCarPhone;
    @Bind(R.id.iv_car_vin)
    ImageView ivCarVin;
    @Bind(R.id.ll_car_vin)
    LinearLayout llCarVin;
    @Bind(R.id.cars_HintLine)
    View carsHintLine;
    @Bind(R.id.err_layout)
    ErrorView errorView;

    @Bind(R.id.tv_car_type)
    TextView tvCarType;
    @Bind(R.id.tv_car_licensePN)
    TextView tvCarLicensePN;
    @Bind(R.id.tv_car_phone)
    TextView tvCarPhone;
    @Bind(R.id.tv_car_vin)
    TextView tvCarVin;

    private XRecyclerView mXRecyclerView;
    private EmptyView emptyView;
    private PopupWindow popupWindow;
    private RelativeLayout rlContainer;

    private Animation animationUp, animationDown;
    private RlCarStyleDrawer rlCarStyleDrawer;
    private Context context;

    private LinearLayout llCarLicensePop;
    private LinearLayout llPhoneNumPop;
    private LinearLayout llCarVinPop;


    private int clickType = -1;
    private static final int CAR_STYLE = 1;
    private static final int CAR_LICENSE = 2;
    private static final int PHONE_NUM = 3;
    private static final int CAR_VIN = 4;

    private EVCarListPresenter mPresenter;
    private EVCarListAdapter evCarListAdapter;
    private List<EVCarList.DataBean> evCarList = new ArrayList<>();
    private int pageIndex = 1;
    //车辆列表请求参数
    private String license = "";
    private String brandName = "";
    private String mobile = "";
    private String seriesName = "";
    private String styleName = "";
    private String vin = "";

    //用来区分当前根据哪个条件在进行查询
    private final int DEFAULT = 0;
    private final int STYLE = 1;
    private final int LICENSE = 2;
    private final int PHONE = 3;
    private final int VIN = 4;
    private int CUR_SEARCH_TYPE = DEFAULT;


    private PGOrderPresenter pgOrderPresenter;
    private String selectCarId = "";

    //当前请求返回的数据条数
    private int requestCount = 0;

    @Override
    protected void setView() {
        setTitle("车辆");
        showBack(false);
        pgOrderPresenter = new PGOrderPresenter(this);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cars, container, false);
        ButterKnife.bind(this, rootView);
        init(rootView);
        initPopWindow();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //获取车辆列表
        mPresenter = new EVCarListPresenter(this);
        requestCarList();
    }

    @Override
    protected void initData() {
        //1
        context = this.getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public void init(View view) {
        animationUp = AnimationUtils.loadAnimation(getActivity(),
                R.anim.list_title_drawable_right_up);
        animationUp.setFillAfter(true);
        animationDown = AnimationUtils.loadAnimation(getActivity(),
                R.anim.list_title_drawable_right_down);
        animationDown.setFillAfter(true);

        llCarType.setOnClickListener(this);
        llCarLicensePN.setOnClickListener(this);
        llCarPhone.setOnClickListener(this);
        llCarVin.setOnClickListener(this);

        //内容显示区域
        mXRecyclerView = (XRecyclerView) view.findViewById(R.id.recycler);
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.CubeTransition);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mXRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, 1, R.color.common_gray_line));
        mXRecyclerView.setLoadingListener(this);
        mXRecyclerView.setPullRefreshEnabled(true);
        mXRecyclerView.setLoadingMoreEnabled(true);
        emptyView = new EmptyView(context, "暂无数据");
        ((ViewGroup) mXRecyclerView.getParent()).addView(emptyView);
        mXRecyclerView.setEmptyView(emptyView);
        errorView.setOnErrorListener(new ErrorView.OnErrorListener() {
            @Override
            public void onError() {
                requestCarList();
            }
        });
        evCarListAdapter = new EVCarListAdapter(context, R.layout.item_ev_car_list, evCarList, mXRecyclerView);
        mXRecyclerView.setAdapter(evCarListAdapter);
        evCarListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                //获取车辆ID
                selectCarId = evCarList.get(position).getID()+"";
                //获取车辆单号
                pgOrderPresenter.getPGOrderList(selectCarId);


            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    private void initPopWindow() {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(getActivity());
            View contentView = LayoutInflater.from(getActivity()).inflate(
                    R.layout.cars_popwindow, null);
            rlContainer = (RelativeLayout) contentView
                    .findViewById(R.id.cars_popWindow);
            contentView.findViewById(R.id.cars_out_popWindow)
                    .setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });
            popupWindow.setContentView(contentView);
            popupWindow.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new ColorDrawable(getResources()
                    .getColor(android.R.color.transparent)));
        }
        popupWindow.setOnDismissListener(this);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
    }



    @Override
    public void onDismiss() {
        switch (clickType) {
            case CAR_STYLE:
                ivCarType.startAnimation(animationDown);
                break;
            case CAR_LICENSE:
                ivCarLicensePN.startAnimation(animationDown);
                break;
            case PHONE_NUM:
                ivCarPhone.startAnimation(animationDown);
                break;
            case CAR_VIN:
                ivCarVin.startAnimation(animationDown);
                break;
            default:
                break;
        }
    }

    /**
     * 请求车辆列表
     *
     * @author zealjiang
     * @time 2016/8/9 17:02
     */
    private void requestCarList() {
        CUR_SEARCH_TYPE = DEFAULT;
        mPresenter.getCarList(JzgApp.getUser().getUserID()+"",pageIndex+"");
    }

    /**
     * 请求车辆列表 by 车型
     *
     * @author zealjiang
     * @time 2016/8/23 17:02
     */
    private void requestCarListByStyleName() {
        CUR_SEARCH_TYPE = STYLE;
        if(brandName==null){
            brandName = "";
        }
        if(seriesName==null){
            seriesName = "";
        }
        if(styleName==null){
            styleName = "";
        }
        mPresenter.getCarListByStyleName(JzgApp.getUser().getUserID()+"",pageIndex+"",brandName,seriesName,styleName);

        tvCarType.setTextColor(getResources().getColor(R.color.common_yellow));
        tvCarLicensePN.setTextColor(getResources().getColor(R.color.black_font));
        tvCarPhone.setTextColor(getResources().getColor(R.color.black_font));
        tvCarVin.setTextColor(getResources().getColor(R.color.black_font));

    }

    /**
     * 请求车辆列表 by 车牌号
     *
     * @author zealjiang
     * @time 2016/8/23 17:02
     */
    private void requestCarListByLisence() {
        CUR_SEARCH_TYPE = LICENSE;
        mPresenter.getCarListByLicense(JzgApp.getUser().getUserID()+"",license,pageIndex+"");

        tvCarType.setTextColor(getResources().getColor(R.color.black_font));
        tvCarLicensePN.setTextColor(getResources().getColor(R.color.common_yellow));
        tvCarPhone.setTextColor(getResources().getColor(R.color.black_font));
        tvCarVin.setTextColor(getResources().getColor(R.color.black_font));
    }

    /**
     * 请求车辆列表 by 手机号
     *
     * @author zealjiang
     * @time 2016/8/23 17:02
     */
    private void requestCarListByPhone() {
        CUR_SEARCH_TYPE = PHONE;
        mPresenter.getCarListByPhone(JzgApp.getUser().getUserID()+"",mobile,pageIndex+"");

        tvCarType.setTextColor(getResources().getColor(R.color.black_font));
        tvCarLicensePN.setTextColor(getResources().getColor(R.color.black_font));
        tvCarPhone.setTextColor(getResources().getColor(R.color.common_yellow));
        tvCarVin.setTextColor(getResources().getColor(R.color.black_font));
    }


    /**
     * 请求车辆列表 by VIN
     *
     * @author zealjiang
     * @time 2016/8/23 17:02
     */
    private void requestCarListByVIN() {
        CUR_SEARCH_TYPE = VIN;
        mPresenter.getCarListByVIN(JzgApp.getUser().getUserID()+"",vin,pageIndex+"");

        tvCarType.setTextColor(getResources().getColor(R.color.black_font));
        tvCarLicensePN.setTextColor(getResources().getColor(R.color.black_font));
        tvCarPhone.setTextColor(getResources().getColor(R.color.black_font));
        tvCarVin.setTextColor(getResources().getColor(R.color.common_yellow));
    }




    @OnClick({R.id.ll_car_type, R.id.ll_car_licensePN, R.id.ll_car_phone, R.id.ll_car_vin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_car_type:
                clickType = CAR_STYLE;
                if(toInitFirstCarListView() == null){
                    break;
                }
                controlPopWindowShow(ivCarType);
                break;
            case R.id.ll_car_licensePN:
                clickType = CAR_LICENSE;
                controlPopWindowShow(ivCarLicensePN);
                break;
            case R.id.ll_car_phone:
                clickType = PHONE_NUM;
                controlPopWindowShow(ivCarPhone);
                break;
            case R.id.ll_car_vin:
                clickType = CAR_VIN;
                controlPopWindowShow(ivCarVin);
                break;
        }
    }

    private View toInitFirstCarListView(){
        if(rlCarStyleDrawer == null){
            rlCarStyleDrawer = new RlCarStyleDrawer(getActivity());

            rlCarStyleDrawer.setIcar(new RlCarStyleDrawer.ICar() {
                @Override
                public void carInfo(CarSourceData.DataBean dataBean) {
                    if(popupWindow.isShowing()){
                        popupWindow.dismiss();
                    }

                    //品牌选择了全部
                    if(null==dataBean){
                        requestCarList();
                        return;
                    }

                    //请求车辆列表
                    brandName = dataBean.getMakeName(); //dataBean.getMakeID()+"";
                    seriesName = dataBean.getModelName(); //dataBean.getModelID()+"";
                    styleName = dataBean.getStyleName(); //dataBean.getStyleID()+"";

                    requestCarListByStyleName();
                }
            });
        }
        return rlCarStyleDrawer;
    }

    private void controlPopWindowShow(ImageView imgView) {

        if (rlContainer != null) {
            rlContainer.removeAllViews();
        }
        rlContainer.addView(initPopView());
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(carsHintLine);
            imgView.startAnimation(animationUp);
        }
    }

    private View initPopView() {
        if(clickType == CAR_STYLE){
            return rlCarStyleDrawer;
        }

        final View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.view_cars_pop, null);


        llCarLicensePop = (LinearLayout) view
                .findViewById(R.id.ll_license_pop);
        final EditText mEditLicense = (EditText) view.findViewById(R.id.et_license_pop);
        llPhoneNumPop = (LinearLayout) view
                .findViewById(R.id.ll_phone_pop);
        final EditText mEditPhoneNum = (EditText) view.findViewById(R.id.et_phone_pop);
        llCarVinPop = (LinearLayout) view
                .findViewById(R.id.ll_vin_pop);
        final EditText mEditCarVin = (EditText) view.findViewById(R.id.et_vin_pop);

        mEditLicense.setText(MTextUtils.nullIfEmpty(license));
        mEditPhoneNum.setText(MTextUtils.nullIfEmpty(mobile));
        mEditCarVin.setText(MTextUtils.nullIfEmpty(vin));


        Button saveBtn = (Button) view
                .findViewById(R.id.btnConfirm);
        final View.OnClickListener timeClickLisener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnConfirm:

                        if(llCarLicensePop.getVisibility() == View.VISIBLE){
                            final String slicense = mEditLicense.getText().toString();
                            if(TextUtils.isEmpty(slicense)){
                                license = "";
                            } else if(slicense.length() > 7||slicense.length()==0){
                                Toast.makeText(getActivity(), "请输入正确车牌号!", Toast.LENGTH_SHORT).show();
                                break;
                            } else {
                                license = slicense;
                            }
                            //请求网络
                            pageIndex=1;
                            requestCarListByLisence();
                        }

                        if(llPhoneNumPop.getVisibility() == View.VISIBLE){
                            final String smobile = mEditPhoneNum.getText().toString();

                            if(TextUtils.isEmpty(smobile)){
                                mobile = "";
                            } else if(smobile.length() < 11){
                                Toast.makeText(getActivity(), "请输入正确电话号码!", Toast.LENGTH_SHORT).show();
                                break;
                            } else {
                                mobile = smobile;
                            }

                            //请求网络
                            pageIndex=1;
                            requestCarListByPhone();
                        }

                        if(llCarVinPop.getVisibility() == View.VISIBLE){
                            final String svin = mEditCarVin.getText().toString();

                            if(TextUtils.isEmpty(svin)){
                                vin = "";
                            } else if(svin.length() != 17){
                                Toast.makeText(getActivity(), "请输入正确VIN!", Toast.LENGTH_SHORT).show();
                                break;
                            } else {
                                vin = svin;
                            }

                            //请求网络
                            pageIndex=1;
                            requestCarListByVIN();
                        }


                        if (popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                        break;
                    default:
                        break;
                }

            }
        };

        saveBtn.setOnClickListener(timeClickLisener);
        int width = getActivity().getWindowManager().getDefaultDisplay()
                .getWidth();
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        switch (clickType) {
            case CAR_STYLE:// 车型

                llCarLicensePop.setVisibility(View.GONE);
                llPhoneNumPop.setVisibility(View.GONE);
                llCarVinPop.setVisibility(View.GONE);

                break;
            case CAR_LICENSE:// 车牌号

                llCarLicensePop.setVisibility(View.VISIBLE);
                llPhoneNumPop.setVisibility(View.GONE);
                llCarVinPop.setVisibility(View.GONE);

                break;
            case PHONE_NUM:// 手机号

                llCarLicensePop.setVisibility(View.GONE);
                llPhoneNumPop.setVisibility(View.VISIBLE);
                llCarVinPop.setVisibility(View.GONE);

                break;
            case CAR_VIN:// 车辆VIN
                llCarLicensePop.setVisibility(View.GONE);
                llPhoneNumPop.setVisibility(View.GONE);
                llCarVinPop.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        return view;
    }

    @Override
    public void succeed(Object object) {
        errorView.setVisibility(View.GONE);
        stopLoading();
        if(pageIndex==1) {
            evCarList.clear();
        }

        requestCount = ((List<EVCarList.DataBean>) object).size();
        evCarList.addAll((List<EVCarList.DataBean>)object);
        evCarListAdapter.notifyDataSetChanged();

    }


    @Override
    public void onRefresh() {
        pageIndex=1;
        switch (CUR_SEARCH_TYPE){
            case DEFAULT:
                requestCarList();
                break;
            case STYLE:
                requestCarListByStyleName();
                break;
            case LICENSE:
                requestCarListByLisence();
                break;
            case PHONE:
                requestCarListByPhone();
                break;
            case VIN:
                requestCarListByVIN();
                break;
        }

    }

    @Override
    public void onLoadMore() {
        if(evCarList.size()==0){
            mXRecyclerView.loadMoreComplete();
            return;
        }
        if(evCarList.size()>0 && evCarList.size()% Constant.PAGECOUNT!=0){
            mXRecyclerView.loadMoreComplete();
            return;
        }

        if(requestCount<Constant.PAGECOUNT){
            //加载完了所有数据
            MyToast.showShort("加载完了所有数据");
            return;
        }

        pageIndex++;

        switch (CUR_SEARCH_TYPE){
            case DEFAULT:
                requestCarList();
                break;
            case STYLE:
                requestCarListByStyleName();
                break;
            case LICENSE:
                requestCarListByLisence();
                break;
            case PHONE:
                requestCarListByPhone();
                break;
            case VIN:
                requestCarListByVIN();
                break;
        }
    }
    private void stopLoading(){
        if(mXRecyclerView==null)
            return;
        if(pageIndex==1)
            mXRecyclerView.refreshComplete();
        else
            mXRecyclerView.loadMoreComplete();
    }

    @Override
    public void showError(String error) {
        super.showError(error);
        MyToast.showShort("请求失败，请重试");
        if(pageIndex==1)
            mXRecyclerView.refreshComplete();
        else
            mXRecyclerView.loadMoreComplete();
    }

    @Override
    public void succeedPGOrder(List<PGOrderModel.DataBean> dataBeanList) {
        //获取车辆单号
        if(dataBeanList==null||dataBeanList.size()<=0){
            return;
        }
        PGOrderModel.DataBean[] array = new PGOrderModel.DataBean[dataBeanList.size()];
        for(int i=0;i<dataBeanList.size();i++){
            array[i] = dataBeanList.get(i);
        }
        Intent intent = new Intent(context,EvaluateOrderActivity.class);
        Bundle bundle = new Bundle();

        //传递对象
        bundle.putParcelableArray("pgOrderArray",array);
        ArrayList<PGOrderModel.DataBean> arr = new ArrayList<>();
        arr.addAll(dataBeanList);
        bundle.putParcelableArrayList("pgOrderArray",arr);
        bundle.putString("carId",selectCarId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
