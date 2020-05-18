package com.qufu.controller;

import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.Rcomment;
import com.qufu.service.RcommentService;
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
@RequestMapping("/rcommentc")
public class RcommentController {
    private static Logger log = LoggerFactory.getLogger(RcommentController.class);
    @Autowired
    RcommentService service;

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
            String rcid = request.getParameter("rcid");
            Rcomment rcomment1 = service.Select(Integer.valueOf(rcid));
            if (rcomment1 != null) {
                jsonResult.setData(rcomment1);
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
    @PostMapping("/selectAll")
    public JsonResult SelectAll(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"rid", "id"};
        try {
            V.valid(request, pranms);
            String rid = request.getParameter("rid");
            Integer id = Integer.valueOf(request.getParameter("id"));
            List<Rcomment> list = service.SelectAll(rid, id);
            if (list != null) {
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
    @PostMapping("/addComment")
    public JsonResult addComment(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"rid", "rctext", "id"};
        try {
            V.valid(request, prams);
            Rcomment rcomment = V.entity(request, Rcomment.class, prams);
            int count = service.addComment(rcomment);
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
    @PostMapping("/addrzcan")
    public JsonResult addrzcan(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"rcid", "id", "llike"};
        try {
            V.valid(request, prams);
            String rcid = request.getParameter("rcid");
            String id = request.getParameter("id");
            String llike = request.getParameter("llike");
            int count = service.addrzcan(Integer.valueOf(rcid), Integer.valueOf(id), llike);
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
    @PostMapping("/releasetrash")
    public JsonResult releasetrash(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"rcid", "id"};
        try {
            V.valid(request, prams);
            String rcid = request.getParameter("rcid");
            String id = request.getParameter("id");
            int count = service.deleteRcc(Integer.valueOf(rcid), Integer.valueOf(id));
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
