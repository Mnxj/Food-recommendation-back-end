package com.qufu.mapper;


import com.qufu.pojo.Rcomment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RcommentMapper {
    /**
     * 查询这个评论作为点赞和回复的依据
     *
     * @return
     */
    Rcomment Select(int rcid);

    /**
     * 查询指定店铺的所有评论
     *
     * @return
     */
    List<Rcomment> SelectAll(@Param("rid") String rid);

    /**
     * 查询对应评论的回复
     *
     * @param rcid
     * @return
     */
    Integer SelectRcc(@Param("rcid") int rcid);

    /**
     * 查询点赞的数量
     *
     * @param rcid
     * @return
     */
    int SelectLike(@Param("rcid") int rcid);

    /**
     * 查询初始是否点赞
     *
     * @param rcid
     * @return
     */
    String Selectbol(@Param("rcid") int rcid, @Param("id") int id);

    /**
     * 添加评论数据
     *
     * @param rcomment
     * @return
     */
    int addComment(Rcomment rcomment);

//    点赞

    /**
     * 查询
     *
     * @param rcid
     * @param id
     * @return
     */
    Integer selectlike(@Param("rcid") int rcid, @Param("id") int id);

    /**
     * 点赞添加
     *
     * @param
     * @return
     */
    int addlike(@Param("rcid") int rcid, @Param("id") int id, @Param("llike") String llike);


    /**
     * 更新
     *
     * @param rcid
     * @param id
     * @param llike
     * @return
     */
    int uplike(@Param("rcid") int rcid, @Param("id") int id, @Param("llike") String llike);

    /**
     * 删除评论
     *
     * @param rcid
     * @param id
     * @return
     */
    int deleteRcc(@Param("rcid") int rcid, @Param("id") int id);

    int deleteLike(@Param("rcid") int rcid);

    int deleteLike2(@Param("rcid") int rcid);

    int deleteRccomment(@Param("rcid") int rcid);

}
