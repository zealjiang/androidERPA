package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/24 22:53
 * @desc:
 */
public class PingguCustomer extends com.jzg.erp.base.BaseObject{


    /**
     * Mobile : 1380000000
     * CustomerName : 客户名
     * ReplaceStyle : null
     * CustomerType : 1
     * Gender : null
     * CustomerWantPrice : 123123.0
     * SellcarLevel : 1
     * CustomerSource : 284
     * Address : 11111111
     * PresellTime : null
     * DicSubValue : 微信
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String Mobile;
        private String CustomerName;
        private String ReplaceStyle;
        private int CustomerType;
        private String Gender;
        private float CustomerWantPrice;
        private int SellcarLevel;
        private int CustomerSource;
        private String Address;
        private int PresellTime;
        private String PresellTimeStr;
        private String DicSubValue;
        private String Contact;
        private String SellcarLevelStr;

        public String getSellcarLevelStr() {
            return SellcarLevelStr;
        }

        public void setSellcarLevelStr(String sellcarLevelStr) {
            SellcarLevelStr = sellcarLevelStr;
        }

        public String getPresellTimeStr() {
            return PresellTimeStr;
        }

        public void setPresellTimeStr(String presellTimeStr) {
            PresellTimeStr = presellTimeStr;
        }

        public String getContact() {
            return Contact;
        }

        public void setContact(String contact) {
            Contact = contact;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public String getReplaceStyle() {
            return ReplaceStyle;
        }

        public void setReplaceStyle(String ReplaceStyle) {
            this.ReplaceStyle = ReplaceStyle;
        }

        public int getCustomerType() {
            return CustomerType;
        }

        public void setCustomerType(int CustomerType) {
            this.CustomerType = CustomerType;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

        public float getCustomerWantPrice() {
            return CustomerWantPrice;
        }

        public void setCustomerWantPrice(float CustomerWantPrice) {
            this.CustomerWantPrice = CustomerWantPrice;
        }

        public int getSellcarLevel() {
            return SellcarLevel;
        }

        public void setSellcarLevel(int SellcarLevel) {
            this.SellcarLevel = SellcarLevel;
        }

        public int getCustomerSource() {
            return CustomerSource;
        }

        public void setCustomerSource(int CustomerSource) {
            this.CustomerSource = CustomerSource;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public int getPresellTime() {
            return PresellTime;
        }

        public void setPresellTime(int PresellTime) {
            this.PresellTime = PresellTime;
        }

        public String getDicSubValue() {
            return DicSubValue;
        }

        public void setDicSubValue(String DicSubValue) {
            this.DicSubValue = DicSubValue;
        }
    }
}
