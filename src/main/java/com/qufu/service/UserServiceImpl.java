package com.qufu.service;


import com.qufu.mapper.EssayMapper;
import com.qufu.mapper.UserMapper;
import com.qufu.pojo.JsonSelcetAll;
import com.qufu.pojo.User;
import com.qufu.pojo.UserWebscoket;
import com.qufu.pojo.VisitCount;
import com.qufu.utils.DateFlag;
import com.qufu.utils.GetList;
import com.qufu.utils.RadomMath;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    /**
     * 查询
     *
     * @return
     */
    public List<User> Select(Integer flag) {
        List<User> user = mapper.Select(flag);
        for (int i = 0; i < user.size(); i++) {
            user.get(i).setAge(user.get(i).getBirthday());
        }
        return user;
    }

    /**
     * 生成一个账号
     *
     * @return
     */
    @Override
    public Integer Select() {
        List<User> users = mapper.Select();
        List<Integer> list = new ArrayList<Integer>();
        for (User num :
                users) {
            list.add(num.getAnumber());
        }
        int x = RadomMath.Id(list);
        return x;
    }

    @Override
    public List<Integer> Selectid() {
        return mapper.Selectid();
    }

    @Override
    public JsonSelcetAll SelectAll(Integer flag) {
        List<User> user = mapper.Select(flag);
        JsonSelcetAll selcetAll = new JsonSelcetAll();//存数据
        VisitCount visitCount = new VisitCount();//获取今天时间
        String zuotian = DateFlag.TimeJ(visitCount.getVisittime(), 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");//存放那一年注册的
        String[] str = new String[user.size()];//暂存年份
        String data = "";
        int nan = 0, sum = 0, j = 0, z = 0;//分别记录今天昨天总和
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        for (User user1 : user) {
            str[sum] = sdf2.format(user1.getBirthday());
            data = sdf.format(user1.getCreationdate());
            if (user1.getGender().equals("男")) {
                nan++;
            } else if (data.equals(visitCount.getVisittime())) {
                j++;
            } else if (data.equals(zuotian)) {
                z++;
            }
            sum++;
        }
        //那年创建的
        boolean flag2 = false;
        int shu = 0;
        for (int i = 0; i < str.length; i++) {
            flag2 = m1.containsKey(str[i]);
            if (flag2) {
                shu = m1.get(str[i]);
                shu++;
                m1.put(str[i], shu);
            } else {
                m1.put(str[i], 1);
            }
        }
        String[] birday = new String[m1.size() * 2];
        int size = 0;
        Iterator<Map.Entry<String, Integer>> entries = m1.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Integer> entry = entries.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            birday[size++] = key;
            birday[size++] = "" + value;
        }

        //性别
        String[] gender = new String[4];
        gender[0] = "男";
        gender[1] = "" + nan;
        gender[2] = "女";
        gender[3] = "" + (sum - nan);
        //今日明天总数
        int[] summ = new int[3];
        summ[0] = z;
        summ[1] = j;
        summ[2] = sum;
        selcetAll.setGender(gender);
        selcetAll.setLianame(summ);
        selcetAll.setBirday(birday);
        return selcetAll;
    }

    @Override
    public User SelectUserid(int id) {
        User user = mapper.SelectUserid(id);
        user.setAge(user.getBirthday());
        return user;
    }

    public int UDetFlag(int id) {
        return mapper.UDetFlag(id);
    }

    @Override
    public User SelectUser(String Anumber, String password) {
        //查询
        User user = mapper.SelectUser(Anumber, password);
        if (user != null) {
            user.setAge(user.getBirthday());
            //好友数
            List<UserWebscoket> count = mapper.SelectUserWeb(user.getId());
            List<UserWebscoket> count2 = mapper.SelectUserWeb1(user.getId());
            int uwcount = count.size() + count2.size();
            user.setEwcount(uwcount);
            //日志总数
            user.setEcount(mapper.selcetECount(user.getId()));
            //视频总数
            user.setVcount(mapper.selcetVCount(user.getId()));
        }
        return user;
    }

    @Override
    public User SelectUsert(String Anumber) {

        return mapper.SelectUsert(Anumber);
    }

    @Override
    public Integer Flag(User user) {
        return mapper.Flag(user);
    }

    @Override
    public int UpdatePsd(User user) {
        return mapper.UpdatePsd(user);
    }

    @Override
    public int UpdateSig(User user) {
        return mapper.UpdateSig(user);
    }

    @Override
    public int InsertUser(User user) {
        return mapper.InsertUser(user);
    }

    @Override
    public List<User> SelectUserr(int id) {
        List<UserWebscoket> count = mapper.SelectUserWeb(id);
        List<UserWebscoket> count2 = mapper.SelectUserWeb1(id);
        List<User> user2 = new ArrayList<>();
        for (int i = 0; i < count.size(); i++) {
            user2.add(mapper.SelectUserid(count.get(i).getId2()));
        }
        for (int i = 0; i < count2.size(); i++) {
            user2.add(mapper.SelectUserid(count2.get(i).getId1()));
        }
        user2 = GetList.getUserlist(user2);
        for (int i = 0; i < user2.size(); i++) {
            user2.get(i).setAge(user2.get(i).getBirthday());
        }
        return user2;
    }

    @Override
    public int deleUserWeb(int uwid) {
        return mapper.deleUserWeb(uwid);
    }

    @Override
    public UserWebscoket SelectUserr(int id1, int id2) {
        UserWebscoket webscoket = mapper.SelectUserr(id1, id2);
        UserWebscoket webscoket2 = mapper.SelectUserr(id2, id1);
        if (webscoket != null) {
            return webscoket;
        } else if (webscoket2 != null) {
            return webscoket2;

        }
        return null;
    }

    @Override
    public User SelcetAdmin(String Anumber, String password) {
        User user = mapper.SelcetAdmin(Anumber, password);
        if (user != null) {
            user.setAge(user.getBirthday());
        }
        return user;
    }
}
