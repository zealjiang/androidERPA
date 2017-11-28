package com.jzg.erp.appraiser.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.erp.R;
import com.jzg.erp.appraiser.model.CarStyle;

import java.util.List;

/**
 * ClassName:CarTypeAdapter <br/>
 * Date: 2014-5-26 上午11:28:41 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseStyleCategoryAdapter extends BaseAdapter
{
	private LayoutInflater inflater;
	private List<CarStyle.YearGroupListBean.StyleListBean> list;
	private List<String> groupkey;

	public JzgCarChooseStyleCategoryAdapter(Context context, List<CarStyle.YearGroupListBean.StyleListBean> list,
											List<String> groupkey)
	{
		super();
		inflater = LayoutInflater.from(context);
		this.list = list;
		this.groupkey = groupkey;
	}

	@Override
	public int getCount()
	{
		return list.size();
	}

	@Override
	public Object getItem(int position)
	{
		if(!TextUtils.isEmpty(list.get(position).getNowMsrp())){
			
			return list.get(position).getName();
		}
		return list.get(position).getName();
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public boolean isEnabled(int position)
	{
		if (groupkey.contains(getItem(position)))
		{
			return false;
		}
		return super.isEnabled(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView;
		if (groupkey.contains(getItem(position)))
		{
			view = inflater.inflate(R.layout.jzgcarchoose_addexam_list_item_tag, null);
			ImageView image = (ImageView) view.findViewById(R.id.addexam_list_icon);
			image.setVisibility(View.GONE);
			TextView text = (TextView) view
					.findViewById(R.id.addexam_list_item_text);
			text.setTextColor(Color.parseColor("#3399cc"));
			text.setText((CharSequence) getItem(position));
		} else
		{
			view = inflater.inflate(R.layout.jzgcarchoose_addexam_list_item, null);
			view.setBackgroundColor(Color.parseColor("#ffffff"));

			ImageView image = (ImageView) view.findViewById(R.id.addexam_list_icon);
			image.setVisibility(View.GONE);
			TextView text = (TextView) view
					.findViewById(R.id.addexam_list_item_text);
			TextView priceText = (TextView) view
					.findViewById(R.id.addexam_list_item_price);
			TextView priceHint = (TextView) view
					.findViewById(R.id.addexam_list_item_priceHint);
			if (!TextUtils.isEmpty(list.get(position).getNowMsrp()))
			{
				priceText.setVisibility(View.VISIBLE);
				priceHint.setVisibility(View.VISIBLE);
				priceText.setText(list.get(position).getNowMsrp()+"万元");
			}
			text.setText((CharSequence) getItem(position));
			text.setTextColor(Color.BLACK);
		}

		return view;
	}
}
