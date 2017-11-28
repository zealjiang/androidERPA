package com.jzg.erp.appraiser.model;

import com.jzg.erp.model.SubmitParamWrapper;

import java.util.List;

/**
 * 此类是提交提单前缓存数据的临时类
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/24 14:08
 * @desc:
 */
public class SubmitFinalJson extends com.jzg.erp.base.BaseObject{


    private int isUpdate;//0 表示不覆盖， 1  表示覆盖
    /**
     * customer : {"SellerId":3482,"CustomerName":"abc","Mobile":"18111111111","ReplaceStyle":"","Gender":"m","CustomerWantPrice":"","SellcarLevel":"","CustomerSource":"","Address":"","PresellTime":"","CustomerType":"","Contact":"","CreateUserName":"","SaleID":"","SaleName":""}
     * carinfo : {"ID":3482,"FactoryName":null,"MakeID":9,"ModelID":2593,"StyleID":101721,"MakeName":" 奥迪 ","carId":"","VIN":"","ModelName":"","StyleName":"","Regdate":"","Color":"","InnerClolor":"","Mileage":"","ManufactureDate":"","NewCarPrice":"LicensePlate","CheckEndDate":"","CompulsoryInsuranceExpired":"","TransferNum":"","VehicleAndVesselTaxExpired":"","D4SMaintenance":"","LuQiaoFeeExpired":"","SecureYearBusiness":"","CarKey":"","NewCarWarranty":"","DrivingLicense":"","CommercialInsuranceAmount":"","TransferTicket":"","VehicleSpecification":"","RegistrationLicense":"","PurchaseTax":"","CarMaintenanceManual":""}
     * carPrice : {"CreateUserId":130,"CreateUserName":"wang","PingguPriceMin":123,"PingguPriceMax":"","PingguUserId":"","PingguUserName":"","StoreId":"","StoreName":"","Remark":""}
     * carCI : ["1,1","2,1","3,1"]
     * carPic : [{"DictID":350,"PicPath":"/JietongdaImage/2016/08/13/dfe6dec8-a3eb-4c42-9fb7-ab2c6ee4c310_{0}.jpg","ImgText":" 图片描述 "}]
     * carCheck : ["188,2"]
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public static class DataBean {
        /**
         * SellerId : 3482
         * CustomerName : abc
         * Mobile : 18111111111
         * ReplaceStyle :
         * Gender : m
         * CustomerWantPrice :
         * SellcarLevel :
         * CustomerSource :
         * Address :
         * PresellTime :
         * CustomerType :
         * Contact :
         * CreateUserName :
         * SaleID :
         * SaleName :
         */

        private Customer customer;
        /**
         * ID : 3482
         * FactoryName : null
         * MakeID : 9
         * ModelID : 2593
         * StyleID : 101721
         * MakeName :  奥迪
         * carId :
         * VIN :
         * ModelName :
         * StyleName :
         * Regdate :
         * Color :
         * InnerClolor :
         * Mileage :
         * ManufactureDate :
         * NewCarPrice : LicensePlate
         * CheckEndDate :
         * CompulsoryInsuranceExpired :
         * TransferNum :
         * VehicleAndVesselTaxExpired :
         * D4SMaintenance :
         * LuQiaoFeeExpired :
         * SecureYearBusiness :
         * CarKey :
         * NewCarWarranty :
         * DrivingLicense :
         * CommercialInsuranceAmount :
         * TransferTicket :
         * VehicleSpecification :
         * RegistrationLicense :
         * PurchaseTax :
         * CarMaintenanceManual :
         */

        private CarInfo carinfo;
        /**
         * CreateUserId : 130
         * CreateUserName : wang
         * PingguPriceMin : 123
         * PingguPriceMax :
         * PingguUserId :
         * PingguUserName :
         * StoreId :
         * StoreName :
         * Remark :
         */

        private CarPrice carPrice;
        private List<String> carCI;
        /**
         * DictID : 350
         * PicPath : /JietongdaImage/2016/08/13/dfe6dec8-a3eb-4c42-9fb7-ab2c6ee4c310_{0}.jpg
         * ImgText :  图片描述
         */

        private List<SubmitParamWrapper.PhotoItem> carPic;
        private List<String> carCheck;

        public DataBean(Customer customer, CarInfo carinfo, CarPrice carPrice, List<String> carCI, List<SubmitParamWrapper.PhotoItem> carPic, List<String> carCheck) {
            this.customer = customer;
            this.carinfo = carinfo;
            this.carPrice = carPrice;
            this.carCI = carCI;
            this.carPic = carPic;
            this.carCheck = carCheck;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public CarInfo getCarinfo() {
            return carinfo;
        }

        public void setCarinfo(CarInfo carinfo) {
            this.carinfo = carinfo;
        }

        public CarPrice getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(CarPrice carPrice) {
            this.carPrice = carPrice;
        }

        public List<String> getCarCI() {
            return carCI;
        }

        public void setCarCI(List<String> carCI) {
            this.carCI = carCI;
        }

        public List<SubmitParamWrapper.PhotoItem> getCarPic() {
            return carPic;
        }

        public void setCarPic(List<SubmitParamWrapper.PhotoItem> carPic) {
            this.carPic = carPic;
        }

        public List<String> getCarCheck() {
            return carCheck;
        }

        public void setCarCheck(List<String> carCheck) {
            this.carCheck = carCheck;
        }

        public static class Customer {
            private int SellerId;
            private String CustomerName;
            private String Mobile;
            private String ReplaceStyle;
            private String Gender;
            private String CustomerWantPrice;
            private int SellcarLevel;
            private int CustomerSource;
            private String Address;
            private int PresellTime;
            private String CustomerType;
            private String Contact;
            private String CreateUserName;
            private String SaleID;
            private String SaleName;
            private int ID;

            public int getSellerId() {
                return SellerId;
            }

            public void setSellerId(int SellerId) {
                this.SellerId = SellerId;
            }

            public String getCustomerName() {
                return CustomerName;
            }

            public void setCustomerName(String CustomerName) {
                this.CustomerName = CustomerName;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public String getReplaceStyle() {
                return ReplaceStyle;
            }

            public void setReplaceStyle(String ReplaceStyle) {
                this.ReplaceStyle = ReplaceStyle;
            }

            public String getGender() {
                return Gender;
            }

            public void setGender(String Gender) {
                this.Gender = Gender;
            }

            public String getCustomerWantPrice() {
                return CustomerWantPrice;
            }

            public void setCustomerWantPrice(String CustomerWantPrice) {
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

            public String getCustomerType() {
                return CustomerType;
            }

            public void setCustomerType(String CustomerType) {
                this.CustomerType = CustomerType;
            }

            public String getContact() {
                return Contact;
            }

            public void setContact(String Contact) {
                this.Contact = Contact;
            }

            public String getCreateUserName() {
                return CreateUserName;
            }

            public void setCreateUserName(String CreateUserName) {
                this.CreateUserName = CreateUserName;
            }

            public String getSaleID() {
                return SaleID;
            }

            public void setSaleID(String SaleID) {
                this.SaleID = SaleID;
            }

            public String getSaleName() {
                return SaleName;
            }

            public void setSaleName(String SaleName) {
                this.SaleName = SaleName;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }
        }

        public static class CarInfo {
            private int ID;
            private String VIN;
            private String MakeName;
            private String ModelName;
            private String StyleName;
            private int MakeID;
            private int ModelID;
            private int StyleID;
            private String LicensePlate;
            private String Regdate;
            private int Color;
            private int InnerClolor;
            private String FactoryName;
            private String Mileage;
            private String ManufactureDate;
            private String NewCarPrice;
            private String CheckEndDate;
            private String CompulsoryInsuranceExpired;
            private String SecureYear;//交强险到期日
            private String TransferNum;
            private String VehicleAndVesselTaxExpired;
            private String D4SMaintenance;
            private String LuQiaoFeeExpired;
            private String SecureYearBusiness;
            private String CarKey;
            private String NewCarWarranty;
            private String DrivingLicense;
            private String CommercialInsuranceAmount;
            private String TransferTicket;
            private String VehicleSpecification;
            private String RegistrationLicense;
            private String PurchaseTax;
            private String CarMaintenanceManual;
            private String NewInvoice;


            public String getLicensePlate() {
                return LicensePlate;
            }

            public void setLicensePlate(String licensePlate) {
                LicensePlate = licensePlate;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getFactoryName() {
                return FactoryName;
            }

            public void setFactoryName(String FactoryName) {
                this.FactoryName = FactoryName;
            }

            public int getMakeID() {
                return MakeID;
            }

            public void setMakeID(int MakeID) {
                this.MakeID = MakeID;
            }

            public int getModelID() {
                return ModelID;
            }

            public void setModelID(int ModelID) {
                this.ModelID = ModelID;
            }

            public int getStyleID() {
                return StyleID;
            }

            public void setStyleID(int StyleID) {
                this.StyleID = StyleID;
            }

            public String getMakeName() {
                return MakeName;
            }

            public void setMakeName(String MakeName) {
                this.MakeName = MakeName;
            }

            public String getVIN() {
                return VIN;
            }

            public void setVIN(String VIN) {
                this.VIN = VIN;
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

            public String getMileage() {
                return Mileage;
            }

            public void setMileage(String Mileage) {
                this.Mileage = Mileage;
            }

            public String getManufactureDate() {
                return ManufactureDate;
            }

            public void setManufactureDate(String ManufactureDate) {
                this.ManufactureDate = ManufactureDate;
            }

            public String getNewCarPrice() {
                return NewCarPrice;
            }

            public void setNewCarPrice(String NewCarPrice) {
                this.NewCarPrice = NewCarPrice;
            }

            public String getCheckEndDate() {
                return CheckEndDate;
            }

            public void setCheckEndDate(String CheckEndDate) {
                this.CheckEndDate = CheckEndDate;
            }

            public String getCompulsoryInsuranceExpired() {
                return CompulsoryInsuranceExpired;
            }

            public void setCompulsoryInsuranceExpired(String CompulsoryInsuranceExpired) {
                this.CompulsoryInsuranceExpired = CompulsoryInsuranceExpired;
            }

            public String getTransferNum() {
                return TransferNum;
            }

            public void setTransferNum(String TransferNum) {
                this.TransferNum = TransferNum;
            }

            public String getVehicleAndVesselTaxExpired() {
                return VehicleAndVesselTaxExpired;
            }

            public void setVehicleAndVesselTaxExpired(String VehicleAndVesselTaxExpired) {
                this.VehicleAndVesselTaxExpired = VehicleAndVesselTaxExpired;
            }

            public String getD4SMaintenance() {
                return D4SMaintenance;
            }

            public void setD4SMaintenance(String D4SMaintenance) {
                this.D4SMaintenance = D4SMaintenance;
            }

            public String getLuQiaoFeeExpired() {
                return LuQiaoFeeExpired;
            }

            public void setLuQiaoFeeExpired(String LuQiaoFeeExpired) {
                this.LuQiaoFeeExpired = LuQiaoFeeExpired;
            }

            public String getSecureYearBusiness() {
                return SecureYearBusiness;
            }

            public void setSecureYearBusiness(String SecureYearBusiness) {
                this.SecureYearBusiness = SecureYearBusiness;
            }

            public String getCarKey() {
                return CarKey;
            }

            public void setCarKey(String CarKey) {
                this.CarKey = CarKey;
            }

            public String getNewCarWarranty() {
                return NewCarWarranty;
            }

            public void setNewCarWarranty(String NewCarWarranty) {
                this.NewCarWarranty = NewCarWarranty;
            }

            public String getDrivingLicense() {
                return DrivingLicense;
            }

            public void setDrivingLicense(String DrivingLicense) {
                this.DrivingLicense = DrivingLicense;
            }

            public String getCommercialInsuranceAmount() {
                return CommercialInsuranceAmount;
            }

            public void setCommercialInsuranceAmount(String CommercialInsuranceAmount) {
                this.CommercialInsuranceAmount = CommercialInsuranceAmount;
            }

            public String getTransferTicket() {
                return TransferTicket;
            }

            public void setTransferTicket(String TransferTicket) {
                this.TransferTicket = TransferTicket;
            }

            public String getVehicleSpecification() {
                return VehicleSpecification;
            }

            public void setVehicleSpecification(String VehicleSpecification) {
                this.VehicleSpecification = VehicleSpecification;
            }

            public String getRegistrationLicense() {
                return RegistrationLicense;
            }

            public void setRegistrationLicense(String RegistrationLicense) {
                this.RegistrationLicense = RegistrationLicense;
            }

            public String getPurchaseTax() {
                return PurchaseTax;
            }

            public void setPurchaseTax(String PurchaseTax) {
                this.PurchaseTax = PurchaseTax;
            }

            public String getCarMaintenanceManual() {
                return CarMaintenanceManual;
            }

            public void setCarMaintenanceManual(String CarMaintenanceManual) {
                this.CarMaintenanceManual = CarMaintenanceManual;
            }

            public String getNewInvoice() {
                return NewInvoice;
            }

            public void setNewInvoice(String newInvoice) {
                NewInvoice = newInvoice;
            }

            public String getSecureYear() {
                return SecureYear;
            }

            public void setSecureYear(String secureYear) {
                SecureYear = secureYear;
            }
        }

        public static class CarPrice {
            private String  CreateUserId;
            private String CreateUserName;
            private String PingguPriceMin;
            private String PingguPriceMax;
            private String PingguUserId;
            private String PingguUserName;
            private String StoreId;
            private String StoreName;
            private String Remark;
            private String UpdateUserName;

            public CarPrice() {
            }

            public CarPrice(String createUserId, String createUserName, String pingguPriceMin, String pingguPriceMax, String pingguUserId, String pingguUserName, String storeId, String storeName, String remark, String updateUserName) {
                CreateUserId = createUserId;
                CreateUserName = createUserName;
                PingguPriceMin = pingguPriceMin;
                PingguPriceMax = pingguPriceMax;
                PingguUserId = pingguUserId;
                PingguUserName = pingguUserName;
                StoreId = storeId;
                StoreName = storeName;
                Remark = remark;
                UpdateUserName = updateUserName;
            }

            public String getUpdateUserName() {
                return UpdateUserName;
            }

            public void setUpdateUserName(String updateUserName) {
                UpdateUserName = updateUserName;
            }

            public String getCreateUserId() {
                return CreateUserId;
            }

            public void setCreateUserId(String CreateUserId) {
                this.CreateUserId = CreateUserId;
            }

            public String getCreateUserName() {
                return CreateUserName;
            }

            public void setCreateUserName(String CreateUserName) {
                this.CreateUserName = CreateUserName;
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

            public String getPingguUserId() {
                return PingguUserId;
            }

            public void setPingguUserId(String PingguUserId) {
                this.PingguUserId = PingguUserId;
            }

            public String getPingguUserName() {
                return PingguUserName;
            }

            public void setPingguUserName(String PingguUserName) {
                this.PingguUserName = PingguUserName;
            }

            public String getStoreId() {
                return StoreId;
            }

            public void setStoreId(String StoreId) {
                this.StoreId = StoreId;
            }

            public String getStoreName() {
                return StoreName;
            }

            public void setStoreName(String StoreName) {
                this.StoreName = StoreName;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }
        }
    }

}
