package com.jzg.erp.appraiser.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;
import com.jzg.erp.adapter.ItemAdapter;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarCondOptionsModel;
import com.jzg.erp.appraiser.model.ParamOption;
import com.jzg.erp.appraiser.presenter.CarCondOptionsPresenter;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.model.IdNameValue;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.ACache;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.view.ICommon;
import com.jzg.erp.widget.RecycleViewDivider;
import com.jzg.erp.widget.XRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 车况描述
 * @author zealjiang
 * @time 2016/8/12 10:28
 */
public class CarDescActivity extends BaseActivity implements ICommon,OnItemClickListener {


    @Bind(R.id.xrv)
    XRecyclerView xrv;
    private ItemAdapter adapter;
    private List<IdNameValue> mList;
    private Context mContext;
    private Class[] classArray;

    //常量
    //内饰
    private final int INNERDECOR = 1;
    //车况等级
    private final int CARLEVEL = 3;

    private CarCondOptionsPresenter carCondOptionsPresenter;
    //选项
    private CarCondOptionsModel carCondOptionsModel;
    private List<CarCondOptionsModel.DataBean.OptionBean> listInner;
    private List<CarCondOptionsModel.DataBean.OptionBean> listCond;
    private List<CarCondOptionsModel.DataBean.OptionBean> listLevel;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vehicle_detect);
        ButterKnife.bind(this);
        mContext = this.getApplicationContext();
        //车况所有选项参数 可能为空，第一次进来时，没请求网络就为空
        carCondOptionsModel = (CarCondOptionsModel) ACache.get(JzgApp.getAppContext()).getAsObject("carCondOptionsModel");
        init();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        xrv.setLayoutManager(linearLayoutManager);
        xrv.setPullRefreshEnabled(false);
        xrv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 6, R.color.common_gray_bg));
        adapter = new ItemAdapter(this, R.layout.item_car_info, mList, xrv);
        adapter.setOnItemClickListener(this);
        xrv.setAdapter(adapter);

        carCondOptionsPresenter = new CarCondOptionsPresenter(this);
        //请求 车况所有选项参数
        carCondOptionsPresenter.getCarCondOptions();
    }

    @Override
    protected void setData() {
        setTitle("车况描述");
        setTextRight("保存");
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    public void init(){
        mList = new ArrayList<>();
        mList.add(new IdNameValue("","外观损伤设置",""));
        mList.add(new IdNameValue("","骨架损伤设置",""));
        mList.add(new IdNameValue("","主要零部件维修记录",""));
        mList.add(new IdNameValue("","内饰",""));
        mList.add(new IdNameValue("","工况",""));
        mList.add(new IdNameValue("","车况等级",""));
        mList.add(new IdNameValue("","车况等级介绍",""));


        classArray = new Class[] {AppearanceDefaceSetActivity.class,SkeletonDefaceSetActivity.class,MCompMaintenanceActivity.class};
        if(carCondOptionsModel==null){
            return;
        }
        //内饰
        listInner = carCondOptionsModel.getData().getKey185();
        //工况
        listCond = carCondOptionsModel.getData().getKey186();
        //等级
        listLevel = carCondOptionsModel.getData().getKey229();

        //设置上次保存的值
        SubmitParamWrapper.CarCondition carCondition = JzgApp.getAppContext().getSubmitParam().getCarCondition();
        if(null!=carCondition&&null!=carCondition.getData()){
            List<String> listString =  carCondition.getData();
            for (int i = 0; i < listString.size(); i++) {
                String[] idValue = listString.get(i).split(",");

                if(idValue[0].equals(String.valueOf(listInner.get(0).getId()))){//获取内饰的值
                    if(idValue.length==1){
                        mList.get(3).setValue("");
                    }else{
                        mList.get(3).setValue(idValue[1]);
                    }
                }else if(idValue[0].equals(String.valueOf(listLevel.get(0).getId()))){//获取等级的值
                    if(idValue.length==1){
                        mList.get(5).setValue("");
                    }else{
                        mList.get(5).setValue(idValue[1]);
                    }
                }
            }
        }

    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        //判断选项数据是否存在
        carCondOptionsModel = (CarCondOptionsModel)ACache.get(JzgApp.getAppContext()).getAsObject("carCondOptionsModel");
        if(carCondOptionsModel==null){
            return;
        }
        List<ParamOption.DataBean.KeyValueItem> list;
        List<CarCondOptionsModel.DataBean.ItemBean> itemList;
        Intent intent = new Intent();
        switch (position){
            case 0:
                intent.setClass(mContext, classArray[position]);
                startActivityForResult(intent, Constant.CHOOSE_CAR_BRAND);
                break;
            case 1:
                intent.setClass(mContext, classArray[position]);
                startActivityForResult(intent, Constant.CHOOSE_CAR_BRAND);
                break;
            case 2:
                intent.setClass(mContext, classArray[position]);
                startActivityForResult(intent, Constant.CHOOSE_CAR_BRAND);
                break;
            case 3:
                //内饰
                itemList =  listInner.get(0).getOption();
                String[] innerDecor = new String[itemList.size()];
                for (int j = 0; j < itemList.size(); j++) {
                    innerDecor[j] = itemList.get(j).getName();
                }

                if(innerDecor!=null && innerDecor.length>0) {
                    startRadioActivity(mContext,INNERDECOR, "内饰", innerDecor, mList.get(3).getValue());
                }else{
                    MyToast.showShort("没有可供选择的内饰，请检查");
                }
                break;
            case 4:
                //工况
                intent.setClass(mContext, CarConditionActivity.class);
                startActivity(intent);
                break;
            case 5:
                //车况等级

                List<CarCondOptionsModel.DataBean.OptionBean> listOptions229 = carCondOptionsModel.getData().getKey229();
                itemList =  listOptions229.get(0).getOption();
                String[] carLevel = new String[itemList.size()];
                for (int j = 0; j < itemList.size(); j++) {
                    carLevel[j] = itemList.get(j).getName();
                }

                if(carLevel!=null && carLevel.length>0) {
                    startRadioActivity(mContext,CARLEVEL, "车况等级", carLevel, mList.get(5).getValue());
                }else{
                    MyToast.showShort("没有可供选择的车况等级，请检查");
                }
                break;
            case 6:
                //车况等级介绍
                intent.setClass(this,CarLevelDescActivity.class);
                startActivity(intent);
                break;
        }


    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

    /**
     * 刷新
     * @author zealjiang
     * @time 2016/8/4 16:18*
     */
    private void refresh(){
        adapter.notifyDataSetChanged();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case INNERDECOR:
                //内饰
                if (data != null) {
                    String str = data.getStringExtra(Constant.activity_radio);
                    mList.get(3).setValue(str);
                    refresh();
                }
                break;
            case CARLEVEL:
                //车况等级
                if (data != null) {
                    String str = data.getStringExtra(Constant.activity_radio);
                    mList.get(5).setValue(str);
                    refresh();
                }
                break;
        }
    }

    /**
     * 保存
     * @author zealjiang
     * @time 2016/8/15 14:55
     */
    private void save(){

        SubmitParamWrapper.CarCondition carCondition = JzgApp.getAppContext().getSubmitParam().getCarCondition();
        if(null==carCondition){
            carCondition = new SubmitParamWrapper.CarCondition();
            JzgApp.getAppContext().getSubmitParam().setCarCondition(carCondition);
        }
        List<String> list = carCondition.getData();
        if(null==list){
            list = new ArrayList<String>();
            carCondition.setData(list);
        }

        if(list.size()==0) {
            //内饰
            list.add(listInner.get(0).getId() + "," + mList.get(3).getValue());
            //车况等级
            list.add(listLevel.get(0).getId() + "," + mList.get(5).getValue());
        }else{
            //将内饰、车况等级值加入到list中
            String innerId = listInner.get(0).getId()+"";
            String levelId = listLevel.get(0).getId()+"";

            for (int i = 0; i < list.size(); i++) {
                String[] idValue = list.get(i).split(",");
                String listId = idValue[0];

                if(innerId.equals(listId)){
                    list.remove(i);//注意：list size大小发生了变化，所以i要重新调整
                    i--;
                }else if(levelId.equals(listId)){
                    list.remove(i);
                    i--;
                }
            }
            //内饰
            list.add(listInner.get(0).getId() + "," + mList.get(3).getValue());
            //车况等级
            list.add(listLevel.get(0).getId() + "," + mList.get(5).getValue());
        }

        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void succeed(Object object) {
        CarCondOptionsModel carCondOptionsModel = (CarCondOptionsModel)object;
        ACache.get(JzgApp.getAppContext()).put("carCondOptionsModel",carCondOptionsModel);

        //内饰
        listInner = carCondOptionsModel.getData().getKey185();
        //工况
        listCond = carCondOptionsModel.getData().getKey186();
        //等级
        listLevel = carCondOptionsModel.getData().getKey229();
    }
}
