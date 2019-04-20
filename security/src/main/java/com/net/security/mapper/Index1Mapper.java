package com.net.security.mapper;

import com.net.security.bean.Index;
import org.apache.ibatis.annotations.Select;

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

}
