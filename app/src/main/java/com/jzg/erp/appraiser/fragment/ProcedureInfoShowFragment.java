package com.jzg.erp.appraiser.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jzg.erp.R;
import com.jzg.erp.adapter.NameVaItemAdapter;
import com.jzg.erp.appraiser.model.ProcedureInfoModel;
import com.jzg.erp.appraiser.presenter.ProcedureInfoPresenter;
import com.jzg.erp.base.NewBaseFragment;
import com.jzg.erp.model.NameValue;
import com.jzg.erp.utils.DateTimeUtils;
import com.jzg.erp.utils.MyToast;
import com.jzg.erp.view.ICommon;
import com.jzg.erp.widget.RecycleViewDivider;
import com.jzg.erp.widget.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 配置信息
 * @author zealjiang
 * @time 2016/8/3 20:18
 */
public class ProcedureInfoShowFragment extends NewBaseFragment implements ICommon{
    @Bind(R.id.xrv)
    XRecyclerView xrv;
    private NameVaItemAdapter adapter;
    private List<NameValue> mList;
    private View rootView;
    private Context mContext;
    private ProcedureInfoShowFragment mHandlingInfoFragment;
    /**通用*/
    private String[] ConfigCommonArray;
    /**新车质保*/
    private String[] QANewCar;
    /**购置税*/
    private String[] PurchaseTax;

    private ProcedureInfoPresenter presenter;
    private String carId = "";//车辆ID

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add(new NameValue("年检到期日",""));
        mList.add(new NameValue("交强险到期日",""));
        mList.add(new NameValue("过户次数",""));
        mList.add(new NameValue("车船税到期日",""));
        mList.add(new NameValue("4S店定期保养",""));
        mList.add(new NameValue("路桥费到期日",""));
        mList.add(new NameValue("商业险到期日",""));
        mList.add(new NameValue("钥匙",""));
        mList.add(new NameValue("新车质保",""));
        mList.add(new NameValue("行驶证",""));

        mList.add(new NameValue("商险金额",""));
        mList.add(new NameValue("过户票",""));
        mList.add(new NameValue("车辆说明书",""));
        mList.add(new NameValue("登记证",""));
        mList.add(new NameValue("购置税",""));
        mList.add(new NameValue("新车保养手册",""));
        mList.add(new NameValue("新车发票",""));

        ConfigCommonArray = getResources().getStringArray(
                R.array.config_common);

        QANewCar = getResources().getStringArray(
                R.array.QA_NEW_CAR);
        PurchaseTax = getResources().getStringArray(
                R.array.purchase_tax);

        presenter = new ProcedureInfoPresenter(this);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_baseinfo, container, false);
            ButterKnife.bind(this, rootView);
            mContext = this.getContext();
            mHandlingInfoFragment = this;
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            xrv.setLayoutManager(linearLayoutManager);
            xrv.setPullRefreshEnabled(false);
            xrv.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, 6, R.color.common_gray_bg));
            adapter = new NameVaItemAdapter(context, R.layout.item_car_info, mList, xrv);
            adapter.setShowJianTou(false);
            xrv.setAdapter(adapter);
        }
        refresh();

        //获取数据
        Bundle bundle = getArguments();
        if(bundle!=null){
            carId = bundle.getString("carId");
        }else{
            MyToast.showShort("无法获取评估单号");
            return rootView;
        }

        //请求手续信息数据
        request(carId);

        return rootView;
    }

    @Override
    protected void setView() {

    }


    @Override
    protected void setTextLeft(int res) {
        super.setTextLeft(res);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    /**
     * 刷新
     * @author zealjiang
     * @time 2016/8/4 16:18
     */
    private void refresh(){
        adapter.notifyDataSetChanged();

        int i = getNotEmptyValue();
        //((ABaseInfoFragment)this.getParentFragment()).setTabText(2,"手续信息"+" "+i+"/"+mList.size());
    }

    private int getNotEmptyValue(){
        int count = 0;
        for (int i = 0; i < mList.size(); i++) {
            if(!TextUtils.isEmpty(mList.get(i).getValue())){
                count++;
            }
        }
        return count;
    }

    /**
     * 修改信息
     * @author zealjiang
     * @time 2016/8/11 18:08
     */
    public void setValues(ProcedureInfoModel.DataBean dataBean){

        //年检到期日
        mList.get(0).setValue(DateTimeUtils.formatMillsStr(dataBean.getCheckEndDate(),DateTimeUtils.YYYYMMDD));
        //交强险到期日
        mList.get(1).setValue(DateTimeUtils.formatMillsStr(dataBean.getSecureYear(),DateTimeUtils.YYYYMMDD));
        //过户次数
        mList.get(2).setValue(dataBean.getTransferNum()+" 次");
        //车船税到期日
        mList.get(3).setValue(DateTimeUtils.formatMillsStr(dataBean.getVehicleAndVesselTaxExpired(),DateTimeUtils.YYYYMMDD));
        //4S店定期保养
        mList.get(4).setValue(dataBean.getD4SMaintenance());
        //路桥费到期日
        mList.get(5).setValue(DateTimeUtils.formatMillsStr(dataBean.getLuQiaoFeeExpired(),DateTimeUtils.YYYYMMDD));
        //商业险到期日
        mList.get(6).setValue(DateTimeUtils.formatMillsStr(dataBean.getSecureYearBusiness(),DateTimeUtils.YYYYMMDD));
        //钥匙
        mList.get(7).setValue(dataBean.getCarKey()+" 把");
        //新车质保
        mList.get(8).setValue(dataBean.getNewCarWarranty());
        //行驶证
        mList.get(9).setValue(dataBean.getDrivingLicense());
        //商险金额
        mList.get(10).setValue(dataBean.getCommercialInsuranceAmount()+" 元");
        //过户票
        mList.get(11).setValue(dataBean.getTransferTicket());
        //车辆说明书
        mList.get(12).setValue(dataBean.getVehicleSpecification());
        //登记证
        mList.get(13).setValue(dataBean.getRegistrationLicense());
        //购置税
        mList.get(14).setValue(dataBean.getPurchaseTax());
        //新车保养手册
        mList.get(15).setValue(dataBean.getCarMaintenanceManual());
        //新车发票
        mList.get(16).setValue(dataBean.getNewInvoice());

        refresh();

    }

    private void request(String id){
        presenter.getProcedureInfoList(id);
    }

    @Override
    public void succeed(Object object) {
        ProcedureInfoModel.DataBean dataBean = (ProcedureInfoModel.DataBean)object;
        if(null!=dataBean){
            setValues(dataBean);
        }
    }
}
