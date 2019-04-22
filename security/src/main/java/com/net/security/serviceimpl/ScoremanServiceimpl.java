package com.net.security.serviceimpl;

import com.net.security.bean.Score;
import com.net.security.mapper.ScoremanMapper;
import com.net.security.service.ScoremanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScoremanServiceimpl implements ScoremanService {
    @Autowired
    private ScoremanMapper scoremanMapper;

    @Override
    public List<Score> getAllby3id(int arg0, int arg1,int arg2) {
        return scoremanMapper.getAllby3id(arg0,arg1,arg2);
    }

    @Override
    public void updateisscored(int arg0, int arg1,int arg2,int arg3) {
        scoremanMapper.updateisscored(arg0,arg1,arg2,arg3);
    }


    @Override
    public void insertscoreman(Score score) {
        scoremanMapper.insertscoreman(score);
    }


    @Override
    public Integer getIsscorcedBy4id(int arg0, int arg1, int arg2,int arg3) {
        return scoremanMapper.getIsscoredBy4id(arg0,arg1,arg2,arg3);
    }

    @Override
    public List<Integer> getiscoredbyindex3(int arg0,int arg1) {
        return scoremanMapper.getiscoredbyindex3id(arg0,arg1);
    }

    @Override
    public Integer getScoreBy4id(int arg0, int arg1, int arg2, int arg3) {
        return scoremanMapper.getScoreBy4id(arg0,arg1,arg2,arg3);
    }
}
