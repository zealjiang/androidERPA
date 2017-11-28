package com.jzg.erp.appraiser.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zealjiang on 2016/8/23 17:10.
 * Email: zealjiang@126.com
 */
public class CarInfoModel extends com.jzg.erp.base.BaseObject implements Serializable{


    /**
     * carinfo : {"ID":3482,"FactoryName":"","MakeID":9,"ModelID":2593,"StyleID":101721,"MakeName":"奥迪","ModelName":"一汽奥迪A4L","StyleName":"1.8T CVT 30TFSI 舒适型","FullName":"奥迪A4L 2013款 1.8T CVT 30TFSI 舒适型","Color":16,"VIN":"12345678901234567","LicensePlate":"235","Mileage":120000,"Regdate":"2016-08-02 12:00:00","CarProperty":2,"LincensePlateTitle":"1","TransferNum":3,"CheckEndDate":"1900-01-01 12:00:00","SecureYear":"1900-01-01 12:00:00","SecureYearBusiness":"1900-01-01 12:00:00","CustomerId":0,"CarTypeId":4,"CheckInDate":"0001-01-01 12:00:00","CompanyPlate":"","SpeedBox":"","DescInfo":"","StockDays":0,"SetPrice":0,"PreSellTypeId":0,"StockAddr":"","BuyInfo":"","BusinessType":"","CarOwner":"","CarOwnerInside":"","AssceeAdvisor":"","Appraiser":"","CreateTime":"2016-08-13 02:24:19","CreateUser":"129","UpdateUser":"129","UpdateTime":"2016-08-13 02:48:25","Status":0,"CarStatus":0,"IsCheck":0,"BuyPrice":0,"BuyDate":"0001-01-01 12:00:00","D4sId":"106","BuyCarMonth":0,"CarBody":3,"OutStockDate":"0001-01-01 12:00:00","OutStockReson":"","getCertificate":"0001-01-01 12:00:00","StockingDate":"0001-01-01 12:00:00","CarGrade":"B+","InnerClolor":17,"CarType":"82","AirIntakeMethod":396,"NewCarPrice":291000,"TransferDate":"2016-08-03 12:00:00","EmissionsStandards":93,"SupplySystem":400,"CarPlaceProvinceName":"北京","CarPlaceCityName":"北京","Displacement":"1.8","DriveType":415,"AuthenInformation":"134","ManufactureDate":"2016-08-01 12:00:00","IsChecked":2,"IsPriced":2,"IsUpshelf":2,"IsAuthentication":0,"IsPreparation":2,"IsRecommend":2,"CarStatus2":0,"AuthenCode":"","CarYear":2013,"TransformType":412,"CompulsoryInsuranceExpired":"0001-01-01 12:00:00","VehicleAndVesselTaxExpired":"1900-01-01 12:00:00","D4SMaintenance":"有","LuQiaoFeeExpired":"1900-01-01 12:00:00","CarKey":0,"NewCarWarranty":"保内","DrivingLicense":"有","CommercialInsuranceAmount":0,"TransferTicket":"无","VehicleSpecification":"有","RegistrationLicense":"有","PurchaseTax":"无","CarMaintenanceManual":"有","NewInvoice":"有","ToWholesaleDate":"1900-01-01 12:00:00","Surveyor":"","SurveyorDate":"0001-01-01 12:00:00","UpshelfDate":"0001-01-01 12:00:00","AssessStatus":5,"CIID":0,"GroupID":0,"CI":"","AddAble":1,"Sort":0}
     * carCI : {"abs":"1","airBag":"1","eba":"1","esp":"1","elecMirror":"1","navi":"0","cdDvd":"0","airCondition":"0","centerControlLock":"1","remoteKey":"1","oneKeyStart":"0","elecWindow":"2","backRadar":"1","backVideo":"0","spareTire":"0","constSpeedCruise":"0","skyLight":"1","leatherSeats":"0","elecSeats":"1","autoParking":"2"}
     * carPic : [{"DictID":349,"PicPath":"/JietongdaImage/2016/08/13/692ff441-9e27-4822-93b1-9a79ad2e40bf_{0}.jpg","ImgText":""},{"DictID":350,"PicPath":"/JietongdaImage/2016/08/13/dfe6dec8-a3eb-4c42-9fb7-ab2c6ee4c310_{0}.jpg","ImgText":""},{"DictID":351,"PicPath":"/JietongdaImage/2016/08/13/e7340001-7b96-4fba-b36e-49a0a6b353bf_{0}.jpg","ImgText":""},{"DictID":353,"PicPath":"/JietongdaImage/2016/08/13/6d88549a-c6ed-4b33-a3ca-aa98d38fcc9f_{0}.jpg","ImgText":""},{"DictID":354,"PicPath":"/JietongdaImage/2016/08/13/2bd58363-02e8-4e43-b86d-20c5a6eeeafc_{0}.jpg","ImgText":""},{"DictID":352,"PicPath":"/JietongdaImage/2016/08/13/d1de1b16-743e-4434-a8c4-51fad796ce45_{0}.jpg","ImgText":""}]
     * isExist : true
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * ID : 3482
         * FactoryName :
         * MakeID : 9
         * ModelID : 2593
         * StyleID : 101721
         * MakeName : 奥迪
         * ModelName : 一汽奥迪A4L
         * StyleName : 1.8T CVT 30TFSI 舒适型
         * FullName : 奥迪A4L 2013款 1.8T CVT 30TFSI 舒适型
         * Color : 16
         * VIN : 12345678901234567
         * LicensePlate : 235
         * Mileage : 120000
         * Regdate : 2016-08-02 12:00:00
         * CarProperty : 2
         * LincensePlateTitle : 1
         * TransferNum : 3
         * CheckEndDate : 1900-01-01 12:00:00
         * SecureYear : 1900-01-01 12:00:00
         * SecureYearBusiness : 1900-01-01 12:00:00
         * CustomerId : 0
         * CarTypeId : 4
         * CheckInDate : 0001-01-01 12:00:00
         * CompanyPlate :
         * SpeedBox :
         * DescInfo :
         * StockDays : 0
         * SetPrice : 0
         * PreSellTypeId : 0
         * StockAddr :
         * BuyInfo :
         * BusinessType :
         * CarOwner :
         * CarOwnerInside :
         * AssceeAdvisor :
         * Appraiser :
         * CreateTime : 2016-08-13 02:24:19
         * CreateUser : 129
         * UpdateUser : 129
         * UpdateTime : 2016-08-13 02:48:25
         * Status : 0
         * CarStatus : 0
         * IsCheck : 0
         * BuyPrice : 0
         * BuyDate : 0001-01-01 12:00:00
         * D4sId : 106
         * BuyCarMonth : 0
         * CarBody : 3
         * OutStockDate : 0001-01-01 12:00:00
         * OutStockReson :
         * getCertificate : 0001-01-01 12:00:00
         * StockingDate : 0001-01-01 12:00:00
         * CarGrade : B+
         * InnerClolor : 17
         * CarType : 82
         * AirIntakeMethod : 396
         * NewCarPrice : 291000
         * TransferDate : 2016-08-03 12:00:00
         * EmissionsStandards : 93
         * SupplySystem : 400
         * CarPlaceProvinceName : 北京
         * CarPlaceCityName : 北京
         * Displacement : 1.8
         * DriveType : 415
         * AuthenInformation : 134
         * ManufactureDate : 2016-08-01 12:00:00
         * IsChecked : 2
         * IsPriced : 2
         * IsUpshelf : 2
         * IsAuthentication : 0
         * IsPreparation : 2
         * IsRecommend : 2
         * CarStatus2 : 0
         * AuthenCode :
         * CarYear : 2013
         * TransformType : 412
         * CompulsoryInsuranceExpired : 0001-01-01 12:00:00
         * VehicleAndVesselTaxExpired : 1900-01-01 12:00:00
         * D4SMaintenance : 有
         * LuQiaoFeeExpired : 1900-01-01 12:00:00
         * CarKey : 0
         * NewCarWarranty : 保内
         * DrivingLicense : 有
         * CommercialInsuranceAmount : 0
         * TransferTicket : 无
         * VehicleSpecification : 有
         * RegistrationLicense : 有
         * PurchaseTax : 无
         * CarMaintenanceManual : 有
         * NewInvoice : 有
         * ToWholesaleDate : 1900-01-01 12:00:00
         * Surveyor :
         * SurveyorDate : 0001-01-01 12:00:00
         * UpshelfDate : 0001-01-01 12:00:00
         * AssessStatus : 5
         * CIID : 0
         * GroupID : 0
         * CI :
         * AddAble : 1
         * Sort : 0
         */

        private CarinfoBean carinfo;
        /**
         * abs : 1
         * airBag : 1
         * eba : 1
         * esp : 1
         * elecMirror : 1
         * navi : 0
         * cdDvd : 0
         * airCondition : 0
         * centerControlLock : 1
         * remoteKey : 1
         * oneKeyStart : 0
         * elecWindow : 2
         * backRadar : 1
         * backVideo : 0
         * spareTire : 0
         * constSpeedCruise : 0
         * skyLight : 1
         * leatherSeats : 0
         * elecSeats : 1
         * autoParking : 2
         */

        private CarCIBean carCI;
        private boolean isExist;
        /**
         * DictID : 349
         * PicPath : /JietongdaImage/2016/08/13/692ff441-9e27-4822-93b1-9a79ad2e40bf_{0}.jpg
         * ImgText :
         */

        private List<CarPicBean> carPic;

        public CarinfoBean getCarinfo() {
            return carinfo;
        }

        public void setCarinfo(CarinfoBean carinfo) {
            this.carinfo = carinfo;
        }

        public CarCIBean getCarCI() {
            return carCI;
        }

        public void setCarCI(CarCIBean carCI) {
            this.carCI = carCI;
        }

        public boolean isIsExist() {
            return isExist;
        }

        public void setIsExist(boolean isExist) {
            this.isExist = isExist;
        }

        public List<CarPicBean> getCarPic() {
            return carPic;
        }

        public void setCarPic(List<CarPicBean> carPic) {
            this.carPic = carPic;
        }

        public static class CarinfoBean implements Serializable{
            private int ID;
            private String FactoryName;
            private int MakeID;
            private int ModelID;
            private int StyleID;
            private String MakeName;
            private String ModelName;
            private String StyleName;
            private String FullName;
            private int Color;
            private String VIN;
            private String LicensePlate;
            private int Mileage;
            private String Regdate;
            private int CarProperty;
            private String LincensePlateTitle;
            private int TransferNum;
            private String CheckEndDate;
            private String SecureYear;
            private String SecureYearBusiness;
            private int CustomerId;
            private int CarTypeId;
            private String CheckInDate;
            private String CompanyPlate;
            private String SpeedBox;
            private String DescInfo;
            private int StockDays;
            private int SetPrice;
            private int PreSellTypeId;
            private String StockAddr;
            private String BuyInfo;
            private String BusinessType;
            private String CarOwner;
            private String CarOwnerInside;
            private String AssceeAdvisor;
            private String Appraiser;
            private String CreateTime;
            private String CreateUser;
            private String UpdateUser;
            private String UpdateTime;
            private int Status;
            private int CarStatus;
            private int IsCheck;
            private int BuyPrice;
            private String BuyDate;
            private String D4sId;
            private int BuyCarMonth;
            private int CarBody;
            private String OutStockDate;
            private String OutStockReson;
            private String getCertificate;
            private String StockingDate;
            private String CarGrade;
            private int InnerClolor;
            private String CarType;
            private int AirIntakeMethod;
            private String NewCarPrice;
            private String TransferDate;
            private int EmissionsStandards;
            private int SupplySystem;
            private String CarPlaceProvinceName;
            private String CarPlaceCityName;
            private String Displacement;
            private int DriveType;
            private String AuthenInformation;
            private String ManufactureDate;
            private int IsChecked;
            private int IsPriced;
            private int IsUpshelf;
            private int IsAuthentication;
            private int IsPreparation;
            private int IsRecommend;
            private int CarStatus2;
            private String AuthenCode;
            private int CarYear;
            private int TransformType;
            private String CompulsoryInsuranceExpired;
            private String VehicleAndVesselTaxExpired;
            private String D4SMaintenance;
            private String LuQiaoFeeExpired;
            private int CarKey;
            private String NewCarWarranty;
            private String DrivingLicense;
            private int CommercialInsuranceAmount;
            private String TransferTicket;
            private String VehicleSpecification;
            private String RegistrationLicense;
            private String PurchaseTax;
            private String CarMaintenanceManual;
            private String NewInvoice;
            private String ToWholesaleDate;
            private String Surveyor;
            private String SurveyorDate;
            private String UpshelfDate;
            private int AssessStatus;
            private int CIID;
            private int GroupID;
            private String CI;
            private int AddAble;
            private int Sort;

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

            public String getFullName() {
                return FullName;
            }

            public void setFullName(String FullName) {
                this.FullName = FullName;
            }

            public int getColor() {
                return Color;
            }

            public void setColor(int Color) {
                this.Color = Color;
            }

            public String getVIN() {
                return VIN;
            }

            public void setVIN(String VIN) {
                this.VIN = VIN;
            }

            public String getLicensePlate() {
                return LicensePlate;
            }

            public void setLicensePlate(String LicensePlate) {
                this.LicensePlate = LicensePlate;
            }

            public int getMileage() {
                return Mileage;
            }

            public void setMileage(int Mileage) {
                this.Mileage = Mileage;
            }

            public String getRegdate() {
                return Regdate;
            }

            public void setRegdate(String Regdate) {
                this.Regdate = Regdate;
            }

            public int getCarProperty() {
                return CarProperty;
            }

            public void setCarProperty(int CarProperty) {
                this.CarProperty = CarProperty;
            }

            public String getLincensePlateTitle() {
                return LincensePlateTitle;
            }

            public void setLincensePlateTitle(String LincensePlateTitle) {
                this.LincensePlateTitle = LincensePlateTitle;
            }

            public int getTransferNum() {
                return TransferNum;
            }

            public void setTransferNum(int TransferNum) {
                this.TransferNum = TransferNum;
            }

            public String getCheckEndDate() {
                return CheckEndDate;
            }

            public void setCheckEndDate(String CheckEndDate) {
                this.CheckEndDate = CheckEndDate;
            }

            public String getSecureYear() {
                return SecureYear;
            }

            public void setSecureYear(String SecureYear) {
                this.SecureYear = SecureYear;
            }

            public String getSecureYearBusiness() {
                return SecureYearBusiness;
            }

            public void setSecureYearBusiness(String SecureYearBusiness) {
                this.SecureYearBusiness = SecureYearBusiness;
            }

            public int getCustomerId() {
                return CustomerId;
            }

            public void setCustomerId(int CustomerId) {
                this.CustomerId = CustomerId;
            }

            public int getCarTypeId() {
                return CarTypeId;
            }

            public void setCarTypeId(int CarTypeId) {
                this.CarTypeId = CarTypeId;
            }

            public String getCheckInDate() {
                return CheckInDate;
            }

            public void setCheckInDate(String CheckInDate) {
                this.CheckInDate = CheckInDate;
            }

            public String getCompanyPlate() {
                return CompanyPlate;
            }

            public void setCompanyPlate(String CompanyPlate) {
                this.CompanyPlate = CompanyPlate;
            }

            public String getSpeedBox() {
                return SpeedBox;
            }

            public void setSpeedBox(String SpeedBox) {
                this.SpeedBox = SpeedBox;
            }

            public String getDescInfo() {
                return DescInfo;
            }

            public void setDescInfo(String DescInfo) {
                this.DescInfo = DescInfo;
            }

            public int getStockDays() {
                return StockDays;
            }

            public void setStockDays(int StockDays) {
                this.StockDays = StockDays;
            }

            public int getSetPrice() {
                return SetPrice;
            }

            public void setSetPrice(int SetPrice) {
                this.SetPrice = SetPrice;
            }

            public int getPreSellTypeId() {
                return PreSellTypeId;
            }

            public void setPreSellTypeId(int PreSellTypeId) {
                this.PreSellTypeId = PreSellTypeId;
            }

            public String getStockAddr() {
                return StockAddr;
            }

            public void setStockAddr(String StockAddr) {
                this.StockAddr = StockAddr;
            }

            public String getBuyInfo() {
                return BuyInfo;
            }

            public void setBuyInfo(String BuyInfo) {
                this.BuyInfo = BuyInfo;
            }

            public String getBusinessType() {
                return BusinessType;
            }

            public void setBusinessType(String BusinessType) {
                this.BusinessType = BusinessType;
            }

            public String getCarOwner() {
                return CarOwner;
            }

            public void setCarOwner(String CarOwner) {
                this.CarOwner = CarOwner;
            }

            public String getCarOwnerInside() {
                return CarOwnerInside;
            }

            public void setCarOwnerInside(String CarOwnerInside) {
                this.CarOwnerInside = CarOwnerInside;
            }

            public String getAssceeAdvisor() {
                return AssceeAdvisor;
            }

            public void setAssceeAdvisor(String AssceeAdvisor) {
                this.AssceeAdvisor = AssceeAdvisor;
            }

            public String getAppraiser() {
                return Appraiser;
            }

            public void setAppraiser(String Appraiser) {
                this.Appraiser = Appraiser;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getCreateUser() {
                return CreateUser;
            }

            public void setCreateUser(String CreateUser) {
                this.CreateUser = CreateUser;
            }

            public String getUpdateUser() {
                return UpdateUser;
            }

            public void setUpdateUser(String UpdateUser) {
                this.UpdateUser = UpdateUser;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public int getCarStatus() {
                return CarStatus;
            }

            public void setCarStatus(int CarStatus) {
                this.CarStatus = CarStatus;
            }

            public int getIsCheck() {
                return IsCheck;
            }

            public void setIsCheck(int IsCheck) {
                this.IsCheck = IsCheck;
            }

            public int getBuyPrice() {
                return BuyPrice;
            }

            public void setBuyPrice(int BuyPrice) {
                this.BuyPrice = BuyPrice;
            }

            public String getBuyDate() {
                return BuyDate;
            }

            public void setBuyDate(String BuyDate) {
                this.BuyDate = BuyDate;
            }

            public String getD4sId() {
                return D4sId;
            }

            public void setD4sId(String D4sId) {
                this.D4sId = D4sId;
            }

            public int getBuyCarMonth() {
                return BuyCarMonth;
            }

            public void setBuyCarMonth(int BuyCarMonth) {
                this.BuyCarMonth = BuyCarMonth;
            }

            public int getCarBody() {
                return CarBody;
            }

            public void setCarBody(int CarBody) {
                this.CarBody = CarBody;
            }

            public String getOutStockDate() {
                return OutStockDate;
            }

            public void setOutStockDate(String OutStockDate) {
                this.OutStockDate = OutStockDate;
            }

            public String getOutStockReson() {
                return OutStockReson;
            }

            public void setOutStockReson(String OutStockReson) {
                this.OutStockReson = OutStockReson;
            }

            public String getGetCertificate() {
                return getCertificate;
            }

            public void setGetCertificate(String getCertificate) {
                this.getCertificate = getCertificate;
            }

            public String getStockingDate() {
                return StockingDate;
            }

            public void setStockingDate(String StockingDate) {
                this.StockingDate = StockingDate;
            }

            public String getCarGrade() {
                return CarGrade;
            }

            public void setCarGrade(String CarGrade) {
                this.CarGrade = CarGrade;
            }

            public int getInnerClolor() {
                return InnerClolor;
            }

            public void setInnerClolor(int InnerClolor) {
                this.InnerClolor = InnerClolor;
            }

            public String getCarType() {
                return CarType;
            }

            public void setCarType(String CarType) {
                this.CarType = CarType;
            }

            public int getAirIntakeMethod() {
                return AirIntakeMethod;
            }

            public void setAirIntakeMethod(int AirIntakeMethod) {
                this.AirIntakeMethod = AirIntakeMethod;
            }

            public String getNewCarPrice() {
                return NewCarPrice;
            }

            public void setNewCarPrice(String NewCarPrice) {
                this.NewCarPrice = NewCarPrice;
            }

            public String getTransferDate() {
                return TransferDate;
            }

            public void setTransferDate(String TransferDate) {
                this.TransferDate = TransferDate;
            }

            public int getEmissionsStandards() {
                return EmissionsStandards;
            }

            public void setEmissionsStandards(int EmissionsStandards) {
                this.EmissionsStandards = EmissionsStandards;
            }

            public int getSupplySystem() {
                return SupplySystem;
            }

            public void setSupplySystem(int SupplySystem) {
                this.SupplySystem = SupplySystem;
            }

            public String getCarPlaceProvinceName() {
                return CarPlaceProvinceName;
            }

            public void setCarPlaceProvinceName(String CarPlaceProvinceName) {
                this.CarPlaceProvinceName = CarPlaceProvinceName;
            }

            public String getCarPlaceCityName() {
                return CarPlaceCityName;
            }

            public void setCarPlaceCityName(String CarPlaceCityName) {
                this.CarPlaceCityName = CarPlaceCityName;
            }

            public String getDisplacement() {
                return Displacement;
            }

            public void setDisplacement(String Displacement) {
                this.Displacement = Displacement;
            }

            public int getDriveType() {
                return DriveType;
            }

            public void setDriveType(int DriveType) {
                this.DriveType = DriveType;
            }

            public String getAuthenInformation() {
                return AuthenInformation;
            }

            public void setAuthenInformation(String AuthenInformation) {
                this.AuthenInformation = AuthenInformation;
            }

            public String getManufactureDate() {
                return ManufactureDate;
            }

            public void setManufactureDate(String ManufactureDate) {
                this.ManufactureDate = ManufactureDate;
            }

            public int getIsChecked() {
                return IsChecked;
            }

            public void setIsChecked(int IsChecked) {
                this.IsChecked = IsChecked;
            }

            public int getIsPriced() {
                return IsPriced;
            }

            public void setIsPriced(int IsPriced) {
                this.IsPriced = IsPriced;
            }

            public int getIsUpshelf() {
                return IsUpshelf;
            }

            public void setIsUpshelf(int IsUpshelf) {
                this.IsUpshelf = IsUpshelf;
            }

            public int getIsAuthentication() {
                return IsAuthentication;
            }

            public void setIsAuthentication(int IsAuthentication) {
                this.IsAuthentication = IsAuthentication;
            }

            public int getIsPreparation() {
                return IsPreparation;
            }

            public void setIsPreparation(int IsPreparation) {
                this.IsPreparation = IsPreparation;
            }

            public int getIsRecommend() {
                return IsRecommend;
            }

            public void setIsRecommend(int IsRecommend) {
                this.IsRecommend = IsRecommend;
            }

            public int getCarStatus2() {
                return CarStatus2;
            }

            public void setCarStatus2(int CarStatus2) {
                this.CarStatus2 = CarStatus2;
            }

            public String getAuthenCode() {
                return AuthenCode;
            }

            public void setAuthenCode(String AuthenCode) {
                this.AuthenCode = AuthenCode;
            }

            public int getCarYear() {
                return CarYear;
            }

            public void setCarYear(int CarYear) {
                this.CarYear = CarYear;
            }

            public int getTransformType() {
                return TransformType;
            }

            public void setTransformType(int TransformType) {
                this.TransformType = TransformType;
            }

            public String getCompulsoryInsuranceExpired() {
                return CompulsoryInsuranceExpired;
            }

            public void setCompulsoryInsuranceExpired(String CompulsoryInsuranceExpired) {
                this.CompulsoryInsuranceExpired = CompulsoryInsuranceExpired;
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

            public int getCarKey() {
                return CarKey;
            }

            public void setCarKey(int CarKey) {
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

            public int getCommercialInsuranceAmount() {
                return CommercialInsuranceAmount;
            }

            public void setCommercialInsuranceAmount(int CommercialInsuranceAmount) {
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

            public void setNewInvoice(String NewInvoice) {
                this.NewInvoice = NewInvoice;
            }

            public String getToWholesaleDate() {
                return ToWholesaleDate;
            }

            public void setToWholesaleDate(String ToWholesaleDate) {
                this.ToWholesaleDate = ToWholesaleDate;
            }

            public String getSurveyor() {
                return Surveyor;
            }

            public void setSurveyor(String Surveyor) {
                this.Surveyor = Surveyor;
            }

            public String getSurveyorDate() {
                return SurveyorDate;
            }

            public void setSurveyorDate(String SurveyorDate) {
                this.SurveyorDate = SurveyorDate;
            }

            public String getUpshelfDate() {
                return UpshelfDate;
            }

            public void setUpshelfDate(String UpshelfDate) {
                this.UpshelfDate = UpshelfDate;
            }

            public int getAssessStatus() {
                return AssessStatus;
            }

            public void setAssessStatus(int AssessStatus) {
                this.AssessStatus = AssessStatus;
            }

            public int getCIID() {
                return CIID;
            }

            public void setCIID(int CIID) {
                this.CIID = CIID;
            }

            public int getGroupID() {
                return GroupID;
            }

            public void setGroupID(int GroupID) {
                this.GroupID = GroupID;
            }

            public String getCI() {
                return CI;
            }

            public void setCI(String CI) {
                this.CI = CI;
            }

            public int getAddAble() {
                return AddAble;
            }

            public void setAddAble(int AddAble) {
                this.AddAble = AddAble;
            }

            public int getSort() {
                return Sort;
            }

            public void setSort(int Sort) {
                this.Sort = Sort;
            }
        }

        public static class CarCIBean implements Serializable{
            private String abs;
            private String airBag;
            private String eba;
            private String esp;
            private String elecMirror;
            private String navi;
            private String cdDvd;
            private String airCondition;
            private String centerControlLock;
            private String remoteKey;
            private String oneKeyStart;
            private String elecWindow;
            private String backRadar;
            private String backVideo;
            private String spareTire;
            private String constSpeedCruise;
            private String skyLight;
            private String leatherSeats;
            private String elecSeats;
            private String autoParking;

            public String getAbs() {
                return abs;
            }

            public void setAbs(String abs) {
                this.abs = abs;
            }

            public String getAirBag() {
                return airBag;
            }

            public void setAirBag(String airBag) {
                this.airBag = airBag;
            }

            public String getEba() {
                return eba;
            }

            public void setEba(String eba) {
                this.eba = eba;
            }

            public String getEsp() {
                return esp;
            }

            public void setEsp(String esp) {
                this.esp = esp;
            }

            public String getElecMirror() {
                return elecMirror;
            }

            public void setElecMirror(String elecMirror) {
                this.elecMirror = elecMirror;
            }

            public String getNavi() {
                return navi;
            }

            public void setNavi(String navi) {
                this.navi = navi;
            }

            public String getCdDvd() {
                return cdDvd;
            }

            public void setCdDvd(String cdDvd) {
                this.cdDvd = cdDvd;
            }

            public String getAirCondition() {
                return airCondition;
            }

            public void setAirCondition(String airCondition) {
                this.airCondition = airCondition;
            }

            public String getCenterControlLock() {
                return centerControlLock;
            }

            public void setCenterControlLock(String centerControlLock) {
                this.centerControlLock = centerControlLock;
            }

            public String getRemoteKey() {
                return remoteKey;
            }

            public void setRemoteKey(String remoteKey) {
                this.remoteKey = remoteKey;
            }

            public String getOneKeyStart() {
                return oneKeyStart;
            }

            public void setOneKeyStart(String oneKeyStart) {
                this.oneKeyStart = oneKeyStart;
            }

            public String getElecWindow() {
                return elecWindow;
            }

            public void setElecWindow(String elecWindow) {
                this.elecWindow = elecWindow;
            }

            public String getBackRadar() {
                return backRadar;
            }

            public void setBackRadar(String backRadar) {
                this.backRadar = backRadar;
            }

            public String getBackVideo() {
                return backVideo;
            }

            public void setBackVideo(String backVideo) {
                this.backVideo = backVideo;
            }

            public String getSpareTire() {
                return spareTire;
            }

            public void setSpareTire(String spareTire) {
                this.spareTire = spareTire;
            }

            public String getConstSpeedCruise() {
                return constSpeedCruise;
            }

            public void setConstSpeedCruise(String constSpeedCruise) {
                this.constSpeedCruise = constSpeedCruise;
            }

            public String getSkyLight() {
                return skyLight;
            }

            public void setSkyLight(String skyLight) {
                this.skyLight = skyLight;
            }

            public String getLeatherSeats() {
                return leatherSeats;
            }

            public void setLeatherSeats(String leatherSeats) {
                this.leatherSeats = leatherSeats;
            }

            public String getElecSeats() {
                return elecSeats;
            }

            public void setElecSeats(String elecSeats) {
                this.elecSeats = elecSeats;
            }

            public String getAutoParking() {
                return autoParking;
            }

            public void setAutoParking(String autoParking) {
                this.autoParking = autoParking;
            }
        }

        public static class CarPicBean implements Serializable{
            private int DictID;
            private String PicPath;
            private String ImgText;
            private String ViewUrl;

            public String getViewUrl() {
                return ViewUrl;
            }

            public void setViewUrl(String viewUrl) {
                ViewUrl = viewUrl;
            }

            public int getDictID() {
                return DictID;
            }

            public void setDictID(int DictID) {
                this.DictID = DictID;
            }

            public String getPicPath() {
                return PicPath;
            }

            public void setPicPath(String PicPath) {
                this.PicPath = PicPath;
            }

            public String getImgText() {
                return ImgText;
            }

            public void setImgText(String ImgText) {
                this.ImgText = ImgText;
            }
        }
    }
}
