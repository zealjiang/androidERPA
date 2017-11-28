package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * Created by zealjiang on 2016/8/22 15:24.
 * Email: zealjiang@126.com
 */
public class EvalStatusListModel extends com.jzg.erp.base.BaseObject{


    /**
     * RequestStatus : 1
     * StatusName : 已评估待申请
     * RecordCount : 1
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int RequestStatus;
        private String StatusName;
        private int RecordCount;
        private String textId;
        private int textColor;

        public int getRequestStatus() {
            return RequestStatus;
        }

        public void setRequestStatus(int RequestStatus) {
            this.RequestStatus = RequestStatus;
        }

        public String getStatusName() {
            return StatusName;
        }

        public void setStatusName(String StatusName) {
            this.StatusName = StatusName;
        }

        public int getRecordCount() {
            return RecordCount;
        }

        public void setRecordCount(int RecordCount) {
            this.RecordCount = RecordCount;
        }

        public String getTextId() {
            return textId;
        }

        public void setTextId(String textId) {
            this.textId = textId;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }
    }
}
