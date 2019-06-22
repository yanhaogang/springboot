package com.net.security.mapper;

import com.net.security.bean.Reference;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ReferenceMapper {
    @Select("select * from reference where country=#{arg0} and index3=#{arg1}")
    @Results({
            @Result(column = "content_cn",property = "contentcn"),
            @Result(column = "message_cn",property = "messagecn")
    })
    List<Reference> getBycandi(String arg0,String arg1);

    @Delete("delete from reference where id=#{id}")
    void delByid(int id);

    @Insert("insert into reference(country,index3,content,content_cn,message,message_cn,url) values(#{country},#{index3},#{content},#{contentcn},#{message},#{messagecn},#{url})")
    void insert(Reference reference);


}
