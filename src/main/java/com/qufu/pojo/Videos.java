package com.qufu.pojo;

import java.util.Date;

public class Videos {
    private int videoid;
    private int id;//用户id
    private String videotitle;//标题
    private String videosource;//描述
    private String videocontent;//内容
    private Date videotime;//时间
    private String rid;//店铺id
    private String videourl;//图片显示
    private String dynamicTags;//标签
    private String radname;
    private String radcode;
    private String vcount;
    private int plcount;
    private String lx;//类型
    private String name;//作者

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

    public int getPlcount() {
        return plcount;
    }

    public void setPlcount(int plcount) {
        this.plcount = plcount;
    }

    public String getVcount() {
        return vcount;
    }

    public void setVcount(String vcount) {
        this.vcount = vcount;
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

    public int getVideoid() {
        return videoid;
    }

    public void setVideoid(int videoid) {
        this.videoid = videoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideotitle() {
        return videotitle;
    }

    public void setVideotitle(String videotitle) {
        this.videotitle = videotitle;
    }

    public String getVideosource() {
        return videosource;
    }

    public void setVideosource(String videosource) {
        this.videosource = videosource;
    }

    public String getVideocontent() {
        return videocontent;
    }

    public void setVideocontent(String videocontent) {
        this.videocontent = videocontent;
    }

    public Date getVideotime() {
        return videotime;
    }

    public void setVideotime(Date videotime) {
        this.videotime = videotime;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getDynamicTags() {
        return dynamicTags;
    }

    public void setDynamicTags(String dynamicTags) {
        this.dynamicTags = dynamicTags;
    }
}
