package com.jlu.release.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jlu.release.bean.ReleaseParamsBean;
import com.jlu.release.bean.ReleaseResponseBean;
import com.jlu.release.bean.ReleaseStatus;
import com.jlu.release.service.IProductFileService;
import com.jlu.release.service.IReleaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by niuwanpeng on 17/4/6.
 */
@Service
public class ReleaseServiceImpl implements IReleaseService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ReleaseServiceImpl.class);
    private static Gson GSON = new Gson();

    @Autowired
    private IProductFileService productFileService;

    /**
     * 发布执行
     * 创建临时目录->下载文件到本地->上传文件到产品库->删除本地临时文件
     * @param releaseParams
     * @return
     */
    public String doRelease(ReleaseParamsBean releaseParams) {
        ReleaseResponseBean releaseResponse = new ReleaseResponseBean();
        releaseResponse.setId(releaseParams.getId());
        releaseResponse.setModule(releaseParams.getModule());
        releaseResponse.setReleaseStatus(ReleaseStatus.FAIL);
        LOGGER.info("Starting create catalog, releaseParams:[]", releaseParams.toString());
        productFileService.createFileCatalog(releaseParams);
        LOGGER.info("Starting download Resource, releaseParams:[]", releaseParams.getId());
        productFileService.downloadResource(releaseParams);
        LOGGER.info("Starting upload Resource, releaseParams:[]", releaseParams.getId());
        String uploadResult = productFileService.uploadResource(releaseParams);
        if (uploadResult != null) {
            Map<String, String> returnContent = GSON.fromJson(uploadResult, new TypeToken<Map<String, String>>() {}.getType());
            if (returnContent.get("STATUS") != null && returnContent.get("STATUS").equals("SUCCESS")) {
                releaseResponse.setReleaseStatus(ReleaseStatus.SUCCESS);
                releaseResponse.setReleaseProductPath(returnContent.get("DOWN_URL"));
            } else {
                releaseResponse.setErrMsg(returnContent.get("ERR_MSG"));
            }
        } else {
            releaseResponse.setErrMsg("请求失败！");
        }
        LOGGER.info("Starting delete catalog, releaseParams:[]", releaseParams.getId());
        productFileService.deleteResourse(releaseParams);
        LOGGER.info("release successful! releaseParams:[]", releaseParams.getId());
        return new Gson().toJson(releaseResponse);
    }
}
