package com.qufu.mapper;


import com.qufu.pojo.VLike2;
import com.qufu.pojo.Vccomment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface VccommentMapper {
    /**
     * 查询指定用户评论的所有子评论
     *
     * @return
     */
    List<Vccomment> SelectAll(@Param("vcid") int vcid, @Param("id1") int id1);

    /**
     * 添加评论数据
     *
     * @param vccomment
     * @return
     */
    int addCommentc(Vccomment vccomment);

    /**
     * 查询是否存在
     *
     * @param vcid
     * @param id
     * @return
     */
    Integer selectlike2(@Param("vcid") int vcid, @Param("id") int id, @Param("vccid") int vccid);

    /**
     * 查询点赞的数量
     *
     * @param vccid
     * @return
     */
    int SelectLike2(@Param("vccid") int vccid);

    /**
     * 查询初始是否点赞
     *
     * @param vccid
     * @return
     */
    String Selectbol(@Param("vccid") int vccid, @Param("id") int id);

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
    int addlike2(VLike2 like2);

    /**
     * 删除自己的评论
     *
     * @param id2
     * @param vccid
     * @return
     */
    int deleteVccomment(@Param("id2") int id2, @Param("vccid") int vccid);

    /**
     * 更新
     *
     * @param like2
     * @return
     */
    int uplike2(VLike2 like2);

    int deleteLike2(@Param("vccid") int vccid);

}
