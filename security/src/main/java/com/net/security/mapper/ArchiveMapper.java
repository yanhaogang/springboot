package com.net.security.mapper;

import com.net.security.bean.Archive;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface ArchiveMapper {
    @Insert("insert into archive_coun(main_id,index1_id,index1_name,score,set_id,main_name) values(#{mainid},#{index1id},#{index1name},#{score},#{setid},#{mainname})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void addcoun(Archive archive);

    @Insert("insert into archive_org(main_id,index1_id,index1_name,score,set_id,main_name) values(#{mainid},#{index1id},#{index1name},#{score},#{setid}),#{mainname}")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void addorg(Archive archive);
}
