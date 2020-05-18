package com.qufu.controller;


import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.JsonSelcetAll;
import com.qufu.pojo.User;
import com.qufu.pojo.UserWebscoket;
import com.qufu.pojo.VisitCount;
import com.qufu.service.UserService;

import com.qufu.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")//拦截路径
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;
    @Autowired
    TemplateEngine templateEngine;

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping("/list")
    public JsonResult<List> Select() {
        Integer flag = 1;
        List<User> user = userService.Select(flag);
        return new JsonResult<>(user, "用户列表获取成功");
    }

    /**
     * 查询所有用户
     * 封装到json中
     *
     * @return
     */
    @GetMapping("/listAll")
    public JsonResult<JsonSelcetAll> SelectAll() {
        Integer flag = null;
        JsonSelcetAll selcetAll = userService.SelectAll(flag);
        return new JsonResult<>(selcetAll, "");
    }

    @GetMapping("/listid")
    public JsonResult Selectid() {
        List<Integer> list = userService.Selectid();
        return new JsonResult(list, "");
    }

    /**
     * 根据id查询详细信息
     *
     * @param request
     * @return
     */
    @PostMapping("/SelectUserid")
    public JsonResult SelectUserid(HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        User user = userService.SelectUserid(id);
        return new JsonResult(user);
    }

    /**
     * 查询所有用户信息
     *
     * @param request
     * @return
     */
    @PostMapping("/SelectR")
    public JsonResult SelectR(HttpServletRequest request) {
        Integer id1 = Integer.valueOf(request.getParameter("id1"));
        JsonResult jsonResult = new JsonResult();
        List<User> cout = userService.SelectUserr(id1);
        if (cout.size() != 0) {
            jsonResult.setData(cout);
        } else {
            jsonResult.setMsg("共同好友" + E.NO_NUMBER_INFO);
            jsonResult.setCode(E.NO_NUMBER_CODE + "");
        }
        return jsonResult;
    }

    /**
     * 删除聊天频道
     */
    @PostMapping("/deletWEB")
    public JsonResult deletWEB(HttpServletRequest request) {
        Integer uwid = Integer.valueOf(request.getParameter("uwid"));
        JsonResult jsonResult = new JsonResult();
        int cou = userService.deleUserWeb(uwid);
        if (cou == 1) {
            jsonResult.setData("成功");
        } else {
            jsonResult.setMsg("删除好友" + E.YZM_NUMBER_INFO);
            jsonResult.setCode(E.YZM_NUMBER_CODE + "");
        }
        return jsonResult;
    }

    @PostMapping("/SelectWeb")
    public JsonResult SelectWeb(HttpServletRequest request) {
        Integer id1 = Integer.valueOf(request.getParameter("id1"));
        Integer id2 = Integer.valueOf(request.getParameter("id2"));
        JsonResult jsonResult = new JsonResult();
        UserWebscoket cou = userService.SelectUserr(id1, id2);
        if (cou != null) {
            jsonResult.setData(cou);
        } else {
            jsonResult.setMsg("好友" + E.NO_NUMBER_INFO);
            jsonResult.setCode(E.NO_NUMBER_CODE + "");
        }
        return jsonResult;
    }

    /**
     * 伪删除操作
     *
     * @param id
     * @return
     */
    @PostMapping("/detflag")
    public JsonResult UDetFlag(int id) {
        int i = userService.UDetFlag(id);
        if (i == 1) {
            return new JsonResult();
        } else {
            return new JsonResult(200, "操作失败！");
        }
    }

    /**
     * 用户登录操作
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public JsonResult UserLogin(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] params = {"username", "psd"};
        try {
            V.valid(request, params);
            String username = request.getParameter("username");
            String psd = request.getParameter("psd");
            User user = userService.SelectUser(username, psd);
            if (user != null) {
                jsonResult.setData(user);
                jsonResult.setMsg("成功");
            } else {
                jsonResult.setCode(E.NO_NUMBER_CODE + "");
                jsonResult.setMsg("用户" + E.NO_NUMBER_INFO);
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 管理员登录操作
     *
     * @param request
     * @return
     */
    @PostMapping("/adminLogin")
    public JsonResult adminLogin(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] params = {"username", "psd"};
        try {
            V.valid(request, params);
            String username = request.getParameter("username");
            String psd = request.getParameter("psd");
            User user = userService.SelectUser(username, psd);
            if (user != null) {
                jsonResult.setData(user);
                jsonResult.setMsg("成功");
            } else {
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
                jsonResult.setMsg(E.NO_PRIVILEGE_ERROR_INFO);
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 获取邮件sesionn存储60秒
     *
     * @param request
     * @return
     */
    @PostMapping("/email")
    public JsonResult UserEmail(HttpServletRequest request) {
        String email = request.getParameter("email");
        User user = new User();
        user.setEmeil(email);
        Integer z = userService.Flag(user);
        if (z != null) {
            HttpSession session = request.getSession();
            int x = RadomMath.R();
            session.setAttribute(email, x + "");
            String s = (String) session.getAttribute(email);
            Context ctx = new Context();
            ctx.setVariable("eamil", s);
            String mail = templateEngine.process("Email.html", ctx);
            int i = mailService.sendSimpleMail(email, "重置密码", mail);
            System.out.println(i);
            session.setMaxInactiveInterval(60);
            return new JsonResult();
        } else {
            return new JsonResult("403", "邮箱不存在");
        }
    }

    /**
     * 验证sesion
     *
     * @param request
     * @return
     */
    @PostMapping("/tj")
    public JsonResult Tj(HttpServletRequest request) {
        String email = request.getParameter("email");
        JsonResult jsonResult = new JsonResult();
        HttpSession session = request.getSession();
        String s = (String) session.getAttribute(email);
        String id = request.getParameter("yanz");
        if (s.equals(id)) {
            jsonResult.setCode("200");
        } else {
            jsonResult.setCode("401");
        }
        return jsonResult;
    }

    /**
     * 更新密码
     *
     * @param request
     * @return
     */
    @PostMapping("/UpdatePsd")
    public JsonResult UpdatePsd(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] params = {"Emeil", "password"};
        try {
            V.valid(request, params);
            User user = V.entity(request, User.class, params);
            int i = userService.UpdatePsd(user);
            if (i != 1) {
                jsonResult.setCode("401");
                jsonResult.setMsg("失败");
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 更新密码
     *
     * @param request
     * @return
     */
    @PostMapping("/Updatenewpsd")
    public JsonResult Updatenewpsd(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] params = {"anumber", "password1", "password2"};
        try {
            V.valid(request, params);
            String anumber = request.getParameter("anumber");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");
            User user2 = userService.SelectUser(anumber, password1);
            if (user2 != null) {
                User user = new User();
                user.setAnumber(Integer.valueOf(anumber));
                user.setPassword(password2);
                int zz = userService.UpdatePsd(user);
                if (zz == 1) {
                    jsonResult.setData("修改成功，请重新登录！");
                }
            } else {
                jsonResult.setData("原密码输入错误，请仔细检查再次输入！");
                jsonResult.setCode("500");
            }

        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 注册账号，
     * 1.查看邮箱是否重复
     * 2.判断账号是否重复
     *
     * @param request
     * @return
     */
    @PostMapping("/InsertEmail")
    public JsonResult InsertEmail(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] params = {"Emeil"};
        try {
            String email = request.getParameter("Emeil");
            V.valid(request, params);
            System.out.println(email);
            User user = new User();
            user.setEmeil(email);
            Integer yemail = userService.Flag(user);
            if (yemail == null) {
                HttpSession session = request.getSession();
                int x = RadomMath.R();
                System.out.println(x);
                session.setAttribute(email, x + "");
                Context ctx = new Context();
                ctx.setVariable("eamil", x);
                String mail = templateEngine.process("register.html", ctx);
                int i = mailService.sendSimpleMail(email, "注册账号", mail);
                session.setMaxInactiveInterval(60);
            } else {
                jsonResult.setCode(E.NUMBER_CODE + "");
                jsonResult.setMsg("邮箱" + E.NUMBER_INFO);
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 修改资料
     *
     * @param request
     * @return
     */
    @PostMapping("/UpdateSig")
    public JsonResult UpdateSig(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String anumber = "anumber";
        try {
            V.val(request, anumber);
            String And = request.getParameter(anumber);
            User user = userService.SelectUsert(And);
            String src = V.getValue(request, "src", user.getSrc());
            String name = V.getValue(request, "name", user.getName());
            String gender = V.getValue(request, "gender", user.getGender());
            String date = request.getParameter("birthday");
            String signature = V.getValue(request, "signature", user.getSignature());

            if (src == "" && name == "" && gender == "" && date == "" && signature == "") {
                jsonResult.setCode("500");
                jsonResult.setMsg("最少填一个参数");
            } else {
                Date birthday;
                if (date == "") {
                    birthday = user.getBirthday();
                } else {
                    birthday = DateFlag.Time(date);
                }
                user.setSrc(src);
                user.setName(name);
                user.setBirthday(birthday);
                user.setGender(gender);
                user.setSignature(signature);
                int i = userService.UpdateSig(user);
                if (i == 1) {
                    User user2 = userService.SelectUsert(And);
                    jsonResult.setData(user2);
                } else {
                    jsonResult.setData("修改失败");
                }
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @PostMapping("/InsertUser")
    public JsonResult InsertUser(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] params = {"password", "name", "Emeil", "gender", "birthday", "src"};
        try {
            VisitCount count = new VisitCount();
            V.valid(request, params);
            User user = V.entity(request, User.class, params);
            Integer Adnum = userService.Select();
            user.setAnumber(Adnum);
            Date time = DateFlag.Time(count.getVisittime());
            if (time != null) {
                user.setCreationdate(time);
            }
            HttpSession session = request.getSession();
            String s = (String) session.getAttribute(user.getEmeil());
            String id = request.getParameter("yanz");
            if (s.equals(id)) {
                int i = userService.InsertUser(user);
                if (i == 1) {
                    jsonResult.setCode(200 + "");
                    jsonResult.setData(Adnum);
                } else {
                    jsonResult.setCode(E.YZM_NUMBER_CODE + "");
                    jsonResult.setMsg("注册" + E.YZM_NUMBER_INFO);
                }
            } else {
                jsonResult.setCode(E.YZM_NUMBER_CODE + "");
                jsonResult.setMsg("验证" + E.YZM_NUMBER_INFO);
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }
}
