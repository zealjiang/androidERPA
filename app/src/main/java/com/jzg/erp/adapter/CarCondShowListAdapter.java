package com.jzg.erp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.appraiser.model.CarCondInfoShowModel;

import java.util.List;


/**
 * 车况显示
 * @author zealjiang
 * @time 2016/8/25 9:13
 */
public class CarCondShowListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<CarCondInfoShowModel.DataBean> list;

    public CarCondShowListAdapter(Context context, List<CarCondInfoShowModel.DataBean> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.car_cond_show_list_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.item = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        //车况信息
        CarCondInfoShowModel.DataBean dataBean = list.get(position);
        //类型名称
        holder.title.setText(dataBean.getParentItemName());
        //类型子项
        StringBuilder stringBuilder = new StringBuilder();
        String sitem = dataBean.getItemName();
        String[] itemArray =  sitem.split(",");
        for (int j = 0; j < itemArray.length; j++) {
            stringBuilder.append(itemArray[j]+"\n");
        }
        holder.item.setText(stringBuilder.toString());

        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView item;
    }


}
