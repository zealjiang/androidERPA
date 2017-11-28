package com.jzg.erp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.model.NameValue;
import com.jzg.erp.utils.MTextUtils;
import com.jzg.erp.widget.XRecyclerView;
import com.zhy.base.adapter.ViewHolder;

import java.util.List;

/**
 *
 * @author zealjiang
 * @time 2016/8/12 10:51
 */
public class NameVaItemAdapter extends CommonAdapter<NameValue> {

    //为true，显示右边的箭头，false不显示
    private boolean isShowJianTout = true;

    public NameVaItemAdapter(Context context, int layoutId, List<NameValue> datas, XRecyclerView xRecyclerView) {
        super(context, layoutId, datas, xRecyclerView);
    }

    @Override
    public void convert(ViewHolder holder, NameValue nameValue) {

        //set number
        int pos = holder.getPosition();
        TextView tvNumber = holder.getView(R.id.number);
        if(null!=tvNumber){
            tvNumber.setText(pos+"");
        }

        //set name
        TextView tvName = holder.getView(R.id.tv_name);
        String name = nameValue.getName();
        tvName.setText(name);
        if(!TextUtils.isEmpty(name)){
            if(name.endsWith("*")){
                setTextViewNeccessary(tvName,name.length()-1);
            }
        }

        //set jiaotou
        ImageView ivJianTou = holder.getView(R.id.choose_car_brand_text_jiantou);
        if(!isShowJianTout){
            ivJianTou.setVisibility(View.GONE);
        }else{
            ivJianTou.setVisibility(View.VISIBLE);
        }

        //set value
        holder.setText(R.id.tv_value, MTextUtils.nullIfEmpty(nameValue.getValue()));
    }

    private void setTextViewNeccessary(TextView tv, int index) {
        CharSequence text = tv.getText();
        SpannableString sp = new SpannableString(text);
        sp.setSpan(new ForegroundColorSpan(Color.RED), index, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(sp);
    }

    /**
     * 为true，显示右边的箭头，false不显示
     * @author zealjiang
     * @time 2016/8/18 11:52
     */
    public void setShowJianTou(boolean isShow){
        isShowJianTout = isShow;
    }
}
