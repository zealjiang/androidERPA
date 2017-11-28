package com.jzg.erp.appraiser.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment {
    private static final int TRADE_HISTORY = 0;
    private static final int EVALUATE_HISTORY = 1;
    @Bind(android.R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private String modelId;
    private String carId;

    @SuppressLint("ValidFragment")
    public HistoryFragment(String modelId, String carId) {
        this.modelId = modelId;
        this.carId = carId;
    }

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case TRADE_HISTORY:
                        return new TradeHistoryFragment(modelId);
                    case EVALUATE_HISTORY:
                        return new EvaluateHistoryFragment(carId);
                    default:
                        return new TradeHistoryFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case TRADE_HISTORY:
                        return "同车系收购/销售记录";
                    case EVALUATE_HISTORY:
                        return "该车评估记录";
                    default:
                        return "同车系收购/销售记录";
                }
            }
        });
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
