package com.qufu.service;


import com.qufu.exception.InvalidParamException;
import com.qufu.mapper.ReleaseMapper;
import com.qufu.pojo.Release;
import com.qufu.pojo.Releasedata;
import com.qufu.pojo.VisitCount;
import com.qufu.utils.Distance;
import com.qufu.utils.E;
import com.qufu.utils.GetList;
import com.qufu.utils.RadomMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    @Autowired
    ReleaseMapper mapper;

    /**
     * 创建
     *
     * @param release
     * @return
     */
    @Override
    public int InsertRelease(Release release) {
        VisitCount count = new VisitCount();
        release.setRdate(count.getVisittime());
        String str;
        while (true) {
            str = RadomMath.RId();
            if (mapper.SelectRid(str) == null) {
                break;
            }
        }
        release.setRid(str);
        return mapper.InsertRelease(release);
    }

    /**
     * 查询
     *
     * @param release
     * @return
     */
    @Override
    public Release SelcetRidRname(Release release) {
        if (release.getRname() == null) {
            throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE, E.INVALID_PARAM_INFO);
        } else {
            return mapper.SelcetRidRname(release);
        }

    }

    @Override
    public List<Release> SelcetRl() {
        return mapper.SelcetRl();
    }

    @Override
    public int Update(Release release) {
        return mapper.Update(release);
    }

    /**
     * 根据经纬度然后进行计算离你最近的
     *
     * @return
     */
    @Override
    public List<Release> SelcetRelease(double rlong, double rlat, Integer m, String rtype) {
        double rlong1, rlong2, rlat1, rlat2, latDegrees, lngDegree;
        lngDegree = Distance.doLngDegress(m, rlat);
        latDegrees = Distance.doLatDegress(m);
        rlong1 = rlong - lngDegree;
        rlong2 = rlong + lngDegree;
        rlat1 = rlat - latDegrees;
        rlat2 = rlat + latDegrees;
        Releasedata releasedata = new Releasedata();
        releasedata.setRlat1(rlat1);
        releasedata.setRlat2(rlat2);
        releasedata.setRlong1(rlong1);
        releasedata.setRlong2(rlong2);
        releasedata.setRtype(rtype);
        List<Release> list = mapper.SelcetRelease(releasedata);
        for (int i = 0; i < list.size(); i++) {
            int km = (int) Distance.Distance(rlong, rlat,
                    Double.parseDouble(list.get(i).getRlong()), Double.parseDouble(list.get(i).getRlat()));
            list.get(i).setKM(km > 1000 ? km / 1000 + "km" : km + "m");
            list.get(i).setM(km);
        }

        //做一个升序操作
        List<Release> release2 = GetList.getReleaselist(list);
        return release2;
    }

    /**
     * @return
     */
    @Override
    public Integer SelcetReleaseCont(double rlong, double rlat, Integer m, String rtype) {
        double rlong1, rlong2, rlat1, rlat2, latDegrees, lngDegree;
        lngDegree = Distance.doLngDegress(m, rlat);
        latDegrees = Distance.doLatDegress(m);
        rlong1 = rlong - lngDegree;
        rlong2 = rlong + lngDegree;
        rlat1 = rlat - latDegrees;
        rlat2 = rlat + latDegrees;
        Releasedata releasedata = new Releasedata();
        releasedata.setRlat1(rlat1);
        releasedata.setRlat2(rlat2);
        releasedata.setRlong1(rlong1);
        releasedata.setRlong2(rlong2);
        releasedata.setRtype(rtype);
        return mapper.SelcetReleaseCont(releasedata);
    }
}
