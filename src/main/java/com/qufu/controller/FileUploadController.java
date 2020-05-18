package com.qufu.controller;


import com.qufu.service.FileUploadService;
import com.qufu.utils.E;
import com.qufu.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;


@RestController
@RequestMapping("/file")
public class FileUploadController {
    private static Logger log = LoggerFactory.getLogger(FileUploadController.class);
    @Autowired
    FileUploadService fileUploadService;

    /**
     * MultipartFile用作文件上传， 他是基于sevlet3.0 =》tomacat7.0 二springboot是tomcat8.5
     *
     * @param uploadFile
     * @param req
     * @return
     */
    @PostMapping("/upload")
    public JsonResult upload(MultipartFile[] uploadFile, HttpServletRequest req) {
        log.info("----upload----执行");
        JsonResult jsonResult = new JsonResult();
        //规划上传文件储存的路径 获取工程目录再加上 uploadFile
//        String realPath=req.getSession().getServletContext().getRealPath("/uploadFile/");
        try {
            String[] str = fileUploadService.uploadPic(uploadFile, req);
            jsonResult.setData(str);
        } catch (IOException e) {
            jsonResult.setCode("401");
            jsonResult.setMsg(E.YZM_NUMBER_INFO);
        }
        return jsonResult;
    }
}
