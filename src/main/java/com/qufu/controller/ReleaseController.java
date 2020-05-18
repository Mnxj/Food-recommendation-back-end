package com.qufu.controller;


import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.Release;
import com.qufu.pojo.VisitCount;
import com.qufu.service.ReleaseService;
import com.qufu.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/release")
public class ReleaseController {
    private static Logger log = LoggerFactory.getLogger(ImgController.class);
    @Autowired
    ReleaseService releaseService;

    /**
     * 查询店铺
     * 返回经纬度
     * 1、默认为1000米
     *
     * @param request
     * @return
     */
    @PostMapping("/selectRelease")
    public JsonResult SelcetRelease(HttpServletRequest request) {
        log.info("SelcetRelease");
        JsonResult jsonResult = new JsonResult();
        String[] params = {"rlong", "rlat", "m"};
        try {
            V.valid(request, params);
            double rlong = Double.parseDouble(request.getParameter("rlong"));
            double rlat = Double.parseDouble(request.getParameter("rlat"));
            Integer m1 = Integer.valueOf(request.getParameter("m"));
            String rtype = request.getParameter("rtype");
            List<Release> list = releaseService.SelcetRelease(rlong, rlat, m1, rtype);
            if (list.size() != 0) {
                jsonResult.setData(list);
                jsonResult.setMsg("成功");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 查询距离
     *
     * @param request
     * @return
     */
    @PostMapping("/SelcetReleaseCont")
    public JsonResult SelcetReleaseCont(HttpServletRequest request) {
        log.info("SelcetReleaseCont");
        JsonResult jsonResult = new JsonResult();
        String[] params = {"rlong", "rlat", "m"};
        try {
            V.valid(request, params);
            double rlong = Double.parseDouble(request.getParameter("rlong"));
            double rlat = Double.parseDouble(request.getParameter("rlat"));
            Integer m1 = Integer.valueOf(request.getParameter("m"));
            String rtype = request.getParameter("rtype");
            Integer count = releaseService.SelcetReleaseCont(rlong, rlat, m1, rtype);
            if (count != null) {
                jsonResult.setData(count);
                jsonResult.setMsg("成功");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 添加类型
     *
     * @param request
     * @return
     */
    @PostMapping("/insert")
    public JsonResult InsertRelease(HttpServletRequest request) {
        log.info("InsertRelease执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"rname", "rtype", "rurl", "radname", "radcode", "rtel", "rtag", "rlong", "rlat"};
        try {
            V.valid(request, parms);
            Release release = V.entity(request, Release.class, parms);
            int i = releaseService.InsertRelease(release);
            if (i == 1) {
                jsonResult.setData("成功");
            } else {
                jsonResult.setMsg(E.YZM_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 更具名字查询是否重名
     *
     * @param request
     * @return
     */
    @GetMapping("/rname")
    public JsonResult Rname(HttpServletRequest request) {
        String rname = request.getParameter("name");
        JsonResult jsonResult = new JsonResult();
        Release release = new Release();
        release.setRname(rname);
        try {
            release = releaseService.SelcetRidRname(release);
            if (release != null) {
                jsonResult.setCode(401 + "");
                jsonResult.setMsg("重复");
            } else {
                jsonResult.setMsg("");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }

        return jsonResult;
    }

    /**
     *
     */
    @GetMapping("/rlongrlat")
    public JsonResult RlongRlat() {
        JsonResult jsonResult = new JsonResult();
        //查询
        List<Release> list = releaseService.SelcetRl();
        for (Release release : list) {
            String[] s = release.getRlocation().split(",");
            release.setRlong(s[0]);
            release.setRlat(s[1]);
            System.out.println(release);
            releaseService.Update(release);
        }
        return jsonResult;
    }
}
