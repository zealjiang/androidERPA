package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * Created by zealjiang on 2016/8/22 13:35.
 * Email: zealjiang@126.com
 */
public class BaseInfoModel extends com.jzg.erp.base.BaseObject{


    /**
     * MakeName : 本田
     * ModelName : 广汽本田雅阁
     * StyleName : 2.0L CVT 舒享版
     * Regdate : 2013-08-01 00:00:00
     * Color : 19
     * InnerClolor : 18
     * DicSubValue : null
     * ColorDicSubValue : 红
     * InnerClolorDicSubValue : 白
     * NewCarPrice : 169800.0
     * ManufactureDate : 2012-08-14 00:00:00
     * LicensePlate : B12123
     * Mileage : 2000.0
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String MakeName;
        private String ModelName;
        private String StyleName;
        private String Regdate;
        private int Color;
        private int InnerClolor;
        private Object DicSubValue;
        private String ColorDicSubValue;
        private String InnerClolorDicSubValue;
        private double NewCarPrice;
        private String ManufactureDate;
        private String LicensePlate;
        private double Mileage;
        private String VIN;

        public String getMakeName() {
            return MakeName;
        }

        public void setMakeName(String MakeName) {
            this.MakeName = MakeName;
        }

        public String getModelName() {
            return ModelName;
        }

        public void setModelName(String ModelName) {
            this.ModelName = ModelName;
        }

        public String getStyleName() {
            return StyleName;
        }

        public void setStyleName(String StyleName) {
            this.StyleName = StyleName;
        }

        public String getRegdate() {
            return Regdate;
        }

        public void setRegdate(String Regdate) {
            this.Regdate = Regdate;
        }

        public int getColor() {
            return Color;
        }

        public void setColor(int Color) {
            this.Color = Color;
        }

        public int getInnerClolor() {
            return InnerClolor;
        }

        public void setInnerClolor(int InnerClolor) {
            this.InnerClolor = InnerClolor;
        }

        public Object getDicSubValue() {
            return DicSubValue;
        }

        public void setDicSubValue(Object DicSubValue) {
            this.DicSubValue = DicSubValue;
        }

        public String getColorDicSubValue() {
            return ColorDicSubValue;
        }

        public void setColorDicSubValue(String ColorDicSubValue) {
            this.ColorDicSubValue = ColorDicSubValue;
        }

        public String getInnerClolorDicSubValue() {
            return InnerClolorDicSubValue;
        }

        public void setInnerClolorDicSubValue(String InnerClolorDicSubValue) {
            this.InnerClolorDicSubValue = InnerClolorDicSubValue;
        }

        public double getNewCarPrice() {
            return NewCarPrice;
        }

        public void setNewCarPrice(double NewCarPrice) {
            this.NewCarPrice = NewCarPrice;
        }

        public String getManufactureDate() {
            return ManufactureDate;
        }

        public void setManufactureDate(String ManufactureDate) {
            this.ManufactureDate = ManufactureDate;
        }

        public String getLicensePlate() {
            return LicensePlate;
        }

        public void setLicensePlate(String LicensePlate) {
            this.LicensePlate = LicensePlate;
        }

        public double getMileage() {
            return Mileage;
        }

        public void setMileage(double Mileage) {
            this.Mileage = Mileage;
        }

        public String getVIN() {
            return VIN;
        }

        public void setVIN(String VIN) {
            this.VIN = VIN;
        }
    }
}
