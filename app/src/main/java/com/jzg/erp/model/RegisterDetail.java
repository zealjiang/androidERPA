package com.jzg.erp.model;

import java.io.Serializable;

public class RegisterDetail implements Serializable{
	
	/**
     * BianSuXiang :
     * AssceeEndPrice : 58.0
     * CheckEndDate : 2017-01
     * ColorName : 蓝色
     * CustomName : 回到家见到好多
     * SecureYear : 2018-01
     * Gang : 4缸
     * Mileage : 55.00 公里
     * LicensePlate : JXJXJ
     * ExCarType : yshshdhfd
     * FullName : 宝马 宝马7系(进口) 3.0T 自动 740Li xDrive马年限量版
     * CarConditionDes1 : 绝对绝对难道难道你不打扮
     * Mobile : 21212115
     * CarConditionDes2 :
     * OwnerType : 1
     * SecureYearBusiness : 2017-01
     * VinIsSell : 0
     * status : 100
     * CarProperty : 营运
     * RegDate : 2015-01
     * VinCode : BSJXJJXBXBFHDHHDH
     * StyleName : 3.0T 自动 740Li xDrive马年限量版
     * CompanyName : null
     * msg :
     * TransferNum : 25 次
     * AssceeStartPrice : 21.0
     * CarStatus : null
     * MakeName : 宝马
     * AssceeDesc : 技术监督局打扮打扮打扮打扮报道
     * License : 津 JXJXJ
     * ModelName : 宝马7系(进口)
     * CarStyle : 房车
     * LincensePlateTitle : 津
     * Id : 331
     * AppraiseInfoList : {"PicPath":null,"License":null,"AssceeDesc":"技术监督局打扮打扮打扮打扮报道","FullName":"宝马 宝马7系(进口) 3.0T 自动 740Li xDrive马年限量版","CreateTime":"2015/9/23","AssceePriceEnd":58,"PriceString":"","Id":331,"D4sInfoName":null,"AssceePriceStart":21}
     */
	private String ChekuangLevel;//车况等级
    private String BianSuXiang;
    private double AssceeEndPrice;
    private String CheckEndDate;
    private String ColorName;
    private String CustomName;
    private String SecureYear;
    private String Gang;
    private String Mileage;
    private String LicensePlate;
    private String ExCarType;
    private String FullName;
    private String CarConditionDes1;
    private String Mobile;
    private String CarConditionDes2;
    private String OwnerType;
    private String SecureYearBusiness;
    private String VinIsSell;
    private int status;
    private String CarProperty;
    private String RegDate;
    private String VinCode;
    private String StyleName;
    private String CompanyName;
    private String msg;
    private String TransferNum;
    private double AssceeStartPrice;
    private String CarStatus;
    private String MakeName;
    private String AssceeDesc;
    private String License;
    private String ModelName;
    private String CarStyle;
    private String LincensePlateTitle;
    private String Id;
    private String VehicleVesselTax;//车船税
    private String TollCharge;//路桥费
    private String KeyNum;//钥匙把数
    private AppraiseInfoListEntity AppraiseInfoList;

    public RegisterDetail() {
    }

    public String getChekuangLevel() {
		return ChekuangLevel;
	}

	public void setChekuangLevel(String chekuangLevel) {
		ChekuangLevel = chekuangLevel;
	}

	public void setBianSuXiang(String BianSuXiang) {
        this.BianSuXiang = BianSuXiang;
    }

    public void setAssceeEndPrice(double AssceeEndPrice) {
        this.AssceeEndPrice = AssceeEndPrice;
    }

    public void setCheckEndDate(String CheckEndDate) {
        this.CheckEndDate = CheckEndDate;
    }

    public void setColorName(String ColorName) {
        this.ColorName = ColorName;
    }

    public void setCustomName(String CustomName) {
        this.CustomName = CustomName;
    }

    public void setSecureYear(String SecureYear) {
        this.SecureYear = SecureYear;
    }

    public void setGang(String Gang) {
        this.Gang = Gang;
    }

    public void setMileage(String Mileage) {
        this.Mileage = Mileage;
    }

    public void setLicensePlate(String LicensePlate) {
        this.LicensePlate = LicensePlate;
    }

    public void setExCarType(String ExCarType) {
        this.ExCarType = ExCarType;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setCarConditionDes1(String CarConditionDes1) {
        this.CarConditionDes1 = CarConditionDes1;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public void setCarConditionDes2(String CarConditionDes2) {
        this.CarConditionDes2 = CarConditionDes2;
    }

    public void setOwnerType(String OwnerType) {
        this.OwnerType = OwnerType;
    }

    public void setSecureYearBusiness(String SecureYearBusiness) {
        this.SecureYearBusiness = SecureYearBusiness;
    }

    public void setVinIsSell(String VinIsSell) {
        this.VinIsSell = VinIsSell;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCarProperty(String CarProperty) {
        this.CarProperty = CarProperty;
    }

    public void setRegDate(String RegDate) {
        this.RegDate = RegDate;
    }

    public void setVinCode(String VinCode) {
        this.VinCode = VinCode;
    }

    public void setStyleName(String StyleName) {
        this.StyleName = StyleName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTransferNum(String TransferNum) {
        this.TransferNum = TransferNum;
    }

    public void setAssceeStartPrice(double AssceeStartPrice) {
        this.AssceeStartPrice = AssceeStartPrice;
    }

    public void setCarStatus(String CarStatus) {
        this.CarStatus = CarStatus;
    }

    public void setMakeName(String MakeName) {
        this.MakeName = MakeName;
    }

    public void setAssceeDesc(String AssceeDesc) {
        this.AssceeDesc = AssceeDesc;
    }

    public void setLicense(String License) {
        this.License = License;
    }

    public void setModelName(String ModelName) {
        this.ModelName = ModelName;
    }

    public void setCarStyle(String CarStyle) {
        this.CarStyle = CarStyle;
    }

    public void setLincensePlateTitle(String LincensePlateTitle) {
        this.LincensePlateTitle = LincensePlateTitle;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setAppraiseInfoList(AppraiseInfoListEntity AppraiseInfoList) {
        this.AppraiseInfoList = AppraiseInfoList;
    }

    public String getBianSuXiang() {
        return BianSuXiang;
    }

    public double getAssceeEndPrice() {
        return AssceeEndPrice;
    }

    public String getCheckEndDate() {
        return CheckEndDate;
    }

    public String getColorName() {
        return ColorName;
    }

    public String getCustomName() {
        return CustomName;
    }

    public String getSecureYear() {
        return SecureYear;
    }

    public String getGang() {
        return Gang;
    }

    public String getMileage() {
        return Mileage;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }

    public String getExCarType() {
        return ExCarType;
    }

    public String getFullName() {
        return FullName;
    }

    public String getCarConditionDes1() {
        return CarConditionDes1;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getCarConditionDes2() {
        return CarConditionDes2;
    }

    public String getOwnerType() {
        return OwnerType;
    }

    public String getSecureYearBusiness() {
        return SecureYearBusiness;
    }

    public String getVinIsSell() {
        return VinIsSell;
    }

    public int getStatus() {
        return status;
    }

    public String getCarProperty() {
        return CarProperty;
    }

    public String getRegDate() {
        return RegDate;
    }

    public String getVinCode() {
        return VinCode;
    }

    public String getStyleName() {
        return StyleName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getMsg() {
        return msg;
    }

    public String getTransferNum() {
        return TransferNum;
    }

    public double getAssceeStartPrice() {
        return AssceeStartPrice;
    }

    public String getCarStatus() {
        return CarStatus;
    }

    public String getMakeName() {
        return MakeName;
    }

    public String getAssceeDesc() {
        return AssceeDesc;
    }

    public String getLicense() {
        return License;
    }

    public String getModelName() {
        return ModelName;
    }

    public String getCarStyle() {
        return CarStyle;
    }

    public String getLincensePlateTitle() {
        return LincensePlateTitle;
    }

    public String getId() {
        return Id;
    }

    public String getVehicleVesselTax() {
        return VehicleVesselTax;
    }

    public void setVehicleVesselTax(String vehicleVesselTax) {
        VehicleVesselTax = vehicleVesselTax;
    }

    public String getTollCharge() {
        return TollCharge;
    }

    public void setTollCharge(String tollCharge) {
        TollCharge = tollCharge;
    }

    public String getKeyNum() {
        return KeyNum;
    }

    public void setKeyNum(String keyNum) {
        KeyNum = keyNum;
    }

    public AppraiseInfoListEntity getAppraiseInfoList() {
        return AppraiseInfoList;
    }



    public static class AppraiseInfoListEntity {
        /**
         * PicPath : null
         * License : null
         * AssceeDesc : 技术监督局打扮打扮打扮打扮报道
         * FullName : 宝马 宝马7系(进口) 3.0T 自动 740Li xDrive马年限量版
         * CreateTime : 2015/9/23
         * AssceePriceEnd : 58.0
         * PriceString :
         * Id : 331
         * D4sInfoName : null
         * AssceePriceStart : 21.0
         */
        private String PicPath;
        private String License;
        private String AssceeDesc;
        private String FullName;
        private String CreateTime;
        private double AssceePriceEnd;
        private String PriceString;
        private int Id;
        private String D4sInfoName;
        private double AssceePriceStart;

        public void setPicPath(String PicPath) {
            this.PicPath = PicPath;
        }

        public void setLicense(String License) {
            this.License = License;
        }

        public void setAssceeDesc(String AssceeDesc) {
            this.AssceeDesc = AssceeDesc;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public void setAssceePriceEnd(double AssceePriceEnd) {
            this.AssceePriceEnd = AssceePriceEnd;
        }

        public void setPriceString(String PriceString) {
            this.PriceString = PriceString;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setD4sInfoName(String D4sInfoName) {
            this.D4sInfoName = D4sInfoName;
        }

        public void setAssceePriceStart(double AssceePriceStart) {
            this.AssceePriceStart = AssceePriceStart;
        }

        public String getPicPath() {
            return PicPath;
        }

        public String getLicense() {
            return License;
        }

        public String getAssceeDesc() {
            return AssceeDesc;
        }

        public String getFullName() {
            return FullName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public double getAssceePriceEnd() {
            return AssceePriceEnd;
        }

        public String getPriceString() {
            return PriceString;
        }

        public int getId() {
            return Id;
        }

        public String getD4sInfoName() {
            return D4sInfoName;
        }

        public double getAssceePriceStart() {
            return AssceePriceStart;
        }

		public AppraiseInfoListEntity(String picPath, String license,
				String assceeDesc, String fullName, String createTime,
				double assceePriceEnd, String priceString, int id,
				String d4sInfoName, double assceePriceStart) {
			super();
			PicPath = picPath;
			License = license;
			AssceeDesc = assceeDesc;
			FullName = fullName;
			CreateTime = createTime;
			AssceePriceEnd = assceePriceEnd;
			PriceString = priceString;
			Id = id;
			D4sInfoName = d4sInfoName;
			AssceePriceStart = assceePriceStart;
		}
        
        
    }

	public RegisterDetail(String chekuangLevel,String bianSuXiang, double assceeEndPrice,
			String checkEndDate, String colorName, String customName,
			String secureYear, String gang, String mileage,
			String licensePlate, String exCarType, String fullName,
			String carConditionDes1, String mobile, String carConditionDes2,
			String ownerType, String secureYearBusiness, String vinIsSell,
			int status, String carProperty, String regDate, String vinCode,
			String styleName, String companyName, String msg,
			String transferNum, double assceeStartPrice, String carStatus,
			String makeName, String assceeDesc, String license,
			String modelName, String carStyle, String lincensePlateTitle,
			String id, AppraiseInfoListEntity appraiseInfoList) {
		super();
		ChekuangLevel = chekuangLevel;
		BianSuXiang = bianSuXiang;
		AssceeEndPrice = assceeEndPrice;
		CheckEndDate = checkEndDate;
		ColorName = colorName;
		CustomName = customName;
		SecureYear = secureYear;
		Gang = gang;
		Mileage = mileage;
		LicensePlate = licensePlate;
		ExCarType = exCarType;
		FullName = fullName;
		CarConditionDes1 = carConditionDes1;
		Mobile = mobile;
		CarConditionDes2 = carConditionDes2;
		OwnerType = ownerType;
		SecureYearBusiness = secureYearBusiness;
		VinIsSell = vinIsSell;
		this.status = status;
		CarProperty = carProperty;
		RegDate = regDate;
		VinCode = vinCode;
		StyleName = styleName;
		CompanyName = companyName;
		this.msg = msg;
		TransferNum = transferNum;
		AssceeStartPrice = assceeStartPrice;
		CarStatus = carStatus;
		MakeName = makeName;
		AssceeDesc = assceeDesc;
		License = license;
		ModelName = modelName;
		CarStyle = carStyle;
		LincensePlateTitle = lincensePlateTitle;
		Id = id;
		AppraiseInfoList = appraiseInfoList;
	}

    
    
}
