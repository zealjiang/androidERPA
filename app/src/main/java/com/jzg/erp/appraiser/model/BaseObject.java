package com.jzg.erp.appraiser.model;

import java.io.Serializable;

/**
 * Created by zealjiang on 2016/8/5 09:52.
 * Email: zealjiang@126.com
 */
public class BaseObject implements Serializable {

    /**
     * success : true
     * status : 100
     * msg : 成功
     */

    private boolean success;
    private int status;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
