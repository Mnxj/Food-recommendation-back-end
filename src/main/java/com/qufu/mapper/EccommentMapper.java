package com.qufu.mapper;


import com.qufu.pojo.ELike2;
import com.qufu.pojo.Eccomment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface EccommentMapper {
    /**
     * 查询指定用户评论的所有子评论
     *
     * @return
     */
    List<Eccomment> SelectAll(@Param("ecid") int ecid, @Param("id1") int id1);

    /**
     * 添加评论数据
     *
     * @param eccomment
     * @return
     */
    int addCommentc(Eccomment eccomment);

    /**
     * 查询是否存在
     *
     * @param ecid
     * @param id
     * @return
     */
    Integer selectlike2(@Param("ecid") int ecid, @Param("id") int id, @Param("eccid") int eccid);

    /**
     * 查询点赞的数量
     *
     * @param eccid
     * @return
     */
    int SelectLike2(@Param("eccid") int eccid);

    /**
     * 查询初始是否点赞
     *
     * @param eccid
     * @return
     */
    String Selectbol(@Param("eccid") int eccid, @Param("id") int id);

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
    int addlike2(ELike2 like2);

    /**
     * 删除自己的评论
     *
     * @param id2
     * @param eccid
     * @return
     */
    int deleteEccomment(@Param("id2") int id2, @Param("eccid") int eccid);

    /**
     * 更新
     *
     * @param like2
     * @return
     */
    int uplike2(ELike2 like2);

    int deleteLike2(@Param("eccid") int eccid);

}
