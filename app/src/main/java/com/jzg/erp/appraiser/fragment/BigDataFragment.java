package com.jzg.erp.appraiser.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.activity.SelectProviceCity;
import com.jzg.erp.appraiser.model.CarEvaluateInfo;
import com.jzg.erp.event.CityChangedEvent;
import com.jzg.erp.global.Constant;
import com.jzg.erp.global.IntentKey;
import com.jzg.erp.utils.UIUtils;
import com.jzg.erp.widget.YLineView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BigDataFragment extends Fragment {
    private static final int TRADE_REC = 0;
    private static final int EVALUATE_HISTORY = 1;

    @Bind(R.id.yLineView)
    YLineView yLineView;
    @Bind(R.id.chart2)
    BarChart chart2;
    @Bind(android.R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tvCurrCity)
    TextView tvCurrCity;
    private String cityId = "201";
    private String styleId ;
    private String regDate;
    private String mileage;
    private List<CarEvaluateInfo.Data.KeyValueItem> tradepricetendencyresp;
    private List<CarEvaluateInfo.Data.KeyValueItem> carstock;
    private ArrayList<String> years;
    private ArrayList<Float> prices;

    private ArrayList<String> years2;
    private ArrayList<Float> prices2;
    public BigDataFragment() {

    }

    @SuppressLint("ValidFragment")
    public BigDataFragment(List<CarEvaluateInfo.Data.KeyValueItem> carstock, List<CarEvaluateInfo.Data.KeyValueItem> tradepricetendencyresp) {
        styleId = JzgApp.getSubmitParam().getCarInfo().getCarBaseInfo().getStyleId();
        mileage = JzgApp.getSubmitParam().getCarInfo().getCarBaseInfo().getMileage();
        regDate = JzgApp.getSubmitParam().getCarInfo().getCarBaseInfo().getFirstRegDate();
        this.carstock=carstock;
        this.tradepricetendencyresp = tradepricetendencyresp;
        years = new ArrayList<>();
        prices = new ArrayList<>();
        years2 = new ArrayList<>();
        prices2 = new ArrayList<>();
        if(this.tradepricetendencyresp!=null && this.tradepricetendencyresp.size()>0){
            for(CarEvaluateInfo.Data.KeyValueItem item:this.tradepricetendencyresp){
                years.add(item.getKey());
                prices.add(Float.valueOf(item.getPrice()));
            }
        }

        if(this.carstock!=null && this.carstock.size()>0){
            for(CarEvaluateInfo.Data.KeyValueItem item:this.carstock){
                years2.add(item.getKey());
                prices2.add(Float.valueOf(item.getPrice()));

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bit_data, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(years.size()!=0 && years.size()==prices.size()){
            yLineView.setBottomTextList(years);
            yLineView.setShowYCoordinate(true);
            yLineView.setDataList(prices);
        }

         viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case TRADE_REC:
                        return new TradeRecordFragment(styleId,regDate,mileage,cityId);
                    case EVALUATE_HISTORY:
                        return new OnlineDataFragment(styleId,cityId);
                    default:
                        return new TradeRecordFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case TRADE_REC:
                        return "成交记录";
                    case EVALUATE_HISTORY:
                        return "网络在售";
                    default:
                        return "成交记录";
                }
            }
        });
        tabs.setupWithViewPager(viewPager);
        if(years2.size()==0)
            return;
        generateDataBar();
    }
    private void generateDataBar() {
        chart2.setBackgroundColor(Color.WHITE);
        chart2.setHighlightPerDragEnabled(false);
        chart2.setDragEnabled(false);
        chart2.setDoubleTapToZoomEnabled(false);
        chart2.setDragDecelerationEnabled(false);
        chart2.setScaleEnabled(false);
        chart2.setLongClickable(false);

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        for (int i = 0; i < prices2.size(); i++) {
            entries.add(new BarEntry(prices2.get(i),i));
        }
        BarDataSet dataSet = new BarDataSet(entries,"");
        dataSet.setColors(new int[]{UIUtils.getColor(R.color.common_orange)});
        dataSet.setHighLightAlpha(255);
        BarData barData = new BarData(years2,dataSet);
        chart2.setDescription("");
        chart2.setDrawGridBackground(false);
        chart2.setDrawBarShadow(false);

        XAxis xAxis = chart2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setTextSize(12);

        YAxis leftAxis = chart2.getAxisLeft();
        leftAxis.setSpaceTop(20f);
        leftAxis.setAxisMinValue(0);
        leftAxis.setTextSize(12);

        YAxis rightAxis = chart2.getAxisRight();
        rightAxis.setSpaceTop(20f);
        rightAxis.setAxisMinValue(200);
        rightAxis.setLabelCount(5);
        rightAxis.setTextSize(12);
        rightAxis.setDrawLabels(false);

        // set data
        chart2.setData(barData);
        chart2.animateY(700);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tvCurrCity)
    public void onClick() {
        Intent intent = new Intent(getActivity(),SelectProviceCity.class);
        intent.putExtra("cityId",0);
        intent.putExtra("step",1);
        intent.putExtra("backType",1);
        intent.putExtra("cityName","");
        startActivityForResult(intent, IntentKey.REQ_CODE_SELECT_CITY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK && data != null) {
            String result = data.getStringExtra(Constant.ACTIVITY_INPUT);
            switch (requestCode){
                case IntentKey.REQ_CODE_SELECT_CITY:
                    String[] arr = result.split(",");
                    if(arr!=null && arr.length==2){
                        cityId = arr[1];
                        tvCurrCity.setText(arr[0]);
                        EventBus.getDefault().post(new CityChangedEvent(0,cityId));
                    }
                    break;
            }
        }
    }
}
