package com.jlu.release.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jlu.release.bean.ReleaseParamsBean;
import com.jlu.release.service.IFTPService;
import com.jlu.release.utils.HttpClientUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niuwanpeng on 17/4/6.
 */
@Service
public class FTPServiceImpl implements IFTPService{

    private static final Logger LOGGER = LoggerFactory.getLogger(FTPServiceImpl.class);

    private static Gson GSON = new Gson();

    @Value("${ftp.download}")
    private String FTP_DOWNLOAD_API;

    @Value("${ftp.upload}")
    private String FTP_UPLOAD_API;

    @Value("${local.directory.download}")
    private String LOCAL_DOC;

    @Value("${ftp.copy}")
    private String FTP_COPY_API;

    /**
     * 上传产出
     * @param uploadPath [username]/[module]/[1-1-1-1]/[filename]
     * @param filename
     * @param localFile
     * @return
     */
    public String uploadProduct(String uploadPath, String filename, File localFile) {
        String url = FTP_UPLOAD_API;
        Map<String, Object> params = new HashedMap();
        params.put("remoteDir", uploadPath);
        params.put("remoteFileName", filename);
        params.put("file", localFile);
        String result = HttpClientUtils.postUploadFile(url, params);
        return result;
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
            if (downloadHttpUrl == null || downloadHttpUrl.isEmpty()) {
                return "FAIL";
            }
            httpurl = new URL(downloadHttpUrl);
            File file = new File(LOCAL_DOC + releaseParams.getLocalCatalog() +
                    "/" +releaseParams.getProductPath());
            FileUtils.copyURLToFile(httpurl, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "SUCC";
    }

    /**
     * 复制编译产出到release产品库下
     * @param sourceDir
     * @param targetDir
     * @param sourceFileName
     * @return
     */
    public String copyResourceToRelease(String sourceDir, String targetDir, String sourceFileName) {
        String url = FTP_COPY_API;
        Map<String, String> params = new HashMap<String, String>();
        params.put("sourceDir", sourceDir);
        params.put("targetDir", targetDir);
        params.put("sourceFileName", sourceFileName);
        String result = HttpClientUtils.get(url, params);
        return result;
    }
}
