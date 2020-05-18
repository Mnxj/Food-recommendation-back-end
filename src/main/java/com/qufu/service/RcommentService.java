package com.qufu.service;

import com.qufu.pojo.Rcomment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RcommentService {
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
    List<Rcomment> SelectAll(String rid, int id);

    /**
     * 添加评论数据
     *
     * @param rcomment
     * @return
     */
    int addComment(Rcomment rcomment);

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
