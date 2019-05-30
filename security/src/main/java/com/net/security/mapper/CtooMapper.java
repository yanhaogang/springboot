package com.net.security.mapper;

import com.net.security.bean.Countoorg;
import com.net.security.bean.Org;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CtooMapper {

    @Select("select name from country where id in(select cid from coun_to_org where oid=#{oid})")
    List<String> getAllcByoid(int oid);
    @Insert("INSERT INTO coun_to_org(cid,oid) VALUES(#{cid},#{oid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Countoorg countoorg);
    @Delete("delete from coun_to_org where oid =#{oid}")
    void delcto(Integer oid);
}
