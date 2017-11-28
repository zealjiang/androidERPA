package com.jzg.erp.appraiser.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 所有车况检测的选项数据
 * Created by zealjiang on 2016/8/25 17:06.
 * Email: zealjiang@126.com
 */
public class CarCondOptionsModel extends com.jzg.erp.base.BaseObject implements Serializable{

    /**
     * ygssPic : http://192.168.0.140:9001/Images/ygssPic.jpg
     * gjssPic : http://192.168.0.140:9001/Images/gjssPic.jpg
     * zylbjPic : http://192.168.0.140:9001/Images/zylbjPic.jpg
     */
    private String ygssPic;//外观损伤
    private String gjssPic;//骨架损伤
    private String zylbjPic;//主要零部件维修记录

    public String getYgssPic() {
        return ygssPic;
    }

    public void setYgssPic(String ygssPic) {
        this.ygssPic = ygssPic;
    }

    public String getGjssPic() {
        return gjssPic;
    }

    public void setGjssPic(String gjssPic) {
        this.gjssPic = gjssPic;
    }

    public String getZylbjPic() {
        return zylbjPic;
    }

    public void setZylbjPic(String zylbjPic) {
        this.zylbjPic = zylbjPic;
    }

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * Id : 187
         * Name : 左前门
         * DicId : 29
         * option : [{"Id":307,"Name":"曾喷漆","DicId":29}]
         */

        private List<OptionBean> key182;
        /**
         * Id : 204
         * Name : 前机盖
         * DicId : 29
         * option : [{"Id":307,"Name":"曾喷漆","DicId":29}]
         */

        private List<OptionBean> key183;
        /**
         * Id : 225
         * Name : 发动机
         * DicId : 32
         * option : [{"Id":323,"Name":"微小问题，完全不影响使用","DicId":32}]
         */

        private List<OptionBean> key184;
        /**
         * Id : 227
         * Name : 内饰
         * DicId : 33
         * option : [{"Id":327,"Name":"内饰完好","DicId":33}]
         */

        private List<OptionBean> key185;
        /**
         * Id : 228
         * Name : 工况
         * DicId : 34
         * option : [{"Id":330,"Name":"工况完好","DicId":34}]
         */

        private List<OptionBean> key186;
        /**
         * Id : 231
         * Name : 车况等级
         * DicId : 35
         * option : [{"Id":333,"Name":"A++","DicId":35}]
         */

        private List<OptionBean> key229;
        /**
         * Id : 232
         * Name : 车况等级介绍
         * DicId : 0
         * option : []
         */

        private List<OptionBean> key230;

        public List<OptionBean> getKey182() {
            return key182;
        }

        public void setKey182(List<OptionBean> key182) {
            this.key182 = key182;
        }

        public List<OptionBean> getKey183() {
            return key183;
        }

        public void setKey183(List<OptionBean> key183) {
            this.key183 = key183;
        }

        public List<OptionBean> getKey184() {
            return key184;
        }

        public void setKey184(List<OptionBean> key184) {
            this.key184 = key184;
        }

        public List<OptionBean> getKey185() {
            return key185;
        }

        public void setKey185(List<OptionBean> key185) {
            this.key185 = key185;
        }

        public List<OptionBean> getKey186() {
            return key186;
        }

        public void setKey186(List<OptionBean> key186) {
            this.key186 = key186;
        }

        public List<OptionBean> getKey229() {
            return key229;
        }

        public void setKey229(List<OptionBean> key229) {
            this.key229 = key229;
        }

        public List<OptionBean> getKey230() {
            return key230;
        }

        public void setKey230(List<OptionBean> key230) {
            this.key230 = key230;
        }

        public static class OptionBean implements Serializable{
            private int Id;
            private String Name;
            private int DicId;
            private List<ItemBean> option;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getDicId() {
                return DicId;
            }

            public void setDicId(int DicId) {
                this.DicId = DicId;
            }

            public List<ItemBean> getOption() {
                return option;
            }

            public void setOption(List<ItemBean> option) {
                this.option = option;
            }
        }


        public static class ItemBean implements Serializable{
            private int Id;
            private String Name;
            private int DicId;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getDicId() {
                return DicId;
            }

            public void setDicId(int DicId) {
                this.DicId = DicId;
            }
        }
    }
}
