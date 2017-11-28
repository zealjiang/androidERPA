package com.jzg.erp.appraiser.model;

import java.util.List;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/24 22:41
 * @desc:
 */
public class CarPhoto extends com.jzg.erp.base.BaseObject {

    /**
     * Path : /JietongdaImage/2016/08/13/0deaa6b1-3cf0-49b9-8ced-a92f265f7c9c_{0}.jpg
     * PicPath : http://imageup.jingzhengu.com/JietongdaImage/2016/08/13/0deaa6b1-3cf0-49b9-8ced-a92f265f7c9c_902.jpg
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String Path;//PicPath
        private String ViewUrl;//viewUrl
        private String ImgText;
        private int DictID;

        public int getDictID() {
            return DictID;
        }

        public void setDictID(int dictID) {
            DictID = dictID;
        }

        public String getImgText() {
            return ImgText;
        }

        public void setImgText(String imgText) {
            ImgText = imgText;
        }

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }

        public String getViewUrl() {
            return ViewUrl;
        }

        public void setViewUrl(String viewUrl) {
            ViewUrl = viewUrl;
        }
    }
}
