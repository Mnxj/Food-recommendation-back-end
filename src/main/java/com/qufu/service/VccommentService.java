package com.qufu.service;


import com.qufu.pojo.VLike2;
import com.qufu.pojo.Vccomment;

import java.util.List;

public interface VccommentService {
    /**
     * 查询指定用户评论的所有子评论
     *
     * @return
     */
    List<Vccomment> SelectAll(int vid1, int vid2, int vccid);

    /**
     * 添加评论数据
     *
     * @param vccomment
     * @return
     */
    int addCommentc(Vccomment vccomment);

    /**
     * 查询
     *
     * @param vcid
     * @param id
     * @return
     */
    Integer selectlike2(int vcid, int id, int vccid);

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
    int deleteVccomment(int id2, int vccid);

    /**
     * 更新
     *
     * @param like2
     * @return
     */
    int uplike2(VLike2 like2);

}
