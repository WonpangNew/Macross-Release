package com.jlu.release.service.impl;

import com.jlu.release.bean.ReleaseParamsBean;
import com.jlu.release.bean.ReleaseResponseBean;
import com.jlu.release.service.IFTPService;
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
    private IFTPService FTPService;

    /**
     * 发布执行
     * @param releaseParams
     * @return
     */
    public ReleaseResponseBean doRelease(ReleaseParamsBean releaseParams) {
        return new ReleaseResponseBean();
    }
}
