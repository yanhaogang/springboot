package com.net.security.controller;
import com.net.security.bean.*;
import com.net.security.service.CountryService;
import com.net.security.service.Index1Service;
import com.net.security.service.ScoremanService;
import com.net.security.service.SetService;
import com.net.security.utils.JsonResult;
import com.net.security.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/scoring")
@Transactional(propagation = Propagation.REQUIRED)
public class ScoringController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private Index1Service index1Service;
    @Autowired
    private ScoremanService scoremanService;
    @Autowired
    private SetService setService;


    @GetMapping("data")
    public Object finddata(){
        int userid=1,setid=1;
        List<Index> first=new ArrayList<>();
        LinkedHashMap<Integer,List<Integer>> fitothird=new LinkedHashMap<>();
        first=index1Service.get1All();
        for(Index fi:first){
            List<Integer> xfirst=new ArrayList<>();
            List<Index> second=index1Service.get2Allbyparent(fi.getId());
            for(Index se:second){
                List<Index> third=index1Service.get3Allbyparent(se.getId());
                for(Index th:third){
                    xfirst.add(th.getId());
                }

            }
            fitothird.put(fi.getId(),xfirst);

        }
        List<Index> index1=index1Service.get1All();
        List<Country> countries=countryService.getAll();
        List<LinkedHashMap<String,Object>> finalmap=new ArrayList<>();
        for(Country ct:countries){
            LinkedHashMap<String,Object> map=new LinkedHashMap<>();
            map.put("country",ct.getName());
            for(Index index:index1){
                int flag=0;
                List<Integer> ontthree=fitothird.get(index.getId());
                if(ontthree==null){
                    map.put(index.getName(),"no");
                }else{
                    for(Integer num:ontthree){
                        if(scoremanService.getIsscorcedBy4id(num,ct.getId(),userid,setid)!=null&&scoremanService.getIsscorcedBy4id(num,ct.getId(),userid,setid)==1){
                            flag = 1;
                            break;

                        }
                    }

                }
                if(flag==1){
                    map.put(index.getName(),"yes");
                }else {
                    map.put(index.getName(),"no");
                }
            }
            List<Score> scores=scoremanService.getAllbycuid(ct.getId(),userid);
            for(Score ss:scores){
                String index3name = index1Service.get3nameByid(ss.getIndex3id());
                map.put(index3name,ss.getScore());
            }
            finalmap.add(map);


        }
        return new JsonResult<>(ResultCode.SUCCESS,finalmap);

    }


    @PostMapping("undo")
    @Transactional
    public JsonResult delete(@RequestBody Deindex deindex){
        int counid=countryService.getidbyname(deindex.getCountry());
        List<Integer> index2=index1Service.get2idbyparent(counid);
        List<Integer> index3id=new ArrayList<>();
        for(int id:index2){
            List<Integer> index3=index1Service.get3idbyparent(id);
            index3id.addAll(index3);
        }
        for(int i:index3id){
            scoremanService.updateisscored(counid,i);
        }
        return new JsonResult<>(ResultCode.SUCCESS,true);

    }

    @PostMapping("submit")
    @Transactional
    public JsonResult addScore(@RequestBody Scoresadd scoresadd){
        int counid=countryService.getidbyname(scoresadd.getCountry());
        HashMap<String,Float> hashMap= (HashMap<String, Float>) scoresadd.getScores();
        for(Map.Entry<String,Float> entry:hashMap.entrySet()){
            int id=index1Service.get3idbyname(entry.getKey());
            int userid=1;
            Score score=new Score();
            score.setCounid(counid);
            score.setIndex3id(id);
            score.setScore(entry.getValue());
            score.setSetid(setService.getsetid());
            score.setIsscored(1);
            score.setUserid(userid);
            scoremanService.insertscoreman(score);

        }
        return new JsonResult<>(ResultCode.SUCCESS,true);
    }

}
