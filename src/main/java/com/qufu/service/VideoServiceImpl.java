package com.qufu.service;

import com.qufu.exception.InvalidParamException;
import com.qufu.mapper.VcommentMapper;
import com.qufu.mapper.VideoMapper;
import com.qufu.pojo.*;
import com.qufu.utils.E;
import com.qufu.utils.GetList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper mapper;
    @Autowired
    FileUploadService uploadService;
    @Autowired
    VcommentMapper vcommentMapper;

    @Override
    public int addVideo(Videos video) {
        Date date = new Date();
        video.setVideotime(date);
        return mapper.addVideo(video);
    }

    @Override
    public List<Videos> SelectAll(String rid, String id) {
        List<Videos> videos = mapper.SelectAll(rid, id);
        for (int i = 0; i < videos.size(); i++) {
            int videoid = videos.get(i).getVideoid();
            videos.get(i).setPlcount(mapper.SelectVcomment(videoid));
        }
        return videos;
    }

    @Override
    public List<Videos> SelectAllEr(String id) {
        return mapper.SelectAllEr(id);
    }

    /**
     * 删除操作
     * 先删除服务器上的文件，然后是评论及点赞的表
     * 最后是video表
     *
     * @param url
     * @param videoid
     * @param request
     * @return
     */
    @Override
    public int deleteE(String[] url, String videoid, HttpServletRequest request) {
        for (String src : url) {
            uploadService.uploaddelet(src, request);
        }
        List<Vcomment> vcomments = vcommentMapper.SelectAll(videoid);
        for (int i = 0; i < vcomments.size(); i++) {
            vcommentMapper.deleteLike(vcomments.get(i).getVcid());
            vcommentMapper.deleteLike2(vcomments.get(i).getVcid());
            vcommentMapper.deleteVccomment(vcomments.get(i).getVcid());
        }
        vcommentMapper.deleteVid(Integer.valueOf(videoid));
        return mapper.deletE(videoid);
    }

    @Override
    public int addVcount(int videoid) {
        int count;
        if (mapper.addVcount(videoid) == 1) {
            count = mapper.Selectcount(videoid);
        } else {
            return -1;
        }
        return count;
    }

    @Override
    public List<Videos> SelectAllRname(String radname, String radcode, int pag) {
        //两个不能同时为空
        if (radname == null && radcode == null) {
            throw new InvalidParamException(E.NO_NUMBER_CODE, "两个参数不能全空");
        }
        return mapper.SelectAllRname(radname, radcode, (pag - 1) * 8);
    }

    @Override
    public List<Videos> SelectAllRname2(String radname, String radcode) {
        //两个不能同时为空
        if (radname == null && radcode == null) {
            throw new InvalidParamException(E.NO_NUMBER_CODE, "两个参数不能全空");
        }
        return mapper.SelectAllRname2(radname, radcode);
    }

    @Override
    public List<Videos> SelectVideo(String dynamicTags, int videoid) {
        List<List<Videos>> list = new ArrayList<List<Videos>>();
        String[] sum = dynamicTags.split(",");
        for (String n : sum) {
            list.add(mapper.SelectVideo(n, videoid));
        }
        List<Videos> list1 = new ArrayList<Videos>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                list1.add(list.get(i).get(j));
            }
        }
        list1 = GetList.getArrayList(list1);
        return list1;
    }

    @Override
    public List<Videos> SelectLabel(String dynamicTags) {
        return mapper.SelectLabel(dynamicTags);
    }

    @Override
    public List<Videos> selecetAllV(int pag) {
        return mapper.selecetAllV((pag - 1) * 7);
    }

    @Override
    public int updatVreview(String[] url, int essayid, int n, HttpServletRequest request) {
        int num = 0;
        if (n == 1) {
            num = mapper.updatVreview(essayid);
            return num == 1 ? 2 : num;
        } else {
            num = mapper.deletVreview(essayid);
            for (String src : url) {
                uploadService.uploaddelet(src, request);
            }
            return num == 1 ? 1 : num;
        }
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
    public List<Videos> selecetAllVreview() {
        return mapper.selecetAllVreview();
    }
}
