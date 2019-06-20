package com.net.security.serviceimpl;

import com.net.security.bean.Score;
import com.net.security.bean.Scoreauto;
import com.net.security.mapper.ScoreautoMapper;
import com.net.security.service.ScoreautoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreautoServiceimpl implements ScoreautoService {
    @Autowired
    private ScoreautoMapper scoreautoMapper;
    @Override
    public List<Score> getAllby2id(int arg0, int arg1) {
        return scoreautoMapper.getAllby2id(arg0,arg1);
    }

    @Override
    public void insert(Scoreauto scoreauto) {
        scoreautoMapper.insert(scoreauto);
    }
}
