package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * 配置信息
 * Created by zealjiang on 2016/8/22 11:46.
 * Email: zealjiang@126.com
 */
public class ConfigInfoModel extends com.jzg.erp.base.BaseObject{


    /**
     * CI : 防抱死制动系统
     * Status : 1
     * AddAble : 0
     * StatusStr : 有
     * AddAbleStr : null
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String CI;
        private int Status;
        private int AddAble;
        private String StatusStr;
        private String AddAbleStr;

        public String getCI() {
            return CI;
        }

        public void setCI(String CI) {
            this.CI = CI;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public int getAddAble() {
            return AddAble;
        }

        public void setAddAble(int AddAble) {
            this.AddAble = AddAble;
        }

        public String getStatusStr() {
            return StatusStr;
        }

        public void setStatusStr(String StatusStr) {
            this.StatusStr = StatusStr;
        }

        public String getAddAbleStr() {
            return AddAbleStr;
        }

        public void setAddAbleStr(String AddAbleStr) {
            this.AddAbleStr = AddAbleStr;
        }
    }
}
