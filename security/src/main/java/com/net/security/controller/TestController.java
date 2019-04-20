package com.net.security.controller;

import com.net.security.bean.Score;
import com.net.security.service.Index1Service;
import com.net.security.service.ScoremanService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@MapperScan("com.net.security.mapper")
public class TestController {
    @Autowired
    private Index1Service index1Service;
    @Autowired
    private ScoremanService scoremanService;
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


}
