package com.qufu.mapper;

import com.qufu.pojo.Essay;
import com.qufu.pojo.Videos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface EssayMapper {
    /**
     * 添加日志对象
     * 1，id、rid、
     *
     * @param essay
     * @return
     */
    int addEssay(Essay essay);

    /**
     * 添加播放记录
     *
     * @return
     */
    int addEcount(@Param("essayid") int essayid);

    /**
     * 查询所有的日志
     *
     * @return
     */
    List<Essay> SelectAll(@Param("rid") String rid, @Param("id") String id);

    List<Essay> SelectAllEr(@Param("id") String id);

    /**
     * 根据城市名字查询
     *
     * @return
     */
    List<Essay> SelectAllRname(@Param("radname") String radname, @Param("radcode") String radcode, @Param("pag1") int pag1, @Param("pag2") int pag2);

    /**
     * 根据城市名字查询
     *
     * @return
     */
    List<Essay> SelectAllRname2(@Param("radname") String radname, @Param("radcode") String radcode);

    /**
     * 根据用户的id查询总数
     *
     * @param essayid
     * @return
     */
    int Selectcount(@Param("essayid") int essayid);

    /**
     * 删除指定日志
     *
     * @param essayid
     * @return
     */
    int deletE(@Param("essayid") String essayid);

    /**
     * 查询评论总数
     */
    int SelectEcommet(@Param("essayid") int essayid);

    /**
     * 查询评论总数
     */
    int SelectElikecount(@Param("essayid") int essayid);

    /**
     * 查询是不是本人点的
     *
     * @param essayid
     * @param id
     * @return
     */
    String SelcetEslike(@Param("essayid") int essayid, @Param("id") int id);

    /**
     * 添加点赞数
     *
     * @return
     */
    int addEslike(@Param("id") int id, @Param("essayid") int essayid, @Param("eslike") String eslike);

    /**
     * 更新点赞数
     *
     * @return
     */
    int updateEslike(@Param("eslike") String eslike, @Param("essayid") int essayid, @Param("id") int id);

    Essay selectE(@Param("essayid") int essayid);

    //置顶处理
    List<Essay> selecetAll(@Param("pag") int pag);

    //查询总数用来查询页数
    int selcetCount(@Param("ereview") String ereview);

    int updateEsticky(@Param("esticky") int esticky, @Param("essayid") int essayid);

    //审核处理
    List<Essay> selecetAllV(@Param("pag") int pag);

    int updatEreview(@Param("essayid") int essayid);

    int deletEreview(@Param("essayid") int essayid);

    List<Essay> selcetEsticky(@Param("esticky") String esticky);

    List<Essay> SelectLabel(@Param("dynamicTags") String dynamicTags);
}
