package com.qufu.controller;

import com.qufu.exception.InvalidParamException;

import com.qufu.pojo.Videos;
import com.qufu.service.VideoService;
import com.qufu.utils.E;
import com.qufu.utils.JsonResult;
import com.qufu.utils.V;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/video")//拦截路径
public class VideoController {
    private static Logger log = LoggerFactory.getLogger(VideoController.class);
    @Autowired
    VideoService service;

    /**
     * 添加缩略图
     *
     * @return
     */
    @PostMapping("/addVideo")
    public JsonResult addVideo(HttpServletRequest request) {
        log.info("----addVideo----执行");
        JsonResult jsonResult = new JsonResult();
        String[] pranm = {"id", "videotitle", "videosource", "videocontent", "videourl", "dynamicTags"};
        try {
            V.valid(request, pranm);
            String radname = request.getParameter("radname");
            String rid = request.getParameter("rid");
            String radcode = request.getParameter("radcode");
            Videos video = V.entity(request, Videos.class, pranm);
            video.setRadcode(radcode);
            video.setRadname(radname);
            video.setRid(rid);
            int i = service.addVideo(video);
            if (i == 1) {
                jsonResult.setData("视频已发布,请等待管理员审核。");
            } else {
                jsonResult.setMsg("视频已发布" + E.YZM_NUMBER_INFO);
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
    @PostMapping("/selectV")
    public JsonResult SelectV(HttpServletRequest request) {
        log.info("----SelectV----执行");
        JsonResult jsonResult = new JsonResult();
        String parms = "rid";
        try {
            V.val(request, parms);
            String rid = request.getParameter(parms);
            List<Videos> videos = service.SelectAll(rid, "");
            if (videos.size() != 0) {
                jsonResult.setData(videos);
            } else {
                jsonResult.setMsg("视频资源" + E.NO_NUMBER_INFO);
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
        String parms = "id";
        try {
            V.val(request, parms);
            String id = request.getParameter(parms);
            List<Videos> videos = service.SelectAllEr(id);
            if (videos.size() != 0) {
                jsonResult.setData(videos);
            } else {
                jsonResult.setMsg("视频资源" + E.NO_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 查询用户发布了多少
     *
     * @param request
     * @return
     */
    @PostMapping("/selectP")
    public JsonResult SelectP(HttpServletRequest request) {
        log.info("----SelectP----执行");
        JsonResult jsonResult = new JsonResult();
        String parms = "id";
        try {
            V.val(request, parms);
            String id = request.getParameter(parms);
            List<Videos> videos = service.SelectAll("", id);
            if (videos.size() != 0) {
                jsonResult.setData(videos);
            } else {
                jsonResult.setMsg("视频资源" + E.NO_NUMBER_INFO);
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
    @PostMapping("/DeleteV")
    public JsonResult DeleteV(HttpServletRequest request) {
        log.info("----DeleteV----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"src", "url", "videoid"};
        String[] url = new String[2];
        try {
            V.valid(request, parms);
            url[0] = request.getParameter("src");
            url[1] = request.getParameter("url");
            String pag = request.getParameter("videoid");
            int essays = service.deleteE(url, pag, request);
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
    @PostMapping("/addVcount")
    public JsonResult addVcount(HttpServletRequest request) {
        log.info("----addVcount----执行");
        JsonResult jsonResult = new JsonResult();
        String parm = "videoid";
        try {
            V.val(request, parm);
            String videoid = request.getParameter(parm);
            int essays = service.addVcount(Integer.valueOf(videoid));
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
        String[] parms = {"pag"};
        try {
            V.valid(request, parms);
            String radname = request.getParameter("radname");
            String radcode = request.getParameter("radcode");
            String pag = request.getParameter("pag");
            List<Videos> videos = service.SelectAllRname(radname, radcode, Integer.valueOf(pag));
            if (videos.size() != 0) {
                jsonResult.setData(videos);
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
            List<Videos> videos = service.SelectAllRname2(radname, radcode);
            if (videos.size() != 0) {
                jsonResult.setData(videos);
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
     * 、
     * 根据标签进行查询相关视频
     *
     * @param request
     * @return
     */
    @PostMapping("/SelectVideo")
    public JsonResult SelectVideo(HttpServletRequest request) {
        log.info("----SelectVideo----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"dynamicTags", "videoid"};
        try {
            V.valid(request, parms);
            String dynamicTags = request.getParameter("dynamicTags");
            String videoid = request.getParameter("videoid");
            List<Videos> videos = service.SelectVideo(dynamicTags, Integer.valueOf(videoid));
            if (videos.size() != 0) {
                jsonResult.setData(videos);
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
            List<Videos> videos = service.SelectLabel(dynamicTags);
            if (videos.size() != 0) {
                jsonResult.setData(videos);
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
     * 更新审核操作
     *
     * @param request
     * @return
     */
    @PostMapping("/updatVreview")
    public JsonResult updatVreview(HttpServletRequest request) {
        log.info("----updatVreview----执行");
        JsonResult jsonResult = new JsonResult();
        String[] parms = {"src", "url", "videoid", "n"};
        String[] url = new String[2];
        try {
            V.valid(request, parms);
            url[0] = request.getParameter("src");
            url[1] = request.getParameter("url");
            Integer pag = Integer.valueOf(request.getParameter("videoid"));
            Integer n = Integer.valueOf(request.getParameter("n"));
            int count = service.updatVreview(url, pag, n, request);
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
            List<Videos> list = service.selecetAllV(pag);
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

    @GetMapping("selecetAllVreview")
    public JsonResult selecetAllVreview() {
        log.info("----selecetAllVreview----执行");
        JsonResult jsonResult = new JsonResult();
        List<Videos> videos = service.selecetAllVreview();
        if (videos.size() != 0) {
            jsonResult.setData(videos);
        } else {
            jsonResult.setMsg(E.NO_NUMBER_INFO);
            jsonResult.setCode(E.NO_NUMBER_INFO + "");
        }
        return jsonResult;
    }
}
