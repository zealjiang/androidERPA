package com.jzg.erp.utils;

import com.jzg.erp.appraiser.model.CarInfoModel;
import com.jzg.erp.model.SubmitParamWrapper;

/**
 * Created by zealjiang on 2016/8/23 18:10.
 * Email: zealjiang@126.com
 */
public class Model2Model {
    
    /**
     * CarInfoModel
     * @author zealjiang
     * @time 2016/8/23 18:41
     */
    public SubmitParamWrapper CarInfoModeltoSubmitParamWrapper(CarInfoModel carInfoModel, SubmitParamWrapper submitParamWrapper){
        if(null==carInfoModel||null==submitParamWrapper){
            return submitParamWrapper;
        }
        return null;
    }
}
