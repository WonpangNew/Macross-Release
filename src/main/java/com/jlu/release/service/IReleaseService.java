package com.jlu.release.service;

import com.jlu.release.bean.ReleaseParamsBean;

/**
 * Created by niuwanpeng on 17/4/6.
 */
public interface IReleaseService {

    /**
     * 发布执行
     * @param releaseParams
     * @return
     */
    String doRelease(ReleaseParamsBean releaseParams);
}
