package com.jlu.release.service;

/**
 * Created by niuwanpeng on 17/4/19.
 */
public interface ICompileBuildService {

    /**
     * 将编译产出上传到ftp服务器，并回调cihome接口通知编译完毕
     * @param productDir
     * @param productName
     * @param compileBuildId
     * @param repoOwner
     * @param repo
     * @param buildId
     * @param buildNumber
     */
    void dealCompileMessageFromJenkins(String productDir, String productName, String compileBuildId,
                                       String repoOwner, String repo, String buildId, String buildNumber);
}
