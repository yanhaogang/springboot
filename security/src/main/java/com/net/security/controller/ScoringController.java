package com.net.security.controller;
import com.net.security.bean.*;
import com.net.security.bean.Set;
import com.net.security.service.*;
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
    @Autowired
    private ReferenceService referenceService;
    @Autowired
    private ScoreautoService scoreautoService;


    @GetMapping("data")
    public Object finddata(@CookieValue("userid") String id){
        int userid=Integer.parseInt(id),setid=setService.getsetid();
        if(scoremanService.getAllBy2id(userid,setid).size()==0){
            List<Scoreauto> scoreautos=scoreautoService.getAllBysetid(setid);
            for(Scoreauto scoreauto:scoreautos){
                Score score1=new Score();
                score1.setUserid(userid);
                score1.setIsscored(0);
                score1.setCounid(scoreauto.getCounid());
                score1.setIndex3id(scoreauto.getIndex3id());
                score1.setSetid(setid);
                score1.setScore(scoreauto.getScore());
                scoremanService.insertscoreman(score1);
            }
        }
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
            int flag1=0;
            for(Index index:index1){
                int flag=0;
                List<Integer> ontthree=fitothird.get(index.getId());
                if(ontthree.size()==0){
                    map.put(index.getName(),"no");
                }else{
                    for(Integer num:ontthree){
                        if(scoremanService.getIsscorcedBy4id(num,ct.getId(),userid,setid)!=1){
                            int nouse=0;
                            flag = 1;
                            break;
                        }
                    }

                }
                if(flag==0){
                    map.put(index.getName(),"yes");
                }else {
                    map.put(index.getName(),"no");
                }
            }
            map.put("country",ct.getName());
            List<Score> scores=scoremanService.getAllby3id(ct.getId(),setid,userid);
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
    public JsonResult delete(@RequestBody Deindex deindex,@CookieValue("userid") String uid){
        int userid=Integer.parseInt(uid);
        int counid=countryService.getidbyname(deindex.getCountry());
        List<Integer> index2=index1Service.get2idbyparent(index1Service.get1idByname(deindex.getIndex()));
        List<Integer> index3id=new ArrayList<>();
        for(int id:index2){
            List<Integer> index3=index1Service.get3idbyparent(id);
            index3id.addAll(index3);
        }
        for(int i:index3id){
            scoremanService.updateisscored(counid,i,setService.getsetid(),userid);
        }
        return new JsonResult<>(ResultCode.SUCCESS,true);

    }

    @PostMapping("submit")
    @Transactional
    public JsonResult addScore(@RequestBody Scoresadd scoresadd){
        int setid=setService.getsetid();
        int counid=countryService.getidbyname(scoresadd.getCountry());
        HashMap<String,Float> hashMap=  scoresadd.getScores();
        for(Map.Entry<String,Float> entry:hashMap.entrySet()){
            int id=index1Service.get3idbyname(entry.getKey());
            int userid=1;
            scoremanService.updataScore(entry.getValue(),counid,id,setid,userid);
        }
        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @PostMapping("choosesets")
    @Transactional
    public JsonResult chooseSet(@RequestBody Setid setid){
        Integer id=setid.getId();
        if(id==null){
            return new JsonResult<>(ResultCode.WARN,false);
        }else {
            setService.to0Byid(setService.getsetid());
            setService.to1Byid(id);
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }
    }
    @PostMapping("editsets")
    @Transactional
    public JsonResult editSets(@RequestBody Set set){
        if(set.getId()==null){
            Set set1=new Set();
            set1.setName(set.getName());
            set1.setIsactive(0);
            set1.setDate(new Date());
            int setid=setService.insert(set1);
            List<Index> index3list=index1Service.get3All();
            List<Country> countryList=countryService.getAll();
            for(Index ind:index3list){
                for(Country country:countryList){
                    Scoreauto scoreauto=new Scoreauto();
                    scoreauto.setCounid(country.getId());
                    scoreauto.setIndex3id(ind.getId());
                    scoreauto.setSetid(setid);
                    if((referenceService.getBycandi(country.getNickname(),ind.getName()).size())==0){
                        scoreauto.setScore(0);
                    }else{
                        scoreauto.setScore(1);
                    }
                    scoreautoService.insert(scoreauto);
                }
            }

        }else{
            setService.updateByid(set.getName(),new Date(),set.getId());
        }

        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @GetMapping("delsets")
    @Transactional
    public Object delSets(@RequestParam Integer id){
        setService.deleteSetbyid(id);
        return new JsonResult<>(ResultCode.SUCCESS,true);
    }

    /**
     * 返回set表，带分页和搜索
     * @param start
     * @param limit
     * @param search
     * @return
     */
    @GetMapping("sets")
    public JsonResult getAllset(@RequestParam Integer start, Integer limit, String search){
        if(search.length()>0){
            Integer c0=setService.getNumBysearch(search);
            Sets sets0=new Sets();
            if(c0==null) {
                sets0.setTotal(0);
                sets0.setSets(null);
                return new JsonResult<>(ResultCode.SUCCESS,sets0);
            }else {
                sets0.setTotal(c0);
                if(start!=null&&limit!=null){
                    sets0.setSets(setService.getPartBylike(search,start,limit));
                    return new JsonResult<>(ResultCode.SUCCESS,sets0);
                }else {
                    sets0.setSets(setService.getAllBylike(search));
                    return new JsonResult<>(ResultCode.SUCCESS,sets0);
                }
            }

        }else{
            Integer c1=setService.getNum();
            Sets sets1=new Sets();
            if(c1==null){
                sets1.setTotal(0);
                sets1.setSets(null);
                return new JsonResult<>(ResultCode.SUCCESS,sets1);
            }else{
                sets1.setTotal(c1);
                if(start!=null&&limit!=null){
                    sets1.setSets(setService.getPart(start,limit));
                    return new JsonResult<>(ResultCode.SUCCESS,sets1);
                }else {
                    sets1.setSets(setService.getAllset());
                    return new JsonResult<>(ResultCode.SUCCESS,sets1);
                }
            }
        }
    }


}
