package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/11 15:20
 * @desc:评估记录
 */
public class EvaluateHistory extends com.jzg.erp.base.BaseObject {

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
        private int PingguPriceMin;
        private int PingguPriceMax;
        private int PingguUserId;
        private String UserName;
        private String SaleName;
        private int PurchasePrice;
        private int EquippedPrice;
        private String Remark;
        private int StoreId;
        private String StoreName;
        private int CarId;
        private String SearchStartDate;
        private String SearchEndDate;
        private int PageIndex;
        private int PageSize;
        private String PingguNo;
        private String TrueName;

        public String getTrueName() {
            return TrueName;
        }

        public void setTrueName(String trueName) {
            TrueName = trueName;
        }

        public String getPingguNo() {
            return PingguNo;
        }

        public void setPingguNo(String pingguNo) {
            PingguNo = pingguNo;
        }

        public int getEquippedPrice() {
            return EquippedPrice;
        }

        public void setEquippedPrice(int equippedPrice) {
            EquippedPrice = equippedPrice;
        }

        public String getLicensePlate() {
            return LicensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            LicensePlate = licensePlate;
        }

        public int getRequestStatus() {
            return RequestStatus;
        }

        public void setRequestStatus(int requestStatus) {
            RequestStatus = requestStatus;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        public String getApproveTime() {
            return ApproveTime;
        }

        public void setApproveTime(String approveTime) {
            ApproveTime = approveTime;
        }

        public int getPingguPriceMin() {
            return PingguPriceMin;
        }

        public void setPingguPriceMin(int pingguPriceMin) {
            PingguPriceMin = pingguPriceMin;
        }

        public int getPingguPriceMax() {
            return PingguPriceMax;
        }

        public void setPingguPriceMax(int pingguPriceMax) {
            PingguPriceMax = pingguPriceMax;
        }

        public int getPingguUserId() {
            return PingguUserId;
        }

        public void setPingguUserId(int pingguUserId) {
            PingguUserId = pingguUserId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getSaleName() {
            return SaleName;
        }

        public void setSaleName(String saleName) {
            SaleName = saleName;
        }

        public int getPurchasePrice() {
            return PurchasePrice;
        }

        public void setPurchasePrice(int purchasePrice) {
            PurchasePrice = purchasePrice;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

        public int getStoreId() {
            return StoreId;
        }

        public void setStoreId(int storeId) {
            StoreId = storeId;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String storeName) {
            StoreName = storeName;
        }

        public int getCarId() {
            return CarId;
        }

        public void setCarId(int carId) {
            CarId = carId;
        }

        public String getSearchStartDate() {
            return SearchStartDate;
        }

        public void setSearchStartDate(String searchStartDate) {
            SearchStartDate = searchStartDate;
        }

        public String getSearchEndDate() {
            return SearchEndDate;
        }

        public void setSearchEndDate(String searchEndDate) {
            SearchEndDate = searchEndDate;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int pageIndex) {
            PageIndex = pageIndex;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int pageSize) {
            PageSize = pageSize;
        }
    }
}
