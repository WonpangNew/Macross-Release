package com.jlu.release.service;

import com.jlu.release.bean.ReleaseParamsBean;

import java.util.Map;

/**
 * Created by niuwanpeng on 17/4/7.
 *
 * 文件处理
 */
public interface IProductFileService {

    /**
     * 下载文件
     * @param releaseParams
     * @return
     */
    String downloadResource(ReleaseParamsBean releaseParams);

    /**
     * 上传文件
     * @param releaseParams
     * @return
     */
    String uploadResource(ReleaseParamsBean releaseParams);

    /**
     * 删除文件
     * @param releaseParams
     * @return
     */
    boolean deleteResourse(ReleaseParamsBean releaseParams);

    /**
     * 创建临时产出目录
     * @param releaseParams
     * @return
     */
    String createFileCatalog(ReleaseParamsBean releaseParams);
}
