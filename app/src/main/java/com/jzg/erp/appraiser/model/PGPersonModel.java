package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * Created by zealjiang on 2016/8/23 10:44.
 * Email: zealjiang@126.com
 */
public class PGPersonModel extends com.jzg.erp.base.BaseObject{

    /**
     * Tel : 13211112222
     * StoreName : HJK测试门店
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String Tel;
        private String StoreName;

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String StoreName) {
            this.StoreName = StoreName;
        }
    }
}
