package com.jlu.release.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jlu.release.bean.ReleaseParamsBean;
import com.jlu.release.bean.ReleaseResponseBean;
import com.jlu.release.bean.ReleaseStatus;
import com.jlu.release.service.IProductFileService;
import com.jlu.release.service.IReleaseService;
import com.jlu.release.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niuwanpeng on 17/4/6.
 */
@Service
public class ReleaseServiceImpl implements IReleaseService{

    @Autowired
    private IProductFileService productFileService;

    @Value("${cihome.callback.release}")
    private String CIHOME_CALLBACK_RELEASE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReleaseServiceImpl.class);

    private static Gson GSON = new Gson();

    /**
     * 发布执行
     * @param releaseParams
     * @return
     */
    public String doRelease(ReleaseParamsBean releaseParams) {
        final ReleaseResponseBean releaseResponse = new ReleaseResponseBean();
        final ReleaseParamsBean releaseParamsBean = releaseParams;
        releaseResponse.setReleaseId(releaseParams.getReleaseId());
        releaseResponse.setModule(releaseParams.getModule());
        releaseResponse.setReleaseStatus(ReleaseStatus.RUNNING);
        releaseResponse.setReleaseProductPath("");
        LOGGER.info("Starting upload Resource, releaseParams:[]", releaseParams.getReleaseId());
        // FIXME: 17/4/20 使用线程作为异步调用
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (Exception e) {
                    // sleep error
                }
                String uploadResult = productFileService.copyResourceToRelease(releaseParamsBean);
                if (uploadResult != null) {
                    Map<String, String> returnContent = GSON.fromJson(uploadResult, new TypeToken<Map<String, String>>() {}.getType());
                    if (returnContent.get("STATUS") != null && returnContent.get("STATUS").equals("SUCCESS")) {
                        releaseResponse.setReleaseStatus(ReleaseStatus.SUCCESS);
                        releaseResponse.setReleaseProductPath(returnContent.get("DOWN_URL"));
                        LOGGER.info("release successful! releaseParams:{}", releaseParamsBean.getReleaseId());
                    } else {
                        releaseResponse.setReleaseStatus(ReleaseStatus.FAIL);
                        releaseResponse.setErrMsg(returnContent.get("ERR_MSG"));
                        LOGGER.error("release fail!releaseParams:{}", releaseParamsBean.toString());
                    }
                } else {
                    releaseResponse.setReleaseStatus(ReleaseStatus.FAIL);
                    releaseResponse.setErrMsg("请求失败！");
                    LOGGER.error("Request ftp api fail! releaseParams:{}", releaseParamsBean.toString());
                }
                String json = GSON.toJson(releaseResponse);
                Map<String, String> params = GSON.fromJson(json, new TypeToken<Map<String, String>>(){}.getType());
                // 回调，通知发布结束
                HttpClientUtils.get(CIHOME_CALLBACK_RELEASE, params);
            }
        }).start();
        return new Gson().toJson(releaseResponse);
    }
}
