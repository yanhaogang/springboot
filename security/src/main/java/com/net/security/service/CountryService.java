package com.net.security.service;


import com.net.security.bean.Country;

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




}
