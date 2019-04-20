package com.net.security.service;

import com.net.security.bean.Index;

import java.util.List;

public interface Index1Service {
    Index get3byid(int id);
    Index get2byid(int id);
    String get1namebyid(int id);
    Index get1byid(int id);
    List<Index> get1All();
    List<Index> get2All();
    List<Index> get3All();
    List<String> get2namebyparent(int parent);
    List<String> get3nameByparent(int parent);
    String get3nameByid(int id);
    List<Index> get2Allbyparent(int parent);
    List<Index> get3Allbyparent(int parent);
    List<Integer> get2idbyparent(int parent);
    List<Integer> get3idbyparent(int parent);
    Integer get3idbyname(String name);
    Integer get2parentbyname(String name);

}
