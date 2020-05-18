package com.qufu.utils;

import com.qufu.pojo.Release;
import com.qufu.pojo.User;
import com.qufu.pojo.Videos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 去除重复的元素
 */
public class GetList {
    //视频
    public static List<Videos> getArrayList(List<Videos> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getVideoid() == list.get(i).getVideoid()) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    //用户好友
    public static List<User> getUserlist(List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getId() == list.get(i).getId()) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    //通过距离升序返回店铺信息
    public static List<Release> getReleaselist(List<Release> list) {
        for (int i = 0; i < list.size(); i++) {    //外层循环控制排序轮数
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).getM() > list.get(j + 1).getM()) {
                    Release temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }
}
