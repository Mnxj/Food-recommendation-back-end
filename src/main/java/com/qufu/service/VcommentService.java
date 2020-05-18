package com.qufu.service;

import com.qufu.pojo.Ecomment;
import com.qufu.pojo.Vcomment;

import java.util.List;

public interface VcommentService {
    /**
     * 查询这个评论作为点赞和回复的依据
     *
     * @return
     */
    Vcomment Select(int vcid);

    /**
     * 查询指定店铺的所有评论
     *
     * @return
     */
    List<Vcomment> SelectAll(String videoid, int id);

    /**
     * 添加评论数据
     *
     * @param vcomment
     * @return
     */
    int addComment(Vcomment vcomment);

    /**
     * 点赞数添加
     *
     * @param
     * @return
     */
    int addrzcan(int vcid, int id, String bol);

    /**
     * 删除
     *
     * @param vcid
     * @param id
     * @return
     */
    int deleteRcc(int vcid, int id);

}
