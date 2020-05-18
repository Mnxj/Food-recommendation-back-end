package com.qufu.service;

import com.qufu.pojo.Like2;
import com.qufu.pojo.Rccomment;

import java.util.List;

public interface RccommentService {
    /**
     * 查询指定用户评论的所有子评论
     *
     * @return
     */
    List<Rccomment> SelectAll(int rid1, int rid2, int rccid);

    /**
     * 添加评论数据
     *
     * @param rccomment
     * @return
     */
    int addCommentc(Rccomment rccomment);

    /**
     * 查询
     *
     * @param rcid
     * @param id
     * @return
     */
    Integer selectlike2(int rcid, int id, int rccid);

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
    int deleteRccomment(int id2, int rccid);

    /**
     * 更新
     *
     * @param like2
     * @return
     */
    int uplike2(Like2 like2);

}
