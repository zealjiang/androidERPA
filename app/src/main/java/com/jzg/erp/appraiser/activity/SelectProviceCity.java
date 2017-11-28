package com.jzg.erp.appraiser.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;
import com.jzg.erp.appraiser.model.ProvinceCity;
import com.jzg.erp.appraiser.presenter.ProvinceCityPresenter;
import com.jzg.erp.appraiser.view.IProvinceCity;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.global.IntentKey;
import com.jzg.erp.widget.RvDividerItemDecoration;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectProviceCity extends BaseActivity implements IProvinceCity, OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.rvProvinceCity)
    RecyclerView rvProvinceCity;
    @Bind(R.id.srLayout)
    SwipeRefreshLayout srLayout;
    private ProvinceCityPresenter presenter;
    private AddressAdapter adapter;
    private List<ProvinceCity.DataBean> addressList;
    private int step = 0;
    private String cityName;
    private int cityId = 0;
    private int backType;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_select_provice_city);
        ButterKnife.bind(this);
        presenter = new ProvinceCityPresenter(this);
        rvProvinceCity.setLayoutManager(new LinearLayoutManager(this));
        rvProvinceCity.addItemDecoration(new RvDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        step = getIntent().getIntExtra("step", 0);
        cityId = getIntent().getIntExtra("cityId", 0);
        cityName = getIntent().getStringExtra("cityName");
        backType = getIntent().getIntExtra("backType",0);

    }

    @Override
    protected void setData() {
        if(step==1){
            setTitle("选择省份");
        }else{
            setTitle("选择城市");
        }
        addressList = new ArrayList<>();
        adapter = new AddressAdapter(this,R.layout.item_address,addressList);
        rvProvinceCity.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        srLayout.setOnRefreshListener(this);
        onRefresh();
    }

    @Override
    public void showError(String error) {
        super.showError(error);
    }

    @Override
    public void showData(List<ProvinceCity.DataBean> data) {
        addressList.clear();
        if(data!=null && data.size()>0){
            addressList.addAll(data);
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.ivLeft)
    public void onClick() {
        finish();
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if(step==1){
            int cityId = addressList.get(position).getCityId();
            String cityName = addressList.get(position).getCityName();
            Intent intent = new Intent(this,SelectProviceCity.class);
            intent.putExtra("cityId",cityId);
            intent.putExtra("step",2);
            intent.putExtra("cityName",cityName);
            intent.putExtra("backType",backType);
            startActivityForResult(intent, IntentKey.REQ_CODE_SELECT_CITY);
        }else{
            Intent intent = new Intent();
            String result = cityName+addressList.get(position).getCityName();
            if(backType>0){
                result = addressList.get(position).getCityName()+","+addressList.get(position).getCityId();
            }
            intent.putExtra(Constant.ACTIVITY_INPUT,result);
            setResult(RESULT_OK,intent);
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && data!=null){
            setResult(RESULT_OK,data);
            finish();
        }

    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

    @Override
    public void onRefresh() {
        if(step==1){
            presenter.getProviceOfCity(0);
        }else{
            presenter.getProviceOfCity(cityId);
        }
    }

    private class AddressAdapter extends CommonAdapter<ProvinceCity.DataBean>{

        public AddressAdapter(Context context, int layoutId, List<ProvinceCity.DataBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, ProvinceCity.DataBean provinceCity) {
            holder.setText(R.id.tvAddress,provinceCity.getCityName());
        }
    }
}
