package com.qufu.service;

import com.qufu.pojo.Journal;

import java.util.List;

public interface JournalService {
    //添加日志
    int Jinsert(Journal journal);

    //暂时先查询全部
    // 查询所有日志
    List<Journal> SelectJ(int count);

    //查询总数
    Integer SelectCount();
}
