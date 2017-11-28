package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * 手续信息 (用于显示)
 * Created by zealjiang on 2016/8/22 10:31.
 * Email: zealjiang@126.com
 */
public class ProcedureInfoModel extends com.jzg.erp.base.BaseObject{


    /**
     * CheckEndDate : 2016-06-01 00:00:00
     * SecureYear : 2016-08-14 00:00:00
     * TransferNum : 0
     * VehicleAndVesselTaxExpired : 2017-08-01 00:00:00
     * D4SMaintenance : 无
     * LuQiaoFeeExpired : 1900-01-01 00:00:00
     * SecureYearBusiness : 2017-08-01 00:00:00
     * CarKey : 0
     * NewCarWarranty : 保外
     * DrivingLicense : 无
     * CommercialInsuranceAmount : 0
     * TransferTicket : 无
     * VehicleSpecification : 无
     * RegistrationLicense : 无
     * PurchaseTax : 无
     * CarMaintenanceManual : 无
     * NewInvoice : 无
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String CheckEndDate;
        private String SecureYear;
        private int TransferNum;
        private String VehicleAndVesselTaxExpired;
        private String D4SMaintenance;
        private String LuQiaoFeeExpired;
        private String SecureYearBusiness;
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

        public int getTransferNum() {
            return TransferNum;
        }

        public void setTransferNum(int TransferNum) {
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
    }
}
