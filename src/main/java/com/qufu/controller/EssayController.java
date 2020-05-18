package com.qufu.controller;


import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.Essay;
import com.qufu.service.EssayService;
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
@RequestMapping("/essay")//拦截路径
public class EssayController {
    private static Logger log = LoggerFactory.getLogger(ImgController.class);
    @Autowired
    EssayService service;

    /**
     * 添加缩略图
     *
     * @return
     */
    @PostMapping("/addEssay")
    public JsonResult addEssay(HttpServletRequest request) {
        log.info("----addEssay----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranm = {"id", "essaytitle", "essaysource", "essaycontent", "essayurl", "dynamicTags"};
        try {
            V.valid(request, pranm);
            String rid = request.getParameter("rid");
            String radname = request.getParameter("radname");
            String radcode = request.getParameter("radcode");
            Essay essay = V.entity(request, Essay.class, pranm);
            System.out.println(essay);
            essay.setRadcode(radcode);
            essay.setRadname(radname);
            essay.setRid(rid);
            int i = service.addEssay(essay);
            if (i == 1) {
                jsonResult.setData("日志以发布，请等待管理员审核。");
            } else {
                jsonResult.setMsg("日志发布" + E.YZM_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 查询该店铺的视频和日志
     *
     * @param request
     * @return
     */
    @PostMapping("/selectE")
    public JsonResult SelectE(HttpServletRequest request) {
        log.info("----SelectE----执行");
        JsonResult jsonResult = new JsonResult();
        String parms = "rid";
        try {
            V.val(request, parms);
            String rid = request.getParameter(parms);
            List<Essay> essays = service.SelectAll(rid, "");
            if (essays.size() != 0) {
                jsonResult.setData(essays);
            } else {
                jsonResult.setMsg("资源" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 查询该店铺的视频和日志
     *
     * @param request
     * @return
     */
    @PostMapping("/selectP")
    public JsonResult SelectP(HttpServletRequest request) {
        log.info("----SelectAll----执行");
        JsonResult jsonResult = new JsonResult();
        String parm = "id";
        try {
            V.val(request, parm);
            String id = request.getParameter(parm);
            List<Essay> essays = service.SelectAll("", id);
            if (essays.size() != 0) {
                jsonResult.setData(essays);
            } else {
                jsonResult.setMsg("资源" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 查询自己没有审核通过的
     *
     * @param request
     * @return
     */
    @PostMapping("/selectER")
    public JsonResult selectER(HttpServletRequest request) {
        log.info("----selectER----执行");
        JsonResult jsonResult = new JsonResult();
        String parm = "id";
        try {
            V.val(request, parm);
            String id = request.getParameter(parm);
            List<Essay> essays = service.SelectAllEr(id);
            if (essays.size() != 0) {
                jsonResult.setData(essays);
            } else {
                jsonResult.setMsg("资源" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
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
     * @param request
     * @return
     */
    @PostMapping("/DeleteE")
    public JsonResult DeleteE(HttpServletRequest request) {
        log.info("----DeleteE----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parm = {"url", "essayid"};
        try {
            V.valid(request, parm);
            String esssayid = request.getParameter("essayid");
            String url = request.getParameter("url");
            int essays = service.deleteE(esssayid, url, request);
            if (essays == 1) {
                jsonResult.setData("成功");
            } else {
                jsonResult.setMsg("删除" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 添加记录
     *
     * @param request
     * @return
     */
    @PostMapping("/addEcount")
    public JsonResult addEcount(HttpServletRequest request) {
        log.info("----addEcount----执行");
        JsonResult jsonResult = new JsonResult();
        String parm = "essayid";
        try {
            V.val(request, parm);
            String essayid = request.getParameter(parm);
            int essays = service.addEcount(Integer.valueOf(essayid));
            if (essays != -1) {
                jsonResult.setData(essays);
            } else {
                jsonResult.setMsg(E.NO_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 查询
     *
     * @param request
     * @return
     */
    @PostMapping("/SelectAllRname")
    public JsonResult SelectAllRname(HttpServletRequest request) {
        log.info("----SelectAllRname----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"pag", "id"};
        try {
            V.valid(request, parms);
            String radname = request.getParameter("radname");
            String radcode = request.getParameter("radcode");
            String pag = request.getParameter("pag");
            String id = request.getParameter("id");
            List<Essay> essays = service.SelectAllRname(radname, radcode, Integer.valueOf(pag), Integer.valueOf(id));
            if (essays.size() != 0) {
                jsonResult.setData(essays);
            } else {
                jsonResult.setMsg("数据" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 查询
     *
     * @param request
     * @return
     */
    @PostMapping("/SelectAllRname2")
    public JsonResult SelectAllRname2(HttpServletRequest request) {
        log.info("----SelectAllRname2----执行");
        JsonResult jsonResult = new JsonResult();
        try {
            String radname = request.getParameter("radname");
            String radcode = request.getParameter("radcode");
            List<Essay> essays = service.SelectAllRname2(radname, radcode);
            if (essays != null) {
                jsonResult.setData(essays);
            } else {
                jsonResult.setMsg("数据" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @PostMapping("/addLike")
    public JsonResult addLike(HttpServletRequest request) {
        log.info("----addLike----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"eslike", "essayid", "id"};
        try {
            V.valid(request, parms);
            String eslike = request.getParameter("eslike");
            String id = request.getParameter("id");
            String essayid = request.getParameter("essayid");
            String bol = service.addLike(eslike, Integer.valueOf(essayid), Integer.valueOf(id));
            if (bol != null) {
                jsonResult.setData(bol);
            } else {
                jsonResult.setMsg("添加" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 置顶
     *
     * @param request
     * @return
     */
    @PostMapping("/selecetAll")
    public JsonResult selecetAll(HttpServletRequest request) {
        log.info("----selecetAll----执行");
        JsonResult jsonResult = new JsonResult();
        String parms = "pag";
        try {
            V.val(request, parms);
            Integer pag = Integer.valueOf(request.getParameter(parms));
            List<Essay> list = service.selecetAll(pag);
            if (list.size() != 0) {
                jsonResult.setData(list);
            } else {
                jsonResult.setMsg("查询" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 查询总页数
     *
     * @return
     */
    @PostMapping("/selcetCount")
    public JsonResult selcetCount(HttpServletRequest request) {
        log.info("----selcetCount----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"n"};
        try {
            V.valid(request, parms);
            Integer n = Integer.valueOf(request.getParameter("n"));
            int essays = service.selcetCount(n);
            if (essays != 0) {
                jsonResult.setData(essays);
            } else {
                jsonResult.setMsg(E.NO_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 更新置顶
     *
     * @param request
     * @return
     */
    @PostMapping("/updateEsticky")
    public JsonResult updateEsticky(HttpServletRequest request) {
        log.info("----updateEsticky----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"essayid", "n"};
        try {
            V.valid(request, parms);
            Integer pag = Integer.valueOf(request.getParameter("essayid"));
            Integer n = Integer.valueOf(request.getParameter("n"));
            int count = service.updateEsticky(pag, n);
            if (count == 1) {
                jsonResult.setData("1");
            } else if (count == 2) {
                jsonResult.setData("2");
            } else {
                jsonResult.setMsg("更新" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 审核逻辑
     *
     * @param request
     * @return
     */
    @PostMapping("/selecetAllV")
    public JsonResult selecetAllV(HttpServletRequest request) {
        log.info("----selecetAllV----执行");
        JsonResult jsonResult = new JsonResult();
        String parms = "pag";
        try {
            V.val(request, parms);
            Integer pag = Integer.valueOf(request.getParameter(parms));
            List<Essay> list = service.selecetAllV(pag);
            if (list.size() != 0) {
                jsonResult.setData(list);
            } else {
                jsonResult.setMsg("查询" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 更新审核操作
     *
     * @param request
     * @return
     */
    @PostMapping("/updatEreview")
    public JsonResult updatEreview(HttpServletRequest request) {
        log.info("----updatEreview----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"url", "essayid", "n"};
        try {
            V.valid(request, parms);
            String url = request.getParameter("url");
            Integer pag = Integer.valueOf(request.getParameter("essayid"));
            Integer n = Integer.valueOf(request.getParameter("n"));
            int count = service.updatEreview(url, pag, n, request);
            if (count == 1) {
                jsonResult.setData("1");
            } else if (count == 2) {
                jsonResult.setData("2");
            } else {
                jsonResult.setMsg("更新" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 根据传过来的值判断是不是置顶
     * 1，是查询置顶
     * 0,是查询不置顶
     *
     * @param request
     * @return
     */
    @PostMapping("/selcetEsticky")
    public JsonResult selcetEsticky(HttpServletRequest request) {
        log.info("----updatEreview----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"esticky"};
        try {
            V.valid(request, parms);
            String esticky = request.getParameter("esticky");
            List<Essay> essays = service.selcetEsticky(esticky);
            if (essays.size() != 0) {
                jsonResult.setData(essays);
            } else {
                jsonResult.setMsg("查询" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 根据标签进行查询
     *
     * @param request
     * @return
     */
    @PostMapping("/SelectLabel")
    public JsonResult SelectLabel(HttpServletRequest request) {
        log.info("----SelectVideo----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"dynamicTags"};
        try {
            V.valid(request, parms);
            String dynamicTags = request.getParameter("dynamicTags");
            List<Essay> essays = service.SelectLabel(dynamicTags);
            if (essays.size() != 0) {
                jsonResult.setData(essays);
            } else {
                jsonResult.setMsg("数据" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;

    }
}
