package com.jzg.erp.view;

import com.jzg.erp.appraiser.model.EvalStatusListModel;

import java.util.List;

/**
 * 获取 历史评估单状态 选项数据
 * @author zealjiang
 * @time 2016/8/22 15:38
 */
public interface IEvalStatusListInf extends IBaseView{
    void succeed(List<EvalStatusListModel.DataBean> data);
}
