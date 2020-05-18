package com.qufu.mapper;

import com.qufu.pojo.Release;
import com.qufu.pojo.Releasedata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface ReleaseMapper {
    //添加
    int InsertRelease(Release release);

    //查询 rid和ranme
    Release SelcetRidRname(Release release);

    //查询坐标
    List<Release> SelcetRl();

    //修改成为经纬度
    int Update(Release release);

    //查询 所有信息
    List<Release> SelcetRelease(Releasedata releasedata);

    //查询分页
    Integer SelcetReleaseCont(Releasedata releasedata);

    Release SelectR(@Param("rid") String rid);

    //查询是否重复
    Integer SelectRid(@Param("rid") String rid);
}
