package com.qufu.service;

import com.qufu.pojo.Ecomment;

import java.util.List;

public interface EcommentService {
    /**
     * 查询这个评论作为点赞和回复的依据
     *
     * @return
     */
    Ecomment Select(int ecid);

    /**
     * 查询指定店铺的所有评论
     *
     * @return
     */
    List<Ecomment> SelectAll(String essayid, int id);

    /**
     * 添加评论数据
     *
     * @param ecomment
     * @return
     */
    int addComment(Ecomment ecomment);

    /**
     * 点赞数添加
     *
     * @param
     * @return
     */
    int addrzcan(int rcid, int id, String bol);

    /**
     * 删除
     *
     * @param rcid
     * @param id
     * @return
     */
    int deleteRcc(int rcid, int id);

}
