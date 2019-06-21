package com.net.security.mapper;

import com.net.security.bean.Score;
import com.net.security.bean.Scoreauto;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ScoreautoMapper {
    @Select("select * from score_auto where coun_id=#{arg0} and set_id=#{arg1}")
    @Results({
            @Result(column = "coun_id",property = "counid"),
            @Result(column = "index3_id",property = "index3id"),
            @Result(column = "set_id",property = "setid")

    })
    List<Score> getAllby2id(int arg0,int arg1);
    @Insert("insert into score_auto(coun_id,index3_id,set_id,score) values(#{counid},#{index3id},#{setid},#{score})")
    @Options(useGeneratedKeys = true,keyProperty ="id")
    void insert(Scoreauto scoreauto);
    @Select("select * from score_auto where set_id=#{setid}")
    @Results({
            @Result(column = "coun_id",property = "counid"),
            @Result(column = "index3_id",property = "index3id"),
            @Result(column = "set_id",property = "setid")

    })
    List<Scoreauto> getAllBysetid(int setid);

}
