package com.jlu.release.service.impl;

import com.jlu.release.bean.ReleaseParamsBean;
import com.jlu.release.service.IFTPService;
import com.jlu.release.service.IProductFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by niuwanpeng on 17/4/7.
 *
 * 文件处理
 */
@Service
public class ProductFileServiceImpl implements IProductFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductFileServiceImpl.class);

    @Value("{local.directory.download}")
    private String LOCAL_CATALOG;

    @Autowired
    private IFTPService ftpService;

    /**
     * 下载文件到本地临时目录
     * 临时目录结构: xxx/{username}/{module}/{productPath}
     * @param releaseParams
     * @return
     */
    public String downloadResource(ReleaseParamsBean releaseParams) {
        String tempCatalog = LOCAL_CATALOG + releaseParams.getLocalCatalog();
        LOGGER.info("Download file:{} starting....", tempCatalog);
        File file = new File(tempCatalog);
        String result = ftpService.downloadProduct(releaseParams, file);
        LOGGER.info("Download file:{} end...", tempCatalog);
        return result;
    }

    /**
     * 上传文件
     * @param releaseParams
     * @return
     */
    public String uploadResource(ReleaseParamsBean releaseParams) {
        String uploadCatalog = releaseParams.getUploadCatalog();
        String localFilePath = LOCAL_CATALOG + releaseParams.getLocalCatalog() + "/" + releaseParams.getProductPath();
        File localFile = new File(localFilePath);
        String result = ftpService.uploadProduct(uploadCatalog, releaseParams.getProductPath(), localFile);
        return result;
    }

    /**
     * 删除文件
     * @param releaseParams
     */
    @Override
    public boolean deleteResourse(ReleaseParamsBean releaseParams) {
        String deleteDir = LOCAL_CATALOG + releaseParams.getLocalCatalog();
        File file = new File(deleteDir);
        if (file.exists() && file.isDirectory()) {
            if (file.listFiles().length == 0) {
                file.delete();
            } else {
                File[] deleteFiles = file.listFiles();
                for (File fileChild : deleteFiles) {
                    fileChild.delete();
                }
                file.delete();
            }
            LOGGER.info("Directory:{} has been deleted!" , deleteDir);
            return true;
        } else {
            LOGGER.error("Directory:{} is not exist!", deleteDir);
            return false;
        }
    }

    /**
     * 创建临时产出存放目录
     * @param releaseParams
     * @return
     */
    @Override
    public String createFileCatalog(ReleaseParamsBean releaseParams) {
        String tempCatalog = LOCAL_CATALOG + releaseParams.getLocalCatalog();
        File tempDir = new File(tempCatalog);
        if (tempDir.exists()) {
            LOGGER.info("Catalog:{} has existed when Creating directory!", tempCatalog);
            return "该模块其他构建正在进行发布，请更换版本号重新发布！";
        }
        if (tempDir.mkdirs()) {
            LOGGER.info("Create catalog:{} is successful!", tempCatalog);
            return "SUCC";
        } else {
            LOGGER.error("Create catalog:{} is fail!", tempCatalog);
            return "创建临时产出目录失败，请稍后重试";
        }
    }
}
