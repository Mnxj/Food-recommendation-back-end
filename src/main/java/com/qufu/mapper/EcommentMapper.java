package com.qufu.mapper;


import com.qufu.pojo.Ecomment;
import com.qufu.pojo.Rcomment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface EcommentMapper {
    /**
     * 查询这个评论作为点赞和回复的依据
     *
     * @return
     */
    Ecomment Select(int rcid);

    /**
     * 查询指定店铺的所有评论
     *
     * @return
     */
    List<Ecomment> SelectAll(@Param("essayid") String essayid);

    /**
     * 查询对应评论的回复
     *
     * @param ecid
     * @return
     */
    Integer SelectEcc(@Param("ecid") int ecid);

    /**
     * 查询点赞的数量
     *
     * @param ecid
     * @return
     */
    int SelectLike(@Param("ecid") int ecid);

    /**
     * 查询初始是否点赞
     *
     * @param ecid
     * @return
     */
    String Selectbol(@Param("ecid") int ecid, @Param("id") int id);

    /**
     * 添加评论数据
     *
     * @param ecomment
     * @return
     */
    int addComment(Ecomment ecomment);

//    点赞

    /**
     * 查询
     *
     * @param ecid
     * @param id
     * @return
     */
    Integer selectlike(@Param("ecid") int ecid, @Param("id") int id);

    /**
     * 点赞添加
     *
     * @param
     * @return
     */
    int addlike(@Param("ecid") int ecid, @Param("id") int id, @Param("elike") String elike);


    /**
     * 更新
     *
     * @param ecid
     * @param id
     * @param elike
     * @return
     */
    int uplike(@Param("ecid") int ecid, @Param("id") int id, @Param("elike") String elike);

    /**
     * 删除评论
     *
     * @param ecid
     * @param id
     * @return
     */
    int deleteEcc(@Param("ecid") int ecid, @Param("id") int id);

    int deleteLike(@Param("ecid") int ecid);

    int deleteLike2(@Param("ecid") int ecid);

    int deleteEccomment(@Param("ecid") int ecid);

    List<Ecomment> SecletEid(@Param("essayid") int essayid);

    int deleteEid(@Param("essayid") int essayid);
}
