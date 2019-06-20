package com.net.security.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.net.security.bean.*;
import com.net.security.service.*;
import com.net.security.utils.FloatSolve;
import com.net.security.utils.JsonResult;
import com.net.security.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@RestController
@RequestMapping("/api/visual")
public class GetapiController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private ScorefinalService scorefinalService;
    @Autowired
    private Index1Service index1Service;
    @Autowired
    private SetService setService;
    @Autowired
    private CtooService ctooService;
    private Finalscore finalscore;

    @GetMapping("countries")
    public Object countrytocon(){

        List<String> continents;
        List<String> country;
        continents=countryService.getAllcontinent();
        Gson gson=new GsonBuilder().enableComplexMapKeySerialization().create();
        Map<String,List<String>> map=new HashMap<>();
        for(String continent:continents){
            country=countryService.getCountriesbyContinent(continent);
            map.put(continent,country);

        }
        return new JsonResult<>(ResultCode.SUCCESS,map);
    }

    @GetMapping("orgs")
    public Object orgstocon(){
        List<String> continents;
        continents=countryService.getAllcontinent();
        Map<String,List<String>> map=new HashMap<>();
        map.put("大洲",continents);
        continents=orgService.getallorg();
        map.put("联盟",continents);
        Gson gson=new GsonBuilder().enableComplexMapKeySerialization().create();
        return new JsonResult<>(ResultCode.SUCCESS,map);
    }

    @GetMapping("scores")
    public Object finalscores(@CookieValue("userid") String userid){
        List<Country> countrys;
        int counid;
        int setid=setService.getsetid();
        HashSet<String> cnentset=new HashSet<>();
        List<Score> scores;
        List<Finalscore> finalscores=new ArrayList<>();
        float score=0;
        countrys=countryService.getAll();
        for(Country ct:countrys){
            cnentset.add(ct.getContinent());
            //二级指标对应的分数
            LinkedHashMap<Integer,Float> hashMap2=new LinkedHashMap<>();
            //一级指标对应的分数
            LinkedHashMap<Integer,Float> hashMap1=new LinkedHashMap<>();
            finalscore=new Finalscore();
            finalscore.setCountry(ct.getName());
            finalscore.setContinent(ct.getContinent());
            counid=ct.getId();
            scores=scorefinalService.getAllbycounid(counid,Integer.parseInt(userid),setid);
            for(Score score1:scores){
                Index index=  index1Service.get3byid(score1.getIndex3id());
                if(hashMap2.containsKey(index.getParent())){
                    float ind=hashMap2.get(index.getParent());
                    hashMap2.put(index.getParent(),ind+index.getWeight()* score1.getScore());
                }else {
                    hashMap2.put(index.getParent(), index.getWeight() * score1.getScore());
                }

            }
            for(Map.Entry<Integer,Float> entry:hashMap2.entrySet()){
                Index index2=index1Service.get2byid(entry.getKey());

                if(hashMap1.containsKey(index2.getParent())){
                    float ind1=hashMap1.get(index2.getParent());
                    hashMap1.put(index2.getParent(),new FloatSolve().transfloat(ind1+index2.getWeight()*entry.getValue()));
                }else {
                    hashMap1.put(index2.getParent(), new FloatSolve().transfloat(index2.getWeight() * entry.getValue()));
                }
            }

            score=0;
            for(Map.Entry<Integer,Float> entry1:hashMap1.entrySet()){
                Index index1=index1Service.get1byid(entry1.getKey());
                if(index1.getName().equals("strategy")) finalscore.setStrategy(entry1.getValue());
                if(index1.getName().equals("technical")) finalscore.setTechnical(entry1.getValue());
                if(index1.getName().equals("industry")) finalscore.setIndustry(entry1.getValue());
                if(index1.getName().equals("capacity")) finalscore.setCapacity(entry1.getValue());
                if(index1.getName().equals("resources")) finalscore.setResources(entry1.getValue());
                score+=index1.getWeight()*entry1.getValue();
            }
            finalscore.setScore(new FloatSolve().transfloat(score));
            finalscores.add(finalscore);
            
        }
        List<Finalscore> totallists=new ArrayList<>();
        for(String s:cnentset){
            Finalscore cnent=new Finalscore();
            cnent.setCountry(s);
            cnent.setContinent(s);
            float finalscore=0,finallegal=0,finaltech=0,finalorg=0,finalcap=0,finalcoo=0;
            int count=0;
            for(Finalscore f:finalscores){
                if((f.getContinent()).equals(s)){
                    finalscore+=f.getScore();
                    finallegal+=f.getStrategy();
                    finaltech+=f.getTechnical();
                    finalorg+=f.getIndustry();
                    finalcap+=f.getCapacity();
                    finalcoo+=f.getResources();
                    count++;
                }
            }
            cnent.setScore(new FloatSolve().transfloat(finalscore/count));
            cnent.setStrategy(new FloatSolve().transfloat(finallegal/count));
            cnent.setTechnical(new FloatSolve().transfloat(finaltech/count));
            cnent.setIndustry(new FloatSolve().transfloat(finalorg/count));
            cnent.setCapacity(new FloatSolve().transfloat(finalcap/count));
            cnent.setResources(new FloatSolve().transfloat(finalcoo/count));
            totallists.add(cnent);
        }
        List<Org> orglist=orgService.getAll();
        for(Org org:orglist){
            Finalscore orgg=new Finalscore();
            orgg.setCountry(org.getName());
            orgg.setContinent(org.getType());
            List<String> couns=ctooService.getAllcByoid(org.getId());
            HashSet<String> hashSet=new HashSet<>();
            for(String a:couns){
                hashSet.add(a);
            }
            float finalscore1=0,finallegal1=0,finaltech1=0,finalorg1=0,finalcap1=0,finalcoo1=0;
            int count1=0;
            for(Finalscore ff:finalscores){
                if(hashSet.contains(ff.getCountry())){
                    count1++;
                    finalscore1+=ff.getScore();
                    finallegal1+=ff.getStrategy();
                    finaltech1+=ff.getTechnical();
                    finalorg1+=ff.getIndustry();
                    finalcap1+=ff.getCapacity();
                    finalcoo1+=ff.getResources();
                }
            }
            if(count1!=0) {
                orgg.setScore(new FloatSolve().transfloat(finalscore1 / count1));
                orgg.setStrategy(new FloatSolve().transfloat(finallegal1 / count1));
                orgg.setTechnical(new FloatSolve().transfloat(finaltech1 / count1));
                orgg.setIndustry(new FloatSolve().transfloat(finalorg1 / count1));
                orgg.setCapacity(new FloatSolve().transfloat(finalcap1 / count1));
                orgg.setResources(new FloatSolve().transfloat(finalcoo1 / count1));
                totallists.add(orgg);
            }else {
                orgg.setScore(0);
                orgg.setStrategy(0);
                orgg.setTechnical(0);
                orgg.setIndustry(0);
                orgg.setCapacity(0);
                orgg.setResources(0);
                totallists.add(orgg);
            }

        }
        HashMap<String,List<Finalscore>> map =new HashMap<>();

        map.put("countries",finalscores);
        map.put("orgs",totallists);
        return new JsonResult<>(ResultCode.SUCCESS, map);
    }

    //index三张表，国家名对应中英文
    @GetMapping("dname")
    public Object entochinese(){
        Gson gson=new GsonBuilder().enableComplexMapKeySerialization().create();
        LinkedHashMap<String,String> etocmap=new LinkedHashMap<>();
        List<Index> list1,list2,list3;
        list1=index1Service.get1All();
        list2=index1Service.get2All();
        list3=index1Service.get3All();
        for(Index index1:list1){
            etocmap.put(index1.getName(),index1.getNickname());
        }
        for(Index index2:list2){
            etocmap.put(index2.getName(),index2.getNickname());
        }
        for(Index index3:list3){
            etocmap.put(index3.getName(),index3.getNickname());
        }
        List<Country> list=countryService.getAll();
        for(Country country:list){
            etocmap.put(country.getName(),country.getNickname());
        }
        return new JsonResult<>(ResultCode.SUCCESS,etocmap);



    }

    //返回指标的树形结构
    @GetMapping("indexes")
    public Object getTreeofIndex(){
        Gson gson=new GsonBuilder().enableComplexMapKeySerialization().create();
        LinkedHashMap<String,List<String>> setothird=new LinkedHashMap<>();
        LinkedHashMap<String,LinkedHashMap<String,List<String>>> fitose=new LinkedHashMap<>();
        List<Index> index2=index1Service.get2All();
        List<Index> index1=index1Service.get1All();
        for(Index index:index2){
            setothird.put(index.getName(),index1Service.get3nameByparent(index.getId()));
        }
        for(Index i:index1){
            LinkedHashMap<String,List<String>> lt=new LinkedHashMap<>();
            for(Map.Entry<String,List<String>> entry:setothird.entrySet()){
                if(index1Service.get2parentbyname(entry.getKey())!=null&&i.getId()==index1Service.get2parentbyname(entry.getKey())){

                    lt.put(entry.getKey(),entry.getValue());
                }
            }
            fitose.put(i.getName(),lt);
        }


        return new JsonResult<>(ResultCode.SUCCESS,fitose);
    }
    @GetMapping("scoreorgs")
    public Object getScoreorgs(){
        return null;
    }



}
