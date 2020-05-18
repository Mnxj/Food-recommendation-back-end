package com.qufu.service;

import com.qufu.mapper.RcommentMapper;
import com.qufu.pojo.Rcomment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RcommentServiceImpl implements RcommentService {
    @Autowired
    RcommentMapper mapper;

    @Override
    public Rcomment Select(int rcomment) {
        return mapper.Select(rcomment);
    }

    @Override
    public List<Rcomment> SelectAll(String rid, int id) {
        //查询所有
        List<Rcomment> list = mapper.SelectAll(rid);
        for (int i = 0; i < list.size(); i++) {
            int rcid = list.get(i).getRcid();
            int id2 = list.get(i).getId();
            //本用人才可以删除
            if (id2 == id) {
                list.get(i).setTrashflag(true);
            } else {
                list.get(i).setTrashflag(false);
            }
            //评论数
            list.get(i).setCount(mapper.SelectRcc(rcid));
            //当前用户是否点赞
            String bolea = mapper.Selectbol(rcid, id);
            //点赞数
            list.get(i).setRcount(mapper.SelectLike(rcid));
            if (bolea != null && bolea.equals("true")) {
                list.get(i).setLike(false);
            } else {
                list.get(i).setLike(true);
            }
            //开始评论的时候默认都是false
            list.get(i).setLflag(false);
            //开始按钮是隐藏的
            list.get(i).setButton(false);
            //开始输入框长度
            list.get(i).setTextfont2(1);
        }
        return list;
    }

    @Override
    public int addComment(Rcomment rcomment) {
        Date date = new Date();
        rcomment.setRcdate(date);
        return mapper.addComment(rcomment);
    }

    @Override
    public int addrzcan(int rcid, int id, String bol) {
        int i = 0;
        Integer num = mapper.selectlike(rcid, id);
        if (num == null || num == 0) {
            i = mapper.addlike(rcid, id, bol);
        } else {
            i = mapper.uplike(rcid, id, bol);
        }
        return i;
    }

    @Override
    public int deleteRcc(int rcid, int id) {
        int i = mapper.deleteRcc(rcid, id);
        mapper.deleteLike(rcid);
        mapper.deleteLike2(rcid);
        mapper.deleteRccomment(rcid);
        return i;
    }


}
