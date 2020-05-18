package com.qufu.service;

import com.qufu.exception.InvalidParamException;
import com.qufu.mapper.EcommentMapper;
import com.qufu.mapper.EssayMapper;
import com.qufu.pojo.Ecomment;
import com.qufu.pojo.Essay;
import com.qufu.pojo.Rcomment;
import com.qufu.pojo.Videos;
import com.qufu.utils.E;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class EssayServiceImpl implements EssayService {
    @Autowired
    EssayMapper mapper;
    @Autowired
    EcommentMapper ecommentMapper;
    @Autowired
    FileUploadService uploadService;

    @Override
    public int addEssay(Essay essay) {
        Date date = new Date();
        essay.setEssaytime(date);
        return mapper.addEssay(essay);
    }

    @Override
    public List<Essay> SelectAll(String rid, String id) {
        List<Essay> essays = mapper.SelectAll(rid, id);
        for (int i = 0; i < essays.size(); i++) {
            //获取评论总数
            int essayid = essays.get(i).getEssayid();
            //查询评论数
            essays.get(i).setErcomment(mapper.SelectEcommet(essayid));
            //查询喜欢数
            essays.get(i).setElikecount(mapper.SelectElikecount(essayid));
        }
        return essays;
    }

    @Override
    public List<Essay> SelectAllEr(String id) {
        return mapper.SelectAllEr(id);
    }

    @Override
    public int deleteE(String esssayid, String url, HttpServletRequest request) {
        /**
         * 删除前先把所有的评论删除
         */
        List<Ecomment> rcomments = ecommentMapper.SecletEid(Integer.valueOf(esssayid));
        for (int i = 0; i < rcomments.size(); i++) {
            ecommentMapper.deleteLike(rcomments.get(i).getEcid());
            ecommentMapper.deleteLike2(rcomments.get(i).getEcid());
            ecommentMapper.deleteEccomment(rcomments.get(i).getEcid());
        }
        ecommentMapper.deleteEid(Integer.valueOf(esssayid));
        uploadService.uploaddelet(url, request);
        return mapper.deletE(esssayid);
    }

    @Override
    public int addEcount(int essayid) {
        int count;
        if (mapper.addEcount(essayid) == 1) {
            count = mapper.Selectcount(essayid);
        } else {
            return -1;
        }
        return count;
    }

    @Override
    public List<Essay> SelectAllRname(String radname, String radcode, int pag, int id) {
        //两个不能同时为空
        if (radname == null && radcode == null) {
            throw new InvalidParamException(E.NO_NUMBER_CODE, "两个参数不能全空");
        }
        List<Essay> essays = mapper.SelectAllRname(radname, radcode, (pag - 1) * 5, pag * 5);
        for (int i = 0; i < essays.size(); i++) {
            //获取评论总数
            int essayid = essays.get(i).getEssayid();
            //查询评论数
            essays.get(i).setErcomment(mapper.SelectEcommet(essayid));
            //查询喜欢数
            essays.get(i).setElikecount(mapper.SelectElikecount(essayid));
            //判断是不是本人点的
            String bol = mapper.SelcetEslike(essayid, id);
            if (bol != null && bol.equals("true")) {
                essays.get(i).setLikeflag(false);
            } else {
                essays.get(i).setLikeflag(true);
            }
        }
        return essays;
    }

    @Override
    public List<Essay> SelectAllRname2(String radname, String radcode) {
        //两个不能同时为空
        if (radname == null && radcode == null) {
            throw new InvalidParamException(E.NO_NUMBER_CODE, "两个参数不能全空");
        }
        List<Essay> essays = mapper.SelectAllRname2(radname, radcode);
        for (int i = 0; i < essays.size(); i++) {
            //获取评论总数
            int essayid = essays.get(i).getEssayid();
            //查询评论数
            essays.get(i).setErcomment(mapper.SelectEcommet(essayid));
            //查询喜欢数
            essays.get(i).setElikecount(mapper.SelectElikecount(essayid));
        }
        return essays;
    }

    @Override
    public String addLike(String eslike, int essayid, int id) {
        String bol = mapper.SelcetEslike(essayid, id);
        int i = 0;
        if (bol == null) {
            i = mapper.addEslike(id, essayid, eslike);
        } else {
            i = mapper.updateEslike(eslike, essayid, id);
        }
        if (i == 1) {
            return mapper.SelcetEslike(essayid, id);
        }
        return "";

    }

    @Override
    public List<Essay> selecetAll(int pag) {
        List<Essay> essays = mapper.selecetAll((pag - 1) * 7);
        for (int i = 0; i < essays.size(); i++) {
            //获取评论总数
            int essayid = essays.get(i).getEssayid();
            //查询评论数
            essays.get(i).setErcomment(mapper.SelectEcommet(essayid));
            //查询喜欢数
            essays.get(i).setElikecount(mapper.SelectElikecount(essayid));
        }
        return essays;
    }

    @Override
    public int selcetCount(int n) {
        String flag;
        if (n == 1) {
            flag = "true";
        } else {
            flag = "false";
        }
        return mapper.selcetCount(flag);
    }

    @Override
    public int updateEsticky(int essayid, int n) {
        int num = 0;
        if (n == 1) {
            num = mapper.updateEsticky(1, essayid);
            return num == 1 ? 2 : num;
        } else {
            num = mapper.updateEsticky(0, essayid);
            return num == 1 ? 1 : num;
        }
    }

    @Override
    public List<Essay> selecetAllV(int pag) {
        return mapper.selecetAllV((pag - 1) * 7);
    }

    @Override
    public int updatEreview(String url, int essayid, int n, HttpServletRequest request) {
        int num = 0;
        if (n == 1) {
            num = mapper.updatEreview(essayid);
            return num == 1 ? 2 : num;
        } else {
            num = mapper.deletEreview(essayid);
            uploadService.uploaddelet(url, request);
            return num == 1 ? 1 : num;
        }
    }

    @Override
    public List<Essay> selcetEsticky(String esticky) {

        return mapper.selcetEsticky(esticky);
    }

    @Override
    public List<Essay> SelectLabel(String dynamicTags) {
        return mapper.SelectLabel(dynamicTags);
    }
}
