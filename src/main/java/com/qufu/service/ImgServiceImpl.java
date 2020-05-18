package com.qufu.service;

import com.qufu.exception.InvalidParamException;
import com.qufu.mapper.ImgMapper;
import com.qufu.pojo.Img;
import com.qufu.utils.E;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    ImgMapper mapper;

    @Override
    public int ImgInsert(Img img) {

        if (img.getRid() == null | (img.getImg1() == null && img.getImg1() == null
                && img.getImg2() == null && img.getImg3() == null && img.getImg4() == null)) {
            throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE, E.INVALID_PARAM_INFO);
        } else {
            return mapper.ImgInsert(img);
        }

    }
}
