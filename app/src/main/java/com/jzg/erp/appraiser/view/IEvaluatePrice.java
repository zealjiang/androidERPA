package com.jzg.erp.appraiser.view;/**
 * Created by voiceofnet on 2016/8/23.
 */

import com.jzg.erp.appraiser.model.CarEvaluateInfo;
import com.jzg.erp.view.IBaseView;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/23 20:42
 * @desc:
 */
public interface IEvaluatePrice extends IBaseView {
    void showData(CarEvaluateInfo info);
}
