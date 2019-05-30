package com.net.security.mapper;

import com.net.security.bean.Org;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrgMapper {
    @Select("select name from org")
    List<String> getallorg();

    @Select("select * from org where concat(name,nickname) like '%${arg0}%' limit ${arg1},${arg2}")
    List<Org> getPartBylike(String arg0, int arg1, int arg2);
    @Select("select count(*) from org where concat(name,nickname) like '%${_parameter}%'")
    Integer getNumBysearch(String _parameter);
    @Select("select count(*) from org")
    Integer getNum();
    @Select("select * from org limit ${arg0},${arg1}")
    List<Org> getPart(int arg0,int arg1);
    @Select("select * from org where name like %${name}%")
    List<Org> getAllBylike(String name);
    @Delete("delete from org where id=#{id}")
    void deletebyid(int id);
    @Insert("insert into org(name,nickname,type) values(#{name},#{nickname},#{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Org org);
    @Update("update org set name=#{arg0},nickname=#{arg1},type=#{arg2} where id=#{arg3}")
    void update(String arg0,String arg1,String arg2,Integer arg3);
    @Select("select * from org")
    List<Org> getAll();

}
