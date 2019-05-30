package com.net.security.service;

import com.net.security.bean.Org;

import java.util.List;

public interface OrgService {
    List<String> getallorg();

    List<Org> getPartBylike(String arg0, int arg1, int arg2);
    Integer getNumBysearch(String _parameter);
    Integer getNum();
    List<Org> getPart(int arg0,int arg1);
    List<Org> getAllBylike(String name);
    void deletebyid(int id);
    int insert(Org org);
    void update(String arg0,String arg1,String arg2,Integer arg3);
    List<Org> getAll();
}
