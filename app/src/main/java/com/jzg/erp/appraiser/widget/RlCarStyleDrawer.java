package com.jzg.erp.appraiser.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jzg.erp.R;
import com.jzg.erp.adapter.BrandListAdapter;
import com.jzg.erp.adapter.ModelCategoryAdapter;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.adapter.JzgCarChooseStyleCategoryAdapter;
import com.jzg.erp.appraiser.model.CarStyle;
import com.jzg.erp.appraiser.presenter.CarStylePresenter;
import com.jzg.erp.model.CarSourceData;
import com.jzg.erp.model.Make;
import com.jzg.erp.model.MakeList;
import com.jzg.erp.model.Model;
import com.jzg.erp.presenter.BrandPresenter;
import com.jzg.erp.utils.ACache;
import com.jzg.erp.utils.MD5Utils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.utils.ShowDialogTool;
import com.jzg.erp.view.IBrandInterface;
import com.jzg.erp.view.ICommon;
import com.jzg.erp.view.MyLetterListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zealjiang on 2016/8/10 09:16.
 * Email: zealjiang@126.com
 */
public class RlCarStyleDrawer extends RelativeLayout implements IBrandInterface,ICommon,AdapterView.OnItemClickListener,
        SlidingDrawer.OnDrawerOpenListener,SlidingDrawer.OnDrawerCloseListener {

    //品牌
    @Bind(R.id.lv_car_brand)
    ListView lvCarBrand;
    @Bind(R.id.car_brand_index)
    MyLetterListView carBrandIndex;
    LinearLayout makeHeaderLayout;

    //车系
    @Bind(R.id.sd_car_series)
    SlidingDrawer sdCarSeries;
    @Bind(R.id.lv_car_series)
    ListView lvCarSeries;
    @Bind(R.id.iv_car_series_handle)
    ImageView ivCarSeriesHandle;

    //车型
    @Bind(R.id.sd_car_style)
    SlidingDrawer sdCarStyle;
    @Bind(R.id.lv_car_style)
    ListView lvCarStyle;
    @Bind(R.id.iv_car_style_handle)
    ImageView ivCarStyleHandle;
    LinearLayout styleHeaderLayout;


    //品牌
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

    //车系
    private LinearLayout modelHearderLayout;
    private ModelCategoryAdapter modelCategoryAdapter;
    //车系列表数据
    private ArrayList<Model> models;
    //车系列表分组数据
    private ArrayList<String> modelsGroupNames;
    private String makeId = "";
    private String makeLogo = "";
    /**当前选中的品牌下的某款车系 by zj*/
    private Model curModel;

    //车型
    /**
     * 车型列表适配器
     */
    private JzgCarChooseStyleCategoryAdapter mStyleCategoryAdapter;
    /**
     * 车型列表所有数据包括标题
     */
    private List<CarStyle.YearGroupListBean.StyleListBean> mStyles;
    /**
     * 所有车型标题
     */
    private List<String> mStylessGroupkey;
    /**
     * 车型
     */
    private List<CarStyle.YearGroupListBean> carStyleYearGroupList;
    private CarStylePresenter mCarStylePresenter;


    private Context context;


    public RlCarStyleDrawer(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public RlCarStyleDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public RlCarStyleDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View.inflate(getContext(), R.layout.car_style_drawer, this);
        ButterKnife.bind(this);

        //品牌
        makeHeaderLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.make_header_view, null);
        lvCarBrand.addHeaderView(makeHeaderLayout);
        lvCarBrand.setOnItemClickListener(this);
        carBrandIndex.setOnTouchingLetterChangedListener(new LetterListViewListener());

        //车系
        lvCarSeries.setOnItemClickListener(this);
        modelHearderLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.model_header_view, null);
        // 设置SlidingDrawer打开或者关闭时的监听器
        sdCarSeries.setOnDrawerOpenListener(this);
        sdCarSeries.setOnDrawerCloseListener(this);

        //车型
        //添加Header全部
        styleHeaderLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.make_header_view, null);
        lvCarStyle.addHeaderView(styleHeaderLayout);
        lvCarStyle.setOnItemClickListener(this);
        sdCarStyle.setOnDrawerOpenListener(this);
        sdCarStyle.setOnDrawerCloseListener(this);

        init();
    }

    public void init() {

        //从缓存得到数据
        json = ACache.get(context).getAsString("makeinfo");

        //品牌
        carSourcePresenter = new BrandPresenter(this);
        carSourcePresenter.getBrand(getBrandParams());//请求车型车系
        if (!TextUtils.isEmpty(json)) {
            MakeList list = new Gson().fromJson(json, MakeList.class);//从本地缓存中度数据
            carSourcePresenter.showMake(list.getMakeList());
            dismissDialog();
        }

    }

    @Override
    public void showBrand(ArrayList<Map<String, Object>> items) {
        brandItems = items;
        BrandListAdapter brandListAdapter = new BrandListAdapter(context, brandItems);
        lvCarBrand.setAdapter(brandListAdapter);
    }

    @Override
    public void showModel() {
        if(lvCarSeries.getHeaderViewsCount()>0){
            lvCarSeries.removeHeaderView(modelHearderLayout);
        }
        modelCategoryAdapter = new ModelCategoryAdapter(context, models, modelsGroupNames);
        setHeader();
        lvCarSeries.addHeaderView(modelHearderLayout);
        lvCarSeries.setAdapter(modelCategoryAdapter);
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
        this.models = mModels;
    }

    @Override
    public void setModelsGroupNames(ArrayList<String> mModelsGroupNames) {
        this.modelsGroupNames = mModelsGroupNames;
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
        Map<String, String> params = new HashMap<String, String>();
        params.put("makeId", makeId + "");
        params.put("op", "GetModel");
        params.put("InSale", "1");
        params.put("userId",JzgApp.getUser().getUserID()+"");
        Map<String, Object> signMaps = new HashMap<>();
        signMaps.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(signMaps, "2CB3147B-D93C-964B-47AE-EEE448C84E3C"));

        return params;
    }

    @Override
    public boolean readFromCache(MakeList makeList) {
        Gson gson = new Gson();
        String json = ACache.get(context).getAsString("makeinfo");//从缓存得到数据
        String json1 = gson.toJson(makeList);//网路数据

        if (!TextUtils.isEmpty(json)) {//如果缓存不为空
            if (json.equals(json1)) {  //如果缓存和网路数据一致
                return true;
            } else {
                ACache.get(context).put("makeinfo", json1);
                return false;
            }
        } else {
            ACache.get(context).put("makeinfo", json1);
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
            case R.id.lv_car_brand:
                if (position == 0) {
                    //带全部选项并且点击全部所走流程 sendMakeAllBroadcast(); 处理点击全部的逻辑
                    makeName = "";
                    if(iCar!=null){
                        //点击全部功能
                        if(iCar!=null){
                            iCar.carInfo(null);
                        }
                    }
                    return;
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

                makeId = curMake.getMakeId()+"";
                makeLogo = curMake.getMakeLogo();

                //车系
                carSourcePresenter = new BrandPresenter(this);
                carSourcePresenter.getModel(getModelParams());//请求车系
                //打开车系drawer
                openDrawer(sdCarSeries);
                break;
            case R.id.lv_car_series://车系
                //如果点击位置不是车系的头部位置则进行跳转，并把当前的品牌数据和车系数据发送到车型界面去
                if (position != 0) {
                    curModel = models.get(position - 1);

                    Intent in = new Intent();
                    in.putExtra("modelName", curModel.getName());
                    in.putExtra("modelId", curModel.getId());

                    //车型
                    startStyleListThread(curModel.getId()+"","0");
                    //打开车型drawer
                    openDrawer(sdCarStyle);
                }else{
                    //点击了全部车系
                    if(iCar!=null){
                        CarSourceData.DataBean bean = new CarSourceData.DataBean();
                        bean.setMakeID(Integer.valueOf(makeId));
                        bean.setMakeName(makeName);

                        iCar.carInfo(bean);
                    }
                }
                break;
            case R.id.lv_car_style:
                if (position == 0) {
                    //点击了车型的全部
                    //获取选中的品牌、车系、车型
                    String brandId = makeId;
                    String seriesId = curModel.getId()+"";


                    //MyToast.showShort(brandId+"  "+seriesId+"  "+styleId);
                    if(iCar!=null){
                        CarSourceData.DataBean bean = new CarSourceData.DataBean();
                        bean.setMakeID(Integer.valueOf(brandId));
                        bean.setMakeName(makeName);
                        bean.setModelID(Integer.valueOf(seriesId));
                        bean.setModelName(curModel.getName());

                        iCar.carInfo(bean);
                    }

                }else{
                    //获取选中的品牌、车系、车型
                    String brandId = makeId;
                    String seriesId = curModel.getId()+"";
                    String styleId = mStyles.get(position - 1).getId();

                    //MyToast.showShort(brandId+"  "+seriesId+"  "+styleId);
                    if(iCar!=null){
                        CarSourceData.DataBean bean = new CarSourceData.DataBean();
                        bean.setMakeID(Integer.valueOf(brandId));
                        bean.setMakeName(makeName);
                        bean.setModelID(Integer.valueOf(seriesId));
                        bean.setModelName(curModel.getName());
                        bean.setStyleID(Integer.valueOf(styleId));
                        bean.setStyleName(mStyles.get(position - 1).getName());

                        iCar.carInfo(bean);
                    }
                }
                break;
        }
    }

    @Override
    public void succeed(Object object) {
        //车型回调
        // 组装汽车类型数据
        assemblyStyleList((CarStyle)object);
    }
    private void assemblyStyleList(CarStyle carStyle) {

        carStyleYearGroupList = carStyle.getYearGroupList();

        if(carStyleYearGroupList!=null){
            showStyleList(carStyle.getYearGroupList());
        }else{
            Toast.makeText(context, carStyle.getMsg(), Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 显示车型列表
     *
     * @param carStyles
     */
    protected void showStyleList(List<CarStyle.YearGroupListBean> carStyles) {
        mStyles = new ArrayList<CarStyle.YearGroupListBean.StyleListBean>();
        mStylessGroupkey = new ArrayList<String>();
        //添加是按照 year、item0、item1....year2、itemN...
        for (CarStyle.YearGroupListBean category : carStyles) {
            CarStyle.YearGroupListBean.StyleListBean style = new CarStyle.YearGroupListBean.StyleListBean();
            String groupName = category.getYear();
            mStylessGroupkey.add(groupName);
            style.setName(groupName);
            // 添加标题到列表
            mStyles.add(style);
            // 添加所有选项到列表
            mStyles.addAll(category.getStyleList());
        }

        mStyleCategoryAdapter = new JzgCarChooseStyleCategoryAdapter(context, mStyles, mStylessGroupkey);
        lvCarStyle.setAdapter(mStyleCategoryAdapter);

    }


    class LetterListViewListener implements MyLetterListView.OnTouchingLetterChangedListener {

        @Override
        public void onTouchingLetterChanged(final String s) {
            if (indexData != null && indexData.get(s) != null) {
                int position = indexData.get(s);
                lvCarBrand.setSelection(position);
            }
        }
    }

    /**
     * 车型查询线程 startStyleListThread: <br/>
     *
     * @author wang
     * @param modelid
     * @since JDK 1.6
     */
    private void startStyleListThread(final String modelid,final String InSale) {

        mCarStylePresenter = new CarStylePresenter(this);
        mCarStylePresenter.request(modelid,InSale);
    }

    /**
     * 显示错误信息
     *
     * @param error 错误信息
     */
    @Override
    public void showError(String error) {
        if(!TextUtils.isEmpty(error))
            MyToast.showLong(error);
    }

    /**
     * 显示加载
     */
    @Override
    public void showDialog() {
        ShowDialogTool.showLoadingDialog(context);
    }

    /**
     * 关闭加载
     */
    @Override
    public void dismissDialog() {
        ShowDialogTool.dismissLoadingDialog();
    }

    /**
     * 车系
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
    public void onDrawerClosed() {
        // 如果sdCarSeries关闭，但是sdCarStyle还开启着，则sdCarStyle也关联关闭
        if (!sdCarSeries.isOpened() && sdCarStyle.isOpened()) {
            sdCarStyle.close();
        }
        // 如果sdCarStyle关闭，则调整handle的初始宽度为0
        RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(0,
                LayoutParams.MATCH_PARENT);
        if (!sdCarStyle.isOpened()) {
            ivCarStyleHandle.setLayoutParams(layout);
        }

        // 如果mCarTypeDrawer关闭，则调整两个Drawer的handle的初始宽度为0
        if (!sdCarSeries.isOpened()) {
            ivCarSeriesHandle.setLayoutParams(layout);
            sdCarStyle.close();
        }

    }

    @Override
    public void onDrawerOpened() {
        if (sdCarSeries.isOpened()) {
            ivCarSeriesHandle.setLayoutParams(new LayoutParams(40,
                    LayoutParams.MATCH_PARENT));
        }
        if (sdCarStyle.isOpened()) {
            ivCarStyleHandle.setLayoutParams(new LayoutParams(40,
                    LayoutParams.MATCH_PARENT));
        }
    }

    protected void openDrawer(SlidingDrawer mDrawer) {
        if (!mDrawer.isOpened()) {
            mDrawer.animateOpen();
        }
    }

    protected void colseDrawer(SlidingDrawer mDrawer) {
        if (mDrawer.isOpened()) {
            mDrawer.close();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ButterKnife.bind(this);
    }

    private ICar iCar;
    public void setIcar(ICar icar){
        iCar = icar;
    }
    public interface ICar{
        public void carInfo(CarSourceData.DataBean dataBean);
    }
}
