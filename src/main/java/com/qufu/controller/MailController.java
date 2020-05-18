package com.qufu.controller;


import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.Release;
import com.qufu.utils.JsonResult;
import com.qufu.utils.MailService;
import com.qufu.utils.V;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Mail")//拦截路径
public class MailController {
    private static Logger log = LoggerFactory.getLogger(MailController.class);
    @Autowired
    MailService mailService;
    @Autowired
    TemplateEngine templateEngine;

    /**
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public JsonResult InsertImg(HttpServletRequest request) {
        log.info("----insertImg----执行");
        JsonResult jsonResult = new JsonResult();
        String[] params = {"name", "email", "text"};
        try {
            V.valid(request, params);
            String rname = request.getParameter("name");
            String email = request.getParameter("email");
            String text = request.getParameter("text");
            Context ctx = new Context();
            ctx.setVariable("username", rname);
            ctx.setVariable("email", email);
            ctx.setVariable("text", text);
            String mail = templateEngine.process("mailtemplate.html", ctx);
            int i = mailService.sendSimpleMail(email, "联系我们", mail);
            jsonResult.setData(i + "");
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * @param request
     * @return
     */
    @PostMapping("/feedback")
    public JsonResult InsertFk(HttpServletRequest request) {
        log.info("----InsertFk----执行");
        JsonResult jsonResult = new JsonResult();
        String[] params = {"anumber", "email", "textarea"};
        try {
            V.valid(request, params);
            String anumber = request.getParameter("anumber");
            String email = request.getParameter("email");
            String text = request.getParameter("textarea");
            Context ctx = new Context();
            ctx.setVariable("anumber", anumber);
            ctx.setVariable("email", email);
            ctx.setVariable("text", text);
            String mail = templateEngine.process("Feedback.html", ctx);
            int i = mailService.sendSimpleMail(email, "意见反馈", mail);
            jsonResult.setData(i + "");
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }
}
