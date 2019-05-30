package com.net.security.mapper;

import com.net.security.bean.Set;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface SetMapper {
    @Select("select id from aset where is_active=1")
    Integer getsetid();

    @Select("select * from aset")
    @Results({
            @Result(column = "set_date",property = "date"),
            @Result(column = "is_active", property = "isactive")
    })
    List<Set> getAllset();

    @Update("update aset set is_active=1 where id =#{id}")
    void to1Byid(int id);

    @Update("update aset set is_active=0 where id =#{id}")
    void to0Byid(int id);

    @Update("update aset set name=#{arg0} ,set_date=#{arg1} where id=#{arg2}")
    void updateByid(String arg0,  Date arg1,  int arg2);

    @Insert("INSERT INTO aset(name,set_date,is_active) VALUES(#{name},#{date},#{isactive})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Set set);
    @Select("select * from aset where name like '%${arg0}%' limit ${arg1},${arg2}")
    @Results({
            @Result(column = "set_date",property = "date"),
            @Result(column = "is_active", property = "isactive")
    })
    List<Set> getPartBylike(String arg0,int arg1,int arg2);
    @Select("select count(*) from aset where name like '%${_parameter}%'")
    Integer getNumBysearch(String _parameter);
    @Select("select count(*) from aset")
    Integer getNum();
    @Select("select * from aset limit ${arg0},${arg1}")
    @Results({
            @Result(column = "set_date",property = "date"),
            @Result(column = "is_active", property = "isactive")
    })
    List<Set> getPart(int arg0,int arg1);
    @Select("select * from aset where name like '%${_parameter}%'")
    @Results({
            @Result(column = "set_date",property = "date"),
            @Result(column = "is_active", property = "isactive")
    })
    List<Set> getAllBylike(String _parameter);
    @Delete("delete from aset where id=#{id}")
    void deleteSetbyid(int id);

}
