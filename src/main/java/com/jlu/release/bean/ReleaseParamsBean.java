package com.jlu.release.bean;

import org.apache.commons.lang.StringUtils;

/**
 * Created by niuwanpeng on 17/4/6.
 *
 * 请求release接口的参数
 */
public class ReleaseParamsBean {

    private int releaseId;

    private String module;

    private String version;

    private String username;

    private String compileProductPath;

    public ReleaseParamsBean() {

    }

    public ReleaseParamsBean(int releaseId, String module, String version, String username, String compileProductPath) {
        this.releaseId = releaseId;
        this.module = module;
        this.version = version;
        this.username = username;
        this.compileProductPath = compileProductPath;
    }

    public int getReleaseId() {
        return this.releaseId;
    }

    public void setReleaseId(int id) {
        this.releaseId = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductPath() {
        return StringUtils.substringBeforeLast(this.compileProductPath, "/");
    }

    public void setProductPath(String productPath) {
        this.compileProductPath = productPath;
    }

    public String getLocalCatalog() {
        return username + "_" + module + "_" + version;
    }

    public String getUploadCatalog() {
        return username + "/" + module + "/" + version.replaceAll("\\.", "-");
    }

    public String getFileName() {
        return StringUtils.substringAfterLast(this.compileProductPath, "/");
    }

    @Override
    public String toString() {
        return "ReleaseParamsBean{" +
                "ReleaseId=" + releaseId +
                ", module='" + module + '\'' +
                ", version='" + version + '\'' +
                ", username='" + username + '\'' +
                ", compileProductPath='" + compileProductPath + '\'' +
                '}';
    }
}
