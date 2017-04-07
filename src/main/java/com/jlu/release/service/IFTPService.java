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
     * @param localFile 本地文件，也是待上传的文件
     * @return
     */
    String uploadProduct(String uploadPath, File localFile);

    /**
     * 下载产出
     * @param releaseParams
     * @param localFile
     * @return
     */
    String downloadProduct(ReleaseParamsBean releaseParams, File localFile);
}
