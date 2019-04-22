package com.net.security.controller;

import com.net.security.bean.*;
import com.net.security.service.*;
import com.net.security.utils.JsonResult;
import com.net.security.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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
        int counid=countryService.getidbyname(scoresadd.getCountry());
        HashMap<String,Float> hashMap= (HashMap<String, Float>) scoresadd.getScores();
        int userid=1;
        for(Map.Entry<String,Float> entry:hashMap.entrySet()){
            int id=index1Service.get3idbyname(entry.getKey());

            Score score=new Score();
            score.setCounid(counid);
            score.setIndex3id(id);
            score.setSetid(setService.getsetid());
            score.setScore(entry.getValue());
            score.setIsscored(1);
            score.setUserid(userid);
            scorefinalService.InsertScore(score);
        }
        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @Transactional
    @PostMapping("undo")
    public JsonResult Deletefinal(@RequestBody Deindex deindex){
        int counid=countryService.getidbyname(deindex.getCountry());
        int userid=1;
        List<Integer> index2=index1Service.get2idbyparent(counid);
        List<Integer> index3id=new ArrayList<>();
        for(int id:index2){
            List<Integer> index3=index1Service.get3idbyparent(id);
            index3id.addAll(index3);
        }
        for(int i:index3id){
            scorefinalService.updateIsscored(counid,i,setService.getsetid(),userid);
        }
        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @GetMapping("status")
    public Object getAllstatus(){
        int flag=0;
        List<Country> countries=countryService.getAll();
        List<Index> index3=index1Service.get3All();
        List<LinkedHashMap<String,Object>> result=new ArrayList<>();
        for(Country ct:countries){
            LinkedHashMap<String,Object> map=new LinkedHashMap<>();
            map.put("country",ct.getName());
            for(Index id3:index3){
                List<Score> scoreList=scoremanService.getAllbycisid(ct.getId(),id3.getId(),setService.getsetid());
                //如果该指标没有评过分，则认为该指标无冲突
                if(scoreList==null){
                    map.put(id3.getName(),0);
                }
                float temp=scoreList.get(1).getScore();
                flag=0;
                for(Score ss:scoreList){
                    if(temp!=ss.getScore()){
                        map.put(id3.getName(),1);
                        flag=1;
                        break;
                    }
                }
                if(flag==0){
                    map.put(id3.getName(),0);
                }
            }
            result.add(map);
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
        int flag=0;
        List<Index> index1 = index1Service.get1All();
        LinkedHashMap<Integer, List<Integer>> finamap = new LinkedHashMap<>();
        for (Index id1 : index1) {
            List<Index> index2 = index1Service.get2Allbyparent(id1.getId());
            List<Integer> tp = new ArrayList<>();
            for (Index ind : index2) {
                tp.add(ind.getId());
            }
            finamap.put(id1.getId(), tp);
        }
        int counid=countryService.getidbyname(country);
        List<Integer> inde3=finamap.get(index1Service.get3idbyname(index));
        List<Detail> details=new ArrayList<>();
        for(Integer n:inde3){
            flag=0;
            StringBuffer linshi=new StringBuffer();
            Detail detail=new Detail();
            List<Score> list=scoremanService.getAllbycisid(counid,n,setService.getsetid());
            if(list==null){
                detail.setIndex(index1Service.get3nameByid(n));
                detail.setStatus("无评分");
                detail.setScore(0);
            }else{
                float temp=list.get(0).getScore();
                for(Score sc:list){
                    if(temp!=sc.getScore()){
                        flag=1;
                        break;
                    }
                    linshi.append("评分员"+sc.getUserid()+":"+sc.getScore()+",");
                }
                if(flag==0){
                    detail.setIndex(index1Service.get3nameByid(n));
                    detail.setStatus("一致");
                    detail.setDetail(linshi.toString());
                    detail.setScore(temp);
                }else {
                    detail.setIndex(index1Service.get3nameByid(n));
                    detail.setStatus("冲突");
                    detail.setDetail(linshi.toString());
                    detail.setScore(0);
                }
            }
            details.add(detail);
        }
        return new JsonResult<>(ResultCode.SUCCESS,details);
    }



}
