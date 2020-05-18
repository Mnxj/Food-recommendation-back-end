package com.qufu.utils;

import java.math.BigDecimal;

public class Distance {

    private static double R = 6378137; // 地球半径

    /**
     * 计算地球上任意两点(经纬度)距离
     *
     * @param long1 第一点经度
     * @param lat1  第一点纬度
     * @param long2 第二点经度
     * @param lat2  第二点纬度
     * @return 返回距离 单位：米
     */
    public static double Distance(double long1, double lat1, double long2,
                                  double lat2) {
        double a, b;

        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    /**
     * 计算思
     * 1. 就是 水平平移的距度（d*sinα）除以 当前纬度切面周长（2π*r），再每乘以360度) ，就知道了水平横向平移了多少度
     * 2. 比较简单，就是, 垂直平移的距离d（d*cosα）除以 地球纵向周长，再乘上360度，就知道纵向平移了多少度
     * long2 =d*sinα/[r*cos(lat1)*2π/360]
     * lat2 = d*cosα/ (r*2π/360)
     * 距离转换成经度
     *
     * @param distance
     * @return
     */
    public static Double doLngDegress(double distance, double lat) {
        double lngDegree = distance / (R * Math.cos(Math.toRadians(lat)));
        lngDegree = lngDegree * (180 / Math.PI);
        BigDecimal b = new BigDecimal(lngDegree);
        double f1 = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    /**
     * 距离转换成纬度 就是圆心角度数
     *
     * @param distance
     * @return
     */
    public static Double doLatDegress(double distance) {
        double latDegrees = distance / R;
        // 转换弧度
        latDegrees = latDegrees * (180 / Math.PI);
        BigDecimal b = new BigDecimal(latDegrees);
        double f1 = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public static void main(String[] args) {

        System.out.println(doLatDegress(500));
        System.out.println(doLngDegress(500, 23.14));
    }
}
