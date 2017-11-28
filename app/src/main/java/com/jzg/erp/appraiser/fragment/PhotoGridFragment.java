package com.jzg.erp.appraiser.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;
import com.jzg.erp.appraiser.adapter.GridPhotoAdapter;
import com.jzg.erp.appraiser.model.CarPhoto;
import com.jzg.erp.appraiser.presenter.QueryCarConcernPresenter;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.ui.activity.BrowsePicsActivity;
import com.jzg.erp.view.IBaseView;
import com.jzg.erp.widget.GridSpacingItemDecoration;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoGridFragment extends Fragment implements OnItemClickListener, IBaseView, QueryCarConcernPresenter.OnDataSucceedListener<CarPhoto> {
    private QueryCarConcernPresenter presenter;
    private String pingguNo;
    private String carId;

    @Bind(R.id.rvPhoto)
    RecyclerView rvPhoto;
    private List<SubmitParamWrapper.PhotoItem> photoItems;
    private GridPhotoAdapter adapter;

    public PhotoGridFragment() {

    }
    @SuppressLint("ValidFragment")
    public PhotoGridFragment(String pingguNo) {
        this.pingguNo = pingguNo;
        photoItems = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_grid, container, false);
        ButterKnife.bind(this, view);
        carId = getActivity().getIntent().getStringExtra("carId");
        presenter = new QueryCarConcernPresenter(this,this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvPhoto.setLayoutManager(new GridLayoutManager(getActivity(),3));
        rvPhoto.addItemDecoration(new GridSpacingItemDecoration(3,16,false));
        adapter = new GridPhotoAdapter(getActivity(),R.layout.item_rv_photo,photoItems);
        rvPhoto.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter.getCarPhoto(carId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        Intent intent = new Intent(getActivity(), BrowsePicsActivity.class);
        intent.putExtra("data",urls);
        intent.putExtra("index",position);
        getActivity().startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
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

    private ArrayList<String> urls = new ArrayList<>();

    @Override
    public void showData(CarPhoto data) {
        photoItems.clear();
        urls.clear();
        List<CarPhoto.DataBean> newList = data.getData();
        if(newList!=null && newList.size()>0){
            for(CarPhoto.DataBean item:newList){
                SubmitParamWrapper.PhotoItem photoItem = new SubmitParamWrapper.PhotoItem(0,item.getPath(),item.getViewUrl(),item.getImgText());
                photoItems.add(photoItem);
                urls.add(item.getViewUrl());
            }
        }
        adapter.notifyDataSetChanged();
    }
}
