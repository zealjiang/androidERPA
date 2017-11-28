package com.jzg.erp.event;

/**
 * Created by zealjiang on 2016/8/28 13:41.
 * Email: zealjiang@126.com
 */
public class PriceInfoActivityEvent {
    boolean isFinish = true;

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
