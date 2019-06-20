package com.net.security.service;

import com.net.security.bean.User;

import java.util.List;

public interface UserService {
    void insert(User user);
    List<User> getAll();
    Object findall();
    User findbyid(Long id);
    void updatenameByid(String arg0,int arg1,int arg2);
    List<User> getPartBylike(String arg0, int arg1, int arg2);
    Integer getNumBysearch(String _parameter);
    Integer getNum();
    List<User> getPart(int arg0,int arg1);
    List<User> getAllBylike(String name);
    void deletebyid(int id);
    void update1(String arg0,String arg1,String arg2,Integer arg3,Integer arg4);
    void updatepart(String arg0,String arg1,Integer arg2,Integer arg3);


    List<User> getByname(String name);
    User getBynp(String arg0,String arg1);
    void updatePD(String arg0,int arg1);

}
