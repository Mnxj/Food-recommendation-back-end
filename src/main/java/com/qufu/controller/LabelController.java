package com.qufu.controller;


import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.Img;
import com.qufu.pojo.Release;
import com.qufu.service.LabelService;
import com.qufu.utils.E;
import com.qufu.utils.JsonResult;
import com.qufu.utils.V;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    LabelService service;

    /**
     * 添加
     *
     * @return
     */
    @PostMapping("/insert")
    public JsonResult insertL(HttpServletRequest request) {
        log.info("----insertL----执行");
        JsonResult jsonResult = new JsonResult();
        String pam = "latext";
        try {
            V.val(request, pam);
            String latext = request.getParameter("latext");
            int i = service.insertL(latext);
            if (i != 1) {
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
                jsonResult.setMsg("添加" + E.YZM_NUMBER_INFO);
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 删除
     *
     * @return
     */
    @PostMapping("/dell")
    public JsonResult delL(HttpServletRequest request) {
        log.info("----delL----执行");
        JsonResult jsonResult = new JsonResult();
        String pam = "latext";
        try {
            V.val(request, pam);
            String latext = request.getParameter(pam);
            int i = service.delL(latext);
            if (i != 1) {
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
                jsonResult.setMsg("添加" + E.YZM_NUMBER_INFO);
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/selcetAll")
    public JsonResult selcetAll(HttpServletRequest request) {
        log.info("----selcetAll----执行");
        JsonResult jsonResult = new JsonResult();
        List<String> list = service.SelecetAll();
        if (list.size() != 0) {
            jsonResult.setData(list);
        } else {
            jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            jsonResult.setMsg("添加" + E.YZM_NUMBER_INFO);
        }
        return jsonResult;
    }
}
