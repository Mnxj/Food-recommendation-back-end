package com.qufu.service;

import com.qufu.mapper.VccommentMapper;
import com.qufu.pojo.VLike2;
import com.qufu.pojo.Vccomment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VccommentServiceImpl implements VccommentService {
    @Autowired
    VccommentMapper mapper;

    @Override
    public List<Vccomment> SelectAll(int eid, int eid2, int ecid) {
        //查询所有子评论
        List<Vccomment> list = mapper.SelectAll(ecid, eid);
        for (int i = 0; i < list.size(); i++) {
            int rccid = list.get(i).getVccid();
            //评论的谁
            int id = list.get(i).getId1();
            //谁回复的
            int id2 = list.get(i).getId2();
            //回复人的名字
            list.get(i).setName1(mapper.SelectName(id));
            //点赞数-
            list.get(i).setVcount(mapper.SelectLike2(rccid));
            //当前用户是否点赞
            String bolea = mapper.Selectbol(rccid, eid2);
            if (bolea != null && bolea.equals("true")) {
                list.get(i).setVlike(false);
            } else {
                list.get(i).setVlike(true);
            }
            //显示输入框
            list.get(i).setVhf(false);
            //判断是不是本人
            if (id2 == eid2) {
                list.get(i).setVtrashflag2(true);
            } else {
                list.get(i).setVtrashflag2(false);
            }
            //开始按钮是隐藏的
            list.get(i).setVbutton(false);
            //开始输入框长度
            list.get(i).setTextfont2(1);
        }
        return list;
    }

    @Override
    public int addCommentc(Vccomment rccomment) {
        Date date = new Date();
        rccomment.setVccdate(date);
        return mapper.addCommentc(rccomment);
    }

    @Override
    public Integer selectlike2(int ecid, int id, int rccid) {
        return mapper.selectlike2(ecid, id, rccid);
    }

    @Override
    public int addlike2(VLike2 like2) {
        int i = 0;
        Integer num = mapper.selectlike2(like2.getVcid(), like2.getId(), like2.getVccid());
        if (num == null || num == 0) {
            i = mapper.addlike2(like2);
        } else {
            i = mapper.uplike2(like2);
        }
        return i;
    }

    @Override
    public int deleteVccomment(int id2, int eccid) {
        int i = mapper.deleteVccomment(id2, eccid);
        mapper.deleteLike2(eccid);
        return i;
    }

    @Override
    public int uplike2(VLike2 like2) {
        return mapper.uplike2(like2);
    }
}
