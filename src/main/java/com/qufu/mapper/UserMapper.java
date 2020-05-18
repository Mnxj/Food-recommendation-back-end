package com.qufu.mapper;


import com.qufu.pojo.User;
import com.qufu.pojo.UserWebscoket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper是mybatis中的一个接口
 */
@Component
@Mapper
public interface UserMapper {
    /**
     * 查询没有被删除的用户
     *
     * @return
     */
    List<User> Select(@Param("flag") Integer flag);

    List<User> Select();

    List<Integer> Selectid();

    /**
     * 伪删除
     *
     * @param id
     * @return
     */
    int UDetFlag(@Param("id") int id);

    /**
     * 根据传入的值进行判断
     *
     * @return
     */
    User SelectUser(@Param("Anumber") String Anumber, @Param("password") String password);

    /**
     * 根据传入的值进行判断
     *
     * @return
     */
    User SelectUsert(@Param("Anumber") String Anumber);

    /**
     * 用作添加啊好友
     *
     * @param id
     * @return
     */
    User SelectUserid(@Param("id") int id);

    /**
     * 邮箱验证
     *
     * @param user
     * @return
     */
    Integer Flag(User user);

    /**
     * 找回密码
     * 更具邮箱修改密码
     *
     * @return
     */
    int UpdatePsd(User user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int InsertUser(User user);

    /**
     * 根据判断旧密码是否修改密码
     *
     * @return
     */
    int Updatenewpsd();

    /**
     * 修改资料
     *
     * @return
     */
    int UpdateSig(User user);

    /**
     * 查询是否已经存在*
     *
     * @return
     */
    List<UserWebscoket> SelectUserWeb1(@Param("id2") int id2);

    List<UserWebscoket> SelectUserWeb(@Param("id1") int id1);

    /**
     * 查询频道
     *
     * @param
     * @return
     */
    UserWebscoket SelectUserr(@Param("id1") int id1, @Param("id2") int id2);

    /**
     * 添加频道号
     *
     * @param id1
     * @return
     */
    int addUserWEb(@Param("id1") int id1, @Param("id2") int id2);

    /**
     * 删除好友的时候触发
     */
    int deleUserWeb(@Param("uwid") int uwid);

    /**
     * 管理员查询
     *
     * @return
     */
    User SelcetAdmin(@Param("Anumber") String Anumber, @Param("password") String password);

    //查询日志和视频总数
    int selcetVCount(@Param("id") int id);

    int selcetECount(@Param("id") int id);

}
