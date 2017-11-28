package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/11 15:16
 * @desc:
 */
public class TradeHistory extends com.jzg.erp.base.BaseObject {

    /**
     * MakeID : null
     * ModelID : null
     * StyleID : null
     * MakeName : 奥迪
     * ModelName : 奥迪进口S8
     * StyleName : 4.0T 自动 TFSI Quattro
     * FullName : 奥迪进口S8 4.0T 自动 TFSI Quattro
     * PurchasePrice : 55555
     * SalePrice : null
     * AppraiserId : 0
     * AppraiserName : 王
     * SaleId : null
     * SaleName : null
     * PurchaseTime : 2016-08-17T23:26:43.347
     * SaleTime : null
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String MakeID;
        private String ModelID;
        private String StyleID;
        private String MakeName;
        private String ModelName;
        private String StyleName;
        private String FullName;
        private float PurchasePrice;
        private float SalePrice;
        private int AppraiserId;
        private String AppraiserName;
        private int SaleId;
        private String SaleName;
        private String PurchaseTime;
        private String SaleTime;

        public String getMakeID() {
            return MakeID;
        }

        public void setMakeID(String makeID) {
            MakeID = makeID;
        }

        public String getModelID() {
            return ModelID;
        }

        public void setModelID(String modelID) {
            ModelID = modelID;
        }

        public String getStyleID() {
            return StyleID;
        }

        public void setStyleID(String styleID) {
            StyleID = styleID;
        }

        public String getMakeName() {
            return MakeName;
        }

        public void setMakeName(String makeName) {
            MakeName = makeName;
        }

        public String getModelName() {
            return ModelName;
        }

        public void setModelName(String modelName) {
            ModelName = modelName;
        }

        public String getStyleName() {
            return StyleName;
        }

        public void setStyleName(String styleName) {
            StyleName = styleName;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String fullName) {
            FullName = fullName;
        }

        public float getPurchasePrice() {
            return PurchasePrice;
        }

        public void setPurchasePrice(float purchasePrice) {
            PurchasePrice = purchasePrice;
        }

        public float getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(float salePrice) {
            SalePrice = salePrice;
        }

        public int getAppraiserId() {
            return AppraiserId;
        }

        public void setAppraiserId(int appraiserId) {
            AppraiserId = appraiserId;
        }

        public String getAppraiserName() {
            return AppraiserName;
        }

        public void setAppraiserName(String appraiserName) {
            AppraiserName = appraiserName;
        }

        public int getSaleId() {
            return SaleId;
        }

        public void setSaleId(int saleId) {
            SaleId = saleId;
        }

        public String getSaleName() {
            return SaleName;
        }

        public void setSaleName(String saleName) {
            SaleName = saleName;
        }

        public String getPurchaseTime() {
            return PurchaseTime;
        }

        public void setPurchaseTime(String purchaseTime) {
            PurchaseTime = purchaseTime;
        }

        public String getSaleTime() {
            return SaleTime;
        }

        public void setSaleTime(String saleTime) {
            SaleTime = saleTime;
        }
    }
}
