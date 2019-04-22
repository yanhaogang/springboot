package com.net.security.service;

import com.net.security.bean.Score;

import java.util.List;

public interface ScoremanService {
    List<Score> getAllby3id(int arg0,int arg1,int arg2);
    List<Integer> getiscoredbyindex3(int arg0,int arg1);
    void updateisscored(int arg0,int arg1,int arg2,int arg3);
    void insertscoreman(Score score);
    Integer getIsscorcedBy4id(int arg0,int arg1,int arg2,int arg3);
    Integer getScoreBy4id(int arg0,int arg1,int arg2,int arg3);
}
