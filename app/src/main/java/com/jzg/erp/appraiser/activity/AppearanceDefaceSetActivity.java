package com.jzg.erp.appraiser.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jzg.erp.R;
import com.jzg.erp.adapter.ItemAdapter;
import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarCondOptionsModel;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.global.Constant;
import com.jzg.erp.model.IdNameValue;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.ACache;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.utils.ScreenUtils;
import com.jzg.erp.widget.RecycleViewDivider;
import com.jzg.erp.widget.XRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 外观损伤设置
 * @author zealjiang
 * @time 2016/8/12 10:27
 */
public class AppearanceDefaceSetActivity extends BaseActivity implements OnItemClickListener {

    @Bind(R.id.iv_instruction)
    SimpleDraweeView ivInstruction;
    @Bind(R.id.xrv)
    XRecyclerView xrv;
    private ItemAdapter adapter;
    private Context mContext;


    /**
     * 存放每个ITEM的所有选项 如：左前门下的4个选项、左后门下的4个选项
     */
    private SparseArray<String[]> valueArray;
    /**
     * 存放每个选项的名字及对应的值 如：左前门 曾喷漆
     */
    private List<IdNameValue> mList;
    /**
     * 存放第个ITEM对应的请求码
     */
    private int[] requestArray;


    //选项
    private CarCondOptionsModel carCondOptionsModel;
    private List<CarCondOptionsModel.DataBean.OptionBean> listOptions;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_deface_setting);
        ButterKnife.bind(this);
        mContext = this.getApplicationContext();

        carCondOptionsModel = (CarCondOptionsModel) ACache.get(JzgApp.getAppContext()).getAsObject("carCondOptionsModel");
        //640,480
        int screenWidth = ScreenUtils.getScreenWidth(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth,screenWidth*480/640);
        params.topMargin = ScreenUtils.dip2px(this,10);
        ivInstruction.setLayoutParams(params);

        String imgUrl = carCondOptionsModel.getYgssPic();
        if (null != imgUrl && imgUrl.length() > 0) {
            ivInstruction.setImageURI(Uri.parse(imgUrl));
        }

        init();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        xrv.setLayoutManager(linearLayoutManager);
        xrv.setPullRefreshEnabled(false);
        xrv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 6, R.color.common_gray_bg));
        adapter = new ItemAdapter(this, R.layout.item_deface_setting, mList, xrv);
        adapter.setOnItemClickListener(this);
        xrv.setAdapter(adapter);
    }

    @Override
    protected void setData() {
        setTitle("外观损伤设置");
        setTextRight("保存");
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    public void init(){

        valueArray = new SparseArray<>();
        mList = new ArrayList<>();
        listOptions = carCondOptionsModel.getData().getKey182();
        requestArray = new int[listOptions.size()];

        for (int i = 0; i < listOptions.size(); i++) {
            requestArray[i] = i;
            mList.add(new IdNameValue(listOptions.get(i).getId()+"",listOptions.get(i).getName(),""));
            List<CarCondOptionsModel.DataBean.ItemBean> itemList =  listOptions.get(i).getOption();
            String[] array = new String[itemList.size()];
            for (int j = 0; j < itemList.size(); j++) {
                array[j] = itemList.get(j).getName();
            }
            valueArray.put(i,array);
        }


        //设置上次保存的值
        SubmitParamWrapper.CarCondition carCondition = JzgApp.getAppContext().getSubmitParam().getCarCondition();
        if(null!=carCondition&&null!=carCondition.getData()){
            List<String> listString =  carCondition.getData();
            for (int i = 0; i < listString.size(); i++) {
                String[] idValue = listString.get(i).split(",");
                for (int j = 0; j < mList.size(); j++) {
                    if(mList.get(j).getId().equals(idValue[0])){
                        if(idValue.length==1){
                            mList.get(j).setValue("");
                        }else{
                            mList.get(j).setValue(idValue[1]);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        setSelection(valueArray.get(position),mList.get(position).getName(),requestArray[position],position,"没有可供选择的"+mList.get(position).getName()+"，请检查");

    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }


    /**
     * 点击子项进入选择页面
     * @author zealjiang
     * @time 2016/8/12 14:54
     * @param array
     * @param title
     * @param request
     * @param itemPos
     * @param emptyToast
     */
    private void setSelection(String[] array,String title,int request,int itemPos,String emptyToast){
        if(array!=null && array.length>0) {
            startRadioActivity(mContext,request, title, array, mList.get(itemPos).getValue());
        }else{
            MyToast.showShort(emptyToast);
        }
    }


    /**
     * 刷新
     * @author zealjiang
     * @time 2016/8/4 16:18
     */
    private void refresh(){
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setItemValue(resultCode,data);
    }

    /**
     * 设置子项选项选择后的值
     * @author zealjiang
     * @time 2016/8/12 15:42
     */
    private void setItemValue(int resultCode,Intent data){

        int pos = -1;
        for (int i = 0; i < requestArray.length; i++) {
            if(resultCode==requestArray[i]){
                pos = i;
                break;
            }
        }

        if (data != null) {
            String leftDoor = data.getStringExtra(Constant.activity_radio);
            mList.get(pos).setValue(leftDoor);
            refresh();
        }
    }

    /**
     * 保存
     * @author zealjiang
     * @time 2016/8/15 17:38
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
            for (int i = 0; i < mList.size(); i++) {
                list.add(mList.get(i).getId() + "," + mList.get(i).getValue());
            }
        }else{
            //将mList中的值加入到list中
            for (int j = 0; j < mList.size(); j++) {
                String mListId = mList.get(j).getId();
                for (int i = 0; i < list.size(); i++) {
                    String[] idValue = list.get(i).split(",");
                    String listId = idValue[0];

                    if(mListId.equals(listId)){
                        list.remove(i);
                    }
                }
                list.add(mList.get(j).getId()+","+mList.get(j).getValue());
            }
        }
        this.finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
