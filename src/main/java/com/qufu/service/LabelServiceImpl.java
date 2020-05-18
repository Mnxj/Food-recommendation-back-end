package com.qufu.service;

import com.qufu.mapper.LabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    LabelMapper mapper;

    @Override
    public int insertL(String latext) {
        String text = mapper.SelecetL(latext);
        if (text == null) {
            return mapper.insertL(latext);
        } else {
            return 0;
        }
    }

    @Override
    public int delL(String latext) {
        return mapper.delL(latext);
    }

    @Override
    public List<String> SelecetAll() {
        return mapper.SelecetAll();
    }

}
