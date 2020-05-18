package com.qufu.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 思路
 * 1。先判断当时是不是第一次访问网页
 */
public class VisitCount {
    private int visitid;
    private int visitnumber;
    private String visittime;

    public VisitCount() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        this.visittime = format;
        this.visitnumber = 1;
    }

    public int getVisitid() {
        return visitid;
    }

    public void setVisitid(int visitid) {

        this.visitid = visitid;
    }

    public int getVisitnumber() {
        return visitnumber;
    }

    public void setVisitnumber(int visitnumber) {
        this.visitnumber = visitnumber;
    }

    public String getVisittime() {
        return visittime;
    }

    @Override
    public String toString() {
        return "VisitCount{" +
                "visitid=" + visitid +
                ", visitnumber=" + visitnumber +
                ", visittime='" + visittime + '\'' +
                '}';
    }
}
