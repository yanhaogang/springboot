package com.net.security.serviceimpl;

import com.net.security.bean.Score;
import com.net.security.mapper.ScorefinalMapper;
import com.net.security.service.ScorefinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScorefinalServiceimpl implements ScorefinalService {


    @Autowired
    private ScorefinalMapper scorefinalMapper;
    @Override
    public Object getAll() {
        return scorefinalMapper.getAll();
    }

    @Override
    public List<Score> getAllbycounid(int arg0, int arg1,int arg2) {
        return scorefinalMapper.getAllscorebyCounid(arg0,arg1,arg2);
    }
}
