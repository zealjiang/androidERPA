package com.jzg.erp.appraiser.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.adapter.CarCondShowListAdapter;
import com.jzg.erp.appraiser.model.CarCondInfoShowModel;
import com.jzg.erp.appraiser.presenter.CarCondInfoShowPresenter;
import com.jzg.erp.appraiser.widget.ListViewForScrollView;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.utils.MTextUtils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.view.ICommon;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zealjiang on 2016/8/18 13:58.
 * Email: zealjiang@126.com
 */
public class CarConditionShowFragment extends NewBaseFragment implements ICommon{

    @Bind(R.id.tv_car_level)
    TextView tvCarLevel;
    @Bind(R.id.tv_car_level_reason)
    TextView tvCarLevelReason;
    @Bind(R.id.tv_appearance)
    TextView tvAppearance;
    @Bind(R.id.ll_appearance)
    LinearLayout llAppearance;
    @Bind(R.id.tv_skeleton)
    TextView tvSkeleton;
    @Bind(R.id.ll_skeleton)
    LinearLayout llSkeleton;
    @Bind(R.id.tv_inner)
    TextView tvInner;
    @Bind(R.id.ll_inner)
    LinearLayout llInner;
    @Bind(R.id.tv_maintail_record)
    TextView tvMaintailRecord;
    @Bind(R.id.ll_maintain_record)
    LinearLayout llMaintainRecord;
    @Bind(R.id.tv_condition)
    TextView tvCondition;
    @Bind(R.id.ll_condition)
    LinearLayout llCondition;
    @Bind(R.id.tv_list_info)
    TextView tvListInfo;
    @Bind(R.id.lv_info)
    ListViewForScrollView lvInfo;

    private Context context;

    private CarCondInfoShowPresenter presenter;
    private List<CarCondInfoShowModel.DataBean> dataBeanList;
    private CarCondShowListAdapter adapter;
    private String pingguId = "";//评估单ID

    @Override
    protected void setView() {
        //3
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car_condition_show, container, false);
        ButterKnife.bind(this, rootView);
        context = this.getContext();

        dataBeanList = new ArrayList<CarCondInfoShowModel.DataBean>();
        adapter = new CarCondShowListAdapter(context, dataBeanList);
        lvInfo.setAdapter(adapter);
        lvInfo.setFocusable(false);

        //获取数据
        Bundle bundle = getArguments();
        if(bundle!=null){
            pingguId = bundle.getString("pingguId");
        }else{
            MyToast.showShort("无法获取车辆ID");
            return rootView;
        }

        request();
        return rootView;
    }

    @Override
    protected void initData() {
        //1
        presenter = new CarCondInfoShowPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setData(){
        //评估等级
        tvCarLevel.setText("A++");
        //评估说明
        tvCarLevelReason.setText("车质量特别好");
        //外观损伤
        tvAppearance.setText("");
        //骨架损伤
        tvSkeleton.setText("");
        //内饰损伤
        tvInner.setText("");
        //主要零部件维修记录
        tvMaintailRecord.setText("");
        //工况
        tvCondition.setText("");
    }

    private void request(){
        presenter.getCarConditionInfo(pingguId);
    }

    @Override
    public void succeed(Object object) {
        List<CarCondInfoShowModel.DataBean> listData = (List<CarCondInfoShowModel.DataBean>)object;
        if(listData==null||listData.size()<=0){
            return;
        }
        //车况信息
        dataBeanList.clear();
        for (int i = 0; i < listData.size(); i++) {
            CarCondInfoShowModel.DataBean dataBean = listData.get(i);
            if("车况等级".equals(dataBean.getParentItemName())){
                //车辆等级
                tvCarLevel.setText(MTextUtils.nullIfEmpty(dataBean.getResult()));
                //车辆等级说明
                tvCarLevelReason.setText(MTextUtils.nullIfEmpty(dataBean.getReMark()));
            }else{
                dataBeanList.add(dataBean);
            }
        }
        adapter.notifyDataSetChanged();
    }


}
