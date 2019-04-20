package com.net.security.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.net.security.bean.Country;
import com.net.security.bean.Finalscore;
import com.net.security.bean.Index;
import com.net.security.bean.Score;
import com.net.security.service.CountryService;
import com.net.security.service.Index1Service;
import com.net.security.service.OrgService;
import com.net.security.service.ScorefinalService;
import com.net.security.utils.JsonResult;
import com.net.security.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Object finalscores(){
        List<Country> countrys=new ArrayList<>();
        String continent=null;
        int counid=0,userid=1,setid=1;
        List<Score> scores;
        List<Finalscore> finalscores=new ArrayList<>();
        float score=0;
        float weights1 = 0;
        countrys=countryService.getAll();
        for(Country ct:countrys){
            //二级指标对应的分数
            LinkedHashMap<Integer,Float> hashMap2=new LinkedHashMap<>();
            //一级指标对应的分数
            LinkedHashMap<Integer,Float> hashMap1=new LinkedHashMap<>();
            finalscore=new Finalscore();
            finalscore.setCountry(ct.getName());
            finalscore.setContinent(ct.getContinent());
            counid=ct.getId();
            scores=scorefinalService.getAllbycounid(counid,userid,setid);
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
                    hashMap1.put(index2.getParent(),ind1+index2.getWeight()*entry.getValue());
                }else {
                    hashMap1.put(index2.getParent(), index2.getWeight() * entry.getValue());
                }
            }
            score=0;

            for(Map.Entry<Integer,Float> entry1:hashMap1.entrySet()){
                Index index1=index1Service.get1byid(entry1.getKey());
                if(index1.getName().equals("legal")) finalscore.setLegal(entry1.getValue());
                if(index1.getName().equals("technical")) finalscore.setTechnical(entry1.getValue());
                if(index1.getName().equals("organization")) finalscore.setOrganization(entry1.getValue());
                if(index1.getName().equals("capacity")) finalscore.setCapacity(entry1.getValue());
                if(index1.getName().equals("cooperation")) finalscore.setCooperation(entry1.getValue());
                score+=index1.getWeight()*entry1.getValue();
            }
            finalscore.setScore(score);
            finalscores.add(finalscore);
            
        }

        return new JsonResult<>(ResultCode.SUCCESS, finalscores);
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





}
