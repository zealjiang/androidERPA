package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/23 17:11
 * @desc:
 */
public class TradeRecord extends com.jzg.erp.base.BaseObject {

    /**
     *  Status    : 200
     *  Message  : 请求成功！
     * jsonlist : [{"StyleName":"1.8L 自动 两厢时尚型","PurchaseDate":"2011.10","Mileage":"4.80","Price":"5.37","Date":"16.08.12","CityName":"北京"},{"StyleName":"1.8L 自动 两厢时尚型","PurchaseDate":"2012.06","Mileage":"4.80","Price":"5.66","Date":"16.08.06","CityName":"北京"},{"StyleName":"1.8L 自动 两厢时尚型","PurchaseDate":"2012.05","Mileage":"4.00","Price":"5.67","Date":"16.07.18","CityName":"北京"},{"StyleName":"1.8L 自动 两厢时尚型","PurchaseDate":"2012.01","Mileage":"4.50","Price":"5.55","Date":"16.08.16","CityName":"天水"},{"StyleName":"1.8L 自动 两厢时尚型","PurchaseDate":"2011.11","Mileage":"5.00","Price":"5.40","Date":"16.08.08","CityName":"包头"},{"StyleName":"1.8L 自动 两厢时尚型","PurchaseDate":"2011.07","Mileage":"4.30","Price":"5.35","Date":"16.08.07","CityName":"营口"},{"StyleName":"1.8L 自动 两厢时尚型","PurchaseDate":"2011.05","Mileage":"4.30","Price":"5.30","Date":"16.07.17","CityName":"朔州"},{"StyleName":"1.8L 自动 两厢时尚型","PurchaseDate":"2011.10","Mileage":"5.90","Price":"5.33","Date":"16.07.13","CityName":"南宁"},{"StyleName":"1.8L 自动 两厢时尚型","PurchaseDate":"2011.09","Mileage":"6.30","Price":"5.39","Date":"16.07.13","CityName":"漳州"},{"StyleName":"1.8L 自动 两厢时尚型","PurchaseDate":"2011.11","Mileage":"8.60","Price":"4.58","Date":"16.08.20","CityName":"北京"}]
     */
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    /**
     * StyleName : 1.8L 自动 两厢时尚型
     * PurchaseDate : 2011.10
     * Mileage : 4.80
     * Price : 5.37
     * Date : 16.08.12
     * CityName : 北京
     */

    private java.util.List<DataBean> data;

    public static class DataBean {
        private String StyleName;
        private String PurchaseDate;
        private String Mileage;
        private String Price;
        private String Date;
        private String CityName;

        public String getStyleName() {
            return StyleName;
        }

        public void setStyleName(String StyleName) {
            this.StyleName = StyleName;
        }

        public String getPurchaseDate() {
            return PurchaseDate;
        }

        public void setPurchaseDate(String PurchaseDate) {
            this.PurchaseDate = PurchaseDate;
        }

        public String getMileage() {
            return Mileage;
        }

        public void setMileage(String Mileage) {
            this.Mileage = Mileage;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }
    }
}
