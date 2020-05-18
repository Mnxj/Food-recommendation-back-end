package com.qufu.controller;


import com.qufu.exception.InvalidParamException;
import com.qufu.pojo.Journal;
import com.qufu.service.JournalService;
import com.qufu.utils.DateFlag;
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
@RequestMapping("/journal")
public class JournalController {
    private static Logger log = LoggerFactory.getLogger(JournalController.class);
    @Autowired
    JournalService service;

    /**
     * 日志添加操作
     *
     * @param request
     * @return
     */
    @PostMapping("/insert")
    public JsonResult InsertJournal(HttpServletRequest request) {
        log.info("----InsertJournal----执行");
        JsonResult jsonResult = new JsonResult();
        String[] params = {"jdate", "jtext", "jname"};
        try {
            V.valid(request, params);
            Journal journal = V.entity(request, Journal.class, params);
            int i = service.Jinsert(journal);
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

    @PostMapping("/SelectJ")
    public JsonResult SelectJournal(HttpServletRequest request) {
        log.info("----SelectJournal----执行");
        JsonResult jsonResult = new JsonResult();
        String params = "count";
        try {
            V.val(request, params);
            String count = request.getParameter(params);
            List<Journal> journal = service.SelectJ(Integer.valueOf(count));
            for (int i = 0; i < journal.size(); i++) {
                String date = journal.get(i).getJdate();
                journal.get(i).setJdate(DateFlag.ReqDate(date));
            }
            if (journal.size() == 0) {
                jsonResult.setCode("401");
                jsonResult.setMsg("查询失败");
            } else {
                jsonResult.setData(journal);
            }
        } catch (InvalidParamException e) {
            jsonResult.setCode(e.getCode() + "");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @GetMapping("/selcetCount")
    public JsonResult SelectCount() {
        log.info("----SelectJournal----执行");
        JsonResult jsonResult = new JsonResult();
        Integer integer = service.SelectCount();
        if (integer != null || integer != 0) {
            jsonResult.setData(integer);
        } else {
            jsonResult.setData("没有数据");
            jsonResult.setCode(404 + "");
        }
        return jsonResult;

    }
}
