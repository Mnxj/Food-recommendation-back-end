package com.qufu.service;

import com.qufu.mapper.RccommentMapper;
import com.qufu.pojo.Like2;
import com.qufu.pojo.Rccomment;
import com.qufu.pojo.Rcomment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RccommentServiceImpl implements RccommentService {
    @Autowired
    RccommentMapper mapper;

    @Override
    public List<Rccomment> SelectAll(int rid, int rid2, int rcid) {
        //查询所有子评论
        List<Rccomment> list = mapper.SelectAll(rcid, rid);
        for (int i = 0; i < list.size(); i++) {
            int rccid = list.get(i).getRccid();
            //评论的谁
            int id = list.get(i).getId1();
            //谁回复的
            int id2 = list.get(i).getId2();
            //回复人的名字
            list.get(i).setName1(mapper.SelectName(id));
            //点赞数
            list.get(i).setRcount(mapper.SelectLike2(rccid));
            //当前用户是否点赞
            String bolea = mapper.Selectbol(rccid, rid2);
            if (bolea != null && bolea.equals("true")) {
                list.get(i).setLike(false);
            } else {
                list.get(i).setLike(true);
            }
            //显示输入框
            list.get(i).setHf(false);
            //判断是不是本人
            if (id2 == rid2) {
                list.get(i).setTrashflag2(true);
            } else {
                list.get(i).setTrashflag2(false);
            }
            //开始按钮是隐藏的
            list.get(i).setVbutton(false);
            //开始输入框长度
            list.get(i).setTextfont2(1);
        }
        return list;
    }

    @Override
    public int addCommentc(Rccomment rccomment) {
        Date date = new Date();
        rccomment.setRccdate(date);
        return mapper.addCommentc(rccomment);
    }

    @Override
    public Integer selectlike2(int rcid, int id, int rccid) {
        return mapper.selectlike2(rcid, id, rccid);
    }

    @Override
    public int addlike2(Like2 like2) {
        int i = 0;
        Integer num = mapper.selectlike2(like2.getRcid(), like2.getId(), like2.getRccid());
        if (num == null || num == 0) {
            i = mapper.addlike2(like2);
        } else {
            i = mapper.uplike2(like2);
        }
        return i;
    }

    @Override
    public int deleteRccomment(int id2, int rccid) {
        int i = mapper.deleteRccomment(id2, rccid);
        mapper.deleteLike2(rccid);
        return i;
    }

    @Override
    public int uplike2(Like2 like2) {
        return mapper.uplike2(like2);
    }
}
