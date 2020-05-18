package com.qufu.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期判断周几
 */
public class DateFlag {
    public static String dayForWeek(String time) throws Throwable {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //将字符串解析成日期格式
        Date tmpDate = format.parse(time);
        //不仅能获取当前的时间，还能指定需要获取的时间点
        Calendar cal = Calendar.getInstance();
        //用这个是因为他到周末就是0
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        try {
            //把我输入的时间放入
            cal.setTime(tmpDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    //时间计算
    public static String TimeJ(String time, int x) {

        String s = time.substring(time.length() - 2, time.length());
        time = time.substring(0, time.length() - 2);
        int y = Integer.valueOf(s);
        if ((y - x) < 10) {
            time += "0";
        }
        time += String.valueOf(y - x);

        return time;
    }

    //把字符串类型的时间转换为时间格式
    public static Date Time(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //将字符串解析成日期格式
            Date tmpDate = format.parse(time);
            return tmpDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //把-替换为/
    public static String ReqDate(String date) {
        return date.replace("-", "/");
    }

    //返回当前时间
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


}
