package com.jzg.erp.view;/**
 * Created by voiceofnet on 2016/6/22.
 */

import com.jzg.erp.appraiser.model.CarInfoModel;

/**
 *
 * @author zealjiang
 * @time 2016/8/4 11:11
 */
public interface IVinSearch extends IBaseView {
    void succeed(CarInfoModel carInfoModel);
    void unusual(CarInfoModel carInfoModel);
}
