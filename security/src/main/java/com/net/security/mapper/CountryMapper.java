package com.net.security.mapper;

import com.net.security.bean.Country;
import org.apache.ibatis.annotations.*;

import java.util.Date;
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
    @Select("select * from country where concat(name,nickname) like '%${arg0}%' limit ${arg1},${arg2}")
    List<Country> getPartBylike(String arg0,int arg1,int arg2);
    @Select("select count(*) from country where concat(name,nickname) like '%${_parameter}%'")
    Integer getNumBysearch(String _parameter);
    @Select("select count(*) from country")
    Integer getNum();
    @Select("select * from country limit ${arg0},${arg1}")
    List<Country> getPart(int arg0,int arg1);
    @Select("select * from country where name like '%${_parameter}%'")
    List<Country> getAllBylike(String _parameter);
    @Insert("insert into country(name,nickname,est,language,capital,population,area,economy,other,continent) values(#{name},#{nickname},#{est},#{language},#{capital},#{population},#{area},#{economy},#{other},#{continent})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Country country);
    @Update("update country set name=#{arg0},nickname=#{arg1},est=#{arg2},language=#{arg3},capital=#{arg4},population=#{arg5},area=#{arg6},economy=#{arg7},other=#{arg8},continent=#{arg9} where id=#{arg10}")
    void update(String arg0,String arg1,Date arg2,String arg3,String arg4,Float arg5,Float arg6,String arg7,String arg8,String arg9,int arg10);
}
