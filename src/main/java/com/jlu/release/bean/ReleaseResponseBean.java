package com.jlu.release.bean;

/**
 * Created by niuwanpeng on 17/4/6.
 *
 * 发布后回传信息bean
 */
public class ReleaseResponseBean {

    private int id;

    private String module;

    private String releaseProductPath;

    private ReleaseStatus releaseStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getReleaseProductPath() {
        return releaseProductPath;
    }

    public void setReleaseProductPath(String releaseProductPath) {
        this.releaseProductPath = releaseProductPath;
    }

    public ReleaseStatus getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(ReleaseStatus releaseStatus) {
        this.releaseStatus = releaseStatus;
    }
}
