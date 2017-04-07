package com.jlu.release.service.impl;

import com.google.gson.Gson;
import com.jlu.release.bean.ReleaseParamsBean;
import com.jlu.release.bean.ReleaseResponseBean;
import com.jlu.release.service.IProductFileService;
import com.jlu.release.service.IReleaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by niuwanpeng on 17/4/6.
 */
public class ReleaseServiceImpl implements IReleaseService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ReleaseServiceImpl.class);

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
        productFileService.createFileCatalog(releaseParams);
        productFileService.downloadResource(releaseParams);
        productFileService.uploadResource(releaseParams);
        productFileService.deleteResourse(releaseParams);
        // TODO: 17/4/7 期间各种逻辑处理
        return new Gson().toJson(releaseResponse);
    }
}
