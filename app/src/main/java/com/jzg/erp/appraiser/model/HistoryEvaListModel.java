package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * 历史评估单
 * Created by zealjiang on 2016/8/19 14:41.
 * Email: zealjiang@126.com
 */
public class HistoryEvaListModel extends com.jzg.erp.base.BaseObject{


    /**
     * LicensePlate : aa111
     * RequestStatus : 1
     * CreateTime : 2016-08-13T15:25:25.217
     * ApproveTime : 1900-01-01T00:00:00
     * PingguPriceMin : 11
     * PingguPriceMax : 15
     * PingguUserId : 153
     * UserName : null
     * SaleName : 鲁春艳
     * PurchasePrice : 1111
     * Remark : null
     * StoreId : 0
     * StoreName : null
     * CarId : 0
     * SearchStartDate : null
     * SearchEndDate : null
     * PageIndex : 0
     * PageSize : 0
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String LicensePlate;
        private int RequestStatus;
        private String CreateTime;
        private String ApproveTime;
        private String PingguPriceMin;
        private String PingguPriceMax;
        private int PingguUserId;
        private String UserName;
        private String TrueName;
        private String SaleName;
        private String PurchasePrice;
        private String Remark;
        private int StoreId;
        private String StoreName;
        private int CarId;
        private String SearchStartDate;
        private String SearchEndDate;
        private int PageIndex;
        private int PageSize;

        public String getLicensePlate() {
            return LicensePlate;
        }

        public void setLicensePlate(String LicensePlate) {
            this.LicensePlate = LicensePlate;
        }

        public int getRequestStatus() {
            return RequestStatus;
        }

        public void setRequestStatus(int RequestStatus) {
            this.RequestStatus = RequestStatus;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getApproveTime() {
            return ApproveTime;
        }

        public void setApproveTime(String ApproveTime) {
            this.ApproveTime = ApproveTime;
        }

        public String getPingguPriceMin() {
            return PingguPriceMin;
        }

        public void setPingguPriceMin(String PingguPriceMin) {
            this.PingguPriceMin = PingguPriceMin;
        }

        public String getPingguPriceMax() {
            return PingguPriceMax;
        }

        public void setPingguPriceMax(String PingguPriceMax) {
            this.PingguPriceMax = PingguPriceMax;
        }

        public int getPingguUserId() {
            return PingguUserId;
        }

        public void setPingguUserId(int PingguUserId) {
            this.PingguUserId = PingguUserId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getSaleName() {
            return SaleName;
        }

        public void setSaleName(String SaleName) {
            this.SaleName = SaleName;
        }

        public String getPurchasePrice() {
            return PurchasePrice;
        }

        public void setPurchasePrice(String PurchasePrice) {
            this.PurchasePrice = PurchasePrice;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public int getStoreId() {
            return StoreId;
        }

        public void setStoreId(int StoreId) {
            this.StoreId = StoreId;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String StoreName) {
            this.StoreName = StoreName;
        }

        public int getCarId() {
            return CarId;
        }

        public void setCarId(int CarId) {
            this.CarId = CarId;
        }

        public String getSearchStartDate() {
            return SearchStartDate;
        }

        public void setSearchStartDate(String SearchStartDate) {
            this.SearchStartDate = SearchStartDate;
        }

        public String getSearchEndDate() {
            return SearchEndDate;
        }

        public void setSearchEndDate(String SearchEndDate) {
            this.SearchEndDate = SearchEndDate;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex) {
            this.PageIndex = PageIndex;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public String getTrueName() {
            return TrueName;
        }

        public void setTrueName(String trueName) {
            TrueName = trueName;
        }
    }

}
