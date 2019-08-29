package com.lxx.nettyclientdemo.entity;

/**
 * Created by luox on 2018/7/30.
 */
public class MyMessage {

    private final String value;

    public MyMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
