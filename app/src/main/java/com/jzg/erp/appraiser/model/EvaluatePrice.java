package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/24 22:46
 * @desc:
 */
public class EvaluatePrice extends com.jzg.erp.base.BaseObject {

    /**
     * PriceMin : 34.12
     * PriceMax : 32.12
     * StoreName : HJK测试门店
     * PingguPrice : 34.12万-32.12万
     * FullName : 长安轿车CS351.6L 手动 舒适型 国四
     * ReMark : null
     * CreateTime : 2016-08-16 01:42:37
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private float PriceMin;
        private float PriceMax;
        private String StoreName;
        private String PingguPrice;
        private String FullName;
        private String ReMark;
        private String CreateTime;
        private String SaleName;

        public String getSaleName() {
            return SaleName;
        }

        public void setSaleName(String saleName) {
            SaleName = saleName;
        }

        public float getPriceMin() {
            return PriceMin;
        }

        public void setPriceMin(float PriceMin) {
            this.PriceMin = PriceMin;
        }

        public float getPriceMax() {
            return PriceMax;
        }

        public void setPriceMax(float PriceMax) {
            this.PriceMax = PriceMax;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String StoreName) {
            this.StoreName = StoreName;
        }

        public String getPingguPrice() {
            return PingguPrice;
        }

        public void setPingguPrice(String PingguPrice) {
            this.PingguPrice = PingguPrice;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getReMark() {
            return ReMark;
        }

        public void setReMark(String ReMark) {
            this.ReMark = ReMark;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
