package com.qufu.utils;


import com.qufu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RadomMath {
    @Autowired
    UserService userService;

    /**
     * 在添加好友后
     * 生成一个聊天室频道
     *
     * @return
     */
    public static int UserWeb() {
        Random rd = new Random();
        int x = rd.nextInt(1000000) + 1000000;
        return x;
    }

    /**
     * 生成一个验证码用来邮箱验证
     *
     * @return
     */
    public static int R() {
        Random rd = new Random();
        int x = rd.nextInt(100000) + 100000;
        return x;
    }

    /**
     * 生成一个账户
     * 可以使用list.indexof判断
     * 1.先查询所有的id账号
     *
     * @param list
     * @return
     */
    public static int Id(List<Integer> list) {
        Random rd = new Random();
        int x;
        while (true) {
            x = rd.nextInt(100000000) + 10000000;
            if (list.indexOf(x) == -1) {
                break;
            }
        }
        return x;
    }

    /**
     * 生成一个账户
     *
     * @return
     */
    public static String RId() {
        Random rd = new Random();
        int x = rd.nextInt(1000000000) + 1000000000;
        return x + "";
    }

    /**
     * 段子API生成器
     */
    public static int Pid() {
        Random rd = new Random();
        int x = rd.nextInt(1620) + 1;
        return x;
    }


    public static void main(String[] args) {
        System.out.println(RId());
    }

}
