package com.net.security.mapper;

import com.net.security.bean.Set;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SetMapper {
    @Select("select id from aset where is_active=1")
    Integer getsetid();
    @Select("select * from aset")
    @Result(column = "is_active",property = "isactive")
    List<Set> getAllset();
}