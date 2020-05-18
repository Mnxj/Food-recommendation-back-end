package com.qufu.pojo;

import java.util.Date;
import java.util.List;

/**
 * 店铺评论实体类
 */
public class Ecomment {
    private int id;//用户id
    private String name;
    private String src;
    private int ecid;
    private String essayid;//店铺id
    private Date ecdate;//时间
    private String ectext;//评论内容
    private int ecount;//点赞数
    private int count;//子评论数
    private boolean elike;//点赞
    private boolean elflag;//评论的开关
    private boolean ebutton;//按钮的开关
    private int etextfont2;//输入框宽度
    private boolean etrashflag;//控制显示是不是本人
    private List<Eccomment> eccomment;//子评论

    public String getEssayid() {
        return essayid;
    }

    public void setEssayid(String essayid) {
        this.essayid = essayid;
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

    public int getEcid() {
        return ecid;
    }

    public void setEcid(int ecid) {
        this.ecid = ecid;
    }


    public Date getEcdate() {
        return ecdate;
    }

    public void setEcdate(Date ecdate) {
        this.ecdate = ecdate;
    }

    public String getEctext() {
        return ectext;
    }

    public void setEctext(String ectext) {
        this.ectext = ectext;
    }

    public int getEcount() {
        return ecount;
    }

    public void setEcount(int ecount) {
        this.ecount = ecount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isElike() {
        return elike;
    }

    public void setElike(boolean elike) {
        this.elike = elike;
    }

    public boolean isElflag() {
        return elflag;
    }

    public void setElflag(boolean elflag) {
        this.elflag = elflag;
    }

    public boolean isEbutton() {
        return ebutton;
    }

    public void setEbutton(boolean ebutton) {
        this.ebutton = ebutton;
    }

    public int getEtextfont2() {
        return etextfont2;
    }

    public void setEtextfont2(int etextfont2) {
        this.etextfont2 = etextfont2;
    }

    public boolean isEtrashflag() {
        return etrashflag;
    }

    public void setEtrashflag(boolean etrashflag) {
        this.etrashflag = etrashflag;
    }

    public List<Eccomment> getEccomment() {
        return eccomment;
    }

    public void setEccomment(List<Eccomment> eccomment) {
        this.eccomment = eccomment;
    }
}
