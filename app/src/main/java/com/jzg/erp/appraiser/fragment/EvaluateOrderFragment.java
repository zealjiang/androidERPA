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

public class EvaluateOrderFragment extends Fragment {
    private static final String[] TITLES = {"基本信息","客户","车况","图片","评估价格"};

    @Bind(android.R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private String pingguNo;
    private String carId = "";
    private String pingguId = "";

    public EvaluateOrderFragment() {
    }
    @SuppressLint("ValidFragment")
    public EvaluateOrderFragment(String pingguNo,String carId,String pingguId) {
        this.pingguNo = pingguNo;
        this.carId = carId;
        this.pingguId = pingguId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return TITLES.length;
            }

            @Override
            public Fragment getItem(int position) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        ABaseInfoFragment aBaseInfoFragment = new ABaseInfoFragment();
                        bundle.putString("pingguNo",pingguNo);
                        bundle.putString("carId",carId);
                        aBaseInfoFragment.setArguments(bundle);
                        return aBaseInfoFragment;
                    case 1:
                        return new CustomerFragment(pingguNo);
                    case 2:
                        CarConditionShowFragment carConditionShowFragment = new CarConditionShowFragment();
                        bundle.putString("pingguId",pingguId);
                        carConditionShowFragment.setArguments(bundle);
                        return carConditionShowFragment;
                    case 3:
                        return new PhotoGridFragment(carId);
                    case 4:
                        return new EvaluatePriceResultFragment(pingguNo);
                    default:
                        return new TradeHistoryFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
               return TITLES[position];
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
