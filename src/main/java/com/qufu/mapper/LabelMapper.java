package com.qufu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface LabelMapper {
    /**
     * 添加标签
     *
     * @param latext
     * @return
     */
    int insertL(@Param("latext") String latext);

    /**
     * 删除标签
     *
     * @param latext
     * @return
     */
    int delL(@Param("latext") String latext);

    /**
     * |
     * 判断重复
     *
     * @param latext
     * @return
     */
    String SelecetL(@Param("latext") String latext);

    /**
     * 查询所有的
     *
     * @return
     */
    List<String> SelecetAll();
}
