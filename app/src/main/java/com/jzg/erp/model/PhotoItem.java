package com.jzg.erp.model;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/10 11:03
 * @desc:
 */
public class PhotoItem {
    private String PicPath;//图片url，http开头是网络图片，res:开头是占位图，/开头是本地文件
    private String desc;//描述
    private int id;

    public PhotoItem(String picPath, String desc, int id) {
        PicPath = picPath;
        this.desc = desc;
        this.id = id;
    }

    public String getPicPath() {
        return PicPath;
    }

    public void setPicPath(String picPath) {
        PicPath = picPath;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
