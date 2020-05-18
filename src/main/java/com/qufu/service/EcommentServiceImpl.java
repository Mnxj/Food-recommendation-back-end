package com.qufu.service;

import com.qufu.mapper.EcommentMapper;
import com.qufu.pojo.Ecomment;
import com.qufu.pojo.Rcomment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EcommentServiceImpl implements EcommentService {
    @Autowired
    EcommentMapper mapper;

    @Override
    public Ecomment Select(int rcomment) {
        return mapper.Select(rcomment);
    }

    @Override
    public List<Ecomment> SelectAll(String essayid, int id) {
        //查询所有
        List<Ecomment> list = mapper.SelectAll(essayid);
        for (int i = 0; i < list.size(); i++) {
            int ecid = list.get(i).getEcid();
            int id2 = list.get(i).getId();
            //本用人才可以删除
            if (id2 == id) {
                list.get(i).setEtrashflag(true);
            } else {
                list.get(i).setEtrashflag(false);
            }
            //评论数
            list.get(i).setCount(mapper.SelectEcc(ecid));
            //当前用户是否点赞
            String bolea = mapper.Selectbol(ecid, id);
            //点赞数
            list.get(i).setEcount(mapper.SelectLike(ecid));
            if (bolea != null && bolea.equals("true")) {
                list.get(i).setElike(false);
            } else {
                list.get(i).setElike(true);
            }
            //开始评论的时候默认都是false
            list.get(i).setElflag(false);
            //开始按钮是隐藏的
            list.get(i).setEbutton(false);
            //开始输入框长度
            list.get(i).setEtextfont2(1);
        }
        return list;
    }

    @Override
    public int addComment(Ecomment rcomment) {
        Date date = new Date();
        rcomment.setEcdate(date);
        return mapper.addComment(rcomment);
    }

    @Override
    public int addrzcan(int ecid, int id, String bol) {
        int i = 0;
        Integer num = mapper.selectlike(ecid, id);
        if (num == null || num == 0) {
            i = mapper.addlike(ecid, id, bol);
        } else {
            i = mapper.uplike(ecid, id, bol);
        }
        return i;
    }

    @Override
    public int deleteRcc(int ecid, int id) {
        int i = mapper.deleteEcc(ecid, id);
        mapper.deleteLike(ecid);
        mapper.deleteLike2(ecid);
        mapper.deleteEccomment(ecid);
        return i;
    }


}
