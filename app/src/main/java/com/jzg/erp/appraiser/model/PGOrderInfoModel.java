package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * Created by zealjiang on 2016/8/23 10:39.
 * Email: zealjiang@126.com
 */
public class PGOrderInfoModel extends com.jzg.erp.base.BaseObject{


    /**
     * PingguUserId : 146
     * Result : A+
     * PingguUserName : 王玥-销售顾问
     * RequestStatus : 1
     * RequestStatusStr : 待申请
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private int PingguUserId;
        private String Result;
        private String PingguUserName;
        private int RequestStatus;
        private String RequestStatusStr;
        private int ShowAdd;

        public int getShowAdd() {
            return ShowAdd;
        }

        public void setShowAdd(int showAdd) {
            ShowAdd = showAdd;
        }

        public int getPingguUserId() {
            return PingguUserId;
        }

        public void setPingguUserId(int PingguUserId) {
            this.PingguUserId = PingguUserId;
        }

        public String getResult() {
            return Result;
        }

        public void setResult(String result) {
            Result = result;
        }

        public String getPingguUserName() {
            return PingguUserName;
        }

        public void setPingguUserName(String PingguUserName) {
            this.PingguUserName = PingguUserName;
        }

        public int getRequestStatus() {
            return RequestStatus;
        }

        public void setRequestStatus(int RequestStatus) {
            this.RequestStatus = RequestStatus;
        }

        public String getRequestStatusStr() {
            return RequestStatusStr;
        }

        public void setRequestStatusStr(String RequestStatusStr) {
            this.RequestStatusStr = RequestStatusStr;
        }
    }
}
