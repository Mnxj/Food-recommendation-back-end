package com.qufu.pojo;

import java.util.Date;
import java.util.List;

/**
 * 店铺评论实体类
 */
public class Rcomment {
    private int id;//用户id
    private String name;
    private String src;
    private int rcid;
    private String rid;//店铺id
    private Date rcdate;//时间
    private String rctext;//评论内容
    private int rcount;//点赞数
    private int count;//子评论数
    private boolean like;//点赞
    private boolean lflag;//评论的开关
    private boolean button;//按钮的开关
    private int textfont2;//输入框宽度
    private boolean trashflag;//控制显示是不是本人
    private List<Rccomment> rccomment;//子评论

    public boolean isTrashflag() {
        return trashflag;
    }

    public void setTrashflag(boolean trashflag) {
        this.trashflag = trashflag;
    }

    public List<Rccomment> getRccomment() {
        return rccomment;
    }

    public void setRccomment(List<Rccomment> rccomment) {
        this.rccomment = rccomment;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public int getTextfont2() {
        return textfont2;
    }

    public void setTextfont2(int textfont2) {
        this.textfont2 = textfont2;
    }

    public boolean isLflag() {
        return lflag;
    }

    public void setLflag(boolean lflag) {
        this.lflag = lflag;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
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

    public int getRcid() {
        return rcid;
    }

    public void setRcid(int rcid) {
        this.rcid = rcid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Date getRcdate() {
        return rcdate;
    }

    public void setRcdate(Date rcdate) {
        this.rcdate = rcdate;
    }

    public String getRctext() {
        return rctext;
    }

    public void setRctext(String rctext) {
        this.rctext = rctext;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rcomment{" +
                "rcid=" + rcid +
                ", rid='" + rid + '\'' +
                ", rcdate=" + rcdate +
                ", rctext='" + rctext + '\'' +
                ", rcount=" + rcount +
                ", count=" + count +
                ", like=" + like +
                ", lflag=" + lflag +
                ", button=" + button +
                ", textfont2=" + textfont2 +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", src='" + src + '\'' +
                '}';
    }
}
