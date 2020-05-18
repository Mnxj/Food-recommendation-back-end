package com.qufu.pojo;

import java.util.Date;
import java.util.List;

/**
 * 店铺评论实体类
 */
public class Vcomment {
    private int id;//用户id
    private String name;
    private String src;
    private int vcid;
    private String videoid;//视频id
    private Date vcdate;//时间
    private String vctext;//评论内容
    private int vcount;//点赞数
    private int count;//子评论数
    private boolean vlike;//点赞
    private boolean vlflag;//评论的开关
    private boolean vbutton;//按钮的开关
    private int vtextfont2;//输入框宽度
    private boolean vtrashflag;//控制显示是不是本人
    private List<Vccomment> vccomment;//子评论

    @Override
    public String toString() {
        return "Vcomment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", src='" + src + '\'' +
                ", vcid=" + vcid +
                ", videoid='" + videoid + '\'' +
                ", vcdate=" + vcdate +
                ", vctext='" + vctext + '\'' +
                ", vcount=" + vcount +
                ", count=" + count +
                ", vlike=" + vlike +
                ", vlflag=" + vlflag +
                ", vbutton=" + vbutton +
                ", vtextfont2=" + vtextfont2 +
                ", vtrashflag=" + vtrashflag +
                ", vccomment=" + vccomment +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getVcid() {
        return vcid;
    }

    public void setVcid(int vcid) {
        this.vcid = vcid;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }

    public Date getVcdate() {
        return vcdate;
    }

    public void setVcdate(Date vcdate) {
        this.vcdate = vcdate;
    }

    public String getVctext() {
        return vctext;
    }

    public void setVctext(String vctext) {
        this.vctext = vctext;
    }

    public int getVcount() {
        return vcount;
    }

    public void setVcount(int vcount) {
        this.vcount = vcount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isVlike() {
        return vlike;
    }

    public void setVlike(boolean vlike) {
        this.vlike = vlike;
    }

    public boolean isVlflag() {
        return vlflag;
    }

    public void setVlflag(boolean vlflag) {
        this.vlflag = vlflag;
    }

    public boolean isVbutton() {
        return vbutton;
    }

    public void setVbutton(boolean vbutton) {
        this.vbutton = vbutton;
    }

    public int getVtextfont2() {
        return vtextfont2;
    }

    public void setVtextfont2(int vtextfont2) {
        this.vtextfont2 = vtextfont2;
    }

    public boolean isVtrashflag() {
        return vtrashflag;
    }

    public void setVtrashflag(boolean vtrashflag) {
        this.vtrashflag = vtrashflag;
    }

    public List<Vccomment> getVccomment() {
        return vccomment;
    }

    public void setVccomment(List<Vccomment> vccomment) {
        this.vccomment = vccomment;
    }
}
