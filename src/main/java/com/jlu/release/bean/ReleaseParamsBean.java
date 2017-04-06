package com.jlu.release.bean;

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
        return productPath;
    }

    public void setProductPath(String productPath) {
        this.productPath = productPath;
    }
}
