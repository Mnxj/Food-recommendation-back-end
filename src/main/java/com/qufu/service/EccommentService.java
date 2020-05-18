package com.qufu.service;

import com.qufu.pojo.*;

import java.util.List;

public interface EccommentService {
    /**
     * 查询指定用户评论的所有子评论
     *
     * @return
     */
    List<Eccomment> SelectAll(int eid1, int eid2, int eccid);

    /**
     * 添加评论数据
     *
     * @param eccomment
     * @return
     */
    int addCommentc(Eccomment eccomment);

    /**
     * 查询
     *
     * @param ecid
     * @param id
     * @return
     */
    Integer selectlike2(int ecid, int id, int eccid);

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
    int deleteEccomment(int id2, int eccid);

    /**
     * 更新
     *
     * @param like2
     * @return
     */
    int uplike2(ELike2 like2);

}
