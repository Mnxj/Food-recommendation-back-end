package com.qufu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ParagraphMapper {
    String SelectText(int pid);

}
