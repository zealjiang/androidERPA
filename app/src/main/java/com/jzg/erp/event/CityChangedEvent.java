package com.jzg.erp.event;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/23 17:51
 * @desc:
 */
public class CityChangedEvent {
    private int type;
    private String cityId;

    public CityChangedEvent(int type, String cityId) {
        this.type = type;
        this.cityId = cityId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
