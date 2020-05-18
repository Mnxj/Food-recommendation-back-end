package com.qufu.controller;

import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.Vcomment;
import com.qufu.service.VcommentService;
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
@RequestMapping("/vcommentc")
public class VcommentController {
    private static Logger log = LoggerFactory.getLogger(VcommentController.class);
    @Autowired
    VcommentService service;

    /**
     * 查询这个评论作为点赞和回复的依据
     *
     * @return
     */
    @PostMapping("/vcselect")
    public JsonResult VcSelect(HttpServletRequest request) {
        log.info("----VcSelect----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"vcid"};
        try {
            V.valid(request, pranms);
            String vcid = request.getParameter("vcid");
            Vcomment vcomment1 = service.Select(Integer.valueOf(vcid));
            if (vcomment1 != null) {
                jsonResult.setData(vcomment1);
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
    @PostMapping("/vcselectAll")
    public JsonResult VCSelectAll(HttpServletRequest request) {
        log.info("----vcSelectAll----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"videoid", "id"};
        try {
            V.valid(request, pranms);
            String videoid = request.getParameter("videoid");
            Integer id = Integer.valueOf(request.getParameter("id"));
            List<Vcomment> list = service.SelectAll(videoid, id);
            if (list.size() != 0) {
                jsonResult.setData(list);
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
     * 添加评论
     *
     * @param request
     * @return
     */
    @PostMapping("/vcaddComment")
    public JsonResult VcaddComment(HttpServletRequest request) {
        log.info("----VcaddComment----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"videoid", "vctext", "id"};
        try {
            V.valid(request, prams);
            Vcomment ecomment = V.entity(request, Vcomment.class, prams);
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
    @PostMapping("/vcaddrzcan")
    public JsonResult Vcaddrzcan(HttpServletRequest request) {
        log.info("----Vcaddrzcan----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"vcid", "id", "vlike"};
        try {
            V.valid(request, prams);
            String vcid = request.getParameter("vcid");
            String id = request.getParameter("id");
            String vlike = request.getParameter("vlike");
            int count = service.addrzcan(Integer.valueOf(vcid), Integer.valueOf(id), vlike);
            if (count == 1) {
                jsonResult.setData("点赞成功");
            } else {
                jsonResult.setMsg("点赞" + E.YZM_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
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
    @PostMapping("/vcreleasetrash")
    public JsonResult Vcreleasetrash(HttpServletRequest request) {
        log.info("----Vcreleasetrash----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"vcid", "id"};
        try {
            V.valid(request, prams);
            String vcid = request.getParameter("vcid");
            String id = request.getParameter("id");
            int count = service.deleteRcc(Integer.valueOf(vcid), Integer.valueOf(id));
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
