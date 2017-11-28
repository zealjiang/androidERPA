package com.jzg.erp.appraiser.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/15 15:01
 * @desc:
 */
public class ParamOption extends com.jzg.erp.base.BaseObject implements Serializable{


    /**
     * appearance : {"dicId":29,"data":[{"Name":"曾喷漆","Id":307}]}
     * damage : {"dicId":30,"data":[{"Name":"有轻微划痕","Id":312}]}
     * preSellDate : {"dicId":31,"data":[{"Name":"随时","Id":315}]}
     * customerLevel : {"dicId":5,"data":[{"Name":"H","Id":50}]}
     * carColor : {"dicId":27,"data":[{"Name":"红","Id":304}]}
     * carLevel : {"dicId":35,"data":[{"Name":"A++","Id":333}]}
     * customerFrom : {"dicId":4,"data":[{"Name":"123","Id":279}]}
     * customerType : {"dicId":7,"data":[{"Name":"私人","Id":58}]}
     * gearBox : {"dicId":26,"data":[{"Name":"MT","Id":298}]}
     * innerColor : {"dicId":27,"data":[{"Name":"红","Id":304}]}
     * innerDecor : {"dicId":33,"data":[{"Name":"内饰完好","Id":327}]}
     * repaireRec : {"dicId":32,"data":[{"Name":"微小问题，完全不影响使用","Id":323}]}
     * workCondition : {"dicId":34,"data":[{"Name":"工况完好","Id":330}]}
     * assessImage : {"dicId":36,"data":[{"Name":"左前45°带号牌","Id":349}]}
     * stores : [{"Name":"测试门店1","Id":89}]
     * carCheckOptions : [{"Name":"左前门","Id":187}]
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
         * dicId : 29
         * data : [{"Name":"曾喷漆","Id":307}]
         */

        private AppearanceBean appearance;
        /**
         * dicId : 30
         * data : [{"Name":"有轻微划痕","Id":312}]
         */

        private DamageBean damage;
        /**
         * dicId : 31
         * data : [{"Name":"随时","Id":315}]
         */

        private PreSellDateBean preSellDate;
        /**
         * dicId : 5
         * data : [{"Name":"H","Id":50}]
         */

        private CustomerLevelBean customerLevel;
        /**
         * dicId : 27
         * data : [{"Name":"红","Id":304}]
         */

        private CarColorBean carColor;
        /**
         * dicId : 35
         * data : [{"Name":"A++","Id":333}]
         */

        private CarLevelBean carLevel;
        /**
         * dicId : 4
         * data : [{"Name":"123","Id":279}]
         */

        private CustomerFromBean customerFrom;
        /**
         * dicId : 7
         * data : [{"Name":"私人","Id":58}]
         */

        private CustomerTypeBean customerType;
        /**
         * dicId : 26
         * data : [{"Name":"MT","Id":298}]
         */

        private GearBoxBean gearBox;
        /**
         * dicId : 27
         * data : [{"Name":"红","Id":304}]
         */

        private InnerColorBean innerColor;
        /**
         * dicId : 33
         * data : [{"Name":"内饰完好","Id":327}]
         */

        private InnerDecorBean innerDecor;
        /**
         * dicId : 32
         * data : [{"Name":"微小问题，完全不影响使用","Id":323}]
         */

        private RepaireRecBean repaireRec;
        /**
         * dicId : 34
         * data : [{"Name":"工况完好","Id":330}]
         */

        private WorkConditionBean workCondition;
        /**
         * dicId : 36
         * data : [{"Name":"左前45°带号牌","Id":349}]
         */

        private AssessImageBean assessImage;
        /**
         * Name : 测试门店1
         * Id : 89
         */

        private List<KeyValueItem> stores;
        /**
         * Name : 左前门
         * Id : 187
         */

        private List<KeyValueItem> carCheckOptions;

        public AppearanceBean getAppearance() {
            return appearance;
        }

        public void setAppearance(AppearanceBean appearance) {
            this.appearance = appearance;
        }

        public DamageBean getDamage() {
            return damage;
        }

        public void setDamage(DamageBean damage) {
            this.damage = damage;
        }

        public PreSellDateBean getPreSellDate() {
            return preSellDate;
        }

        public void setPreSellDate(PreSellDateBean preSellDate) {
            this.preSellDate = preSellDate;
        }

        public CustomerLevelBean getCustomerLevel() {
            return customerLevel;
        }

        public void setCustomerLevel(CustomerLevelBean customerLevel) {
            this.customerLevel = customerLevel;
        }

        public CarColorBean getCarColor() {
            return carColor;
        }

        public void setCarColor(CarColorBean carColor) {
            this.carColor = carColor;
        }

        public CarLevelBean getCarLevel() {
            return carLevel;
        }

        public void setCarLevel(CarLevelBean carLevel) {
            this.carLevel = carLevel;
        }

        public CustomerFromBean getCustomerFrom() {
            return customerFrom;
        }

        public void setCustomerFrom(CustomerFromBean customerFrom) {
            this.customerFrom = customerFrom;
        }

        public CustomerTypeBean getCustomerType() {
            return customerType;
        }

        public void setCustomerType(CustomerTypeBean customerType) {
            this.customerType = customerType;
        }

        public GearBoxBean getGearBox() {
            return gearBox;
        }

        public void setGearBox(GearBoxBean gearBox) {
            this.gearBox = gearBox;
        }

        public InnerColorBean getInnerColor() {
            return innerColor;
        }

        public void setInnerColor(InnerColorBean innerColor) {
            this.innerColor = innerColor;
        }

        public InnerDecorBean getInnerDecor() {
            return innerDecor;
        }

        public void setInnerDecor(InnerDecorBean innerDecor) {
            this.innerDecor = innerDecor;
        }

        public RepaireRecBean getRepaireRec() {
            return repaireRec;
        }

        public void setRepaireRec(RepaireRecBean repaireRec) {
            this.repaireRec = repaireRec;
        }

        public WorkConditionBean getWorkCondition() {
            return workCondition;
        }

        public void setWorkCondition(WorkConditionBean workCondition) {
            this.workCondition = workCondition;
        }

        public AssessImageBean getAssessImage() {
            return assessImage;
        }

        public void setAssessImage(AssessImageBean assessImage) {
            this.assessImage = assessImage;
        }

        public List<KeyValueItem> getStores() {
            return stores;
        }

        public void setStores(List<KeyValueItem> stores) {
            this.stores = stores;
        }

        public List<KeyValueItem> getCarCheckOptions() {
            return carCheckOptions;
        }

        public void setCarCheckOptions(List<KeyValueItem> carCheckOptions) {
            this.carCheckOptions = carCheckOptions;
        }

        public static class AppearanceBean implements Serializable{
            private int dicId;
            /**
             * Name : 曾喷漆
             * Id : 307
             */

            private List<KeyValueItem> data;

            public Map<String,Integer> getAppearanceMap(){
                Map<String,Integer> param= new LinkedHashMap<>();
                if(data!=null && data.size()>0){
                    for(KeyValueItem item:data){
                        param.put(item.getName(),item.getId());
                    }
                }

                return param;
            }

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class DamageBean implements Serializable{
            private int dicId;
            /**
             * Name : 有轻微划痕
             * Id : 312
             */

            private List<KeyValueItem> data;

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class PreSellDateBean implements Serializable{
            private int dicId;
            /**
             * Name : 随时
             * Id : 315
             */

            public int getValueByName(String name){
                return getPreSellDateMap().get(name);
            }

            private List<KeyValueItem> data;

            public Map<String,Integer> getPreSellDateMap(){
                Map<String,Integer> param= new LinkedHashMap<>();
                if(data!=null && data.size()>0){
                    for(KeyValueItem item:data){
                        param.put(item.getName(),item.getId());
                    }
                }
                return param;
            }

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class CustomerLevelBean implements Serializable{
            private int dicId;
            public int getValueByName(String name){
                return getCustomerLevelMap().get(name);
            }

            /**
             * Name : H
             * Id : 50
             */
            private List<KeyValueItem> data;

            public Map<String,Integer> getCustomerLevelMap(){
                Map<String,Integer> param= new LinkedHashMap<>();
                if(data!=null && data.size()>0){
                    for(KeyValueItem item:data){
                        param.put(item.getName(),item.getId());
                    }
                }
                return param;
            }

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class CarColorBean implements Serializable{
            private int dicId;
            public int getValueByName(String name){
                int result = 0;
                for(KeyValueItem item:data){
                    if(name.equals(item.getName())){
                        result = item.getId();
                        break;
                    }
                }
                return result;
            }
            private List<KeyValueItem> data;

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class CarLevelBean implements Serializable{
            private int dicId;
            /**
             * Name : A++
             * Id : 333
             */

            private List<KeyValueItem> data;

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class CustomerFromBean implements Serializable{
            private int dicId;
            private List<KeyValueItem> data;

            public int getValueByName(String name){
                return getCustomerFromMap().get(name);
            }

            public Map<String,Integer> getCustomerFromMap(){
                Map<String,Integer> param= new LinkedHashMap<>();
                if(data!=null && data.size()>0){
                    for(KeyValueItem item:data){
                        param.put(item.getName(),item.getId());
                    }
                }
                return param;
            }

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class CustomerTypeBean implements Serializable{
            private int dicId;
            /**
             * Name : 私人
             * Id : 58
             */

            private List<KeyValueItem> data;

            public int getValueByName(String name){
                return getCustomerTypeMap().get(name);
            }

            public Map<String,Integer> getCustomerTypeMap(){
                Map<String,Integer> param= new LinkedHashMap<>();
                if(data!=null && data.size()>0){
                    for(KeyValueItem item:data){
                        param.put(item.getName(),item.getId());
                    }
                }
                return param;
            }

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class GearBoxBean implements Serializable{
            private int dicId;
            private List<KeyValueItem> data;

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class InnerColorBean implements Serializable{
            private int dicId;
            /**
             * Name : 红
             * Id : 304
             */

            private List<KeyValueItem> data;

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class InnerDecorBean implements Serializable{
            private int dicId;
            /**
             * Name : 内饰完好
             * Id : 327
             */

            private List<KeyValueItem> data;

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class RepaireRecBean implements Serializable{
            private int dicId;
            /**
             * Name : 微小问题，完全不影响使用
             * Id : 323
             */

            private List<KeyValueItem> data;

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class WorkConditionBean implements Serializable{
            private int dicId;
            /**
             * Name : 工况完好
             * Id : 330
             */

            private List<KeyValueItem> data;

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }

        public static class AssessImageBean implements Serializable{
            private int dicId;
            /**
             * Name : 左前45°带号牌
             * Id : 349
             */

            private List<KeyValueItem> data;

            public int getDicId() {
                return dicId;
            }

            public void setDicId(int dicId) {
                this.dicId = dicId;
            }

            public List<KeyValueItem> getData() {
                return data;
            }

            public void setData(List<KeyValueItem> data) {
                this.data = data;
            }

        }


        public static class KeyValueItem implements Serializable{
            private String Name;
            private int Id;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }
        }


    }
}
