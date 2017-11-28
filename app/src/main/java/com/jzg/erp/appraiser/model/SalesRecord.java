package com.jzg.erp.appraiser.model;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/15 11:18
 * @desc:
 */
public class SalesRecord {
    private int id;
    private String carFullName;
    private String manufactureDate;
    private float mileage;
    private float soldPrice;
    private String soldDate;
    private String city;
    private String company;

    public SalesRecord() {
    }

    public SalesRecord(int id, String carFullName, String manufactureDate, float mileage, float soldPrice, String soldDate, String city, String company) {
        this.id = id;
        this.carFullName = carFullName;
        this.manufactureDate = manufactureDate;
        this.mileage = mileage;
        this.soldPrice = soldPrice;
        this.soldDate = soldDate;
        this.city = city;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarFullName() {
        return carFullName;
    }

    public void setCarFullName(String carFullName) {
        this.carFullName = carFullName;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public float getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(float soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(String soldDate) {
        this.soldDate = soldDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
