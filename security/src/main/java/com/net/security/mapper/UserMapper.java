package com.net.security.mapper;
import com.net.security.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Insert("INSERT INTO user(name,nickname,password,role) VALUES(#{name},#{nickname},#{password},#{role})")
    @Options(useGeneratedKeys=true,keyProperty = "id")
    void insert(User user);

    @Select("SElECT * FROM user")
    List<User> getAll();

    @Select("select * from user where id=#{id}")
    User findById(Long id);

    @Update("update user set nickname=#{arg0},role=#{arg1} where id=#{arg2}")
    void updatenameByid(String arg0,int arg1,int arg2);





    @Select("select * from user where name like '%${arg0}%' limit ${arg1},${arg2}")
    List<User> getPartBylike(String arg0,int arg1,int arg2);
    @Select("select count(*) from user where name like '%${_parameter}%'")
    Integer getNumBysearch(String _parameter);
    @Select("select count(*) from user")
    Integer getNum();
    @Select("select * from user limit ${arg0},${arg1}")
    List<User> getPart(int arg0,int arg1);
    @Select("select * from user where name like %${name}%")
    List<User> getAllBylike(String name);
    @Delete("delete from user where id=#{id}")
    void deletebyid(int id);
    @Update("update user set name=#{arg0},nickname=#{arg1},password=#{arg2},role=#{arg3} where id=#{arg4}")
    void update1(String arg0,String arg1,String arg2,Integer arg3,Integer arg4);
    @Update("update user set name=#{arg0},nickname=#{arg1},role=#{arg2} where id =#{arg3}")
    void updatepart(String arg0,String arg1,Integer arg2,Integer arg3);


    @Select("select * from user where name=#{name} ")
    List<User> getByname(String name);

    @Select("select * from user where name=#{arg0} and password=#{arg1}")
    User getBynp(String arg0,String arg1);


}
