package com.jlu.release.bean;

/**
 * Created by niuwanpeng on 17/4/6.
 */
public enum ReleaseStatus {
    SUCCESS("SUCCESS"), FAIL("FAIL");

    private String value;

    private ReleaseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
