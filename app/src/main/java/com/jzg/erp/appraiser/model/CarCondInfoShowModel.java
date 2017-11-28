package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * 车况展示类
 * Created by zealjiang on 2016/8/24 16:09.
 * Email: zealjiang@126.com
 */
public class CarCondInfoShowModel extends com.jzg.erp.base.BaseObject{

    /**
     * ParentId : 0
     * CarGrade :
     * ParentItemName : 外观损伤检测
     * ItemName : 左前门左后门左前翼子板左后翼子板右前门右后门右前翼子板右后翼子板前保险杠后保险杠发动机盖后备箱盖车厢顶棚左前轮左后轮右前轮右后轮
     * ReMark : null
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private int ParentId;
        private String CarGrade;
        private String ParentItemName;
        private String ItemName;
        private String ReMark;
        private String Result;

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public String getCarGrade() {
            return CarGrade;
        }

        public void setCarGrade(String CarGrade) {
            this.CarGrade = CarGrade;
        }

        public String getParentItemName() {
            return ParentItemName;
        }

        public void setParentItemName(String ParentItemName) {
            this.ParentItemName = ParentItemName;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public String getReMark() {
            return ReMark;
        }

        public void setReMark(String ReMark) {
            this.ReMark = ReMark;
        }

        public String getResult() {
            return Result;
        }

        public void setResult(String result) {
            Result = result;
        }
    }
}
