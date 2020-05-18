package com.qufu.service;

import com.qufu.mapper.VcommentMapper;
import com.qufu.pojo.Vcomment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VcommentServiceImpl implements VcommentService {
    @Autowired
    VcommentMapper mapper;

    @Override
    public Vcomment Select(int vcomment) {
        return mapper.Select(vcomment);
    }

    @Override
    public List<Vcomment> SelectAll(String videoid, int id) {
        //查询所有
        List<Vcomment> list = mapper.SelectAll(videoid);
        for (int i = 0; i < list.size(); i++) {
            int rcid = list.get(i).getVcid();
            int id2 = list.get(i).getId();
            //本用人才可以删除
            if (id2 == id) {
                list.get(i).setVtrashflag(true);
            } else {
                list.get(i).setVtrashflag(false);
            }
            //评论数
            list.get(i).setCount(mapper.SelectVcc(rcid));
            //当前用户是否点赞
            String bolea = mapper.Selectbol(rcid, id);
            //点赞数
            list.get(i).setVcount(mapper.SelectLike(rcid));
            if (bolea != null && bolea.equals("true")) {
                list.get(i).setVlike(false);
            } else {
                list.get(i).setVlike(true);
            }
            //开始评论的时候默认都是false
            list.get(i).setVlflag(false);
            //开始按钮是隐藏的
            list.get(i).setVbutton(false);
            //开始输入框长度
            list.get(i).setVtextfont2(1);
        }
        return list;
    }

    @Override
    public int addComment(Vcomment rcomment) {
        Date date = new Date();
        rcomment.setVcdate(date);
        return mapper.addComment(rcomment);
    }

    @Override
    public int addrzcan(int vcid, int id, String bol) {
        System.out.println(vcid + "   " + id + "   " + bol);
        int i = 0;
        Integer num = mapper.selectlike(vcid, id);
        System.out.println(num);
        if (num == null || num == 0) {
            i = mapper.addlike(vcid, id, bol);
        } else {
            i = mapper.uplike(vcid, id, bol);
        }
        return i;
    }

    @Override
    public int deleteRcc(int vcid, int id) {
        int i = mapper.deleteVcc(vcid, id);
        mapper.deleteLike(vcid);
        mapper.deleteLike2(vcid);
        mapper.deleteVccomment(vcid);
        return i;
    }


}
