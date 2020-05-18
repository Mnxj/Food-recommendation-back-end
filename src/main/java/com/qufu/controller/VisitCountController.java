package com.qufu.controller;


import com.qufu.pojo.DateCount;
import com.qufu.service.VisitCountServiceImpl;
import com.qufu.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visit")
public class VisitCountController {
    private static Logger log = LoggerFactory.getLogger(VisitCountController.class);
    @Autowired
    VisitCountServiceImpl countService;

    /**
     * 访问记录逻辑
     * 1.今天是不是第一次
     * 2.如果是就创建
     * 3、不是的话让访问记录++
     *
     * @return
     */
    @GetMapping("/count")
    public void Count() {
        log.info("执行-----countService.Count----");
        countService.Count();

    }

    /**
     * 把查到的所有访问记录放在DateCount中
     * 1.
     *
     * @return
     */
    @GetMapping("/selectall")
    public JsonResult SelectAll() {
        log.info("执行-----countService.SelectAll()----");
        DateCount count = countService.SelectAll();
        return new JsonResult(count, "访问记录获取成功");
    }

}
