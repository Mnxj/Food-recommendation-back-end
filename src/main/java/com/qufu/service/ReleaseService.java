package com.qufu.service;


import com.qufu.pojo.Release;
import com.qufu.pojo.Releasedata;

import java.util.List;

/**
 * 插入操作
 */
public interface ReleaseService {
    int InsertRelease(Release release);

    Release SelcetRidRname(Release release);

    //查询坐标
    List<Release> SelcetRl();

    //修改成为经纬度
    int Update(Release release);

    Integer SelcetReleaseCont(double rlong, double rlat1, Integer m, String rtype);

    List<Release> SelcetRelease(double rlong, double rlat1, Integer m, String rtype);
}
