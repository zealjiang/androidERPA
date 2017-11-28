package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * Created by zealjiang on 2016/8/23 10:46.
 * Email: zealjiang@126.com
 */
public class PGOrderDetailModel extends com.jzg.erp.base.BaseObject{


    /**
     * CreateTime : 0001-01-01 00:00:00
     * CreateUserName : null
     * ApproveTime : 2016-08-15 10:58:49
     * ApproveUserName : 采购经理李
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String CreateTime;
        private Object CreateUserName;
        private String ApproveTime;
        private String ApproveUserName;

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public Object getCreateUserName() {
            return CreateUserName;
        }

        public void setCreateUserName(Object CreateUserName) {
            this.CreateUserName = CreateUserName;
        }

        public String getApproveTime() {
            return ApproveTime;
        }

        public void setApproveTime(String ApproveTime) {
            this.ApproveTime = ApproveTime;
        }

        public String getApproveUserName() {
            return ApproveUserName;
        }

        public void setApproveUserName(String ApproveUserName) {
            this.ApproveUserName = ApproveUserName;
        }
    }
}
