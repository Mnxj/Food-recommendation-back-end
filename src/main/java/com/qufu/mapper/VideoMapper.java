package com.qufu.mapper;


import com.qufu.pojo.Ecomment;
import com.qufu.pojo.Videos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface VideoMapper {
    /**
     * 视频日志对象
     * 1，id、rid、
     *
     * @param
     * @return
     */
    int addVideo(Videos video);

    /**
     * 添加播放记录
     *
     * @return
     */
    int addVcount(@Param("videoid") int videoid);

    /**
     * 查询所有的视频
     *
     * @return
     */
    List<Videos> SelectAll(@Param("rid") String rid, @Param("id") String id);

    List<Videos> SelectAllEr(@Param("id") String id);

    /**
     * 根据城市名字查询
     *
     * @return
     */
    List<Videos> SelectAllRname(@Param("radname") String radname, @Param("radcode") String radcode, @Param("pag") int pag);

    /**
     * 根据城市名字查询
     *
     * @return
     */
    List<Videos> SelectAllRname2(@Param("radname") String radname, @Param("radcode") String radcode);

    /**
     * 根据用户的id查询总数
     *
     * @param videoid
     * @return
     */
    int Selectcount(@Param("videoid") int videoid);

    /**
     * 删除指定vlog
     *
     * @param videoid
     * @return
     */
    int deletE(@Param("videoid") String videoid);

    /**
     * 根据标题 分级查询
     *
     * @return
     */
    List<Videos> SelectVideo(@Param("dynamicTags") String dynamicTags, @Param("videoid") int videoid);

    /**
     * 获取评论
     *
     * @return
     */
    int SelectVcomment(@Param("videoid") int videoid);

    /**
     * 按id查询
     *
     * @param videoid
     * @return
     */
    Videos SelctV(@Param("videoid") int videoid);

    //审核处理
    List<Videos> selecetAllV(@Param("pag") int pag);

    int updatVreview(@Param("videoid") int videoid);

    int deletVreview(@Param("videoid") int videoid);

    int selcetCount(@Param("vreview") String vreview);

    List<Videos> selecetAllVreview();

    List<Videos> SelectLabel(@Param("dynamicTags") String dynamicTags);
}
