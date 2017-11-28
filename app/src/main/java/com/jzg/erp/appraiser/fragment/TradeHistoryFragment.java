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
import com.jzg.erp.appraiser.model.TradeHistory;
import com.jzg.erp.appraiser.presenter.QueryCarConcernPresenter;
import com.jzg.erp.utils.DateTimeUtils;
import com.jzg.erp.view.IBaseView;
import com.jzg.erp.widget.RecycleViewDivider;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TradeHistoryFragment extends Fragment implements IBaseView,QueryCarConcernPresenter.OnDataSucceedListener<TradeHistory> {
    @Bind(R.id.rvHistory)
    RecyclerView rvHistory;
    private List<TradeHistory.DataBean> historyList;
    private TradeHistoryAdapter adapter;
    private QueryCarConcernPresenter presenter;
    private String modelId;

    public TradeHistoryFragment() {
    }

    @SuppressLint("ValidFragment")
    public TradeHistoryFragment(String modelId) {
        this.modelId = modelId;
        historyList = new ArrayList<>();
        presenter = new QueryCarConcernPresenter(this,this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trade_history, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, 2,R.color.common_gray_dark));
        adapter = new TradeHistoryAdapter(getActivity(),R.layout.item_trade_history,historyList);
        rvHistory.setAdapter(adapter);
        presenter.getTradeHistoryListByModelId("orderListByModel",modelId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showData(TradeHistory data) {
        historyList.clear();
        List<TradeHistory.DataBean> newList = data.getData();
        if(newList!=null && newList.size()>0){
            historyList.addAll(newList);
        }
        adapter.notifyDataSetChanged();
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

    private class TradeHistoryAdapter extends CommonAdapter<TradeHistory.DataBean>{

        public TradeHistoryAdapter(Context context, int layoutId, List<TradeHistory.DataBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, TradeHistory.DataBean tradeHistory) {
            holder.setText(R.id.tvCarFullName,tradeHistory.getFullName());
            holder.setText(R.id.tvEvaluator,tradeHistory.getAppraiserName());
            holder.setText(R.id.tvPurchasePrice,tradeHistory.getPurchasePrice()+"万");
            holder.setText(R.id.tvPurchaseDate, DateTimeUtils.formatMillsStr(tradeHistory.getPurchaseTime(),DateTimeUtils.YYYYMMDD));
            holder.setText(R.id.tvSeller,tradeHistory.getSaleName());
            holder.setText(R.id.tvSoldPrice,tradeHistory.getSalePrice()+"万");
            holder.setText(R.id.tvSoldDate,DateTimeUtils.formatMillsStr(tradeHistory.getSaleTime(),DateTimeUtils.YYYYMMDD));
        }
    }


}
