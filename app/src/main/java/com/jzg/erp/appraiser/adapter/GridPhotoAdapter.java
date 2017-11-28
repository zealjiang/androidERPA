package com.jzg.erp.appraiser.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jzg.erp.R;
import com.jzg.erp.model.SubmitParamWrapper;
import com.jzg.erp.utils.LogUtil;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/18 10:33
 * @desc:
 */
public class GridPhotoAdapter extends CommonAdapter<SubmitParamWrapper.PhotoItem> {
    private static final String TAG = "GridPhotoAdapter";
    private boolean showDelete = false;

    public boolean isShowDelete() {
        return showDelete;
    }

    public void setShowDelete(boolean showDelete) {
        this.showDelete = showDelete;
    }

    public GridPhotoAdapter(Context context, int layoutId, List<SubmitParamWrapper.PhotoItem> datas) {
        super(context, layoutId, datas);
    }
    @Override
    public void convert(final ViewHolder holder, final SubmitParamWrapper.PhotoItem photoItem) {
        SimpleDraweeView ivPhoto = holder.getView(R.id.ivPhoto);
        String url = photoItem.getViewUrl();
        url = TextUtils.isEmpty(url)?"":url;
        LogUtil.e(TAG,"url:"+url);
        ivPhoto.setImageURI(Uri.parse(url));
        holder.setText(R.id.tvPhotoDesc,photoItem.getImgText());
        if(isShowDelete()){
            if(photoItem.getDictID()==353){
                holder.setVisible(R.id.ivDelete,true);
            }else{
                holder.setVisible(R.id.ivDelete,false);
            }
            holder.setTag(R.id.ivDelete,photoItem);
            holder.setOnClickListener(R.id.ivDelete, listener);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SubmitParamWrapper.PhotoItem photoItem = (SubmitParamWrapper.PhotoItem) v.getTag();
            if(photoItem!=null){
                mDatas.remove(photoItem);
                if(mDatas.size()==29){
                    String viewUrl = mDatas.get(28).getViewUrl();
                    if(viewUrl!=null && !viewUrl.startsWith("res")){
                        SubmitParamWrapper.PhotoItem item = new SubmitParamWrapper.PhotoItem(0, "", "res:///" + R.mipmap.img_06, "添加更多");
                        mDatas.add(item);
                    }
                }
                notifyDataSetChanged();
            }
        }
    };
}