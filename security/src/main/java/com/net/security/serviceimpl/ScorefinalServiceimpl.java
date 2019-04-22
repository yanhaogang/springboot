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
    public Integer getIsscorcedBy4id(int arg0, int arg1, int arg2, int arg3) {
        return scorefinalMapper.getIsscoredBy4id(arg0,arg1,arg2,arg3);
    }

    @Override
    public void updateIsscored(int arg0, int arg1, int arg2, int arg3) {
        scorefinalMapper.updateisscored(arg0,arg1,arg2,arg3);
    }

    @Override
    public void InsertScore(Score score) {
        scorefinalMapper.insertscorefinal(score);
    }

    @Override
    public List<Score> getAllbycounid(int arg0, int arg1,int arg2) {
        return scorefinalMapper.getAllscorebyCounid(arg0,arg1,arg2);
    }
}
