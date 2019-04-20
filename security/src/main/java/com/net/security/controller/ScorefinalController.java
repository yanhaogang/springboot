package com.net.security.controller;

import com.net.security.bean.Country;
import com.net.security.bean.Score;
import com.net.security.bean.Scoresadd;
import com.net.security.service.*;
import com.net.security.serviceimpl.Index1Serviceimpl;
import com.net.security.utils.JsonResult;
import com.net.security.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Transactional(propagation = Propagation.REQUIRED)
@RestController
@RequestMapping("/api/scorefinal")
public class ScorefinalController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private ScoreautoService scoreautoService;
    @Autowired
    private ScoremanService scoremanService;
    @Autowired
    private Index1Service index1Service;

    @Autowired
    private ScorefinalService scorefinalService;
    @RequestMapping("findall")
    public Object findall(){
        return scorefinalService.getAll();
    }
    @Transactional
    @PostMapping("submit")
    public JsonResult insertscore(@RequestBody Scoresadd scoresadd){

        return null;
    }
    @GetMapping("status")
    public Object getAllstatus(){
        int setid=1,userid=1;
        List<Country> countries=countryService.getAll();
        List<LinkedHashMap<String,Object>> result=new ArrayList<>();
        for(Country ct:countries){
            LinkedHashMap<String,Object> cousores=new LinkedHashMap<>();
            cousores.put("country",ct.getName());
            List<Score> scoreList=scoreautoService.getAllby2id(ct.getId(),setid);
            for(Score sc:scoreList){
                if(sc.getScore()==scoremanService.getScoreBy4id(ct.getId(),sc.getIndex3id(),setid,userid)){
                    cousores.put(index1Service.get3nameByid(sc.getIndex3id()),0);
                }else{
                    cousores.put(index1Service.get3nameByid(sc.getIndex3id()),1);
                }
            }
            result.add(cousores);
        }
        return new JsonResult<>(ResultCode.SUCCESS,result);
    }

}
