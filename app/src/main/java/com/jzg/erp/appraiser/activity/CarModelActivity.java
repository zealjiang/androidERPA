package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jzg.erp.R;
import com.jzg.erp.adapter.ModelCategoryAdapter;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.model.CarSourceData;
import com.jzg.erp.model.Make;
import com.jzg.erp.model.MakeList;
import com.jzg.erp.model.Model;
import com.jzg.erp.presenter.BrandPresenter;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.view.IBrandInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zealjiang on 2016/8/4 14:12.
 * Email: zealjiang@126.com
 */
public class CarModelActivity extends BaseActivity implements IBrandInterface,AdapterView.OnItemClickListener {

    @Bind(R.id.model_list)
    ListView model_list;
    private LinearLayout modelHearderLayout;

    private ModelCategoryAdapter modelCategoryAdapter;
    //车系列表数据
    private ArrayList<Model> models;
    //车系列表分组数据
    private ArrayList<String> modelsGroupNames;


    private BrandPresenter carSourcePresenter;
    private String makeName = ""; //品牌名字
    private String makeId = "";
    private String makeLogo = "";
    /**当前选中的品牌下的某款车系 by zj*/
    private Model curModel;
    public static final int QUERYCAR_MSG = 1;// 车辆信息

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_car_model);
        ButterKnife.bind(this);

        model_list.setOnItemClickListener(this);

        modelHearderLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.model_header_view, null);

    }

    @Override
    protected void setData() {
        makeId = getIntent().getStringExtra("brandId");
        makeName = getIntent().getStringExtra("brandName");
        makeLogo = getIntent().getStringExtra("brandLogo");
        setTitle("选择车系");
        init();
    }


    private void init() {

        carSourcePresenter = new BrandPresenter(this);
        carSourcePresenter.getModel(getModelParams());//请求车系

    }

    /**
     * 设置ListView头部信息
     */
    private void setHeader() {
        SimpleDraweeView img = (SimpleDraweeView) modelHearderLayout.findViewById(R.id.header_img);
        TextView text = (TextView) modelHearderLayout.findViewById(R.id.header_text);
        Uri uri = Uri.parse(makeLogo);
        img.setImageURI(uri);
        text.setText(makeName);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void showBrand(ArrayList<Map<String, Object>> items) {

    }

    @Override
    public void showModel() {
        modelCategoryAdapter = new ModelCategoryAdapter(this, models, modelsGroupNames);
        setHeader();
        model_list.addHeaderView(modelHearderLayout);
        model_list.setAdapter(modelCategoryAdapter);
    }

    @Override
    public int getDefaultFontColor() {
        return R.color.grey3;
    }

    @Override
    public void setIndexData(Map<String, Integer> indexData) {
    }

    @Override
    public void setBrands(ArrayList<Make> makes) {

    }

    @Override
    public void setModels(ArrayList<Model> mModels) {
        this.models = mModels;
    }

    @Override
    public void setModelsGroupNames(ArrayList<String> mModelsGroupNames) {
        this.modelsGroupNames = mModelsGroupNames;
    }

    @Override
    public Map<String, String> getBrandParams() {
        return null;
    }

    @Override
    public Map<String, String> getModelParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("makeId", makeId + "");
        params.put("op", "GetModel");
        params.put("InSale", "1");
        params.put("userId", JzgApp.getUser().getUserID()+"");
        Map<String, Object> signMaps = new HashMap<>();
        signMaps.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMaps, "2CB3147B-D93C-964B-47AE-EEE448C84E3C"));

        return params;
    }

    @Override
    public boolean readFromCache(MakeList makeList) {
        return false;
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
            case R.id.model_list://车系
                //如果点击位置不是车系的头部位置则进行跳转，并把当前的品牌数据和车系数据发送到车型界面去
                if (position != 0) {
                    curModel = models.get(position - 1);

                    Intent in = new Intent();
                    in.putExtra("seriesName", curModel.getName());
                    in.putExtra("seriesId", curModel.getId()+"");
                    setResult(QUERYCAR_MSG, in);
                    finish();
                }else{
                }
                break;
        }
    }
}
