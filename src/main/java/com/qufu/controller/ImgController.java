package com.qufu.controller;


import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.Img;
import com.qufu.pojo.Release;
import com.qufu.service.ImgService;
import com.qufu.service.ReleaseService;
import com.qufu.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/img")//拦截路径
public class ImgController {
    private static Logger log = LoggerFactory.getLogger(ImgController.class);

    @Autowired
    ImgService service;
    @Autowired
    ReleaseService releaseService;


    /**
     * 添加缩略图
     *
     * @return
     */
    @PostMapping("/insert")
    public JsonResult InsertImg(HttpServletRequest request) {
        log.info("----insertImg----执行");
        JsonResult jsonResult = new JsonResult();
        String rname = request.getParameter("name");
        Release release = new Release();
        release.setRname(rname);
        Integer rid = null;
        try {
            release = releaseService.SelcetRidRname(release);

        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        log.info("----insertImg----读取");
        String img1 = request.getParameter("img1");
        String img2 = request.getParameter("img2");
        String img3 = request.getParameter("img3");
        String img4 = request.getParameter("img4");
        log.info("----insertImg----img");
        Img img = new Img(rid, img1, img2, img3, img4);
        try {
            int i = service.ImgInsert(img);
            if (i != 1) {
                jsonResult.setCode("401");
                jsonResult.setMsg("添加失败！");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

}
