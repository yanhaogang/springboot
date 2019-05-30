package com.net.security.service;


import com.net.security.bean.Country;

import java.util.Date;
import java.util.List;

public interface CountryService {
    List<Country> getAll();
    //获取所有大洲的名字
    List<String> getAllcontinent();
    //通过大洲名字获取所有国家名
    List<String> getCountriesbyContinent(String c);
    String getContinentbycontry(String name);

    List<String> getallcountry();

    Boolean deletebyid(int id);

    String getnamebyid(int id);

    int getidbyname(String name);
    List<Country> getPartBylike(String arg0,int arg1,int arg2);
    Integer getNumBysearch(String _parameter);
    Integer getNum();
    List<Country> getPart(int arg0,int arg1);
    List<Country> getAllBylike(String _parameter);
    void insert(Country country);
    void update(String arg0, String arg1, Date arg2, String arg3, String arg4, Float arg5, Float arg6, String arg7, String arg8, String arg9, int arg10);




}
