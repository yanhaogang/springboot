package com.net.security.mapper;

import com.net.security.bean.Country;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CountryMapper {



    @Select("select * from country")
    List<Country> getAll();

    @Select("select name from country")
    List<String> getallcountry();

    @Select("select distinct continent from country ")
    List<String> getAllcontinent();

    @Select("select name from country where continent=#{continent}")
    List<String> getCountriesbycontinent(String continent);

    @Select("select continent from country where name=#{name}")
    String getContinentbycountry(String name);

    @Delete("delete from country where id=#{id}")
    Boolean deletebyid(int id);
    @Select("select name from country where id=#{id}")
    String getNamebyId(int id);
    @Select("select id from country where name=#{name}")
    int getIdbyName(String name);
}
