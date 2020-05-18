package com.qufu.pojo;


import java.util.Date;
import java.util.List;


/**
 * 用户信息的实体类
 */

public class User {
    private int id;//id
    private Integer Anumber;//账号
    private String password;//密码
    private int age;//年龄
    private String name;//用户名
    private String Emeil;//邮箱
    private String gender;//性别
    private Date birthday;//生日
    private String signature;//个性签名
    private Date Creationdate;//创建时间
    private String src;//头像地址
    private boolean newsfalg;//消息boolean

    private int vcount;//vlog总数
    private int ecount;//文章总数
    private int ewcount;//好友总数

    public int getVcount() {
        return vcount;
    }

    public void setVcount(int vcount) {
        this.vcount = vcount;
    }

    public int getEcount() {
        return ecount;
    }

    public void setEcount(int ecount) {
        this.ecount = ecount;
    }

    public int getEwcount() {
        return ewcount;
    }

    public void setEwcount(int ewcount) {
        this.ewcount = ewcount;
    }

    public int getAge() {
        return age;
    }

    public boolean isNewsfalg() {
        return newsfalg;
    }

    public void setNewsfalg(boolean newsfalg) {
        this.newsfalg = newsfalg;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAnumber() {
        return Anumber;
    }

    public void setAnumber(Integer anumber) {
        Anumber = anumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmeil() {
        return Emeil;
    }

    public void setEmeil(String emeil) {
        Emeil = emeil;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Date birthday) {
        Date date = new Date();
        Integer age = date.getYear() - birthday.getYear();
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getCreationdate() {
        return Creationdate;
    }

    public void setCreationdate(Date creationdate) {
        Creationdate = creationdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Anumber=" + Anumber +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", Emeil='" + Emeil + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", signature='" + signature + '\'' +
                ", Creationdate=" + Creationdate +
                ", src='" + src + '\'' +
                ", newsfalg=" + newsfalg +
                '}';
    }
}
