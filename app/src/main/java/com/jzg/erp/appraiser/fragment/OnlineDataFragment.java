package com.jzg.erp.appraiser.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;
import com.jzg.erp.appraiser.model.OnlineSale;
import com.jzg.erp.appraiser.presenter.QueryCarConcernPresenter;
import com.jzg.erp.event.CityChangedEvent;
import com.jzg.erp.utils.LogUtil;
import com.jzg.erp.view.IBaseView;
import com.jzg.erp.widget.RecycleViewDivider;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OnlineDataFragment extends Fragment implements IBaseView, QueryCarConcernPresenter.OnDataSucceedListener<OnlineSale> {
    private static final String TAG = "OnlineDataFragment";
    @Bind(R.id.rvHistory)
    RecyclerView rvHistory;
    private SaleRecAdapter adapter;
    private QueryCarConcernPresenter presenter;
    private List<OnlineSale.DataBean> recordList;
    private String styleId;
    private String cityId;

    public OnlineDataFragment() {
        recordList = new ArrayList<>();
    }
    @SuppressLint("ValidFragment")
    public OnlineDataFragment(String styleId, String cityId) {
        this.styleId = styleId;
        this.cityId = cityId;
        recordList = new ArrayList<>();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(CityChangedEvent event){
        cityId = event.getCityId();
        LogUtil.e(TAG,"收到城市切换通知");
        presenter.getOnlineSales(styleId,cityId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rv_disscroll, container, false);
        ButterKnife.bind(this, view);
        presenter = new QueryCarConcernPresenter(this,this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getOnlineSales(styleId,cityId);
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, 2,R.color.common_gray_dark));
        adapter = new SaleRecAdapter(getActivity(),R.layout.item_sale_rec,recordList);
        rvHistory.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void showData(OnlineSale data) {
        recordList.clear();
        List<OnlineSale.DataBean> newList = data.getDataBean();
        if(newList!=null && newList.size()>0){
            recordList.addAll(newList);
        }
        adapter.notifyDataSetChanged();
    }

    private class SaleRecAdapter extends CommonAdapter<OnlineSale.DataBean> {

        public SaleRecAdapter(Context context, int layoutId, List<OnlineSale.DataBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, OnlineSale.DataBean salesRecord) {
            holder.setText(R.id.tvCarFullName,salesRecord.getStyleName());
            holder.setText(R.id.tvDateAndMileage,salesRecord.getPublishDate()+" "+salesRecord.getMileage()+"公里");
            holder.setText(R.id.tvSoldPrice,salesRecord.getListingPrice()+"万");
            holder.setText(R.id.tvSoldDate,salesRecord.getPurchaseDate());
            holder.setText(R.id.tvCity,salesRecord.getCityName());
            holder.setText(R.id.tvCompany,salesRecord.getSource());
        }
    }


}
