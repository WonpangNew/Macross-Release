package com.jlu.release.service;

import com.jlu.release.bean.ReleaseParamsBean;

import java.io.File;

/**
 * Created by niuwanpeng on 17/4/6.
 */
public interface IFTPService {

    /**
     * 上传产出
     * @param uploadPath [username]/[module]/[1-1-1-1]
     * @param filename 文件名
     * @param localFile 本地文件，也是待上传的文件
     * @return
     */
    String uploadProduct(String uploadPath, String filename, File localFile);

    /**
     * 下载产出
     * @param releaseParams
     * @param localFile
     * @return
     */
    String downloadProduct(ReleaseParamsBean releaseParams, File localFile);

    /**
     * 复制编译产出到release产品库下
     * @param sourceDir
     * @param targetDir
     * @param sourceFileName
     * @return
     */
    String copyResourceToRelease(String sourceDir, String targetDir, String sourceFileName);
}
