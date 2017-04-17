package com.jlu.release.web;

import com.jlu.release.bean.ReleaseParamsBean;
import com.jlu.release.service.IReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by niuwanpeng on 17/4/6.
 */
@Controller
@RequestMapping("/release")
public class ReleaseController {

    @Autowired
    private IReleaseService releaseService;

    @RequestMapping("/releaseByGet")
    @ResponseBody
    public String releaseByGet() {
        return "release";
    }

    @RequestMapping(value = "/releaseByPost", method = RequestMethod.POST)
    @ResponseBody
    public Strng releaseByPost(@RequestParam("releaseParams") ReleaseParamsBean releaseParams) {
        return releaseService.doRelease(releaseParams);
    }
}
