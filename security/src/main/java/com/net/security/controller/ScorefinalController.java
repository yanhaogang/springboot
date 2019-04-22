package com.net.security.controller;

import com.net.security.bean.Country;
import com.net.security.bean.Index;
import com.net.security.bean.Score;
import com.net.security.bean.Scoresadd;
import com.net.security.service.*;
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
    private SetService setService;

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
        int userid=1;
        List<Country> countries=countryService.getAll();
        List<LinkedHashMap<String,Object>> result=new ArrayList<>();
        for(Country ct:countries){
            LinkedHashMap<String,Object> cousores=new LinkedHashMap<>();
            cousores.put("country",ct.getName());
            List<Score> scoreList=scoreautoService.getAllby2id(ct.getId(),setService.getsetid());
            for(Score sc:scoreList){
                if(sc.getScore()==scoremanService.getScoreBy4id(ct.getId(),sc.getIndex3id(),setService.getsetid(),userid)){
                    cousores.put(index1Service.get3nameByid(sc.getIndex3id()),0);
                }else{
                    cousores.put(index1Service.get3nameByid(sc.getIndex3id()),1);
                }
            }
            result.add(cousores);
        }
        return new JsonResult<>(ResultCode.SUCCESS,result);
    }
    @GetMapping("data")
    public Object getAlldata() {
        Integer setid = setService.getsetid(), userid = 1;
        List<Index> index1 = index1Service.get1All();
        LinkedHashMap<Integer, List<Integer>> finalmap = new LinkedHashMap<>();
        for (Index index : index1) {
            List<Integer> temp = new ArrayList<>();
            List<Index> index2 = index1Service.get2Allbyparent(index.getId());
            for (Index ind : index2) {
                temp.add(ind.getId());
            }
            finalmap.put(index.getId(), temp);
        }
        List<Country> countries = countryService.getAll();
        List<LinkedHashMap<String, Object>> scorefinalmap = new ArrayList<>();
        for (Country ct : countries) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("country", ct.getName());
            for (Index i : index1) {
                int flag = 0;
                List<Integer> threeofone = finalmap.get(i.getId());
                if (threeofone == null) {
                    map.put(i.getName(), "no");
                } else {
                    for (Integer n : threeofone) {
                        if (scorefinalService.getIsscorcedBy4id(n, ct.getId(), userid, setid) != null) {
                            if (scorefinalService.getIsscorcedBy4id(n, ct.getId(), userid, setid) == 1) {
                                flag = 1;
                                break;
                            }
                        }

                    }
                }
                if (flag == 1) {
                    map.put(i.getName(), "yes");
                } else {
                    map.put(i.getName(), "no");
                }
            }
            List<Score> scores = scorefinalService.getAllbycounid(ct.getId(), userid, setid);
            for (Score s : scores) {
                map.put((index1Service.get3nameByid(s.getIndex3id())), s.getScore());
            }
            scorefinalmap.add(map);
        }
        return new JsonResult<>(ResultCode.SUCCESS, scorefinalmap);
    }
    @GetMapping("detail")
    public Object getdetail(@RequestParam String country,String index){
        
        return null;
    }

}
