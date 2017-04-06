package com.jlu.release.service;

import com.jlu.release.bean.ReleaseParamsBean;
import com.jlu.release.bean.ReleaseResponseBean;

/**
 * Created by niuwanpeng on 17/4/6.
 */
public interface IReleaseService {

    /**
     * 发布执行
     * @param releaseParams
     * @return
     */
    ReleaseResponseBean doRelease(ReleaseParamsBean releaseParams);
}
