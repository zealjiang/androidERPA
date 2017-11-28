package com.jzg.erp.appraiser.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.jzg.erp.R;
import com.jzg.erp.adapter.CommonAdapter;
import com.jzg.erp.appraiser.model.HistoryEvaListModel;
import com.jzg.erp.utils.DateTimeUtils;
import com.jzg.erp.widget.XRecyclerView;
import com.zhy.base.adapter.ViewHolder;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 历史查询
 * @author zealjiang
 * @time 2016/8/18 16:42
 */
public class HistorySearchAdapter extends CommonAdapter<HistoryEvaListModel.DataBean> {

    private DecimalFormat df = new DecimalFormat("##0.00");

    public HistorySearchAdapter(Context context, int layoutId, List<HistoryEvaListModel.DataBean> datas, XRecyclerView xRecyclerView) {
        super(context, layoutId, datas, xRecyclerView);
    }

    @Override
    public void convert(ViewHolder holder, HistoryEvaListModel.DataBean bean) {

        if(bean!=null){

            //车牌号
            holder.setText(R.id.tv_car_license_num, "车牌号 "+bean.getLicensePlate());

            //评估价
            double pgStartPrice = Double.valueOf(bean.getPingguPriceMin());
            double pgEndPrice = Double.valueOf(bean.getPingguPriceMax());
            holder.setText(R.id.tv_evaluate_price_value, pgStartPrice+"-"+pgEndPrice+"万");
            //评估日期
            holder.setText(R.id.tv_evaluate_date, DateTimeUtils.formatMillsStrM( bean.getCreateTime(),DateTimeUtils.YYYYMMDD));
            //状态 (0/ 所有 1/待申请2/待审核3/已批准4/已拒绝5/已删除 6/已取消 )
            holder.setText(R.id.tv_status, idToStringState(""+bean.getRequestStatus()));
            //评估师
            holder.setText(R.id.tv_appraise_name, bean.getTrueName());
            //门店
            holder.setText(R.id.tv_appraise_shop, bean.getStoreName());
            //销售人员
            holder.setText(R.id.tv_salesman_name, bean.getSaleName());
            //收购价
            holder.setText(R.id.tv_purchasing_price_value, bean.getPurchasePrice()+"万");
            //收购日期
            holder.setText(R.id.tv_purchasing_date, DateTimeUtils.formatMillsStrM(bean.getApproveTime(),DateTimeUtils.YYYYMMDD));
            //评估说明
            holder.setText(R.id.tv_evaluate_explain, bean.getRemark());
        }
    }

    private String idToStringState(String str){
        if(str==null)return "";
        switch (str){
            case "1":
                return "待申请";
            case "2":
                return "待审核";
            case "3":
                return "已批准";
            case "4":
                return "已拒绝";
            case "5":
                return "已删除";
            case "6":
                return "已取消";
            default:
                return "";
        }
    }

    private String getPriceDoubleShow(String value){
        if(TextUtils.isEmpty(value)){
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Double.valueOf(value)/10000d);
    }
}
