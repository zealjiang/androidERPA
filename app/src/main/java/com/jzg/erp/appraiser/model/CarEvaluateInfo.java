package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/23 20:23
 * @desc:
 */
public class CarEvaluateInfo extends com.jzg.erp.base.BaseObject {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String CarName;

        private float Msrp;

        private String ImageUrl;

        private String EnvironmentStandard;

        private String newcarpreferentialprice;

        private float C2BPrice;

        private float B2BPrice;

        private float B2CPrice;

        private String Price_A;

        private String Price_B;

        private String Price_C;

        private int C2BDepreciations;

        private int Oils;

        private int Insurances;

        private int Maintences;

        private int Taxs;

        private float DepreciationPer;

        private float OilPer;

        private float InsurancePer;

        private float MaintainPer;

        private float TaxPer;

        public void setCarName(String CarName){
            this.CarName = CarName;
        }
        public String getCarName(){
            return this.CarName;
        }
        public void setMsrp(float Msrp){
            this.Msrp = Msrp;
        }
        public float getMsrp(){
            return this.Msrp;
        }
        public void setImageUrl(String ImageUrl){
            this.ImageUrl = ImageUrl;
        }
        public String getImageUrl(){
            return this.ImageUrl;
        }
        public void setEnvironmentStandard(String EnvironmentStandard){
            this.EnvironmentStandard = EnvironmentStandard;
        }
        public String getEnvironmentStandard(){
            return this.EnvironmentStandard;
        }
        public void setNewcarpreferentialprice(String newcarpreferentialprice){
            this.newcarpreferentialprice = newcarpreferentialprice;
        }
        public String getNewcarpreferentialprice(){
            return this.newcarpreferentialprice;
        }
        public void setC2BPrice(float C2BPrice){
            this.C2BPrice = C2BPrice;
        }
        public float getC2BPrice(){
            return this.C2BPrice;
        }
        public void setB2BPrice(float B2BPrice){
            this.B2BPrice = B2BPrice;
        }
        public float getB2BPrice(){
            return this.B2BPrice;
        }
        public void setB2CPrice(float B2CPrice){
            this.B2CPrice = B2CPrice;
        }
        public float getB2CPrice(){
            return this.B2CPrice;
        }
        public void setPrice_A(String Price_A){
            this.Price_A = Price_A;
        }
        public String getPrice_A(){
            return this.Price_A;
        }
        public void setPrice_B(String Price_B){
            this.Price_B = Price_B;
        }
        public String getPrice_B(){
            return this.Price_B;
        }
        public void setPrice_C(String Price_C){
            this.Price_C = Price_C;
        }
        public String getPrice_C(){
            return this.Price_C;
        }
        public void setC2BDepreciations(int C2BDepreciations){
            this.C2BDepreciations = C2BDepreciations;
        }
        public int getC2BDepreciations(){
            return this.C2BDepreciations;
        }
        public void setOils(int Oils){
            this.Oils = Oils;
        }
        public int getOils(){
            return this.Oils;
        }
        public void setInsurances(int Insurances){
            this.Insurances = Insurances;
        }
        public int getInsurances(){
            return this.Insurances;
        }
        public void setMaintences(int Maintences){
            this.Maintences = Maintences;
        }
        public int getMaintences(){
            return this.Maintences;
        }
        public void setTaxs(int Taxs){
            this.Taxs = Taxs;
        }
        public int getTaxs(){
            return this.Taxs;
        }
        public void setDepreciationPer(float DepreciationPer){
            this.DepreciationPer = DepreciationPer;
        }
        public float getDepreciationPer(){
            return this.DepreciationPer;
        }
        public void setOilPer(float OilPer){
            this.OilPer = OilPer;
        }
        public float getOilPer(){
            return this.OilPer;
        }
        public void setInsurancePer(float InsurancePer){
            this.InsurancePer = InsurancePer;
        }
        public float getInsurancePer(){
            return this.InsurancePer;
        }
        public void setMaintainPer(float MaintainPer){
            this.MaintainPer = MaintainPer;
        }
        public float getMaintainPer(){
            return this.MaintainPer;
        }
        public void setTaxPer(float TaxPer){
            this.TaxPer = TaxPer;
        }
        public float getTaxPer(){
            return this.TaxPer;
        }

        private List<KeyValueItem> tradepricetendencyresp;
        private List<KeyValueItem> carstock;

        public List<KeyValueItem> getTradepricetendencyresp() {
            return tradepricetendencyresp;
        }

        public void setTradepricetendencyresp(List<KeyValueItem> tradepricetendencyresp) {
            this.tradepricetendencyresp = tradepricetendencyresp;
        }

        public List<KeyValueItem> getCarstock() {
            return carstock;
        }

        public void setCarstock(List<KeyValueItem> carstock) {
            this.carstock = carstock;
        }

        public static class KeyValueItem{
            private String Key;
            private String Price;

            public String getKey() {
                return Key;
            }

            public void setKey(String key) {
                Key = key;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String price) {
                Price = price;
            }
        }

    }
}
