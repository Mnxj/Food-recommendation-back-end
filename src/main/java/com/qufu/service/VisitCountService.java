package com.qufu.service;

import com.qufu.pojo.DateCount;

public interface VisitCountService {

    /**
     * 访问记录逻辑
     * 1.今天是不是第一次
     * 2.如果是就创建
     * 3、不是的话让访问记录++
     *
     * @return
     */
    void Count();

    /**
     * 把查到的所有访问记录放在DateCount中
     * 1.
     *
     * @return
     */
    DateCount SelectAll();
}
