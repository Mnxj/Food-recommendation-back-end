package com.qufu.service;


import com.qufu.mapper.JournalMapper;
import com.qufu.pojo.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    JournalMapper mapper;

    @Override
    public int Jinsert(Journal journal) {
        return mapper.Jinsert(journal);
    }

    @Override
    public List<Journal> SelectJ(int count) {
        return mapper.SelectJ((count - 1) * 5);
    }

    @Override
    public Integer SelectCount() {
        return mapper.SelectCount();
    }
}
