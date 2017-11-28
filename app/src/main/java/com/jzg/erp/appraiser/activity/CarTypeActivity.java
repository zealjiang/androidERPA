package com.jzg.erp.appraiser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jzg.erp.R;
import com.jzg.erp.appraiser.adapter.JzgCarChooseStyleCategoryAdapter;
import com.jzg.erp.appraiser.model.CarStyle;
import com.jzg.erp.appraiser.presenter.CarStylePresenter;
import com.jzg.erp.base.BaseActivity;
import com.jzg.erp.utils.ACache;
import com.jzg.erp.view.ICommon;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zealjiang on 2016/8/4 18:30.
 * Email: zealjiang@126.com
 */
public class CarTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener,ICommon {

    @Bind(R.id.lv_type)
    ListView mCarYearstyleContent;

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

    private String modelId = "";

    public static final int QUERYCAR_MSG = 1;

    private CarStylePresenter mCarStylePresenter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_car_type);
        ButterKnife.bind(this);

    }

    @Override
    protected void setData() {

        setTitle("选择车型");
        modelId = getIntent().getStringExtra("seriesId");


        // 开启车型查询线程
        startStyleListThread(modelId,"0");

        mCarYearstyleContent.setOnItemClickListener(this);
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

        mStyleCategoryAdapter = new JzgCarChooseStyleCategoryAdapter(getApplicationContext(), mStyles, mStylessGroupkey);
        mCarYearstyleContent.setAdapter(mStyleCategoryAdapter);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int viewid = parent.getId();
        styleItemClick(position);
    }

    private void styleItemClick(int position) {

        CarStyle.YearGroupListBean.StyleListBean style = mStyles.get(position);


        ACache.get(this).put("carYear",style.getYear());
        Intent in = new Intent();
        in.putExtra("styleId", style.getId());
        in.putExtra("styleName", style.getName());
        in.putExtra("styleFullName", style.getFullName());
        setResult(QUERYCAR_MSG, in);
        finish();

    }

    @Override
    public void succeed(Object object) {
        // 组装汽车类型数据
        assemblyStyleList((CarStyle)object);
    }

    private void assemblyStyleList(CarStyle carStyle) {

        carStyleYearGroupList = carStyle.getYearGroupList();

        if(carStyleYearGroupList!=null){
            showStyleList(carStyle.getYearGroupList());
        }else{
            Toast.makeText(this, carStyle.getMsg(), Toast.LENGTH_SHORT).show();
        }

    }
}
