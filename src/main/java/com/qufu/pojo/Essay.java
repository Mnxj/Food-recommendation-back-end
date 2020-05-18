package com.qufu.pojo;

import java.util.Date;

/**
 * 日志类
 */
public class Essay {
    private int essayid;
    private int id;//用户id
    private String essaytitle;//标题
    private String essaysource;//描述
    private String essaycontent;//内容
    private Date essaytime;//时间
    private String rid;//店铺id
    private String essayurl;//图片显示
    private String dynamicTags;//标签
    private String radname;
    private String radcode;
    private int ecount;//观看总数
    private int elikecount;//喜欢总数
    private int ercomment;//总评论数
    private boolean likeflag;//判断是不是本人点的
    private String lx;//类型
    private String name;//名字
    private String esticky;//置顶

    public String getEsticky() {
        return esticky;
    }

    public void setEsticky(String esticky) {
        this.esticky = esticky;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public boolean isLikeflag() {
        return likeflag;
    }

    public void setLikeflag(boolean likeflag) {
        this.likeflag = likeflag;
    }

    public int getErcomment() {
        return ercomment;
    }

    public void setErcomment(int ercomment) {
        this.ercomment = ercomment;
    }

    public int getElikecount() {
        return elikecount;
    }

    public void setElikecount(int elikecount) {
        this.elikecount = elikecount;
    }

    public int getEcount() {
        return ecount;
    }

    public void setEcount(int ecount) {
        this.ecount = ecount;
    }

    public String getRadname() {
        return radname;
    }

    public void setRadname(String radname) {
        this.radname = radname;
    }

    public String getRadcode() {
        return radcode;
    }

    public void setRadcode(String radcode) {
        this.radcode = radcode;
    }

    public String getDynamicTags() {
        return dynamicTags;
    }

    public void setDynamicTags(String dynamicTags) {
        this.dynamicTags = dynamicTags;
    }

    public int getEssayid() {
        return essayid;
    }

    public void setEssayid(int essayid) {
        this.essayid = essayid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEssaytitle() {
        return essaytitle;
    }

    public void setEssaytitle(String essaytitle) {
        this.essaytitle = essaytitle;
    }

    public String getEssaysource() {
        return essaysource;
    }

    public void setEssaysource(String essaysource) {
        this.essaysource = essaysource;
    }

    public String getEssaycontent() {
        return essaycontent;
    }

    public void setEssaycontent(String essaycontent) {
        this.essaycontent = essaycontent;
    }

    public Date getEssaytime() {
        return essaytime;
    }

    public void setEssaytime(Date essaytime) {
        this.essaytime = essaytime;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getEssayurl() {
        return essayurl;
    }

    public void setEssayurl(String essayurl) {
        this.essayurl = essayurl;
    }

    @Override
    public String toString() {
        return "Essay{" +
                "essayid=" + essayid +
                ", id=" + id +
                ", essaytitle='" + essaytitle + '\'' +
                ", essaysource='" + essaysource + '\'' +
                ", essaycontent='" + essaycontent + '\'' +
                ", essaytime=" + essaytime +
                ", rid='" + rid + '\'' +
                ", essayurl='" + essayurl + '\'' +
                ", dynamicTags='" + dynamicTags + '\'' +
                '}';
    }
}
