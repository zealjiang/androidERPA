package com.jzg.erp.appraiser.view;/**
 * Created by voiceofnet on 2016/8/26.
 */

import com.jzg.erp.base.BaseObject;
import com.jzg.erp.view.IBaseView;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/26 21:49
 * @desc:
 */
public interface ISubmitView extends IBaseView {
    void succeed();
    void unusual(BaseObject baseObject);
}
