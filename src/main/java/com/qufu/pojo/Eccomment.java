package com.qufu.pojo;

import java.util.Date;

public class Eccomment {
    private int eccid;
    private String ecctext;//内容
    private Date eccdate;//时间
    private int ecid;//用户评论
    private int id1;//回复人id
    private String name1;
    private String name2;
    private int id2;//评论人id
    private boolean elike;//点赞
    private int ecount;//点赞数
    private String src;//头像
    private boolean ehf;//显示回复的输入框
    private boolean etrashflag2;//控制显示是不是本人
    private boolean vbutton;//按钮
    private int textfont2;//输入框宽度

    public int getTextfont2() {
        return textfont2;
    }

    public void setTextfont2(int textfont2) {
        this.textfont2 = textfont2;
    }

    public boolean isEhf() {
        return ehf;
    }

    public void setEhf(boolean ehf) {
        this.ehf = ehf;
    }

    public boolean isVbutton() {
        return vbutton;
    }

    public void setVbutton(boolean vbutton) {
        this.vbutton = vbutton;
    }

    public int getEcid() {
        return ecid;
    }

    public void setEcid(int ecid) {
        this.ecid = ecid;
    }

    public int getEccid() {
        return eccid;
    }

    public void setEccid(int eccid) {
        this.eccid = eccid;
    }

    public String getEcctext() {
        return ecctext;
    }

    public void setEcctext(String ecctext) {
        this.ecctext = ecctext;
    }

    public Date getEccdate() {
        return eccdate;
    }

    public void setEccdate(Date eccdate) {
        this.eccdate = eccdate;
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

    public boolean isElike() {
        return elike;
    }

    public void setElike(boolean elike) {
        this.elike = elike;
    }

    public int getEcount() {
        return ecount;
    }

    public void setEcount(int ecount) {
        this.ecount = ecount;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public boolean isEtrashflag2() {
        return etrashflag2;
    }

    public void setEtrashflag2(boolean etrashflag2) {
        this.etrashflag2 = etrashflag2;
    }
}
