package com.jzg.erp.appraiser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.appraiser.model.PopListDataModel;

import java.util.List;

/**
 * Created by zealjiang on 2016/8/16 18:11.
 * Email: zealjiang@126.com
 */
public class HistorySearchPopAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<PopListDataModel> list;

    public HistorySearchPopAdapter(Context context, List<PopListDataModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        list = data;
    }

    public void setData(List<PopListDataModel> data){
        this.list = data;
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
        ViewHolder viewHolder = null;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_pop_history, null);
            viewHolder.tvItem = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvItem.setText(list.get(position).getText());
        viewHolder.tvItem.setTextColor(context.getResources().getColor(list.get(position).getTextColor()));

        return convertView;
    }

    class ViewHolder {
        TextView tvItem;
    }
}
