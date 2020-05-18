package com.qufu.pojo;

import java.util.Date;

public class Vccomment {
    private int vccid;
    private String vcctext;//内容
    private Date vccdate;//时间
    private int vcid;//用户评论
    private int id1;//回复人id
    private String name1;
    private String name2;
    private int id2;//评论人id
    private boolean vlike;//点赞
    private int vcount;//点赞数
    private String src;//头像
    private boolean Vhf;//显示回复的输入框
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

    public boolean isVhf() {
        return Vhf;
    }

    public void setVhf(boolean vhf) {
        Vhf = vhf;
    }

    private boolean vtrashflag2;//控制显示是不是本人

    public int getVccid() {
        return vccid;
    }

    public void setVccid(int vccid) {
        this.vccid = vccid;
    }

    public String getVcctext() {
        return vcctext;
    }

    public void setVcctext(String vcctext) {
        this.vcctext = vcctext;
    }

    public Date getVccdate() {
        return vccdate;
    }

    public void setVccdate(Date vccdate) {
        this.vccdate = vccdate;
    }

    public int getVcid() {
        return vcid;
    }

    public void setVcid(int vcid) {
        this.vcid = vcid;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
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

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public boolean isVlike() {
        return vlike;
    }

    public void setVlike(boolean vlike) {
        this.vlike = vlike;
    }

    public int getVcount() {
        return vcount;
    }

    public void setVcount(int vcount) {
        this.vcount = vcount;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }


    public boolean isVtrashflag2() {
        return vtrashflag2;
    }

    public void setVtrashflag2(boolean vtrashflag2) {
        this.vtrashflag2 = vtrashflag2;
    }
}
