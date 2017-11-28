package com.jzg.erp.appraiser.model;

/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/8/23 21:44
 * @desc:
 */
public class UploadResult extends com.jzg.erp.base.BaseObject {

    /**
     * submitVal : /JietongdaImage/2016/08/22/875dc028-d70d-40c0-8381-fd7615e29889_{0}.jpg
     * viewUrl : http://imageup.jingzhengu.com/JietongdaImage/2016/08/22/875dc028-d70d-40c0-8381-fd7615e29889_901.jpg
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String submitVal;
        private String viewUrl;

        public String getSubmitVal() {
            return submitVal;
        }

        public void setSubmitVal(String submitVal) {
            this.submitVal = submitVal;
        }

        public String getViewUrl() {
            return viewUrl;
        }

        public void setViewUrl(String viewUrl) {
            this.viewUrl = viewUrl;
        }
    }
}
