package com.qufu.service;

import com.qufu.pojo.Essay;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EssayService {
    /**
     * 添加
     *
     * @return
     */
    public int addEssay(Essay essay);

    /**
     * 查询
     *
     * @return
     */
    List<Essay> SelectAll(String rid, String id);

    List<Essay> SelectAllEr(String id);

    /**
     * 删除
     *
     * @param esssayid
     * @return
     */
    int deleteE(String esssayid, String url, HttpServletRequest request);

    /**
     * 添加记录
     *
     * @return
     */
    int addEcount(int essayid);

    /**
     * 根据城市名字查询
     *
     * @return
     */
    List<Essay> SelectAllRname(String radname, String radcode, int pag, int id);

    List<Essay> SelectAllRname2(String radname, String radcode);

    /**
     * @return
     */
    String addLike(String eslike, int essayid, int id);

    List<Essay> selecetAll(int pag);

    int selcetCount(int n);

    int updateEsticky(int essayid, int n);

    List<Essay> selecetAllV(int pag);

    int updatEreview(String url, int essayid, int n, HttpServletRequest request);

    List<Essay> selcetEsticky(String esticky);

    List<Essay> SelectLabel(String dynamicTags);
}
