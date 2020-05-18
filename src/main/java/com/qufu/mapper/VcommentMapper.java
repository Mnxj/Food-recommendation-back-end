package com.qufu.mapper;


import com.qufu.pojo.Ecomment;
import com.qufu.pojo.Vcomment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface VcommentMapper {
    /**
     * 查询这个评论作为点赞和回复的依据
     *
     * @return
     */
    Vcomment Select(int vcid);

    /**
     * 查询指定视频的所有评论
     *
     * @return
     */
    List<Vcomment> SelectAll(@Param("videoid") String videoid);

    /**
     * 查询对应评论的回复
     *
     * @param vcid
     * @return
     */
    Integer SelectVcc(@Param("vcid") int vcid);

    /**
     * 查询点赞的数量
     *
     * @param vcid
     * @return
     */
    int SelectLike(@Param("vcid") int vcid);

    /**
     * 查询初始是否点赞
     *
     * @param vcid
     * @return
     */
    String Selectbol(@Param("vcid") int vcid, @Param("id") int id);

    /**
     * 添加评论数据
     *
     * @param rcomment
     * @return
     */
    int addComment(Vcomment rcomment);

//    点赞

    /**
     * 查询
     *
     * @param vcid
     * @param id
     * @return
     */
    Integer selectlike(@Param("vcid") int vcid, @Param("id") int id);

    /**
     * 点赞添加
     *
     * @param
     * @return
     */
    int addlike(@Param("vcid") int vcid, @Param("id") int id, @Param("vlike") String vlike);


    /**
     * 更新
     *
     * @param vcid
     * @param id
     * @param vlike
     * @return
     */
    int uplike(@Param("vcid") int vcid, @Param("id") int id, @Param("vlike") String vlike);

    /**
     * 删除评论
     *
     * @param vcid
     * @param id
     * @return
     */
    int deleteVcc(@Param("vcid") int vcid, @Param("id") int id);

    int deleteLike(@Param("vcid") int vcid);

    int deleteLike2(@Param("vcid") int vcid);

    int deleteVccomment(@Param("vcid") int vcid);

    int deleteVid(@Param("videoid") int videoid);
}
