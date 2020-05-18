package com.qufu.pojo;

import java.util.Date;

public class Rccomment {
    private int rccid;
    private String rcctext;//内容
    private Date rccdate;//时间
    private int rcid;//用户评论
    private int id1;//回复人id
    private String name1;
    private String name2;
    private int id2;//评论人id
    private boolean like;//点赞
    private int rcount;//点赞数
    private String src;//头像
    private boolean Hf;//显示回复的输入框
    private boolean trashflag2;//控制显示是不是本人
    private boolean vbutton;//按钮
    private int textfont2;//输入框宽度

    public int getTextfont2() {
        return textfont2;
    }

    public void setTextfont2(int textfont2) {
        this.textfont2 = textfont2;
    }

    public boolean isVbutton() {
        return vbutton;
    }

    public void setVbutton(boolean vbutton) {
        this.vbutton = vbutton;
    }

    public boolean isHf() {
        return Hf;
    }

    public void setHf(boolean hf) {
        Hf = hf;
    }

    public boolean isTrashflag2() {
        return trashflag2;
    }

    public void setTrashflag2(boolean trashflag2) {
        this.trashflag2 = trashflag2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getRccid() {
        return rccid;
    }

    public void setRccid(int rccid) {
        this.rccid = rccid;
    }

    public String getRcctext() {
        return rcctext;
    }

    public void setRcctext(String rcctext) {
        this.rcctext = rcctext;
    }

    public Date getRccdate() {
        return rccdate;
    }

    public void setRccdate(Date rccdate) {
        this.rccdate = rccdate;
    }

    public int getRcid() {
        return rcid;
    }

    public void setRcid(int rcid) {
        this.rcid = rcid;
    }

    @Override
    public String toString() {
        return "Rccomment{" +
                "rccid=" + rccid +
                ", rcctext='" + rcctext + '\'' +
                ", rccdate=" + rccdate +
                ", rcid=" + rcid +
                ", id1=" + id1 +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", id2=" + id2 +
                ", like=" + like +
                ", rcount=" + rcount +
                ", src='" + src + '\'' +
                '}';
    }
}
