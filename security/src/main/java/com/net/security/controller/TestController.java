package com.net.security.controller;

import com.net.security.bean.Score;
import com.net.security.service.CtooService;
import com.net.security.service.Index1Service;
import com.net.security.service.ScoremanService;
import com.net.security.service.SetService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


@RestController
@MapperScan("com.net.security.mapper")
public class TestController {
    @Autowired
    private Index1Service index1Service;
    @Autowired
    private ScoremanService scoremanService;
    @Autowired
    private SetService setService;
    @Autowired
    private CtooService ctooService;
    @GetMapping("/")
    public String test(){
        return "hello";
    }
    @GetMapping("testinsert")
    public void insert(){
        Score score=new Score();
        score.setCounid(2);
        score.setIndex3id(3);
        score.setScore(2);
        score.setIsscored(1);
        scoremanService.insertscoreman(score);
    }
    @GetMapping("testset")
    public Object TestSet(){
        if(setService.getsetid()!=null){
            return setService.getAllset();
        }else{
            return true;
        }


    }
    @GetMapping("testcto")
    public Object getCto(@RequestParam int oid){
        return ctooService.getAllcByoid(oid);
    }
    @GetMapping("testcookie")
    public String getCookies(HttpServletResponse response){
        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        return "测试cookies成功";
    }
    @GetMapping("getwithcookies")
    public String getwithc(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
            return "cookies is null";
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "success";
            }
        }
        return "false";
    }
    @GetMapping("testredis")
    public Object testredis(){
        String username="yhg";
        String password="zz";
        RedisProperties.Jedis jedis=new RedisProperties.Jedis();
        return null;
    }




}
