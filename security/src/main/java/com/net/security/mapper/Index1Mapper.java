package com.net.security.mapper;

import com.net.security.bean.Index;
import com.net.security.bean.Index1;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface Index1Mapper {
    @Select("select * from index_three where id=#{id}")
    Index get3byid(int id);

    @Select("select * from index_two where id=#{id}")
    Index get2byid(int id);

    @Select("select name from index_one where id=#{id}")
    String get1namebyid(int id);
    @Select("select * from index_one where id=#{id}")
    Index get1byid(int id);

    @Select("select * from index_one")
    List<Index> get1All();

    @Select("select * from index_two")
    List<Index> get2All();

    @Select("select * from index_three")
    List<Index> get3All();

    @Select("select name from index_two where parent=#{parent}")
    List<String> get2namebyparent(int parent);

    @Select("select name from index_three where parent=#{parent}")
    List<String> get3namebyparent(int parent);

    @Select("select name from index_three where id=#{id}")
    String get3namebyid(int id);

    @Select("select nickname from index_three where id=#{id}")
    String get3nicknameByid(int id);

    @Select("select * from index_two where parent=#{parent}")
    List<Index> get2Allbyparent(int parent);
    @Select("select * from index_three where parent=#{parent}")
    List<Index> get3Allbyparent(int parent);
    @Select("select id from index_two where parent=#{parent}")
    List<Integer> get2idbyparent(int parent);
    @Select("select id from index_three where parent=#{parent}")
    List<Integer> get3idbyparent(int parent);
    @Select("select id from index_three where name=#{name}")
    Integer get3idbyname(String name);
    @Select("select parent from index_two where name=#{name}")
    Integer get2parentbyname(String name);
    @Select("select id from index_one where name=#{name}")
    Integer get1idByname(String name);
    @Select("select * from index_three where concat(name,nickname) like '%${arg0}%' limit ${arg1},${arg2}")
    List<Index> getPart3Bylike(String arg0,int arg1,int arg2);
    @Select("select count(*) from index_three where concat(name,nickname) like '%${_parameter}%'")
    Integer getNum3Bysearch(String _parameter);
    @Select("select count(*) from index_three")
    Integer getNum3();
    @Select("select * from index_three limit ${arg0},${arg1}")
    List<Index> getPart3(int arg0,int arg1);
    @Select("select * from index_three where name like %${name}%")
    List<Index> getAll3Bylike(String name);
    @Delete("delete from index_three where id=#{id}")
    void delete3byid(int id);
    @Insert("insert into index_three(name,nickname,weight,other,parent) values(#{name},#{nickname},#{weight},#{other},#{parent})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert3(Index index);
    @Update("update index_three set name=#{arg0},nickname=#{arg1},weight=#{arg2},other=#{arg3},parent=#{arg4} where id=#{arg5}")
    void update3(String arg0,String arg1,Float arg2,String arg3,Integer arg4,Integer arg5);




    @Select("select * from index_two where concat(name,nickname) like '%${arg0}%' limit ${arg1},${arg2}")
    List<Index> getPart2Bylike(String arg0,int arg1,int arg2);
    @Select("select count(*) from index_two where concat(name,nickname) like '%${_parameter}%'")
    Integer getNum2Bysearch(String _parameter);
    @Select("select count(*) from index_two")
    Integer getNum2();
    @Select("select * from index_two limit ${arg0},${arg1}")
    List<Index> getPart2(int arg0,int arg1);
    @Select("select * from index_two where name like %${name}%")
    List<Index> getAll2Bylike(String name);
    @Delete("delete from index_two where id=#{id}")
    void delete2byid(int id);
    @Insert("insert into index_two(name,nickname,weight,other,parent) values(#{name},#{nickname},#{weight},#{other},#{parent})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert2(Index index);
    @Update("update index_two set name=#{arg0},nickname=#{arg1},weight=#{arg2},other=#{arg3},parent=#{arg4} where id=#{arg5}")
    void update2(String arg0,String arg1,Float arg2,String arg3,Integer arg4,Integer arg5);

    @Select("select * from index_one where concat(name,nickname) like '%${arg0}%' limit ${arg1},${arg2}")
    List<Index> getPart1Bylike(String arg0,int arg1,int arg2);
    @Select("select count(*) from index_one where concat(name,nickname) like '%${_parameter}%'")
    Integer getNum1Bysearch(String _parameter);
    @Select("select count(*) from index_one")
    Integer getNum1();
    @Select("select * from index_one limit ${arg0},${arg1}")
    List<Index> getPart1(int arg0,int arg1);
    @Select("select * from index_one where name like %${name}%")
    List<Index> getAll1Bylike(String name);
    @Delete("delete from index_one where id=#{id}")
    void delete1byid(int id);
    @Insert("insert into index_one(name,nickname,weight,other) values(#{name},#{nickname},#{weight},#{other})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert1(Index1 index1);
    @Update("update index_one set name=#{arg0},nickname=#{arg1},weight=#{arg2},other=#{arg3}where id=#{arg4}")
    void update1(String arg0,String arg1,Float arg2,String arg3,Integer arg4);

}
