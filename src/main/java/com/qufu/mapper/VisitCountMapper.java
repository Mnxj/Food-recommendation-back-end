package com.qufu.mapper;


import com.qufu.pojo.VisitCount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper是mybatis中的一个接口
 */
@Component
@Mapper
public interface VisitCountMapper {
    /**
     * 查询所有
     *
     * @return
     */
    List<VisitCount> SelectAll();

    /**
     * 查询今天是否第一次访问该网站
     *
     * @param time 时间
     * @return 结果
     */
    int SelecTime(String visittime);

    /**
     * @return
     */
    int SelectInt();

    /**
     * 不存在今天的记录就创建一个
     *
     * @param visitCount
     * @return
     */
    int InsertCount(VisitCount visitCount);

    /**
     * 更新操作
     *
     * @return
     */
    int Uapdete(VisitCount visitCount);
}
