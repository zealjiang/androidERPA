package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * Created by zealjiang on 2016/8/16 17:15.
 * Email: zealjiang@126.com
 *
 */
public class EVCarList extends com.jzg.erp.base.BaseObject{


    /**
     * CarSourceID : 0
     * FullName : 丰田陆地巡洋舰(进口) 2015款 4.0L 自动 4000中东限量版V6
     * LicensePlate : 京A00012
     * CreateTime : 2016-08-13 17:43:38
     * D4sInfoName : 捷通永达
     * PriceMin : 25
     * PriceMax : 26
     * Result : B++
     * PingguPrice : 25.00万-26.00万
     * Path : /JietongdaImage/2016/08/13/0deaa6b1-3cf0-49b9-8ced-a92f265f7c9c_{0}.jpg
     * PicPath : http://imageup.jingzhengu.com/JietongdaImage/2016/08/13/0deaa6b1-3cf0-49b9-8ced-a92f265f7c9c_902.jpg
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private int ID;
        private String FullName;
        private String LicensePlate;
        private String CreateTime;
        private String StoreName;
        private String PriceMin;
        private String PriceMax;
        private String Result;
        private String PingguPrice;
        private String Path;
        private String PicPath;
        private String PingguId;

        public int getID() {
            return ID;
        }

        public void setID(int CarSourceID) {
            this.ID = CarSourceID;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getLicensePlate() {
            return LicensePlate;
        }

        public void setLicensePlate(String LicensePlate) {
            this.LicensePlate = LicensePlate;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String storeName) {
            StoreName = storeName;
        }

        public String getPriceMin() {
            return PriceMin;
        }

        public void setPriceMin(String PriceMin) {
            this.PriceMin = PriceMin;
        }

        public String getPriceMax() {
            return PriceMax;
        }

        public void setPriceMax(String PriceMax) {
            this.PriceMax = PriceMax;
        }

        public String getResult() {
            return Result;
        }

        public void setResult(String result) {
            Result = result;
        }

        public String getPingguPrice() {
            return PingguPrice;
        }

        public void setPingguPrice(String PingguPrice) {
            this.PingguPrice = PingguPrice;
        }

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }

        public String getPicPath() {
            return PicPath;
        }

        public void setPicPath(String PicPath) {
            this.PicPath = PicPath;
        }

        public String getPingguId() {
            return PingguId;
        }

        public void setPingguId(String pingguId) {
            PingguId = pingguId;
        }
    }
}
