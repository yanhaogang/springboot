package com.net.security.service;

import com.net.security.bean.Score;

import java.util.List;

public interface ScorefinalService {
    Object getAll();
    List<Score> getAllbycounid(int arg0,int arg1,int arg2);
    Integer getIsscorcedBy4id(int arg0,int arg1,int arg2,int arg3);
    void InsertScore(Score score);
    void updateIsscored(int arg0,int arg1,int arg2,int arg3);
    void updataScore(float arg0,int arg1,int arg2,int arg3,int arg4);
    List<Score> getAllBy2id(int arg0,int arg1);
    void deleteBy2id(int arg0,int arg1);
}
