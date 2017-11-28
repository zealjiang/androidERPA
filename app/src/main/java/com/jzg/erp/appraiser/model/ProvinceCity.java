package com.jzg.erp.appraiser.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/23 10:26
 * @desc:
 */
public class ProvinceCity extends com.jzg.erp.base.BaseObject{

    /**
     * data : [{"CityId":1201,"CityName":"武汉"},{"CityId":1202,"CityName":"十堰"},{"CityId":1203,"CityName":"襄阳"},{"CityId":1204,"CityName":"随州"},{"CityId":1207,"CityName":"宜昌"},{"CityId":1208,"CityName":"黄石"},{"CityId":1209,"CityName":"荆门"},{"CityId":1210,"CityName":"荆州"},{"CityId":1216,"CityName":"鄂州"},{"CityId":1217,"CityName":"咸宁"},{"CityId":1229,"CityName":"孝感"},{"CityId":1236,"CityName":"黄冈"},{"CityId":422800,"CityName":"恩施"},{"CityId":429000,"CityName":"湖北直辖"}]
     * Message : ok
     * Status : 1
     */
    /**
     * CityId : 1201
     * CityName : 武汉
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        private int CityId;
        private String CityName;

        public int getCityId() {
            return CityId;
        }

        public void setCityId(int CityId) {
            this.CityId = CityId;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.CityId);
            dest.writeString(this.CityName);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.CityId = in.readInt();
            this.CityName = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
