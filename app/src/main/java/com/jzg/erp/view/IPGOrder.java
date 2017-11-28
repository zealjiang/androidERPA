package com.jzg.erp.view;/**
 * Created by voiceofnet on 2016/6/22.
 */

import com.jzg.erp.appraiser.model.PGOrderModel;

import java.util.List;

/**
 *
 * @author zealjiang
 * @time 2016/8/4 11:11
 */
public interface IPGOrder extends IBaseView {
    void succeedPGOrder(List<PGOrderModel.DataBean> dataBeanList);
}
