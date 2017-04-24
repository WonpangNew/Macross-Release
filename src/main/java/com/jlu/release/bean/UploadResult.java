package com.jlu.release.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by niuwanpeng on 17/4/24.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadResult {

    private String STATUS;

    private String ERR_MS;

    private String DOWN_URL;

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getERR_MS() {
        return ERR_MS;
    }

    public void setERR_MS(String ERR_MS) {
        this.ERR_MS = ERR_MS;
    }

    public String getDOWN_URL() {
        return DOWN_URL;
    }

    public void setDOWN_URL(String DOWN_URL) {
        this.DOWN_URL = DOWN_URL;
    }
}
