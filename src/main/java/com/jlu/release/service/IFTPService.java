package com.jlu.release.service;

import com.jlu.release.bean.ReleaseParamsBean;

/**
 * Created by niuwanpeng on 17/4/6.
 */
public interface IFTPService {

    /**
     * 上传产出
     * @param releaseParams
     * @return
     */
    String uploadProduct(ReleaseParamsBean releaseParams);

    /**
     * 下载产出
     * @param releaseParams
     * @return
     */
    String downloadProduct(ReleaseParamsBean releaseParams);
}
