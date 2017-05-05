package com.jlu.release.service.impl;

import com.google.gson.Gson;
import com.jlu.release.bean.CompileResult;
import com.jlu.release.service.ICompileBuildService;
import com.jlu.release.service.IFTPService;
import com.jlu.release.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niuwanpeng on 17/4/19.
 */
@Service
public class CompileBuildServiceImpl implements ICompileBuildService {

    @Autowired
    private IFTPService ftpService;

    @Value("${cihome.receive.compile}")
    private String CIHOME_RECEIVE_BUILD_URL;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompileBuildServiceImpl.class);

    private static final Gson GSON = new Gson();

    /**
     * 将编译产出上传到ftp服务器，并回调cihome接口通知编译完毕
     * @param productDir
     * @param productName
     * @param compileBuildId
     * @param repoOwner
     * @param repo
     * @param buildId
     */
    public void dealCompileMessageFromJenkins(String productDir, String productName, String compileBuildId,
                                              String repoOwner, String repo, String buildId, String buildNumber) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put(CompileResult.BUILD_STATUS, "FAIL");
        try {
            File productFile = new File(productDir + "/" + productName);
            StringBuilder uploadDir = new StringBuilder(repoOwner);
            uploadDir.append("/").append(repo).append("/").append(buildId);

            String result = ftpService.uploadProduct(uploadDir.toString(), productName, productFile);
            if (result.equals("OK")) {
                params.put(CompileResult.BUILD_STATUS, "SUCC");
                Map<String, String> productPath = new HashMap<String, String>();
                productPath.put("remoteDir", uploadDir.toString());
                productPath.put("remoteFileName", productName);
                params.put(CompileResult.PRODUCT_PATH, GSON.toJson(productPath));
                params.put(CompileResult.ERR_MSG, "Compiling successful!");
                LOGGER.info("Product upload successful! compileBuildId:{}, params:{}", compileBuildId, params.toString());
            } else {
                params.put(CompileResult.PRODUCT_PATH, "");
                params.put(CompileResult.ERR_MSG, "Compiling successful but uploading product is fail!");
                LOGGER.info("Product upload fail! compileBuildId:{}, params:{}", compileBuildId, params.toString());
            }
        } catch (Exception e) {
            LOGGER.error("The compile-product isn't exist! Compiling failed!repoOwner:{}, repo:{}, compileBuildId:{}",
                    repoOwner, repo, compileBuildId);
            params.put(CompileResult.ERR_MSG, "The compile-product isn't exist! Compiling failed!");
            params.put(CompileResult.PRODUCT_PATH, "");
        } finally {
            params.put(CompileResult.COMPILE_BUILD_ID, compileBuildId);
            params.put(CompileResult.JENKINS_BUILD_ID, buildId);
            params.put(CompileResult.BUILD_NUMBER, buildNumber);
            // FIXME: 17/4/20 使用线程作为异步调用
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000L); // FIXME: 调用接口前防止jenkins未构建完成
                    } catch (Exception e) {
                        // sleep error
                    }
                    HttpClientUtils.get(CIHOME_RECEIVE_BUILD_URL, params);
                }
            }).start();
        }
    }
}
