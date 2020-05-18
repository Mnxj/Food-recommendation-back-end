package com.qufu.service;

import com.qufu.pojo.JsonSelcetAll;
import com.qufu.pojo.User;
import com.qufu.pojo.UserWebscoket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    /**
     * 查询所有不是伪删除的用户
     *
     * @param flag
     * @return
     */
    List<User> Select(Integer flag);

    Integer Select();

    List<Integer> Selectid();

    /**
     * 做一个访客查询
     *
     * @param flag
     * @return
     */
    JsonSelcetAll SelectAll(Integer flag);

    User SelectUserid(int id);

    /**
     * 伪删除
     *
     * @param id
     * @return
     */
    int UDetFlag(int id);

    /**
     * 用户登录
     *
     * @param Anumber
     * @param password
     * @return
     */
    User SelectUser(String Anumber, String password);

    User SelectUsert(String Anumber);

    /**
     * 邮箱验证
     *
     * @param user
     * @return
     */
    Integer Flag(User user);

    /**
     * 更具邮箱修改密码
     *
     * @return
     */
    int UpdatePsd(User user);

    /**
     * 更改资料
     *
     * @param user
     * @return
     */
    int UpdateSig(User user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int InsertUser(User user);

    /**
     * 查询频道
     *
     * @param id1
     * @return
     */
    List<User> SelectUserr(int id1);

    int deleUserWeb(int uwid);

    UserWebscoket SelectUserr(int id1, int id2);


    User SelcetAdmin(String Anumber, String password);
}
