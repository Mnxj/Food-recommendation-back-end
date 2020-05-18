package com.qufu.pojo;

/**
 * 子评论的实体类
 */
public class VLike2 {
    private int lid2;
    private int vcid;
    private String vllik2;
    private int id;
    private int vccid;

    @Override
    public String toString() {
        return "VLike2{" +
                "lid2=" + lid2 +
                ", vcid=" + vcid +
                ", vllik2='" + vllik2 + '\'' +
                ", id=" + id +
                ", vccid=" + vccid +
                '}';
    }

    public int getLid2() {
        return lid2;
    }

    public void setLid2(int lid2) {
        this.lid2 = lid2;
    }

    public int getVcid() {
        return vcid;
    }

    public void setVcid(int vcid) {
        this.vcid = vcid;
    }

    public String getVllik2() {
        return vllik2;
    }

    public void setVllik2(String vllik2) {
        this.vllik2 = vllik2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVccid() {
        return vccid;
    }

    public void setVccid(int vccid) {
        this.vccid = vccid;
    }
}
