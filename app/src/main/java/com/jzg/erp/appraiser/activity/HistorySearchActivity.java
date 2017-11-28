package com.jzg.erp.appraiser.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.adapter.EvalStatusItemAdapter;
import com.jzg.erp.appraiser.adapter.HistorySearchAdapter;
import com.jzg.erp.appraiser.adapter.HistorySearchPopAdapter;
import com.jzg.erp.appraiser.model.EvalStatusListModel;
import com.jzg.erp.appraiser.model.HistoryEvaListModel;
import com.jzg.erp.appraiser.model.ParamOption;
import com.jzg.erp.appraiser.model.PopListDataModel;
import com.jzg.erp.appraiser.presenter.EvalStatusListPresenter;
import com.jzg.erp.appraiser.presenter.HistoryEvalListPresenter;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.utils.DataUtils;
import com.jzg.erp.utils.MTextUtils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.view.ICommon;
import com.jzg.erp.view.IEvalStatusListInf;
import com.jzg.erp.widget.ClearableEditText;
import com.jzg.erp.widget.CustomRippleButton;
import com.jzg.erp.widget.EmptyView;
import com.jzg.erp.widget.ErrorView;
import com.jzg.erp.widget.RecycleViewDivider;
import com.jzg.erp.widget.XRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistorySearchActivity extends BaseActivity implements ICommon, IEvalStatusListInf, XRecyclerView.LoadingListener, PopupWindow.OnDismissListener {

    @Bind(R.id.iv_status)
    ImageView ivStatus;
    @Bind(R.id.ll_status)
    LinearLayout llStatus;
    @Bind(R.id.iv_car_licensePN)
    ImageView ivCarLicensePN;
    @Bind(R.id.ll_car_licensePN)
    LinearLayout llCarLicensePN;
    @Bind(R.id.iv_time)
    ImageView ivTime;
    @Bind(R.id.ll_time)
    LinearLayout llTime;
    @Bind(R.id.iv_shop)
    ImageView ivShop;
    @Bind(R.id.ll_shop)
    LinearLayout llShop;
    @Bind(R.id.iv_appraiser)
    ImageView ivAppraiser;
    @Bind(R.id.ll_appraiser)
    LinearLayout llAppraiser;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.recycler)
    XRecyclerView recycler;
    @Bind(R.id.err_layout)
    ErrorView errorView;
    @Bind(R.id.tv_status)
    TextView tvStatus;
    @Bind(R.id.tv_car_licensePN)
    TextView tvCarLicensePN;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_shop)
    TextView tvShop;
    @Bind(R.id.tv_appraiser)
    TextView tvAppraiser;
    private Context context;

    private XRecyclerView mXRecyclerView;
    private EmptyView emptyView;
    private PopupWindow popupWindow;
    private RelativeLayout rlContainer;

    private Animation animationUp, animationDown;


    private HistoryEvalListPresenter mPresenter;
    private HistorySearchAdapter historySearchAdapter;
    private List<HistoryEvaListModel.DataBean> historySearchList = new ArrayList<>();
    private int pageIndex = 1;

    //----popWindow----
    private int popStatusIndex = -1;
    private int popShopIndex = -1;
    private ClearableEditText tvPopTimeBegin;
    private ClearableEditText tvPopTimeEnd;

    private int clickType = -1;
    private final int STATUS = 1;
    private final int CAR_LICENSE = 2;
    private final int TIME = 3;
    private final int SHOP = 4;
    private final int APPRAISER = 5;

    private Calendar calendar;
    private DatePickerDialog dialog;

    //历史列表请求参数
    private String statusId = "-1";// 1/待申请2/待审核3/已批准4/已拒绝5/已删除
    private String licenseId = "";
    private String timeBegin = "";
    private String timeEnd = "";
    private String shopId = "";
    private String appraiser = "";


    //状态列表数据
    private List<EvalStatusListModel.DataBean> historyStatusList;
    private EvalStatusListPresenter statusPresenter;
    private EvalStatusItemAdapter statusAdapter;

    //门店
    List<ParamOption.DataBean.KeyValueItem> storesList = JzgApp.getAppContext().getOption().getData().getStores();
    final String[] historyShop = DataUtils.getListItemNameArray(storesList);
    private List<PopListDataModel> historyShopList;
    private HistorySearchPopAdapter shopAdapter;

    //当前请求返回的数据条数
    private int requestCount = 0;



    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_history_search);
        ButterKnife.bind(this);
        context = this;
        init();
        initPopWindow();

        //状态
        historyStatusList = new ArrayList<EvalStatusListModel.DataBean>();
        statusAdapter = new EvalStatusItemAdapter(context, historyStatusList);
        statusPresenter = new EvalStatusListPresenter(this);
        requestStatusList();

        //门店
        historyShopList = new ArrayList<PopListDataModel>();
        shopAdapter = new HistorySearchPopAdapter(context, historyShopList);
        //获取历史评估单列表
        mPresenter = new HistoryEvalListPresenter(this);
        requestList();


    }

    @Override
    protected void setData() {

        setTitle("历史查询");


        for (int i = 0; i < historyShop.length; i++) {
            PopListDataModel data = new PopListDataModel();
            data.setText(historyShop[i]);
            data.setTextId("" + i);
            if (i == 0) {
                data.setTextColor(R.color.orange_9);
            } else {
                data.setTextColor(R.color.grey3);
            }
            historyShopList.add(data);
        }

    }

    /**
     * 获取历史评估单列表
     */
    private void requestList() {
        mPresenter.getHistoryEvalList(pageIndex + "", licenseId, statusId, timeBegin, timeEnd, shopId, appraiser);


        //状态
        if(popStatusIndex<=0){
            tvStatus.setTextColor(getResources().getColor(R.color.black_font));
        }else {
            tvStatus.setTextColor(getResources().getColor(R.color.common_yellow));
        }

        //门店
        if(popShopIndex<=0){
            tvShop.setTextColor(getResources().getColor(R.color.black_font));
        }else {
            tvShop.setTextColor(getResources().getColor(R.color.common_yellow));
        }

        //车牌号
        if(null!=licenseId&&licenseId.length()>0){
            tvCarLicensePN.setTextColor(getResources().getColor(R.color.common_yellow));
        }else {
            tvCarLicensePN.setTextColor(getResources().getColor(R.color.black_font));
        }
        //时间
        if(timeBegin.length()>0||timeEnd.length()>0){
            tvTime.setTextColor(getResources().getColor(R.color.common_yellow));
        }else {
            tvTime.setTextColor(getResources().getColor(R.color.black_font));
        }
        //评估师
        if(null!=appraiser&&appraiser.length()>0){
            tvAppraiser.setTextColor(getResources().getColor(R.color.common_yellow));
        }else {
            tvAppraiser.setTextColor(getResources().getColor(R.color.black_font));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void init() {
        //动画
        animationUp = AnimationUtils.loadAnimation(context,
                R.anim.list_title_drawable_right_up);
        animationUp.setFillAfter(true);
        animationDown = AnimationUtils.loadAnimation(context,
                R.anim.list_title_drawable_right_down);
        animationDown.setFillAfter(true);

        //titles 设置
        llStatus.setOnClickListener(this);
        llCarLicensePN.setOnClickListener(this);
        llTime.setOnClickListener(this);
        llShop.setOnClickListener(this);
        llAppraiser.setOnClickListener(this);

        //内容显示区域
        mXRecyclerView = (XRecyclerView) findViewById(R.id.recycler);
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
                requestList();
            }
        });
        historySearchAdapter = new HistorySearchAdapter(context, R.layout.item_history_search, historySearchList, mXRecyclerView);
        mXRecyclerView.setAdapter(historySearchAdapter);
        historySearchAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                //jump(EvaluateOrderActivity.class);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    private void initPopWindow() {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(context);
            View contentView = LayoutInflater.from(context).inflate(
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

    @OnClick({R.id.ll_status, R.id.ll_car_licensePN, R.id.ll_time, R.id.ll_shop, R.id.ll_appraiser})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.ll_status:
                clickType = STATUS;
                controlPopWindowShow(ivStatus);
                break;
            case R.id.ll_car_licensePN:
                clickType = CAR_LICENSE;
                controlPopWindowShow(ivCarLicensePN);
                break;
            case R.id.ll_time:
                clickType = TIME;
                controlPopWindowShow(ivTime);
                break;
            case R.id.ll_shop:
                clickType = SHOP;
                controlPopWindowShow(ivShop);
                break;
            case R.id.ll_appraiser:
                clickType = APPRAISER;
                controlPopWindowShow(ivAppraiser);
                break;
        }
    }

    private void controlPopWindowShow(ImageView imgView) {

        if (rlContainer != null) {
            rlContainer.removeAllViews();
        }
        rlContainer.addView(initPopView());
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(line);
            imgView.startAnimation(animationUp);
        }
    }


    private AdapterView.OnItemClickListener popItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            switch (clickType) {
                case STATUS:
                    popStatusIndex = position;
                    statusId = historyStatusList.get(position).getRequestStatus() + "";
                    break;
                case SHOP:
                    popShopIndex = position;
                    //获取选中的门店ID
                    shopId = DataUtils.getParamOpIdByName(storesList, historyShop[position])+"";

                    break;
                default:
                    break;
            }

            //请求网络
            pageIndex = 1;
            requestList();

            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        }
    };


    private View initPopView() {

        final View view = LayoutInflater.from(context).inflate(R.layout.view_history_search_pop, null);

        tvPopTimeBegin = (ClearableEditText) view.findViewById(R.id.clr_et_time_from);
        tvPopTimeBegin.setText("");
        tvPopTimeEnd = (ClearableEditText) view.findViewById(R.id.clr_et_time_to);
        tvPopTimeEnd.setText("");

        TextView editTitle = (TextView) view.findViewById(R.id.tv_edit_title);
        final EditText editContent = (EditText) view.findViewById(R.id.et_input);
        CustomRippleButton confirm = (CustomRippleButton) view.findViewById(R.id.btnConfirm);

        final View.OnClickListener timeClickLisener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.clr_et_time_from:
                        //开始时间
                        calendar = Calendar.getInstance();
                        dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //boolean flag = DateTimeUtils.laterThanNow(year, monthOfYear, dayOfMonth);
                                //if (!flag) {
                                    String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                    dialog.dismiss();
                                    tvPopTimeBegin.setText(date);
                                //} else {
                                    //MyToast.showLong(getString(R.string.selected_date_cannot_before_than_today));
                                //}
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                        dialog.show();
                        break;
                    case R.id.clr_et_time_to:
                        //结束时间
                        calendar = Calendar.getInstance();
                        dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //boolean flag = DateTimeUtils.laterThanNow(year, monthOfYear, dayOfMonth);
                                //if (!flag) {
                                    String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                    dialog.dismiss();
                                    tvPopTimeEnd.setText(date);
                                //} else {
                                    //MyToast.showLong(getString(R.string.selected_date_cannot_before_than_today));
                                //}
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                        dialog.show();
                        break;
                    case R.id.btnConfirm:
                        //开始筛选
                        switch (clickType) {
                            case CAR_LICENSE:
                                licenseId = editContent.getText().toString().trim();
                                break;
                            case TIME:
                                timeBegin = tvPopTimeBegin.getText().toString().trim();
                                timeEnd = tvPopTimeEnd.getText().toString().trim();
                                break;
                            case APPRAISER:
                                appraiser = editContent.getText().toString().trim();
                                break;
                            default:
                                break;
                        }

                        //请求网络
                        pageIndex = 1;
                        requestList();

                        if (popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                        break;
                    default:
                        break;
                }

            }
        };

        tvPopTimeBegin.setOnClickListener(timeClickLisener);
        tvPopTimeEnd.setOnClickListener(timeClickLisener);
        confirm.setOnClickListener(timeClickLisener);

        LinearLayout timeLayout = (LinearLayout) view.findViewById(R.id.ll_purchase_time);
        LinearLayout editLayout = (LinearLayout) view.findViewById(R.id.ll_edit);

        int width = getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams lp = timeLayout.getLayoutParams();
        lp.width = width;
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        timeLayout.setLayoutParams(lp);
        editLayout.setLayoutParams(lp);

        ListView listView = (ListView) view.findViewById(R.id.lv_pop);
        listView.setOnItemClickListener(popItemClickListener);
        switch (clickType) {
            case STATUS://状态
                listView.setVisibility(View.VISIBLE);
                timeLayout.setVisibility(View.GONE);
                editLayout.setVisibility(View.GONE);
                confirm.setVisibility(View.GONE);
                listView.setAdapter(statusAdapter);
                initStatusListItemColor(historyStatusList, popStatusIndex);
                statusAdapter.setData(historyStatusList);
                statusAdapter.notifyDataSetChanged();
                break;
            case CAR_LICENSE://车牌号
                listView.setVisibility(View.GONE);
                timeLayout.setVisibility(View.GONE);
                editLayout.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);
                editContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
                editTitle.setText("车牌号");
                editContent.setText(MTextUtils.nullIfEmpty(licenseId));

                break;
            case TIME://时间
                listView.setVisibility(View.GONE);
                timeLayout.setVisibility(View.VISIBLE);
                editLayout.setVisibility(View.GONE);
                confirm.setVisibility(View.VISIBLE);
                tvPopTimeBegin.setText(MTextUtils.nullIfEmpty(timeBegin));
                tvPopTimeEnd.setText(MTextUtils.nullIfEmpty(timeEnd));

                break;
            case SHOP:
                listView.setVisibility(View.VISIBLE);
                timeLayout.setVisibility(View.GONE);
                editLayout.setVisibility(View.GONE);
                confirm.setVisibility(View.GONE);
                listView.setAdapter(shopAdapter);
                initShopListItemColor(historyShopList, popShopIndex);
                shopAdapter.setData(historyShopList);
                shopAdapter.notifyDataSetChanged();
                break;
            case APPRAISER:
                listView.setVisibility(View.GONE);
                timeLayout.setVisibility(View.GONE);
                editLayout.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);
                editTitle.setText("评估师");
                editContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                editContent.setText(MTextUtils.nullIfEmpty(appraiser));

                break;
            default:
                break;
        }

        return view;
    }

    private void initStatusListItemColor(List<EvalStatusListModel.DataBean> list, int index) {
        for (int i = 0; i < list.size(); i++) {
            if (i == index) {
                list.get(i).setTextColor(R.color.orange_9);
            } else {
                list.get(i).setTextColor(R.color.grey3);
            }
        }
    }

    private void initShopListItemColor(List<PopListDataModel> list, int index) {
        for (int i = 0; i < list.size(); i++) {
            if (i == index) {
                list.get(i).setTextColor(R.color.orange_9);
            } else {
                list.get(i).setTextColor(R.color.grey3);
            }
        }
    }

    @Override
    public void onDismiss() {
        switch (clickType) {
            case STATUS:
                ivStatus.startAnimation(animationDown);
                break;
            case CAR_LICENSE:
                ivCarLicensePN.startAnimation(animationDown);
                break;
            case TIME:
                ivTime.startAnimation(animationDown);
                break;
            case SHOP:
                ivShop.startAnimation(animationDown);
                break;
            case APPRAISER:
                ivAppraiser.startAnimation(animationDown);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        requestList();
    }

    @Override
    public void onLoadMore() {

        if (historySearchList.size() == 0) {
            mXRecyclerView.loadMoreComplete();
            return;
        }
        if (historySearchList.size() > 0 && historySearchList.size() % Constant.PAGECOUNT != 0) {
            mXRecyclerView.loadMoreComplete();
            return;
        }

        if(requestCount<Constant.PAGECOUNT){
            //加载完了所有数据
            MyToast.showShort("加载完了所有数据");
            return;
        }

        pageIndex++;
        requestList();
    }

    /**
     * 获取 历史评估单状态 选项数据
     */
    private void requestStatusList() {
        statusPresenter.getEvalStatusList(licenseId, timeBegin, timeEnd, shopId, appraiser);
    }

    @Override
    public void succeed(Object object) {
        if (object != null) {
            if (pageIndex == 1)
                mXRecyclerView.refreshComplete();
            else
                mXRecyclerView.loadMoreComplete();

            if (pageIndex == 1) {
                historySearchList.clear();
                historySearchList.addAll((List<HistoryEvaListModel.DataBean>) object);
            } else {
                historySearchList.addAll((List<HistoryEvaListModel.DataBean>) object);
            }


            requestCount = ((List<HistoryEvaListModel.DataBean>) object).size();


            mXRecyclerView.setVisibility(View.VISIBLE);
            if (errorView.getVisibility() == View.VISIBLE) {
                errorView.setVisibility(View.GONE);
            }
            historySearchAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void succeed(List<EvalStatusListModel.DataBean> statusList) {
        historyStatusList.clear();
        for (int i = 0; i < statusList.size(); i++) {
            statusList.get(i).setTextColor(R.color.grey3);
            statusList.get(i).setTextId("" + i);
        }

        historyStatusList.addAll(statusList);

    }

    @Override
    public void showError(String error) {
        super.showError(error);
        if(pageIndex==1)
            mXRecyclerView.refreshComplete();
        else
            mXRecyclerView.loadMoreComplete();
    }
}
