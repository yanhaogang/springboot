package com.net.security.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrgMapper {
    @Select("select name from org")
    List<String> getallorg();
}
