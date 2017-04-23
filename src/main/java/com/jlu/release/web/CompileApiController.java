package com.jlu.release.web;

import com.jlu.release.service.ICompileBuildService;
import com.jlu.release.service.IFTPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * Created by niuwanpeng on 17/4/18.
 * 供jenkins编译完毕后调用
 * 返回编译结束消息
 */
@Controller
@RequestMapping("/compile")
public class CompileApiController {

    @Autowired
    private ICompileBuildService compileBuildService;

    @Autowired
    private IFTPService ftpService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompileApiController.class);

    @RequestMapping(value = "/jenkinsBuild", method = RequestMethod.POST)
    @ResponseBody
    public void dealCompileMessage(@RequestParam("productDir") String productDir,
                                   @RequestParam("productName") String productName,
                                   @RequestParam("compileBuildId") String compileBuildId,
                                   @RequestParam("repoOwner") String repoOwner,
                                   @RequestParam("repo") String repo,
                                   @RequestParam("buildId") String buildId,
                                   @RequestParam("buildNumber") String buildNumber) {
        compileBuildService.dealCompileMessageFromJenkins(productDir, productName, compileBuildId,
                repoOwner, repo, buildId, buildNumber);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public void testUpload() {
        String remoteDir = "cihome/test";
        String remoteFileName = "ss.txt";
        File file = new File("/Users/niuwanpeng/worksapce/ss.txt");
        ftpService.uploadProduct(remoteDir, remoteFileName, file);
    }
}
