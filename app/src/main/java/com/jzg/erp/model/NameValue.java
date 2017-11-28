package com.jzg.erp.model;

/**
 * Created by zealjiang on 2016/8/3 17:28.
 * Email: zealjiang@126.com
 */
public class NameValue {
    private String name;
    private String value;

    public NameValue() {
    }

    public NameValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
