package com.jzg.erp.appraiser.view;/**
 * Created by voiceofnet on 2016/8/23.
 */

import com.jzg.erp.appraiser.model.ProvinceCity;
import com.jzg.erp.view.IBaseView;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/23 10:39
 * @desc:
 */
public interface IProvinceCity extends IBaseView {
    void showData(List<ProvinceCity.DataBean> data);
}
