package com.qufu.controller;


import com.qufu.service.ParagraphService;
import com.qufu.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 笑话的API接口
 */
@RestController
@RequestMapping("/paragraph")
public class ParagraphController {
    private static Logger log = LoggerFactory.getLogger(ImgController.class);
    @Autowired
    ParagraphService service;

    /**
     * @return
     */
    @GetMapping("/api")
    public JsonResult InsertImg() {
        JsonResult jsonResult = new JsonResult();
        String text = service.SelectText();
        jsonResult.setData(text);
        return jsonResult;
    }

}
