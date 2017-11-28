package com.jzg.erp.model;

import android.text.TextUtils;

import com.jzg.erp.app.JzgApp;
import com.jzg.erp.appraiser.model.CarPhoto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/8 10:51
 * @desc: 评估师端提交评估信息包装类，包含评估必须的五项内容
 */
public class SubmitParamWrapper {
    private CustomerInfo customerInfo;
    private CarInfo carInfo;
    private CarCondition carCondition;
    private CarPhoto carPhoto;
    private PriceEvaluation priceEvaluation;
    private List<PhotoItem> photoItems;

    public SubmitParamWrapper(){
        initData();
    }

    public void initData(){
//        customerInfo = new CustomerInfo();
//        carInfo = new CarInfo();
//        carCondition = new CarCondition();
//        carPhoto = new CarPhoto();
//        priceEvaluation = new PriceEvaluation();
    }


    public void clearAllData(){
        customerInfo = null;
        carInfo = null;
        carCondition = null;
        carPhoto = null;
        priceEvaluation = null;
        photoItems = null;
    }


    public List<PhotoItem> getPhotoItems() {
        return photoItems;
    }

    public void setPhotoItems(List<PhotoItem> photoItems) {
        this.photoItems = photoItems;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    public CarCondition getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(CarCondition carCondition) {
        this.carCondition = carCondition;
    }

    public CarPhoto getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(CarPhoto carPhoto) {
        this.carPhoto = carPhoto;
    }

    public PriceEvaluation getPriceEvaluation() {
        return priceEvaluation;
    }

    public void setPriceEvaluation(PriceEvaluation priceEvaluation) {
        this.priceEvaluation = priceEvaluation;
    }

    /***
     * 5.价格信息
     */
    public static class PriceEvaluation{
        private float PingguPriceMin;
        private float PingguPriceMax;
        private String Remark;
        private int SaleID;
        private String SaleName;
        private String ReferPrice;

        public String getReferPrice() {
            return ReferPrice;
        }

        public void setReferPrice(String referPrice) {
            ReferPrice = referPrice;
        }

        public boolean isChecked(){
            return PingguPriceMax>0 && !TextUtils.isEmpty(Remark);
        }

        /***
         *
         * @param pingguPriceMin 最低估价
         * @param pingguPriceMax 最高估价
         * @param remark 备注
         * @param saleID 销售员id
         * @param saleName 销售员姓名
         */
        public PriceEvaluation(float pingguPriceMin, float pingguPriceMax, String remark, int saleID, String saleName) {
            PingguPriceMin = pingguPriceMin;
            PingguPriceMax = pingguPriceMax;
            Remark = remark;
            SaleID = saleID;
            SaleName = saleName;
        }

        public PriceEvaluation() {
        }

        public float getPingguPriceMin() {
            return PingguPriceMin;
        }

        public void setPingguPriceMin(float PingguPriceMin) {
            this.PingguPriceMin = PingguPriceMin;
        }

        public float getPingguPriceMax() {
            return PingguPriceMax;
        }

        public void setPingguPriceMax(float PingguPriceMax) {
            this.PingguPriceMax = PingguPriceMax;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public int getSaleID() {
            return SaleID;
        }

        public void setSaleID(int SaleID) {
            this.SaleID = SaleID;
        }

        public String getSaleName() {
            return SaleName;
        }

        public void setSaleName(String SaleName) {
            this.SaleName = SaleName;
        }
    }

    /***
     * 4.车辆照片
     */

    public static class PhotoItem{
        private int DictID;
        private String PicPath;
        private String ImgText;
        private String ViewUrl;

        /***
         *
         * @param dictID
         * @param picPath 提交服务器的相对路径
         * @param viewUrl 用来做展示的完整URL地址
         * @param imgText
         */
        public PhotoItem(int dictID, String picPath,String ViewUrl, String imgText) {
            DictID = dictID;
            PicPath = picPath;
            ImgText = imgText;
            this.ViewUrl = ViewUrl;
        }

        public PhotoItem() {
        }

        public String getViewUrl() {
            return ViewUrl;
        }

        public void setViewUrl(String ViewUrl) {
            this.ViewUrl = ViewUrl;
        }

        public int getDictID() {
            return DictID;
        }

        public void setDictID(int dictID) {
            DictID = dictID;
        }

        public String getPicPath() {
            return PicPath;
        }

        public void setPicPath(String picPath) {
            PicPath = picPath;
        }

        public String getImgText() {
            return ImgText;
        }

        public void setImgText(String imgText) {
            ImgText = imgText;
        }
    }

    /***
     * 3.车况描述
     */
    public static class CarCondition{

        public boolean isChecked(){
            if(data==null||data.size()<=0){
                return false;
            }else{
                for (int i = 0; i < data.size(); i++) {
                    String[] idValue = data.get(i).split(",");
                    if("231".equals(idValue[0])){
                        if(idValue.length==1){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public List<String> data;

        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }
    }


    /***
     * 2.车辆信息
     */
    public static class CarInfo{
        private String vin;
        private CarBaseInfo carBaseInfo;
        private AllocInfo allocInfo;
        private ArrayList<String> allocInfoList;
        private ProcedureInfo procedureInfo;

        public boolean isChecked(){
            if(TextUtils.isEmpty(vin))
                return false;
            if(carBaseInfo==null)
                return false;
            if(TextUtils.isEmpty(carBaseInfo.getBrand()) ||TextUtils.isEmpty(carBaseInfo.getStyle()) || TextUtils.isEmpty(carBaseInfo.getSeries()) || TextUtils.isEmpty(carBaseInfo.getFirstRegDate()) ||TextUtils.isEmpty(carBaseInfo.getMileage()))
                return false;
            return true;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public CarBaseInfo getCarBaseInfo() {
            return carBaseInfo;
        }

        public void setCarBaseInfo(CarBaseInfo carBaseInfo) {
            this.carBaseInfo = carBaseInfo;
        }

        public AllocInfo getAllocInfo() {
            return allocInfo;
        }

        public void setAllocInfo(AllocInfo allocInfo) {
            this.allocInfo = allocInfo;
        }

        public ProcedureInfo getProcedureInfo() {
            return procedureInfo;
        }

        public void setProcedureInfo(ProcedureInfo procedureInfo) {
            this.procedureInfo = procedureInfo;
        }

        public ArrayList<String> getAllocInfoList() {
            return allocInfoList;
        }

        public void setAllocInfoList(ArrayList<String> allocInfoList) {
            this.allocInfoList = allocInfoList;
        }
    }

    /***
     * 2.1车辆基本信息
     */
    public static class CarBaseInfo{
        private String brand;
        private String brandId;
        private String brandLogo;
        private String series;
        private String seriesId;
        private String style;
        private String styleId;
        private String cardId;
        private String FirstRegDate;
        private int color;
        private int innerDecor;//内饰
        private String mileage;
        private String manufactureDate;
        private String newCarPrice;
        private int carId;

        /***
         *
         * @param brand 品牌
         * @param series 车系
         * @param style 车型
         * @param cardId 车牌号
         * @param firstRegDate 初次上牌日期
         * @param color 车身颜色
         * @param innerDecor 内饰
         * @param mileage 表显里程
         * @param manufactureDate 出厂日期
         * @param newCarPrice 新车市场价
         */
        public CarBaseInfo(String brand, String series, String style,String brandId, String seriesId, String styleId,
                           String cardId, String firstRegDate, int color, int innerDecor, String mileage, String manufactureDate, String newCarPrice) {
            this.brand = brand;
            this.series = series;
            this.style = style;
            this.brandId = brandId;
            this.seriesId = seriesId;
            this.styleId = styleId;
            this.cardId = cardId;
            FirstRegDate = firstRegDate;
            this.color = color;
            this.innerDecor = innerDecor;
            this.mileage = mileage;
            this.manufactureDate = manufactureDate;
            this.newCarPrice = newCarPrice;
        }

        public CarBaseInfo() {
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getBrandLogo() {
            return brandLogo;
        }

        public void setBrandLogo(String brandLogo) {
            this.brandLogo = brandLogo;
        }

        public String getSeriesId() {
            return seriesId;
        }

        public void setSeriesId(String seriesId) {
            this.seriesId = seriesId;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getSeries() {
            return series;
        }

        public void setSeries(String series) {
            this.series = series;
        }

        public String getStyleId() {
            return styleId;
        }

        public void setStyleId(String styleId) {
            this.styleId = styleId;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getFirstRegDate() {
            return FirstRegDate;
        }

        public void setFirstRegDate(String firstRegDate) {
            FirstRegDate = firstRegDate;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getInnerDecor() {
            return innerDecor;
        }

        public void setInnerDecor(int innerDecor) {
            this.innerDecor = innerDecor;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getManufactureDate() {
            return manufactureDate;
        }

        public void setManufactureDate(String manufactureDate) {
            this.manufactureDate = manufactureDate;
        }

        public String getNewCarPrice() {
            return newCarPrice;
        }

        public void setNewCarPrice(String newCarPrice) {
            this.newCarPrice = newCarPrice;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }
    }

    /***
     * 2.2车辆配置信息
     */
    public static class AllocInfo{
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

        public AllocInfo() {
        }

        /***
         *
         * @param abs 防抱死
         * @param airBag 安全气囊
         * @param eba 刹车辅助
         * @param esp 车身稳定
         * @param elecMirror 后视镜电动调节
         * @param navi 导航
         * @param cdDvd cd/dvd
         * @param airCondition 空调
         * @param centerControlLock 中控锁
         * @param remoteKey 遥控钥匙
         * @param oneKeyStart 一键启动
         * @param elecWindow 电动车窗
         * @param backRadar 倒车雷达
         * @param backVideo 倒车影像
         * @param spareTire 备用轮胎
         * @param constSpeedCruise 定速巡航
         * @param skyLight 天窗
         * @param leatherSeats 真皮座椅
         * @param elecSeats 前座椅电动调节
         * @param autoParking 自动泊车
         */
        public AllocInfo(String abs, String airBag, String eba, String esp, String elecMirror, String navi, String cdDvd, String airCondition, String centerControlLock, String remoteKey, String oneKeyStart, String elecWindow, String backRadar, String backVideo, String spareTire, String constSpeedCruise, String skyLight, String leatherSeats, String elecSeats, String autoParking) {
            this.abs = abs;
            this.airBag = airBag;
            this.eba = eba;
            this.esp = esp;
            this.elecMirror = elecMirror;
            this.navi = navi;
            this.cdDvd = cdDvd;
            this.airCondition = airCondition;
            this.centerControlLock = centerControlLock;
            this.remoteKey = remoteKey;
            this.oneKeyStart = oneKeyStart;
            this.elecWindow = elecWindow;
            this.backRadar = backRadar;
            this.backVideo = backVideo;
            this.spareTire = spareTire;
            this.constSpeedCruise = constSpeedCruise;
            this.skyLight = skyLight;
            this.leatherSeats = leatherSeats;
            this.elecSeats = elecSeats;
            this.autoParking = autoParking;
        }

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

        public void setOnoKeyStart(String onoKeyStart) {
            this.oneKeyStart = onoKeyStart;
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

    /***
     * 2.3车辆手续信息
     */
    public static class ProcedureInfo{
        private String yearlyInspectDate;
        private String compulsoryInsuranceDate;
        private String regTimes;
        private String taxDate;
        private String maintain4s;
        private String roadBridgeDate;
        private String commercialInsuranceDate;
        private String key;
        private String newCarWarranty;
        private String driveLicense;
        private String commercialInsuranceMoney;
        private String transferBill;
        private String instruction;
        private String registration;
        private String purchaseTax;
        private String maintainManual;
        private String newCarInvoice;

        public ProcedureInfo() {
        }

        /***
         *
         * @param yearlyInspectDate 年检到期日
         * @param compulsoryInsuranceDate 交强险到期日
         * @param regTimes 过户次数
         * @param taxDate 车船税到期日
         * @param maintain4s 4s店定期保养
         * @param roadBridgeDate 路桥费到期
         * @param commercialInsuranceDate 商业险到期日
         * @param key 钥匙
         * @param newCarWarranty 新车质保
         * @param driveLicense 行驶证
         * @param commercialInsuranceMoney 商险金额
         * @param transferBill 过户票
         * @param instruction 车辆说明书
         * @param registration 登记证
         * @param purchaseTax 购置税
         * @param maintainManual 保养手册
         * @param newCarInvoice 新车发票
         */
        public ProcedureInfo(String yearlyInspectDate, String compulsoryInsuranceDate, String regTimes, String taxDate, String maintain4s, String roadBridgeDate, String commercialInsuranceDate, String key, String newCarWarranty, String driveLicense, String commercialInsuranceMoney, String transferBill,String instruction, String registration, String purchaseTax, String maintainManual, String newCarInvoice) {
            this.yearlyInspectDate = yearlyInspectDate;
            this.compulsoryInsuranceDate = compulsoryInsuranceDate;
            this.regTimes = regTimes;
            this.taxDate = taxDate;
            this.maintain4s = maintain4s;
            this.roadBridgeDate = roadBridgeDate;
            this.commercialInsuranceDate = commercialInsuranceDate;
            this.key = key;
            this.newCarWarranty = newCarWarranty;
            this.driveLicense = driveLicense;
            this.commercialInsuranceMoney = commercialInsuranceMoney;
            this.transferBill = transferBill;
            this.instruction = instruction;
            this.registration = registration;
            this.purchaseTax = purchaseTax;
            this.maintainManual = maintainManual;
            this.newCarInvoice = newCarInvoice;
        }

        public String getYearlyInspectDate() {
            return yearlyInspectDate;
        }

        public void setYearlyInspectDate(String yearlyInspectDate) {
            this.yearlyInspectDate = yearlyInspectDate;
        }

        public String getCompulsoryInsuranceDate() {
            return compulsoryInsuranceDate;
        }

        public void setCompulsoryInsuranceDate(String compulsoryInsuranceDate) {
            this.compulsoryInsuranceDate = compulsoryInsuranceDate;
        }

        public String getRegTimes() {
            return regTimes;
        }

        public void setRegTimes(String regTimes) {
            this.regTimes = regTimes;
        }

        public String getTaxDate() {
            return taxDate;
        }

        public void setTaxDate(String taxDate) {
            this.taxDate = taxDate;
        }

        public String getMaintain4s() {
            return maintain4s;
        }

        public void setMaintain4s(String maintain4s) {
            this.maintain4s = maintain4s;
        }

        public String getRoadBridgeDate() {
            return roadBridgeDate;
        }

        public void setRoadBridgeDate(String roadBridgeDate) {
            this.roadBridgeDate = roadBridgeDate;
        }

        public String getCommercialInsuranceDate() {
            return commercialInsuranceDate;
        }

        public void setCommercialInsuranceDate(String commercialInsuranceDate) {
            this.commercialInsuranceDate = commercialInsuranceDate;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getNewCarWarranty() {
            return newCarWarranty;
        }

        public void setNewCarWarranty(String newCarWarranty) {
            this.newCarWarranty = newCarWarranty;
        }

        public String getDriveLicense() {
            return driveLicense;
        }

        public void setDriveLicense(String driveLicense) {
            this.driveLicense = driveLicense;
        }

        public String getCommercialInsuranceMoney() {
            return commercialInsuranceMoney;
        }

        public void setCommercialInsuranceMoney(String commercialInsuranceMoney) {
            this.commercialInsuranceMoney = commercialInsuranceMoney;
        }

        public String getTransferBill() {
            return transferBill;
        }

        public void setTransferBill(String transferBill) {
            this.transferBill = transferBill;
        }

        public String getInstruction() {
            return instruction;
        }

        public void setInstruction(String instruction) {
            this.instruction = instruction;
        }

        public String getRegistration() {
            return registration;
        }

        public void setRegistration(String registration) {
            this.registration = registration;
        }

        public String getPurchaseTax() {
            return purchaseTax;
        }

        public void setPurchaseTax(String purchaseTax) {
            this.purchaseTax = purchaseTax;
        }

        public String getMaintainManual() {
            return maintainManual;
        }

        public void setMaintainManual(String maintainManual) {
            this.maintainManual = maintainManual;
        }

        public String getNewCarInvoice() {
            return newCarInvoice;
        }

        public void setNewCarInvoice(String newCarInvoice) {
            this.newCarInvoice = newCarInvoice;
        }
    }

    /***
     * 1.客户资料
     */
    public static class CustomerInfo{
        private int type;
        private String name;
        private String companyName;
        private String phone;
        private String replaceCar;
        private String gender;
        private String wantPrice;
        private String wantLevel;
        private int wantLevelValue;
        private String carHostSource;
        private int carHostValue;
        private String address;
        private String preSellDate;
        private int preSellDateValue;
        private String CreateUserName;
        private String SaleID;
        private String SaleName;
        private int customerId;
        private String customerNo;
        private String Contact;//当是企业客户是作为联系人显示

        public int getWantLevelValue() {
            return wantLevelValue;
        }

        public void setWantLevelValue(int wantLevelValue) {
            this.wantLevelValue = wantLevelValue;
        }

        public int getCarHostValue() {
            return carHostValue;
        }

        public void setCarHostValue(int carHostValue) {
            this.carHostValue = carHostValue;
        }

        public int getPreSellDateValue() {
            return preSellDateValue;
        }

        public void setPreSellDateValue(int preSellDateValue) {
            this.preSellDateValue = preSellDateValue;
        }

        public String getContact() {
            return Contact;
        }

        public void setContact(String contact) {
            Contact = contact;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public boolean isChecked(){
            if(type==1){//个人
                return !TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(replaceCar)&& !TextUtils.isEmpty(wantPrice);
            }else{
                return !TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(companyName)&& !TextUtils.isEmpty(wantPrice);
            }
        }

        public String getCreateUserName() {
            return CreateUserName;
        }

        public void setCreateUserName(String createUserName) {
            CreateUserName = createUserName;
        }

        public String getSaleID() {
            return SaleID;
        }

        public void setSaleID(String saleID) {
            SaleID = saleID;
        }

        public String getSaleName() {
            return SaleName;
        }

        public void setSaleName(String saleName) {
            SaleName = saleName;
        }

        public String getCustomerNo() {
            return customerNo;
        }

        public void setCustomerNo(String customerNo) {
            this.customerNo = customerNo;
        }

        /***
         * 客户信息构造方法
         * @param type 1：个人，2：企业
         * @param name type=1，则表示是姓名，type=2，则表示是联系人
         * @param companyName type=1，为空
         * @param phone 联系电话
         * @param replaceCar type=2，为空
         * @param gender 性别
         * @param wantPrice 客户心理价
         * @param wantLevel 购买意向等级
         * @param carHostSource 车主来源
         * @param address 通讯地址
         * @param preSellDate 预售日期
         */
        public CustomerInfo(int type, String name, String companyName, String phone, String replaceCar, String gender, String wantPrice, String wantLevel, String carHostSource, String address, String preSellDate) {
            this.type = type;
            this.name = name;
            this.companyName = companyName;
            this.phone = phone;
            this.replaceCar = replaceCar;
            this.gender = gender;
            this.wantPrice = wantPrice;
            this.wantLevel = wantLevel;
            this.carHostSource = carHostSource;
            this.address = address;
            this.preSellDate = preSellDate;
            if(!TextUtils.isEmpty(wantLevel))
                this.wantLevelValue = JzgApp.getOption().getData().getCustomerLevel().getValueByName(wantLevel);
            if(!TextUtils.isEmpty(carHostSource))
                this.carHostValue = JzgApp.getOption().getData().getCustomerFrom().getValueByName(carHostSource);
            if(!TextUtils.isEmpty(preSellDate))
                this.preSellDateValue = JzgApp.getOption().getData().getPreSellDate().getValueByName(preSellDate);

        }

        public CustomerInfo() {

        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReplaceCar() {
            return replaceCar;
        }

        public void setReplaceCar(String replaceCar) {
            this.replaceCar = replaceCar;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getWantPrice() {
            return wantPrice;
        }

        public void setWantPrice(String wantPrice) {
            this.wantPrice = wantPrice;
        }

        public String getWantLevel() {
            return wantLevel;
        }

        public void setWantLevel(String wantLevel) {
            this.wantLevel = wantLevel;
            //TODO
            if(!TextUtils.isEmpty(wantLevel))
                this.wantLevelValue = JzgApp.getOption().getData().getCustomerLevel().getValueByName(wantLevel);
        }

        public String getCarHostSource() {
            return carHostSource;
        }

        public void setCarHostSource(String carHostSource) {
            this.carHostSource = carHostSource;
            //TODO
            if(!TextUtils.isEmpty(carHostSource))
                this.carHostValue = JzgApp.getOption().getData().getCustomerFrom().getValueByName(carHostSource);
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPreSellDate() {
            return preSellDate;
        }

        public void setPreSellDate(String preSellDate) {
            this.preSellDate = preSellDate;
            //TODO
            if(!TextUtils.isEmpty(preSellDate))
                this.preSellDateValue = JzgApp.getOption().getData().getPreSellDate().getValueByName(preSellDate);
        }
    }
}
