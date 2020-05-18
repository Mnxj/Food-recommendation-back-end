package com.qufu.pojo;

public class Paragraph {
    private int pid;
    private String ptext;

    public Paragraph(int pid, String ptext) {
        this.pid = pid;
        this.ptext = ptext;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPtext() {
        return ptext;
    }

    public void setPtext(String ptext) {
        this.ptext = ptext;
    }

    @Override
    public String toString() {
        return "Paragraph{" +
                "pid=" + pid +
                ", ptext='" + ptext + '\'' +
                '}';
    }
}
