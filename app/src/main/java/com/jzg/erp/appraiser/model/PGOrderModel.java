package com.jzg.erp.appraiser.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zealjiang on 2016/8/23 10:35.
 * Email: zealjiang@126.com
 */
public class PGOrderModel extends com.jzg.erp.base.BaseObject implements Serializable{


    /**
     * PingguNo : A201608140017
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Parcelable {

        private String PingguNo;
        private int ID;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getPingguNo() {
            return PingguNo;
        }

        public void setPingguNo(String PingguNo) {
            this.PingguNo = PingguNo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.PingguNo);
            dest.writeInt(this.ID);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.PingguNo = in.readString();
            this.ID = in.readInt();
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
