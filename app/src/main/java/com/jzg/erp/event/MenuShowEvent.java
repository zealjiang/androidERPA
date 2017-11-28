package com.jzg.erp.event;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/26 18:25
 * @desc:
 */
public class MenuShowEvent {
    private int id;
    private boolean show;
    private String pingguNo;

    public MenuShowEvent(int id, boolean show,String pingguNo) {
        this.id = id;
        this.show = show;
        this.pingguNo = pingguNo;
    }

    public String getPingguNo() {
        return pingguNo;
    }

    public void setPingguNo(String pingguNo) {
        this.pingguNo = pingguNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
