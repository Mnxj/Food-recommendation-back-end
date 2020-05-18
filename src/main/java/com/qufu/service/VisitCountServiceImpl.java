package com.qufu.service;


import com.qufu.controller.VisitCountController;
import com.qufu.mapper.VisitCountMapper;
import com.qufu.pojo.DateCount;
import com.qufu.pojo.VisitCount;
import com.qufu.utils.DateFlag;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VisitCountServiceImpl implements VisitCountService {
    private static Logger log = LoggerFactory.getLogger(VisitCountController.class);
    //注入
    @Autowired
    VisitCountMapper visitCountMapper;


    public void Count() {
        log.info("Count--访问记录逻辑开始执行");
        VisitCount visitCount = new VisitCount();
        try {
            int i = visitCountMapper.SelecTime(visitCount.getVisittime());
            log.info("访问记录增加");
            visitCount.setVisitnumber(i);
            int j = visitCountMapper.Uapdete(visitCount);
            if (j == 1) {
                log.info("增加成功");
            }
        } catch (BindingException e) {
            log.info("您是第一个访问的人");
            int x = visitCountMapper.InsertCount(visitCount);
        }
    }

    public DateCount SelectAll() {
        log.info("访问记录查询");
        DateCount count = new DateCount();
        VisitCount vCount = new VisitCount();
        String yesday = DateFlag.TimeJ(vCount.getVisittime(), 1);
        List<VisitCount> visitCounts = visitCountMapper.SelectAll();
        // 记录 时间
        for (VisitCount visitCount : visitCounts) {
            //今天
            if (visitCount.getVisittime().equals(vCount.getVisittime())) {
                count.setNowadays(visitCount.getVisitnumber() + count.getNowadays());
            }
            if (visitCount.getVisittime().equals(yesday)) {
                count.setYesterday(visitCount.getVisitnumber() + count.getYesterday());
            }
            //总
            count.setSumCount(visitCount.getVisitnumber() + count.getSumCount());
            try {
                String work = DateFlag.dayForWeek(visitCount.getVisittime());
                int x = Integer.valueOf(work);
                switch (x) {
                    case 1:
                        count.setMonday(visitCount.getVisitnumber() + count.getMonday());
                        break;
                    case 2:
                        count.setTuesday(visitCount.getVisitnumber() + count.getTuesday());
                        break;
                    case 3:
                        count.setWednesday(visitCount.getVisitnumber() + count.getWednesday());
                        break;
                    case 4:
                        count.setThursday(visitCount.getVisitnumber() + count.getThursday());
                        break;
                    case 5:
                        count.setFriday(visitCount.getVisitnumber() + count.getFriday());
                        break;
                    case 6:
                        count.setSaturday(visitCount.getVisitnumber() + count.getSaturday());
                        break;
                    case 7:
                        count.setSunday(visitCount.getVisitnumber() + count.getSunday());

                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return count;
    }
}
