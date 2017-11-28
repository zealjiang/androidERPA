package com.jzg.erp.appraiser.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.appraiser.model.PingguCustomer;
import com.jzg.erp.appraiser.presenter.QueryCarConcernPresenter;
import com.jzg.erp.view.IBaseView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomerFragment extends Fragment implements IBaseView, QueryCarConcernPresenter.OnDataSucceedListener<PingguCustomer> {


    @Bind(R.id.rlCompanyName)
    RelativeLayout rlCompanyName;
    @Bind(R.id.rlCustomerName)
    RelativeLayout rlCustomerName;
    @Bind(R.id.rlPhone)
    RelativeLayout rlPhone;
    @Bind(R.id.rlDisplaceCar)
    RelativeLayout rlDisplaceCar;

    @Bind(R.id.tvCompanyNameRight)
    TextView tvCompanyNameRight;
    @Bind(R.id.tvCustomerNameRight)
    TextView tvCustomerNameRight;
    @Bind(R.id.tvCustomerNameLeft)
    TextView tvCustomerNameLeft;
    @Bind(R.id.tvPhoneValue)
    TextView tvPhoneValue;
    @Bind(R.id.tvDisplaceCarValue)
    TextView tvDisplaceCarValue;

    @Bind(R.id.tvGenderValue)
    TextView tvGenderValue;
    @Bind(R.id.tvWantPriceValue)
    TextView tvWantPriceValue;
    @Bind(R.id.tvWantLevelValue)
    TextView tvWantLevelValue;
    @Bind(R.id.tvCarHostSourceValue)
    TextView tvCarHostSourceValue;
    @Bind(R.id.tvCustomerAddressValue)
    TextView tvCustomerAddressValue;
    @Bind(R.id.tvPreSellDateValue)
    TextView tvPreSellDateValue;
    private String pingguNo;
    private QueryCarConcernPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    public CustomerFragment() {
    }
    @SuppressLint("ValidFragment")
    public CustomerFragment(String pingguNo) {
        this.pingguNo= pingguNo;
        presenter = new QueryCarConcernPresenter(this,this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getCustomerInfo(pingguNo);
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
    public void showData(PingguCustomer data) {
        List<PingguCustomer.DataBean> customerList = data.getData();
        if(customerList!=null && customerList.size()>0){
            PingguCustomer.DataBean customer = customerList.get(0);
            int customerType = customer.getCustomerType();
            if(customerType==1){
                rlCompanyName.setVisibility(View.GONE);
                rlDisplaceCar.setVisibility(View.VISIBLE);
                tvCustomerNameLeft.setText("姓名");
                tvCustomerNameRight.setText(customer.getCustomerName());
            }else{
                rlCompanyName.setVisibility(View.VISIBLE);
                rlDisplaceCar.setVisibility(View.GONE);
                tvCustomerNameLeft.setText("联系人");
                tvCompanyNameRight.setText(customer.getCustomerName());
                tvCustomerNameRight.setText(customer.getContact());
            }

            tvPhoneValue.setText(customer.getMobile());
            tvDisplaceCarValue.setText(customer.getReplaceStyle());
            tvGenderValue.setText(customer.getGender());
            tvWantPriceValue.setText(customer.getCustomerWantPrice()+"万");
            tvWantLevelValue.setText(customer.getSellcarLevelStr());
            tvCarHostSourceValue.setText(customer.getDicSubValue());
            tvCustomerAddressValue.setText(customer.getAddress());
            tvPreSellDateValue.setText(customer.getPresellTimeStr());
        }
    }


}
