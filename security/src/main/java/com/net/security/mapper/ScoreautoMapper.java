package com.net.security.mapper;

import com.net.security.bean.Score;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScoreautoMapper {
    @Select("select * from score_auto where coun_id=#{arg0} and set_id=#{arg1}")
    List<Score> getAllby2id(int arg0,int arg1);
}
