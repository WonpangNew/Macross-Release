package com.jlu.release.service.impl;

import com.jlu.release.bean.ReleaseParamsBean;
import com.jlu.release.service.IFTPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by niuwanpeng on 17/4/6.
 */
public class FTPServiceImpl implements IFTPService{

    private static final Logger LOGGER = LoggerFactory.getLogger(FTPServiceImpl.class);

    /**
     * 上传产出
     * @param releaseParams
     * @return
     */
    public String uploadProduct(ReleaseParamsBean releaseParams) {
        return "";
    }

    /**
     * 下载产出
     * @param releaseParams
     * @return
     */
    public String downloadProduct(ReleaseParamsBean releaseParams) {
        return "";
    }
}
