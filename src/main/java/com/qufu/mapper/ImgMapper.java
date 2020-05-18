package com.qufu.mapper;


import com.qufu.pojo.Img;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ImgMapper {
    /**
     * 添加
     *
     * @param img
     * @return
     */
    int ImgInsert(Img img);
}
