package com.jlu.release.bean;

import org.apache.commons.lang.StringUtils;

/**
 * Created by niuwanpeng on 17/4/6.
 *
 * 请求release接口的参数
 */
public class ReleaseParamsBean {

    private int id;

    private String module;

    private String version;

    private String username;

    private String productPath;

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
        return StringUtils.substringBeforeLast(this.productPath, "/");
    }

    public void setProductPath(String productPath) {
        this.productPath = productPath;
    }

    public String getLocalCatalog() {
        return username + "_" + module + "_" + version;
    }

    public String getUploadCatalog() {
        return username + "/" + module + "/" + version.replaceAll("\\.", "-");
    }

    public String getFileName() {
        return StringUtils.substringAfterLast(this.productPath, "/");
    }

    @Override
    public String toString() {
        return "ReleaseParamsBean{" +
                "id=" + id +
                ", module='" + module + '\'' +
                ", version='" + version + '\'' +
                ", username='" + username + '\'' +
                ", productPath='" + productPath + '\'' +
                '}';
    }
}
