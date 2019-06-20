package com.net.security.mapper;

import com.net.security.bean.Score;
import com.net.security.bean.Scoreauto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScoreautoMapper {
    @Select("select * from score_auto where coun_id=#{arg0} and set_id=#{arg1}")
    List<Score> getAllby2id(int arg0,int arg1);
    @Insert("insert into score_auto(coun_id,index3_id,set_id,score) values(#{counid,index3id,setid,score)")
    @Options(useGeneratedKeys = true,keyProperty ="id")
    void insert(Scoreauto scoreauto);

}
