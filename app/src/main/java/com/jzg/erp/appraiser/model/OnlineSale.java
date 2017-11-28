package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/23 17:25
 * @desc:
 */
public class OnlineSale extends com.jzg.erp.base.BaseObject {

    /**
     * StyleName : 1.8L 自动 两厢时尚型
     * PurchaseDate : 2011.03
     * Mileage : 5.98
     * ListingPrice : 6.50
     * PublishDate : 16.08.21
     * CityName : 北京
     * Source : 汽车之家
     */

    private List<DataBean> data;

    public List<DataBean> getDataBean() {
        return data;
    }

    public void setDataBean(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String StyleName;
        private String PurchaseDate;
        private String Mileage;
        private String ListingPrice;
        private String PublishDate;
        private String CityName;
        private String Source;

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

        public String getListingPrice() {
            return ListingPrice;
        }

        public void setListingPrice(String ListingPrice) {
            this.ListingPrice = ListingPrice;
        }

        public String getPublishDate() {
            return PublishDate;
        }

        public void setPublishDate(String PublishDate) {
            this.PublishDate = PublishDate;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public String getSource() {
            return Source;
        }

        public void setSource(String Source) {
            this.Source = Source;
        }
    }
}
