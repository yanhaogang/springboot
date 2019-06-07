package com.net.security.service;


import com.net.security.bean.Set;

import java.util.Date;
import java.util.List;

public interface SetService {
    Integer getsetid();
    List<Set> getAllset();
    void to1Byid(int id);
    void to0Byid(int id);
    void updateByid(String arg0,  Date arg1,  int arg2);
    int insert(Set set);
    List<Set> getPartBylike(String arg0,int arg1,int arg2);
    Integer getNumBysearch(String _parameter);
    Integer getNum();
    List<Set> getPart(int arg0,int arg1);
    List<Set> getAllBylike(String _parameter);
    void deleteSetbyid(int id);

}
