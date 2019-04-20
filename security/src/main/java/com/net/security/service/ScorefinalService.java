package com.net.security.service;

import com.net.security.bean.Score;

import java.util.List;

public interface ScorefinalService {
    Object getAll();
    List<Score> getAllbycounid(int arg0,int arg1,int arg2);
}
