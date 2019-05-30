package com.net.security.mapper;

import com.net.security.bean.Score;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ScoremanMapper {

    @Select("select * from score_man where coun_id=#{arg0} and set_id=#{arg1} and user_id=#{arg2}")
    @Results({
            @Result(column = "coun_id",property = "counid"),
            @Result(column = "index3_id",property = "index3id"),
            @Result(column = "set_id",property = "setid"),
            @Result(column = "is_scored",property = "isscored"),
            @Result(column = "user_id",property = "userid")
    })
    List<Score> getAllby3id(int arg0,int arg1,int arg2);
    @Select("select is_scored from score_man where index3_id=#{arg0} and coun_id=#{arg1} and user_id=#{arg2} and set_id=#{arg3}")
    Integer getIsscoredBy4id(int arg0,int arg1,int arg2,int arg3);
    @Select("select is_scored from score_man where index3_id=#{arg0} and coun_id=#{arg1}")
    List<Integer> getiscoredbyindex3id(int arg0,int arg1);
    @Update("update score_man set is_scored=0 where coun_id=#{arg0} and index3_id=#{arg1} and set_id=#{arg2} and user_id=#{arg3}")
    void updateisscored(int arg0,int arg1,int arg2,int arg3);
    @Insert("insert into score_man (coun_id,index3_id,set_id,score,is_scored,user_id) values(#{counid},#{index3id},#{setid},#{score},#{isscored},#{userid})")
    void insertscoreman(Score score);
    @Select("select score from score_man where coun_id=#{arg0} and index3_id=#{arg1} and set_id=#{arg2} and user_id=#{arg3}")
    Integer getScoreBy4id(int arg0,int arg1,int arg2,int arg3);
    @Select("select * from score_man where coun_id=#{arg0} and index3_id=#{arg1} and set_id=#{arg2} and is_scored=1")
    @Results({
            @Result(column = "coun_id",property = "counid"),
            @Result(column = "index3_id",property = "index3id"),
            @Result(column = "set_id",property = "setid"),
            @Result(column = "is_scored",property = "isscored"),
            @Result(column = "user_id",property = "userid")
    })
    List<Score> getAllbycisid(int arg0,int arg1,int arg2);

    @Update("update score_man  set score=#{arg0},is_scored=1 where coun_id=#{arg1} and index3_id=#{arg2} and set_id=#{arg3} and user_id=#{arg4}")
    void updataScore(float arg0,int arg1,int arg2,int arg3,int arg4);
}
