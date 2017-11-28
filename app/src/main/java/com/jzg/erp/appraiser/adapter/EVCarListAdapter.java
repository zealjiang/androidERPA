package com.jzg.erp.appraiser.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jzg.erp.R;
import com.jzg.erp.adapter.CommonAdapter;
import com.jzg.erp.appraiser.model.EVCarList;
import com.jzg.erp.utils.DateTimeUtils;
import com.jzg.erp.widget.XRecyclerView;
import com.zhy.base.adapter.ViewHolder;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by zealjiang on 2016/8/16 18:11.
 * Email: zealjiang@126.com
 */
public class EVCarListAdapter extends CommonAdapter<EVCarList.DataBean> {

    public EVCarListAdapter(Context context, int layoutId, List<EVCarList.DataBean> datas, XRecyclerView xRecyclerView) {
        super(context, layoutId, datas, xRecyclerView);
    }

    @Override
    public void convert(ViewHolder holder, EVCarList.DataBean bean) {

        if(bean!=null){
            SimpleDraweeView ctvState = holder.getView(R.id.sdv);
            if(!TextUtils.isEmpty(bean.getPicPath())){
                ctvState.setImageURI(Uri.parse(bean.getPicPath()));
            }else{
                ctvState.setImageURI(Uri.parse("res:/"+R.drawable.no_car2));
            }

            holder.setText(R.id.cars_item_title, bean.getFullName());
            holder.setText(R.id.cars_item_company, bean.getStoreName());

            final String startPrice = bean.getPriceMin()+"";
            final String endPrice = bean.getPriceMax()+"";

            holder.setText(R.id.cars_item_carprice_start, startPrice);
            holder.setText(R.id.cars_item_carprice_end, endPrice);
            holder.setText(R.id.cars_item_carstatus, bean.getLicensePlate());
            holder.setText(R.id.cars_item_time, DateTimeUtils.formatMillsStr(bean.getCreateTime(),DateTimeUtils.YYYYMMDD));
            holder.setText(R.id.cars_item_cars_chekuang_level, bean.getResult());

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
