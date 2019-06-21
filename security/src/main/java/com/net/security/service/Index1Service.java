package com.net.security.service;

import com.net.security.bean.Index;
import com.net.security.bean.Index1;

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
    Integer get1idByname(String name);
    List<Index> getPart3Bylike(String arg0,int arg1,int arg2);
    Integer getNum3Bysearch(String _parameter);
    Integer getNum3();
    List<Index> getPart3(int arg0,int arg1);
    List<Index> getAll3Bylike(String name);
    void delete3byid(int id);
    void insert3(Index index);
    void update3(String arg0,String arg1,Float arg2,String arg3,Integer arg4,Integer arg5);

    List<Index> getPart2Bylike(String arg0,int arg1,int arg2);
    Integer getNum2Bysearch(String _parameter);
    Integer getNum2();
    List<Index> getPart2(int arg0,int arg1);
    List<Index> getAll2Bylike(String name);
    void delete2byid(int id);
    void insert2(Index index);
    void update2(String arg0,String arg1,Float arg2,String arg3,Integer arg4,Integer arg5);


    List<Index> getPart1Bylike(String arg0,int arg1,int arg2);
    Integer getNum1Bysearch(String _parameter);
    Integer getNum1();
    List<Index> getPart1(int arg0,int arg1);
    List<Index> getAll1Bylike(String name);
    void delete1byid(int id);
    void insert1(Index1 index1);
    void update1(String arg0,String arg1,Float arg2,String arg3,Integer arg4);
    String get3nicknameByid(int id);

}
