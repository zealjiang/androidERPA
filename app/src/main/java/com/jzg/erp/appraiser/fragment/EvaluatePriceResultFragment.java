package com.jzg.erp.appraiser.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.appraiser.model.EvaluatePrice;
import com.jzg.erp.appraiser.presenter.QueryCarConcernPresenter;
import com.jzg.erp.utils.DateTimeUtils;
import com.jzg.erp.view.IBaseView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluatePriceResultFragment extends Fragment implements IBaseView,QueryCarConcernPresenter.OnDataSucceedListener<EvaluatePrice>{

    @Bind(R.id.tvBrandModelStyle)
    TextView tvBrandModelStyle;
    @Bind(R.id.tvEvaluatePrice)
    TextView tvEvaluatePrice;
    @Bind(R.id.tvEvaluateDate)
    TextView tvEvaluateDate;
    @Bind(R.id.tvEvaluateCcntent)
    TextView tvEvaluateCcntent;
    @Bind(R.id.tvSaleName)
    TextView tvSaleName;
    private QueryCarConcernPresenter presenter;
    private String pingguNo;

    public EvaluatePriceResultFragment() {
    }
    @SuppressLint("ValidFragment")
    public EvaluatePriceResultFragment(String pingguNo) {
        this.pingguNo = pingguNo;
        presenter = new QueryCarConcernPresenter(this,this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate_price_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getEvaluatePrice(pingguNo);
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
    public void showData(EvaluatePrice data) {
        List<EvaluatePrice.DataBean> list = data.getData();
        if(list!=null && list.size()>0){
            EvaluatePrice.DataBean price = list.get(0);
            tvBrandModelStyle.setText(price.getFullName());
            tvEvaluatePrice.setText(price.getPingguPrice());
            tvEvaluateDate.setText(DateTimeUtils.formatMillsStr(price.getCreateTime(),DateTimeUtils.YYYYMMDD));
            tvEvaluateCcntent.setText(price.getReMark());
            tvSaleName.setText(price.getSaleName());
        }
    }
}
