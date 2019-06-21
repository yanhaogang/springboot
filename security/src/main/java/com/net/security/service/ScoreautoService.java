package com.net.security.service;

import com.net.security.bean.Score;
import com.net.security.bean.Scoreauto;

import java.util.List;

public interface ScoreautoService  {
    List<Score> getAllby2id(int arg0,int arg1);
    void insert(Scoreauto scoreauto);
    List<Scoreauto> getAllBysetid(int setid);
}
