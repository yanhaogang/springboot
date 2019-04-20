package com.net.security.mapper;

import org.apache.ibatis.annotations.Select;

public interface SetMapper {
    @Select("select id from set where is_active=1")
    int getsetid();
}