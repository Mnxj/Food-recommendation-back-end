package com.qufu.service;


import com.qufu.mapper.ParagraphMapper;
import com.qufu.utils.RadomMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParagraphServiceImpl implements ParagraphService {
    @Autowired
    ParagraphMapper mapper;

    @Override
    public String SelectText() {
        int x = RadomMath.Pid();
        String str = mapper.SelectText(x);
        while (str == null || str.equals("")) {
            x = RadomMath.Pid();
            str = mapper.SelectText(x);
        }
        return str;
    }
}
