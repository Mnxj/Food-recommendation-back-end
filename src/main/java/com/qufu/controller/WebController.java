package com.qufu.controller;


import com.qufu.mapper.EssayMapper;
import com.qufu.mapper.ReleaseMapper;
import com.qufu.mapper.UserMapper;
import com.qufu.mapper.VideoMapper;
import com.qufu.pojo.Essay;
import com.qufu.pojo.Release;
import com.qufu.pojo.SocketMsg;
import com.qufu.pojo.Videos;
import com.qufu.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 这个是调用类
 */
@Slf4j
@RestController
@RequestMapping("/api/ws")
public class WebController {
    @Autowired
    MyWebSocket myWebSocket;
    @Autowired
    UserMapper userMapper;
    @Autowired
    VideoMapper mapper;
    @Autowired
    EssayMapper essayMapper;
    @Autowired
    ReleaseMapper releaseMapper;

    /**
     * 群发消息内
     *
     * @return
     */
    @PostMapping("/sendAll")
    public JsonResult sendAllMessage(HttpServletRequest request) {
        log.info("----insertImg----执行");
        JsonResult jsonResult = new JsonResult();
        String admin = request.getParameter("admin");
        String message = request.getParameter("message");
        try {
            int i = myWebSocket.sendAllMessage("#102:#,#系统通知#,#管理员：" + admin + message + "#,##,##,#" + DateFlag.getDate() + "#");
            if (i == 1) {
                jsonResult.setData("发布成功");
            } else {
                jsonResult.setMsg("发布" + E.YZM_NUMBER_INFO);
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 指定会话ID发消息
     * message 消息内容
     * id 连接会话ID
     *
     * @return
     */
    @PostMapping("/sendOne")
    String sendOneMessage(HttpServletRequest request) {
        String id = request.getParameter("id");//发送者
        Integer type = Integer.valueOf(request.getParameter("type"));//类型
        String message = request.getParameter("message");
        String id2 = request.getParameter("id2");//接收者
        String menuid = request.getParameter("menuid");//接收者
        String menu = request.getParameter("menu");//接收者
        String url1 = request.getParameter("url1");//接收者
        String url2 = request.getParameter("url2");//接收者
        SocketMsg socketMsg = new SocketMsg();
        socketMsg.setType(type);
        socketMsg.setFromUser(id);
        socketMsg.setToUser(id2);
        //加一个判断用来把离线消息写入文件
        String meg = "";
        // 编号+种类(限店铺视频、文章)+标题(除聊天)+消息+类型(除聊天)+发送者(仅聊天需要,有时这个是店铺id)+接收者+时间+头像（店铺图片）+头像2（除聊天有）；
        if (type == 1) {

            meg = "#101:" + message + "#,#" + id + "#,#" + id2 + "#,#" + DateFlag.getDate() + "#,#" + url1 + "#,#" + url2 + "#";
        } else if (type == 2) {
            meg = "#102:" + menu + "#,#有新的回复#,#" + message + "#,#" + type + "#,#" + menuid + "#,#" + DateFlag.getDate() + "#,#" + url1 + "#";
        } else if (type == 3) {
            meg = "#102:" + menu + "#,#新好友信息#,#" + message + "#,#" + type + "#,#" + id2 + "#,#" + DateFlag.getDate() + "#,#" + url1 + "#";
        } else if (type == 4) {
            meg = "#102:" + menu + "#,#同意请求#,#" + message + "#,#" + type + "#,#" + id2 + "#,#" + DateFlag.getDate() + "#,#" + url1 + "#";
        } else if (type == 5) {
            meg = "#102:#,#系统通知#,#" + message + "#,#" + type + "#,#" + id2 + "#,#" + DateFlag.getDate() + "#,#" + url1 + "#";
        }
        socketMsg.setMsg(meg);
        try {
            myWebSocket.sendOneMessage(socketMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * 进行判断
     *
     * @return
     */
    @PostMapping("/SelectMenu")
    public JsonResult Selecet(HttpServletRequest request) {
        log.info("----Selecet----执行");
        JsonResult jsonResult = new JsonResult();
        String menuid = request.getParameter("menuid");
        String menu = request.getParameter("menu");
        if (menu.equals("视频")) {
            Videos v = mapper.SelctV(Integer.valueOf(menuid));
            v.setLx(menu);
            jsonResult.setData(v);
        } else if (menu.equals("日志")) {
            Essay essay = essayMapper.selectE(Integer.valueOf(menuid));
            essay.setLx(menu);
            jsonResult.setData(essay);
        } else if (menu.equals("店铺")) {
            Release release = releaseMapper.SelectR(menuid);
            release.setLx(menu);
            jsonResult.setData(release);
        } else {
            jsonResult.setMsg(E.INVALID_PARAM_INFO);
            jsonResult.setCode(E.INVALID_PARAM_ERROR_CODE + "");
        }
        return jsonResult;
    }

    /**
     * 添加好友
     *
     * @return
     */
    @PostMapping("/addweb")
    public JsonResult addweb(HttpServletRequest request) {
        log.info("----addweb----执行");
        JsonResult jsonResult = new JsonResult();
        Integer id1 = Integer.valueOf(request.getParameter("id1"));
        Integer id2 = Integer.valueOf(request.getParameter("id2"));
        int a = userMapper.SelectUserWeb(id2).size();
        int b = userMapper.SelectUserWeb1(id2).size();
        if (a == 0 && b == 0) {
            int count = userMapper.addUserWEb(id1, id2);
            if (count == 1) {
                jsonResult.setData("添加成功");
            } else {
                jsonResult.setMsg("添加" + E.INVALID_PARAM_INFO);
                jsonResult.setCode(E.INVALID_PARAM_ERROR_CODE + "");
            }
        }
        return jsonResult;
    }
}
