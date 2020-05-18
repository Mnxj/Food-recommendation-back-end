package com.qufu.controller;

import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.Ecomment;
import com.qufu.service.EcommentService;
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
@RequestMapping("/ecommentc")
public class EcommentController {
    private static Logger log = LoggerFactory.getLogger(EcommentController.class);
    @Autowired
    EcommentService service;

    /**
     * 查询这个评论作为点赞和回复的依据
     *
     * @return
     */
    @PostMapping("/ecselect")
    public JsonResult EcSelect(HttpServletRequest request) {
        log.info("----EcSelect----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"ecid"};
        try {
            V.valid(request, pranms);
            String ecid = request.getParameter("ecid");
            Ecomment ecomment1 = service.Select(Integer.valueOf(ecid));
            if (ecomment1 != null) {
                jsonResult.setData(ecomment1);
            } else {
                jsonResult.setMsg(E.NO_NUMBER_INFO);
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
            }
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
    @PostMapping("/ecselectAll")
    public JsonResult EcSelectAll(HttpServletRequest request) {
        log.info("----EcSelectAll----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"essayid", "id"};
        try {
            V.valid(request, pranms);
            String essayid = request.getParameter("essayid");
            Integer id = Integer.valueOf(request.getParameter("id"));
            List<Ecomment> list = service.SelectAll(essayid, id);
            if (list.size() != 0) {
                jsonResult.setData(list);
            } else {
                jsonResult.setData("暂时没有人评论");
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
    @PostMapping("/ecaddComment")
    public JsonResult EcaddComment(HttpServletRequest request) {
        log.info("----EcaddComment----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"essayid", "ectext", "id"};
        try {
            V.valid(request, prams);
            Ecomment ecomment = V.entity(request, Ecomment.class, prams);
            int count = service.addComment(ecomment);
            if (count == 1) {
                jsonResult.setData("评论成功");

            } else {
                jsonResult.setMsg("评论" + E.YZM_NUMBER_INFO);
                jsonResult.setCode("" + E.YZM_NUMBER_CODE);
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
    @PostMapping("/ecaddrzcan")
    public JsonResult Ecaddrzcan(HttpServletRequest request) {
        log.info("----Ecaddrzcan----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"ecid", "id", "elike"};
        try {
            V.valid(request, prams);
            String ecid = request.getParameter("ecid");
            String id = request.getParameter("id");
            String elike = request.getParameter("elike");
            int count = service.addrzcan(Integer.valueOf(ecid), Integer.valueOf(id), elike);
            if (count == 1) {
                System.out.println("点赞成功");
            } else {
                System.out.println("失败");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 本人删除自己的评论
     *
     * @param request
     * @return
     */
    @PostMapping("/ecreleasetrash")
    public JsonResult Ecreleasetrash(HttpServletRequest request) {
        log.info("----Ecreleasetrash----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"ecid", "id"};
        try {
            V.valid(request, prams);
            String ecid = request.getParameter("ecid");
            String id = request.getParameter("id");
            int count = service.deleteRcc(Integer.valueOf(ecid), Integer.valueOf(id));
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
