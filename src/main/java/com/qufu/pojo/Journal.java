package com.qufu.pojo;

//日志的实体类
public class Journal {
    private int jid;
    private String jdate;
    private String jtext;
    private String jname;

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public String getJdate() {
        return jdate;
    }

    public void setJdate(String jdate) {
        this.jdate = jdate;
    }

    public String getJtext() {
        return jtext;
    }

    public void setJtext(String jtext) {
        this.jtext = jtext;
    }
}
