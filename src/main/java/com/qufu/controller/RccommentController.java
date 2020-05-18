package com.qufu.controller;


import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.Like2;
import com.qufu.pojo.Rccomment;
import com.qufu.service.RccommentService;
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
@RequestMapping("/rccomment")
public class RccommentController {
    private static Logger log = LoggerFactory.getLogger(RccommentController.class);
    @Autowired
    RccommentService service;

    /**
     * 查询这个评论作为点赞和回复的依据
     *
     * @return
     */
    @PostMapping("/select")
    public JsonResult Select(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"rcid"};
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
    @PostMapping("/selectAll")
    public JsonResult SelectAll(HttpServletRequest request) {
        log.info("----SelectAll----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"rcid", "id1", "id2"};
        try {
            V.valid(request, pranms);
            Rccomment rccomment = V.entity(request, Rccomment.class, pranms);
            List<Rccomment> rccomments = service.SelectAll(rccomment.getId1(), rccomment.getId2(), rccomment.getRcid());
            if (rccomments.size() != 0) {
                jsonResult.setData(rccomments);
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
    @PostMapping("/addComment")
    public JsonResult addComment(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"rcid", "rcctext", "id1", "id2"};
        try {
            V.valid(request, prams);
            Rccomment rccomment = V.entity(request, Rccomment.class, prams);
            int cunt = service.addCommentc(rccomment);
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
    @PostMapping("/addrzcan")
    public JsonResult addrzcan(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"rccid", "rcid", "id", "llike2"};
        try {
            V.valid(request, prams);
            Like2 like2 = V.entity(request, Like2.class, prams);
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

    @PostMapping("/releaseromeve")
    public JsonResult releaseromeve(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"rccid", "id2"};
        try {
            V.valid(request, prams);
            String rcid = request.getParameter("rccid");
            String id = request.getParameter("id2");
            int count = service.deleteRccomment(Integer.valueOf(id), Integer.valueOf(rcid));
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
