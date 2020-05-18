package com.qufu.controller;


import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.ELike2;
import com.qufu.pojo.Eccomment;
import com.qufu.service.EccommentService;
import com.qufu.utils.E;
import com.qufu.utils.JsonResult;
import com.qufu.utils.V;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/eccomment")
public class EccommentController {
    private static Logger log = LoggerFactory.getLogger(EccommentController.class);
    @Autowired
    EccommentService service;

    /**
     * 查询这个评论作为点赞和回复的依据
     *
     * @return
     */
    @PostMapping("/eselect")
    public JsonResult ESelect(HttpServletRequest request) {
        log.info("----ESelect----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"ecid"};
        try {
            V.valid(request, pranms);
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * \查询指定店铺的所有评论
     *
     * @param request
     * @return
     */
    @PostMapping("/eselectAll")
    public JsonResult ESelectAll(HttpServletRequest request) {
        log.info("----ESelectAll----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"ecid", "id1", "id2"};
        try {
            V.valid(request, pranms);
            Eccomment eccomment = V.entity(request, Eccomment.class, pranms);
            List<Eccomment> eccomments = service.SelectAll(eccomment.getId1(), eccomment.getId2(), eccomment.getEcid());
            if (eccomments.size() != 0) {
                jsonResult.setData(eccomments);
            } else {
                jsonResult.setMsg("子评论" + E.NO_NUMBER_INFO);
                jsonResult.setCode("" + E.NO_NUMBER_CODE);
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 添加评论
     *
     * @param request
     * @return
     */
    @PostMapping("/eaddComment")
    public JsonResult EaddComment(HttpServletRequest request) {
        log.info("----EaddComment----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"ecid", "ecctext", "id1", "id2"};
        try {
            V.valid(request, prams);
            Eccomment eccomment = V.entity(request, Eccomment.class, prams);
            int cunt = service.addCommentc(eccomment);
            if (cunt == 1) {
                jsonResult.setData("回复成功");
            } else {
                jsonResult.setMsg("回复" + E.YZM_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 点赞数添加
     *
     * @param request
     * @return
     */
    @PostMapping("/eaddrzcan")
    public JsonResult Eaddrzcan(HttpServletRequest request) {
        log.info("----Eaddrzcan----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"eccid", "ecid", "id", "elike2"};
        try {
            V.valid(request, prams);
            ELike2 like2 = V.entity(request, ELike2.class, prams);
            int count = service.addlike2(like2);
            if (count == 1) {
                jsonResult.setData("添加成功");
            } else {
                jsonResult.setMsg("添加" + E.YZM_NUMBER_INFO);
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @PostMapping("/ereleaseromeve")
    public JsonResult Ereleaseromeve(HttpServletRequest request) {
        log.info("----Ereleaseromeve----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"eccid", "id2"};
        try {
            V.valid(request, prams);
            String eccid = request.getParameter("eccid");
            String id = request.getParameter("id2");
            int count = service.deleteEccomment(Integer.valueOf(id), Integer.valueOf(eccid));
            if (count == 1) {
                jsonResult.setData("删除成功");
            } else {
                jsonResult.setMsg("删除" + E.YZM_NUMBER_INFO);
                jsonResult.setCode("" + E.YZM_NUMBER_CODE);
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }
}
