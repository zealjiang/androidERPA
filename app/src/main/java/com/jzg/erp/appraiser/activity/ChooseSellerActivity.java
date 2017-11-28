package com.jzg.erp.appraiser.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.Seller;
import com.jzg.erp.appraiser.presenter.QueryCarConcernPresenter;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.view.IBaseView;
import com.jzg.erp.widget.RvDividerItemDecoration;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseSellerActivity extends BaseActivity implements IBaseView, QueryCarConcernPresenter.OnDataSucceedListener<Seller> {

    @Bind(R.id.rvSeller)
    RecyclerView rvSeller;
    private QueryCarConcernPresenter presenter;
    private List<Seller.DataBean> sellers;
    private SellerAdapter adapter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_seller);
        ButterKnife.bind(this);
        rvSeller.setLayoutManager(new LinearLayoutManager(this));
        rvSeller.addItemDecoration(new RvDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        presenter = new QueryCarConcernPresenter(this,this);
        presenter.getStoreSellers(String.valueOf(JzgApp.getUser().getStoreId()));
        sellers = new ArrayList<>();
        adapter = new SellerAdapter(this,R.layout.item_address,sellers);
        rvSeller.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Seller.DataBean seller = sellers.get(position);
                Intent intent = getIntent();
                intent.putExtra("seller",seller);
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    protected void setData() {
        setTitle("选择跟进销售顾问");
    }

    @Override
    public void showData(Seller data) {
        sellers.clear();
        List<Seller.DataBean> newList = data.getData();
        if (newList!=null && newList.size()>0){
            sellers.addAll(newList );
        }
        adapter.notifyDataSetChanged();

    }

    @OnClick(R.id.ivLeft)
    public void onClick() {
        finish();
    }

    private class SellerAdapter extends CommonAdapter<Seller.DataBean> {

        public SellerAdapter(Context context, int layoutId, List<Seller.DataBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, Seller.DataBean provinceCity) {
            holder.setText(R.id.tvAddress,provinceCity.getName());
        }
    }
}
