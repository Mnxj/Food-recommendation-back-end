package com.qufu.pojo;


/**
 * 发布管理的实体类
 */
public class Release {
    private String rid;//唯一标识
    private String rname;//名称
    private String rdate;//时间
    private String rtype;//类型
    private String rjies;//介绍
    private String rurl;//首图
    private String radname;//城市
    private String radcode;//城市code
    private String rating;//评分
    private String rcost;//人均消费
    private String rtel;//电话
    private String rtag;//标签
    private String rlocation;//坐标
    private String rlong;//精度
    private String rlat;//维度
    private String KM;//距离
    private int m;//暂存距离最为一个降序的依据
    private String lx;//类型

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public Release() {
    }

    public Release(String rid, String rname, String rdate, String rtype, String rjies, String rurl, String radname, String radcode, String rating, String rcost, String rtel, String rtag, String rlocation, String rlong, String rlat) {
        this.rid = rid;
        this.rname = rname;
        this.rdate = rdate;
        this.rtype = rtype;
        this.rjies = rjies;
        this.rurl = rurl;
        this.radname = radname;
        this.radcode = radcode;
        this.rating = rating;
        this.rcost = rcost;
        this.rtel = rtel;
        this.rtag = rtag;
        this.rlocation = rlocation;
        this.rlong = rlong;
        this.rlat = rlat;
    }

    public String getKM() {
        return KM;
    }

    public void setKM(String KM) {
        this.KM = KM;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getRjies() {
        return rjies;
    }

    public void setRjies(String rjies) {
        this.rjies = rjies;
    }

    public String getRurl() {
        return rurl;
    }

    public void setRurl(String rurl) {
        this.rurl = rurl;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRcost() {
        return rcost;
    }

    public void setRcost(String rcost) {
        this.rcost = rcost;
    }

    public String getRtel() {
        return rtel;
    }

    public void setRtel(String rtel) {
        this.rtel = rtel;
    }

    public String getRtag() {
        return rtag;
    }

    public void setRtag(String rtag) {
        this.rtag = rtag;
    }

    public String getRlocation() {
        return rlocation;
    }

    public void setRlocation(String rlocation) {
        this.rlocation = rlocation;
    }

    public String getRlong() {
        return rlong;
    }

    public void setRlong(String rlong) {
        this.rlong = rlong;
    }

    public String getRlat() {
        return rlat;
    }

    public void setRlat(String rlat) {
        this.rlat = rlat;
    }

    @Override
    public String toString() {
        return "Release{" +
                "rid='" + rid + '\'' +
                ", rname='" + rname + '\'' +
                ", rdate='" + rdate + '\'' +
                ", rtype='" + rtype + '\'' +
                ", rjies='" + rjies + '\'' +
                ", rurl='" + rurl + '\'' +
                ", radname='" + radname + '\'' +
                ", radcode='" + radcode + '\'' +
                ", rating='" + rating + '\'' +
                ", rcost='" + rcost + '\'' +
                ", rtel='" + rtel + '\'' +
                ", rtag='" + rtag + '\'' +
                ", rlocation='" + rlocation + '\'' +
                ", rlong=" + rlong +
                ", rlat=" + rlat +
                ", KM='" + KM + '\'' +
                '}';
    }
}
