package com.qufu.mapper;

import com.qufu.pojo.Journal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface JournalMapper {
    //添加日志
    int Jinsert(Journal journal);

    //暂时先查询全部
    // 查询所有日志
    List<Journal> SelectJ(@Param("count") int count);

    //查询总数
    Integer SelectCount();
}
