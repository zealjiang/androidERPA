package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jzg.erp.R;
import com.jzg.erp.adapter.BrandListAdapter;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.model.CarSourceData;
import com.jzg.erp.model.Make;
import com.jzg.erp.model.MakeList;
import com.jzg.erp.model.Model;
import com.jzg.erp.presenter.BrandPresenter;
import com.jzg.erp.utils.ACache;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.view.IBrandInterface;
import com.jzg.erp.view.MyLetterListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zealjiang on 2016/8/4 14:12.
 * Email: zealjiang@126.com
 */
public class CarBrandActivity extends BaseActivity implements IBrandInterface,AdapterView.OnItemClickListener {

    @Bind(R.id.brand_list)
    ListView brand_list;
    LinearLayout makeHeaderLayout;
    @Bind(R.id.index_list)
    MyLetterListView indexListView;

    private BrandPresenter carSourcePresenter;
    private String json;
    //品牌列表分组数据
    private ArrayList<Map<String, Object>> brandItems;
    //abcd索引需要的数据
    private Map<String, Integer> indexData;
    //品牌列表数据
    private ArrayList<Make> makes;
    private String makeName = ""; //品牌名字
    //当前选中的品牌
    private Make curMake;
    public static final int QUERYCAR_MSG = 1;// 车辆信息

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_car_brand);
        ButterKnife.bind(this);

        makeHeaderLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.make_header_view, null);
        brand_list.addHeaderView(makeHeaderLayout);
        brand_list.setOnItemClickListener(this);
        indexListView.setOnTouchingLetterChangedListener(new LetterListViewListener());
    }

    @Override
    protected void setData() {
        setTitle("选择车型");
        init();
    }


    private void init() {

        //从缓存得到数据
        json = ACache.get(this).getAsString("makeinfo");

        carSourcePresenter = new BrandPresenter(this);
        carSourcePresenter.getBrand(getBrandParams());//请求车型车系
        if (!TextUtils.isEmpty(json)) {
            MakeList list = new Gson().fromJson(json, MakeList.class);//从本地缓存中度数据
            carSourcePresenter.showMake(list.getMakeList());
            dismissDialog();
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void showBrand(ArrayList<Map<String, Object>> items) {
        brandItems = items;
        BrandListAdapter brandListAdapter = new BrandListAdapter(this, brandItems);
        brand_list.setAdapter(brandListAdapter);
    }

    @Override
    public void showModel() {

    }

    @Override
    public int getDefaultFontColor() {
        return R.color.grey3;
    }

    @Override
    public void setIndexData(Map<String, Integer> indexData) {
        this.indexData = indexData;
    }

    @Override
    public void setBrands(ArrayList<Make> makes) {
        this.makes = makes;
    }

    @Override
    public void setModels(ArrayList<Model> mModels) {

    }

    @Override
    public void setModelsGroupNames(ArrayList<String> mModelsGroupNames) {

    }

    @Override
    public Map<String, String> getBrandParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("op", "GetMake");
        params.put("InSale", "1");
        params.put("userId",JzgApp.getUser().getUserID()+"");
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMap));
        return params;
    }

    @Override
    public Map<String, String> getModelParams() {
        return null;
    }

    @Override
    public boolean readFromCache(MakeList makeList) {
        Gson gson = new Gson();
        String json = ACache.get(this).getAsString("makeinfo");//从缓存得到数据
        String json1 = gson.toJson(makeList);//网路数据

        if (!TextUtils.isEmpty(json)) {//如果缓存不为空
            if (json.equals(json1)) {  //如果缓存和网路数据一致
                return true;
            } else {
                ACache.get(this).put("makeinfo", json1);
                return false;
            }
        } else {
            ACache.get(this).put("makeinfo", json1);
            return false;
        }

    }

    @Override
    public void ShowCarSourceData(CarSourceData carSourceData) {

    }

    @Override
    public void ShowMoreCarSourceData(CarSourceData carSourceData) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.brand_list:
                if (position == 0) {
                    //带全部选项并且点击全部所走流程 sendMakeAllBroadcast(); 处理点击全部的逻辑
                    makeName = "";
                } else if (position != 0) {
                    //带全部选项并且点击其他position所走流程
                    curMake = makes.get(position - 1);
                    //设置是否带有全部品牌选项标记，用于广播接受的时候判断
                    makeName = curMake.getMakeName();
                } else {
                    //不带全部选项的流程
                    curMake = makes.get(position);
                    //设置是否带有全部品牌选项标记，用于广播接受的时候判断
                    makeName = curMake.getMakeName();
                }

                Intent in = new Intent();
                in.putExtra("brandName", makeName);
                in.putExtra("brandId", curMake.getMakeId()+"");
                in.putExtra("brandLogo", curMake.getMakeLogo());
                setResult(QUERYCAR_MSG, in);
                finish();
                break;
        }
    }

    class LetterListViewListener implements MyLetterListView.OnTouchingLetterChangedListener {

        @Override
        public void onTouchingLetterChanged(final String s) {
            if (indexData != null && indexData.get(s) != null) {
                int position = indexData.get(s);
                brand_list.setSelection(position);
            }
        }

    }
}
