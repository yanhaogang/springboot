package com.net.security.mapper;

import com.net.security.bean.Score;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ScorefinalMapper {


    @Select("SELECT * FROM score_final")
    @Results({
            @Result(column = "coun_id",property = "counid"),
            @Result(column = "index3_id",property = "index3id"),
            @Result(column = "set_id",property = "setid"),
            @Result(column = "is_scored",property = "isscored"),
            @Result(column = "user_id",property = "userid")
    })
    List<Score> getAll();

    @Select("select * from score_final where coun_id=#{arg0} and user_id=#{arg1} and set_id=#{arg2}")
    @Results({
            @Result(column = "coun_id",property = "counid"),
            @Result(column = "index3_id",property = "index3id"),
            @Result(column = "set_id",property = "setid"),
            @Result(column = "is_scored",property = "isscored"),
            @Result(column = "user_id",property = "userid")
    })
    List<Score> getAllscorebyCounid(int arg0,int arg1,int arg2);
    @Select("select is_scored from score_final where index3_id=#{arg0} and coun_id=#{arg1} and user_id=#{arg2} and set_id=#{arg3}")
    Integer getIsscoredBy4id(int arg0,int arg1,int arg2,int arg3);
    @Insert("insert into score_man (coun_id,index3_id,set_id,score,is_scored,user_id) values(#{counid},#{index3id},#{setid},#{score},#{isscored},#{userid})")
    void insertscorefinal(Score score);
    @Update("update score_final set is_scored=0 where coun_id=#{arg0} and index3_id=#{arg1} and set_id=#{arg2} and user_id=#{arg3}")
    void updateisscored(int arg0,int arg1,int arg2,int arg3);

    @Update("update score_final set score=#{arg0},is_scored=1 where coun_id=#{arg1} and index3_id=#{arg2} and set_id=#{arg3} and user_id=#{arg4}")
    void updataScore(float arg0,int arg1,int arg2,int arg3,int arg4);
    @Select("select * from score_final where user_id=#{arg0} and set_id=#{arg1}")
    List<Score> getAllBy2id(int arg0,int arg1);

    @Delete("delete from score_final where user_id=#{arg0} and set_id=#{arg1}")
    void deleteBy2id(int arg0,int arg1);
}
