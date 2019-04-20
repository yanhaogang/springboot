package com.net.security.mapper;
import com.net.security.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {


    @Insert("INSERT INTO user(name,nickname,password,role) VALUES(#{name},#{nickname},#{password},#{role})")
    @Options(useGeneratedKeys=true,keyProperty = "id")
    int insert(User user);

    @Select("SElECT * FROM user")
    List<User> getAll();

    @Select("select * from user where id=#{id}")
    User findById(Long id);

    //@Update("")
    //@Delete("")



}
