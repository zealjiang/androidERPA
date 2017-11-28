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
import com.jzg.erp.appraiser.model.TradeRecord;
import com.jzg.erp.appraiser.presenter.QueryCarConcernPresenter;
import com.jzg.erp.event.CityChangedEvent;
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

public class TradeRecordFragment extends Fragment implements IBaseView, QueryCarConcernPresenter.OnDataSucceedListener<TradeRecord> {
    @Bind(R.id.rvHistory)
    RecyclerView rvHistory;
    private SaleRecAdapter adapter;
    private List<TradeRecord.DataBean> recordList;
    private QueryCarConcernPresenter presenter;
    private String styleId;
    private String regDate;
    private String mileage;
    private String cityId;

    public TradeRecordFragment() {
        recordList = new ArrayList<>();
    }

    @SuppressLint("ValidFragment")
    public TradeRecordFragment(String styleId, String regDate, String mileage,String cityId) {
        this.styleId = styleId;
        this.regDate = regDate;
        this.mileage = mileage;
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
        presenter.getTradeRecord(styleId,regDate,mileage,cityId);
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
        presenter.getTradeRecord(styleId,regDate,mileage,cityId);
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
    public void showData(TradeRecord data) {
        recordList.clear();
        List<TradeRecord.DataBean> newList = data.getData();
        if(newList!=null && newList.size()>0){
            recordList.addAll(newList);
        }
        adapter.notifyDataSetChanged();

    }

    private class SaleRecAdapter extends CommonAdapter<TradeRecord.DataBean> {
        public SaleRecAdapter(Context context, int layoutId, List<TradeRecord.DataBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, TradeRecord.DataBean salesRecord) {
            holder.setText(R.id.tvCarFullName,salesRecord.getStyleName());
            holder.setText(R.id.tvDateAndMileage, salesRecord.getDate()+" "+salesRecord.getMileage()+"公里");
            holder.setText(R.id.tvSoldPrice,salesRecord.getPrice()+"万");
            holder.setText(R.id.tvSoldDate,salesRecord.getPurchaseDate());
            holder.setText(R.id.tvCity,salesRecord.getStyleName());
            holder.setText(R.id.tvCompany,"");
        }
    }


}
