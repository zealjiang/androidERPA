package com.jzg.erp.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.jzg.erp.utils.LogUtil;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/18 16:16
 * @desc:
 */
public class DiscrollViewPager extends ViewPager {
    private static final String TAG = "DiscrollViewPager";
    private boolean scrollable = true;//是否禁止滑动

    public DiscrollViewPager(Context context) {
        super(context);
    }

    public DiscrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //重写两个触摸事件，并放回是否确定滑动
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.scrollable && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean result = this.scrollable && super.onInterceptTouchEvent(event);
        LogUtil.e(TAG,"result="+result);
        return result;
    }


    public void setScrollable(boolean flag) {
        this.scrollable = flag;
    }
}
