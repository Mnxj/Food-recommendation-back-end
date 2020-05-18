package com.qufu.mapper;


import com.qufu.pojo.Like2;
import com.qufu.pojo.Rccomment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RccommentMapper {
    /**
     * 查询指定用户评论的所有子评论
     *
     * @return
     */
    List<Rccomment> SelectAll(@Param("rcid") int rcid, @Param("id1") int id1);

    /**
     * 添加评论数据
     *
     * @param rccomment
     * @return
     */
    int addCommentc(Rccomment rccomment);

    /**
     * 查询是否存在
     *
     * @param rcid
     * @param id
     * @return
     */
    Integer selectlike2(@Param("rcid") int rcid, @Param("id") int id, @Param("rccid") int rccid);

    /**
     * 查询点赞的数量
     *
     * @param rccid
     * @return
     */
    int SelectLike2(@Param("rccid") int rccid);

    /**
     * 查询初始是否点赞
     *
     * @param rccid
     * @return
     */
    String Selectbol(@Param("rccid") int rccid, @Param("id") int id);

    /**
     * id
     *
     * @param id
     * @return
     */
    String SelectName(@Param("id") int id);

    /**
     * 点赞添加
     * id当前用户给谁评论的id
     *
     * @param like2
     * @return
     */
    int addlike2(Like2 like2);

    /**
     * 删除自己的评论
     *
     * @param id2
     * @param rccid
     * @return
     */
    int deleteRccomment(@Param("id2") int id2, @Param("rccid") int rccid);

    /**
     * 更新
     *
     * @param like2
     * @return
     */
    int uplike2(Like2 like2);

    int deleteLike2(@Param("rccid") int rccid);

}
