package com.net.security.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.net.security.bean.*;
import com.net.security.bean.Set;
import com.net.security.service.*;
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
    private Index1Service index1Service;
    @Autowired
    private SetService setService;
    @Autowired
    private ArchiveService archiveService;
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
        int setid=setService.getsetid();
        List<Finalscore> finalscores=new ArrayList<>();
        List<Archive> archives=archiveService.getAll1Bysetid(setid);
        List<Country> countries=countryService.getAll();
        HashMap<String,String> cnameToContinent=new HashMap<>();
        HashSet<String> counsets=new HashSet<>();
        for(Country country:countries){
            cnameToContinent.put(country.getName(),country.getContinent());
        }
        for(Archive archive1:archives){
            counsets.add(archive1.getMainname());
        }
        for(String c:counsets){
            List<Archive> archiveList=archiveService.getcBy2(c,setid);
            Finalscore finalscore=new Finalscore();
            for(Archive archive:archiveList){
                finalscore.setCountry(c);
                finalscore.setContinent(cnameToContinent.get(c));
                if(archive.getIndex1id()==-1) finalscore.setScore(archive.getScore());
                if(archive.getIndex1id()==1) finalscore.setStrategy(archive.getScore());
                if(archive.getIndex1id()==2) finalscore.setTechnical(archive.getScore());
                if(archive.getIndex1id()==3) finalscore.setIndustry(archive.getScore());
                if(archive.getIndex1id()==4) finalscore.setCapacity(archive.getScore());
                if(archive.getIndex1id()==5) finalscore.setResources(archive.getScore());
            }
            finalscores.add(finalscore);
        }
        List<Finalscore> totallist=new ArrayList<>();
        List<Archive> archs=archiveService.getAll2Bysetid(setid);
        HashSet<String> orgsets=new HashSet<>();
        HashMap<String,String> orgToType=new HashMap<>();
        List<Org> orgList=orgService.getAll();
        for(Org org:orgList){
            orgToType.put(org.getName(),org.getType());
        }
        for(Archive aa:archs){
            orgsets.add(aa.getMainname());
        }
        for(String s:orgsets){
            List<Archive> archives1=archiveService.getoBy2(s,setid);
            Finalscore finalscore=new Finalscore();
            for(Archive aa:archives1){
                if(aa.getMainid()==-1){
                    finalscore.setCountry(s);
                    finalscore.setContinent("大洲");
                }else {
                    finalscore.setCountry(s);
                    finalscore.setContinent(orgToType.get(s));
                }
                if(aa.getIndex1id()==-1) finalscore.setScore(aa.getScore());
                if(aa.getIndex1id()==1) finalscore.setStrategy(aa.getScore());
                if(aa.getIndex1id()==2) finalscore.setTechnical(aa.getScore());
                if(aa.getIndex1id()==3) finalscore.setIndustry(aa.getScore());
                if(aa.getIndex1id()==4) finalscore.setCapacity(aa.getScore());
                if(aa.getIndex1id()==5) finalscore.setResources(aa.getScore());
            }
            totallist.add(finalscore);
        }
        HashMap<String,List<Finalscore>> hashMap=new HashMap<>();
        hashMap.put("countries",finalscores);
        hashMap.put("orgs",totallist);
    return new JsonResult<>(ResultCode.SUCCESS,hashMap);
    }

    //index三张表，国家名及组织名对应中英文
    @GetMapping("dname")
    public Object entochinese(){
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
        List<Org> orgList=orgService.getAll();
        for(Org org:orgList){
            etocmap.put(org.getName(),org.getNickname());
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

    @PostMapping("line")
    public Object getline(@RequestBody Inputline inputline){
        String index=inputline.getIndex();
        List<Index> index1s=index1Service.get1All();
        HashMap<String,Integer> indexnToid=new HashMap<>();
        for(Index in:index1s){
            indexnToid.put(in.getName(),in.getId());
        }
        indexnToid.put("score",-1);
        List<String> countryList=inputline.getCountries();
        List<HashMap<String,Object>> finallist=new ArrayList<>();
        List<Set> setList=setService.getAllset();
        int index1id=indexnToid.get(index);
        for(Set set:setList){
            HashMap<String,Object> map=new HashMap<>();
            map.put("sets",set.getName());
            for(String c:countryList){
                if(archiveService.getBy3(set.getId(),c,index1id)==null){
                    map.put(c,0);
                }else {
                    map.put(c, archiveService.getBy3(set.getId(), c, index1id).getScore());
                }
            }
            finallist.add(map);
        }
        return new JsonResult<>(ResultCode.SUCCESS,finallist);

    }



}
