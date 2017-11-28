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
import com.jzg.erp.appraiser.model.EvaluateHistory;
import com.jzg.erp.appraiser.presenter.QueryCarConcernPresenter;
import com.jzg.erp.view.IBaseView;
import com.jzg.erp.widget.RecycleViewDivider;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EvaluateHistoryFragment extends Fragment implements IBaseView ,QueryCarConcernPresenter.OnDataSucceedListener<EvaluateHistory>{
    @Bind(R.id.rvHistory)
    RecyclerView rvHistory;
    private List<EvaluateHistory.DataBean> historyList;
    private EvaluateHistoryAdapter adapter;
    private QueryCarConcernPresenter presenter;
    private String carId;

    public EvaluateHistoryFragment() {
    }

    @SuppressLint("ValidFragment")
    public EvaluateHistoryFragment(String carId) {
        this.carId = carId;
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
        adapter = new EvaluateHistoryAdapter(getActivity(),R.layout.item_evaluate_history,historyList);
        rvHistory.setAdapter(adapter);
        presenter.getEvaluateHistoryListByStyleId("listByCar",carId);
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
    public void showData(EvaluateHistory data) {
        historyList.clear();
        List<EvaluateHistory.DataBean> newList = data.getData();
        if(newList!=null && newList.size()>0){
            historyList.addAll(newList);
            adapter.notifyDataSetChanged();
        }
    }

    private class EvaluateHistoryAdapter extends CommonAdapter<EvaluateHistory.DataBean>{

        public EvaluateHistoryAdapter(Context context, int layoutId, List<EvaluateHistory.DataBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, EvaluateHistory.DataBean tradeHistory) {
            holder.setText(R.id.tvEvaluateNo,tradeHistory.getPingguNo());
            holder.setText(R.id.tvEvaluator,tradeHistory.getTrueName());
            holder.setText(R.id.tvPurchasePrice,tradeHistory.getPurchasePrice()+"万");
            holder.setText(R.id.tvShop,tradeHistory.getStoreName());
            holder.setText(R.id.tvSoldPrice,tradeHistory.getEquippedPrice()+"元");
        }
    }


}
