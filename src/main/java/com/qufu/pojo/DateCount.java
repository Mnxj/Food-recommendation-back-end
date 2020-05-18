package com.qufu.pojo;


/**
 * 这个是用来存放记录
 */
public class DateCount {
    private int Monday;//周一
    private int Tuesday;//周二
    private int Wednesday;//周三
    private int Thursday;//周四
    private int Friday;//周五
    private int Saturday;//周六
    private int Sunday;//周日
    private int Nowadays;//今日
    private int yesterday;//昨天
    private int SumCount;//总记录

    @Override
    public String toString() {
        return "DateCount{" +
                "Monday=" + Monday +
                ", Tuesday=" + Tuesday +
                ", Wednesday=" + Wednesday +
                ", Thursday=" + Thursday +
                ", Friday=" + Friday +
                ", Saturday=" + Saturday +
                ", Sunday=" + Sunday +
                ", Nowadays=" + Nowadays +
                ", yesterday=" + yesterday +
                ", SumCount=" + SumCount +
                '}';
    }

    public int getMonday() {
        return Monday;
    }

    public void setMonday(int monday) {
        Monday = monday;
    }

    public int getTuesday() {
        return Tuesday;
    }

    public void setTuesday(int tuesday) {
        Tuesday = tuesday;
    }

    public int getWednesday() {
        return Wednesday;
    }

    public void setWednesday(int wednesday) {
        Wednesday = wednesday;
    }

    public int getThursday() {
        return Thursday;
    }

    public void setThursday(int thursday) {
        Thursday = thursday;
    }

    public int getFriday() {
        return Friday;
    }

    public void setFriday(int friday) {
        Friday = friday;
    }

    public int getSaturday() {
        return Saturday;
    }

    public void setSaturday(int saturday) {
        Saturday = saturday;
    }

    public int getSunday() {
        return Sunday;
    }

    public void setSunday(int sunday) {
        Sunday = sunday;
    }

    public int getNowadays() {
        return Nowadays;
    }

    public void setNowadays(int nowadays) {
        Nowadays = nowadays;
    }

    public int getYesterday() {
        return yesterday;
    }

    public void setYesterday(int yesterday) {
        this.yesterday = yesterday;
    }

    public int getSumCount() {
        return SumCount;
    }

    public void setSumCount(int sumCount) {
        SumCount = sumCount;
    }
}
