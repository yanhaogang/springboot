package com.net.security.mapper;

import com.net.security.bean.Archive;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArchiveMapper {
    @Insert("insert into archive_coun(main_id,index1_id,index1_name,score,set_id,main_name) values(#{mainid},#{index1id},#{index1name},#{score},#{setid},#{mainname})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void addcoun(Archive archive);
    @Delete("delete from archive_coun where set_id=#{setid}")
    void deleteCounBysetid(int setid);
    @Select("select * from archive_coun where set_id=#{setid}")
    @Results({
            @Result(column = "main_id",property = "mainid"),
            @Result(column = "index1_id",property = "index1id"),
            @Result(column = "set_id",property = "setid"),
            @Result(column = "index1_name",property = "index1name"),
            @Result(column = "main_name",property = "mainname")
    })
    List<Archive> getAll1Bysetid(int setid);

    @Select("select * from archive_coun where main_name=#{arg0} and set_id=#{arg1}")
    @Results({
            @Result(column = "main_id",property = "mainid"),
            @Result(column = "index1_id",property = "index1id"),
            @Result(column = "set_id",property = "setid"),
            @Result(column = "index1_name",property = "index1name"),
            @Result(column = "main_name",property = "mainname")
    })
    List<Archive> getcBy2(String arg0,int arg1);


    @Insert("insert into archive_org(main_id,index1_id,index1_name,score,set_id,main_name) values(#{mainid},#{index1id},#{index1name},#{score},#{setid},#{mainname})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void addorg(Archive archive);

    @Delete("delete from archive_org where set_id=#{setid}")
    void deleteOrgBysetid(int setid);
    @Select("select * from archive_org where set_id=#{setid}")
    List<Archive> getAll2Bysetid(int setid);

    @Select("select * from archive_coun where main_name=#{mainname}")
    @Results({
            @Result(column = "main_id",property = "mainid"),
            @Result(column = "index1_id",property = "index1id"),
            @Result(column = "set_id",property = "setid"),
            @Result(column = "index1_name",property = "index1name"),
            @Result(column = "main_name",property = "mainname")
    })
    List<Archive> getcByMainname(String mainname);
    @Select("select * from archive_coun where set_id=#{arg0} and main_name=#{arg1} and index1_id=#{arg2}")
    Archive getBy3(int arg0,String arg1,int arg2);

    @Select("select * from archive_org where main_name=#{arg0} and set_id=#{arg1}")
    @Results({
            @Result(column = "main_id",property = "mainid"),
            @Result(column = "index1_id",property = "index1id"),
            @Result(column = "set_id",property = "setid"),
            @Result(column = "index1_name",property = "index1name"),
            @Result(column = "main_name",property = "mainname")
    })
    List<Archive> getoBy2(String arg0,int arg1);





}
