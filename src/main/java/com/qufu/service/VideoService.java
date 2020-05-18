package com.qufu.service;

import com.qufu.pojo.Essay;
import com.qufu.pojo.Videos;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VideoService {
    /**
     * 视频日志对象
     * 1，id、rid、
     *
     * @param
     * @return
     */
    int addVideo(Videos video);

    /**
     * 查询视频
     *
     * @param rid
     * @return
     */
    List<Videos> SelectAll(String rid, String id);

    List<Videos> SelectAllEr(String id);

    /**
     * 删除
     *
     * @param videoid
     * @return
     */
    int deleteE(String[] url, String videoid, HttpServletRequest request);

    /**
     * 添加记录
     *
     * @return
     */
    int addVcount(int videoid);

    /**
     * 根据城市名字查询
     *
     * @return
     */
    List<Videos> SelectAllRname(String radname, String radcode, int pag);

    /**
     * 根据城市名字查询
     *
     * @return
     */
    List<Videos> SelectAllRname2(String radname, String radcode);

    List<Videos> SelectVideo(String dynamicTags, int videoid);

    List<Videos> SelectLabel(String dynamicTags);

    /**
     * 审核逻辑
     *
     * @param pag
     * @return
     */
    List<Videos> selecetAllV(int pag);

    int updatVreview(String[] url, int essayid, int n, HttpServletRequest request);

    int selcetCount(int n);

    /**
     * 查询所有审核通过的
     *
     * @return
     */
    List<Videos> selecetAllVreview();
}
