package com.jzg.erp.appraiser.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/26 09:34
 * @desc:
 */
public class Seller extends com.jzg.erp.base.BaseObject {

    /**
     * Name :  吝建彬销售勿动
     * Id : 132
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String Name;
        private int Id;

        public DataBean(String name, int id) {
            Name = name;
            Id = id;
        }

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
