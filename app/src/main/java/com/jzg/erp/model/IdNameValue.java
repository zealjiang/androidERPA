package com.jzg.erp.model;

/**
 * Created by zealjiang on 2016/8/3 17:28.
 * Email: zealjiang@126.com
 */
public class IdNameValue {
    private String id;
    private String name;
    private String value;

    public IdNameValue() {
    }

    public IdNameValue(String id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
