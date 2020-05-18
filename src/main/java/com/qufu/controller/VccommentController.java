package com.qufu.controller;


import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.VLike2;
import com.qufu.pojo.Vccomment;
import com.qufu.service.VccommentService;
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
@RequestMapping("/vccomment")
public class VccommentController {
    private static Logger log = LoggerFactory.getLogger(VccommentController.class);
    @Autowired
    VccommentService service;

    /**
     * 查询这个评论作为点赞和回复的依据
     *
     * @return
     */
    @PostMapping("/vselect")
    public JsonResult VSelect(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"vcid"};
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
    @PostMapping("/vselectAll")
    public JsonResult VSelectAll(HttpServletRequest request) {
        log.info("----VSelectAll----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"vcid", "id1", "id2"};
        try {
            V.valid(request, pranms);
            Vccomment vccomment = V.entity(request, Vccomment.class, pranms);
            List<Vccomment> vccomments = service.SelectAll(vccomment.getId1(), vccomment.getId2(), vccomment.getVcid());
            if (vccomments.size() != 0) {
                jsonResult.setData(vccomments);
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
    @PostMapping("/vaddComment")
    public JsonResult VaddComment(HttpServletRequest request) {
        log.info("----VaddComment----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranms = {"vcctext", "vcid", "id1", "id2"};
        try {
            V.valid(request, pranms);
            Vccomment vccomment = V.entity(request, Vccomment.class, pranms);
            System.out.println(vccomment);
            int cunt = service.addCommentc(vccomment);
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
    @PostMapping("/vaddrzcan")
    public JsonResult Vaddrzcan(HttpServletRequest request) {
        log.info("----Vaddrzcan----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"vccid", "vcid", "id", "vllik2"};
        try {
            V.valid(request, prams);
            VLike2 like2 = V.entity(request, VLike2.class, prams);
            System.out.println(like2);
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

    @PostMapping("/vreleaseromeve")
    public JsonResult Vreleaseromeve(HttpServletRequest request) {
        log.info("----Vreleaseromeve----执行");
        JsonResult jsonResult = new JsonResult();
        String[] prams = {"vccid", "id2"};
        try {
            V.valid(request, prams);
            String vccid = request.getParameter("vccid");
            String id = request.getParameter("id2");
            int count = service.deleteVccomment(Integer.valueOf(id), Integer.valueOf(vccid));
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
