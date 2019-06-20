package com.net.security.mapper;

import com.net.security.bean.Reference;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReferenceMapper {
    @Select("select * from reference where country=#{arg0} and index3=#{arg1}")
    @Results({
            @Result(column = "content_cn",property = "contentcn"),
            @Result(column = "message_cn",property = "messagecn")
    })
    List<Reference> getBycandi(String arg0,String arg1);

}
