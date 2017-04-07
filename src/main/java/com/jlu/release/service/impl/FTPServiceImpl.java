package com.jlu.release.service.impl;

import com.jlu.release.bean.ReleaseParamsBean;
import com.jlu.release.service.IFTPService;
import com.jlu.release.utils.HttpClientUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niuwanpeng on 17/4/6.
 */
public class FTPServiceImpl implements IFTPService{

    private static final Logger LOGGER = LoggerFactory.getLogger(FTPServiceImpl.class);

    @Value("${ftp.download}")
    private String FTP_DOWNLOAD_API;

    @Value("${ftp.upload}")
    private String FTP_UPLOAD_API;

    @Value("${local.directory.download}")
    private String LOCAL_DOC;

    /**
     * 上传产出
     * @param uploadPath [username]/[module]/[1-1-1-1]/[filename]
     * @param localFile
     * @return
     */
    public String uploadProduct(String uploadPath, File localFile) {
        return "";
    }

    /**
     * 下载产出
     * @param releaseParams
     * @param localFile
     * @return
     */
    public String downloadProduct(ReleaseParamsBean releaseParams, File localFile) {
        String url = FTP_DOWNLOAD_API;
        Map<String, String> params = new HashMap<String, String>();
        params.put("remoteDir", releaseParams.getUsername() + "/" + releaseParams.getModule());
        params.put("remoteFileName", releaseParams.getProductPath());
        String downloadHttpUrl = HttpClientUtils.get(url, params);
        URL httpurl = null;
        try {
            httpurl = new URL(downloadHttpUrl);
            File file = new File(LOCAL_DOC + releaseParams.getLocalCatalog() +
                    "/" +releaseParams.getProductPath());
            FileUtils.copyURLToFile(httpurl, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
