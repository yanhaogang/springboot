package com.net.security.controller;

import com.net.security.bean.*;
import com.net.security.service.*;
import com.net.security.utils.JsonResult;
import com.net.security.utils.ResultCode;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/admin")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private Index1Service index1Service;
    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private CtooService ctooService;

    @GetMapping("delcountry")
    @Transactional
    public Object deletecoun(@RequestParam Integer id){
        countryService.deletebyid(id);
        return new  JsonResult<>(ResultCode.SUCCESS,true);
    }
    @GetMapping("country")
    public Object getCountry(@RequestParam Integer start,Integer limit,String search){
        if(search.length()>0){
            Integer c0=countryService.getNumBysearch(search);
            Countrys countrys0=new Countrys();
            if(c0==null) {
                countrys0.setTotal(0);
                countrys0.setCountries(null);
                return new JsonResult<>(ResultCode.SUCCESS,countrys0);
            }else {
                countrys0.setTotal(c0);
                if(start!=null&&limit!=null){
                    countrys0.setCountries(countryService.getPartBylike(search,start,limit));
                    return new JsonResult<>(ResultCode.SUCCESS,countrys0);
                }else {
                    countrys0.setCountries(countryService.getAllBylike(search));
                    return new JsonResult<>(ResultCode.SUCCESS,countrys0);
                }
            }

        }else{
            Integer c1=countryService.getNum();
            Countrys countrys1=new Countrys();
            if(c1==null){
                countrys1.setTotal(0);
                countrys1.setCountries(null);
                return new JsonResult<>(ResultCode.SUCCESS,countrys1);
            }else{
                countrys1.setTotal(c1);
                if(start!=null&&limit!=null){
                    countrys1.setCountries(countryService.getPart(start,limit));
                    return new JsonResult<>(ResultCode.SUCCESS,countrys1);
                }else {
                    countrys1.setCountries(countryService.getAll());
                    return new JsonResult<>(ResultCode.SUCCESS,countrys1);
                }
            }
        }
    }
    @PostMapping("editcountry")
    @Transactional
    public JsonResult editCountry(@RequestBody Country country){
        if(country.getId()==null){
            Country country1=new Country();
            country1.setName(country.getName());
            country1.setNickname(country.getNickname());
            country1.setEst(country.getEst());
            country1.setLanguage(country.getLanguage());
            country1.setCapital(country.getCapital());
            country1.setPopulation(country.getPopulation());
            country1.setArea(country.getArea());
            country1.setEconomy(country.getEconomy());
            country1.setOther(country.getOther());
            country1.setContinent(country.getContinent());
            countryService.insert(country1);
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }else{
            countryService.update(country.getName(),country.getNickname(),country.getEst(),country.getLanguage(),country.getCapital(),country.getPopulation(),country.getArea(),country.getEconomy(),country.getOther(),country.getContinent(),country.getId());
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }
    }
    @GetMapping("index3")
    public Object getIndex3(@RequestParam Integer start,Integer limit,String search){
        if(search.length()>0){
            Integer i30=index1Service.getNum3Bysearch(search);
            Indexs3 index3s=new Indexs3();
            if(i30==null) {
                index3s.setTotal(0);
                index3s.setIndex3s(null);
                return new JsonResult<>(ResultCode.SUCCESS,index3s);
            }else {
                index3s.setTotal(i30);
                if(start!=null&&limit!=null){
                    List<Index> list=index1Service.getPart3Bylike(search,start,limit);
                    List<Indexs3.Index3> finallist=new ArrayList<>();
                    for(Index i:list){
                        Index index2=index1Service.get2byid(i.getParent());
                        Index index1=index1Service.get1byid(index2.getParent());
                        Indexs3.Index3 lindex3=new Indexs3.Index3();
                        lindex3.setId1(index1.getId());
                        lindex3.setName1(index1.getName());
                        lindex3.setNickname1(index1.getNickname());
                        lindex3.setId2(index2.getId());
                        lindex3.setName2(index2.getName());
                        lindex3.setNickname2(index2.getNickname());
                        lindex3.setId(i.getId());
                        lindex3.setName(i.getName());
                        lindex3.setNickname(i.getNickname());
                        lindex3.setOther(i.getOther());
                        lindex3.setWeight(i.getWeight());
                        lindex3.setParent(i.getParent());
                        finallist.add(lindex3);
                    }
                    index3s.setIndex3s(finallist);
                    return new JsonResult<>(ResultCode.SUCCESS,index3s);
                }else {
                    List<Index> list=index1Service.getAll3Bylike(search);
                    List<Indexs3.Index3> finallist=new ArrayList<>();
                    for(Index i:list){
                        Index index2=index1Service.get2byid(i.getParent());
                        Index index1=index1Service.get1byid(index2.getParent());
                        Indexs3.Index3 lindex3=new Indexs3.Index3();
                        lindex3.setName1(index1.getName());
                        lindex3.setId1(index1.getId());
                        lindex3.setNickname1(index1.getNickname());
                        lindex3.setId2(index2.getId());
                        lindex3.setName2(index2.getName());
                        lindex3.setNickname2(index2.getNickname());
                        lindex3.setId(i.getId());
                        lindex3.setName(i.getName());
                        lindex3.setNickname(i.getNickname());
                        lindex3.setOther(i.getOther());
                        lindex3.setWeight(i.getWeight());
                        lindex3.setParent(i.getParent());
                        finallist.add(lindex3);
                    }
                    index3s.setIndex3s(finallist);
                    return new JsonResult<>(ResultCode.SUCCESS,index3s);
                }
            }

        }else{
            Integer i31=index1Service.getNum3();
            Indexs3 indexs3=new Indexs3();
            if(i31==null){
                indexs3.setTotal(0);
                indexs3.setIndex3s(null);
                return new JsonResult<>(ResultCode.SUCCESS,indexs3);
            }else{
                indexs3.setTotal(i31);
                if(start!=null&&limit!=null){
                    List<Index> list=index1Service.getPart3(start,limit);
                    List<Indexs3.Index3> finallist=new ArrayList<>();
                    for(Index i:list){
                        Index index2=index1Service.get2byid(i.getParent());
                        Index index1=index1Service.get1byid(index2.getParent());
                        Indexs3.Index3 lindex3=new Indexs3.Index3();
                        lindex3.setName1(index1.getName());
                        lindex3.setNickname1(index1.getNickname());
                        lindex3.setId1(index1.getId());
                        lindex3.setId2(index2.getId());
                        lindex3.setName2(index2.getName());
                        lindex3.setNickname2(index2.getNickname());
                        lindex3.setId(i.getId());
                        lindex3.setName(i.getName());
                        lindex3.setNickname(i.getNickname());
                        lindex3.setOther(i.getOther());
                        lindex3.setWeight(i.getWeight());
                        lindex3.setParent(i.getParent());
                        finallist.add(lindex3);
                    }
                    indexs3.setIndex3s(finallist);
                    return new JsonResult<>(ResultCode.SUCCESS,indexs3);
                }else {
                    List<Index> list=index1Service.get3All();
                    List<Indexs3.Index3> finallist=new ArrayList<>();
                    for(Index i:list){
                        Index index2=index1Service.get2byid(i.getParent());
                        Index index1=index1Service.get1byid(index2.getParent());
                        Indexs3.Index3 lindex3=new Indexs3.Index3();
                        lindex3.setName1(index1.getName());
                        lindex3.setNickname1(index1.getNickname());
                        lindex3.setId2(index2.getId());
                        lindex3.setId1(index1.getId());
                        lindex3.setName2(index2.getName());
                        lindex3.setNickname2(index2.getNickname());
                        lindex3.setId(i.getId());
                        lindex3.setName(i.getName());
                        lindex3.setNickname(i.getNickname());
                        lindex3.setOther(i.getOther());
                        lindex3.setWeight(i.getWeight());
                        lindex3.setParent(i.getParent());
                        finallist.add(lindex3);
                    }
                    indexs3.setIndex3s(finallist);
                    return new JsonResult<>(ResultCode.SUCCESS,indexs3);
                }
            }
        }

    }
    @GetMapping("delindex3")
    @Transactional
    public Object delIndex3(@RequestParam Integer id){
        index1Service.delete3byid(id);

        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @PostMapping("editindex3")
    @Transactional
    public Object editIndex3(@RequestBody Index index){
        if(index.getId()==null){
            Index index3=new Index();
            index3.setName(index.getName());
            index3.setNickname(index.getNickname());
            index3.setOther(index.getOther());
            index3.setWeight(index.getWeight());
            index3.setParent(index.getParent());
            index1Service.insert3(index3);
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }else{
            index1Service.update3(index.getName(),index.getNickname(),index.getWeight(),index.getOther(),index.getParent(),index.getId());
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }
    }
    @GetMapping("index2")
    public Object getIndex2(@RequestParam Integer start,Integer limit,String search){
        if(search.length()>0){
            Integer i20=index1Service.getNum2Bysearch(search);
            Indexs2 indexs2=new Indexs2();
            if(i20==null) {
                indexs2.setTotal(0);
                indexs2.setIndex2List(null);
                return new JsonResult<>(ResultCode.SUCCESS,indexs2);
            }else {
                indexs2.setTotal(i20);
                if(start!=null&&limit!=null){
                    List<Index> list=index1Service.getPart2Bylike(search,start,limit);
                    List<Indexs2.Index2> finallist=new ArrayList<>();
                    for(Index i:list){
                        Index indexa=index1Service.get1byid(i.getParent());
                        Indexs2.Index2 index2=new Indexs2.Index2();
                        index2.setId1(indexa.getId());
                        index2.setName1(indexa.getName());
                        index2.setNickname1(indexa.getNickname());
                        index2.setId(i.getId());
                        index2.setName(i.getName());
                        index2.setNickname(i.getNickname());
                        index2.setOther(i.getOther());
                        index2.setWeight(i.getWeight());
                        index2.setParent(i.getParent());
                        finallist.add(index2);
                    }
                    indexs2.setIndex2List(finallist);
                    return new JsonResult<>(ResultCode.SUCCESS,indexs2);
                }else {
                    List<Index> list=index1Service.getAll2Bylike(search);
                    List<Indexs2.Index2> finallist1=new ArrayList<>();
                    for(Index i:list){
                        Index indexa=index1Service.get1byid(i.getParent());
                        Indexs2.Index2 index2=new Indexs2.Index2();
                        index2.setName1(indexa.getName());
                        index2.setId1(indexa.getId());
                        index2.setNickname1(indexa.getNickname());
                        index2.setId(i.getId());
                        index2.setName(i.getName());
                        index2.setNickname(i.getNickname());
                        index2.setOther(i.getOther());
                        index2.setWeight(i.getWeight());
                        index2.setParent(i.getParent());
                        finallist1.add(index2);
                    }
                    indexs2.setIndex2List(finallist1);
                    return new JsonResult<>(ResultCode.SUCCESS,indexs2);
                }
            }

        }else{
            Integer i21=index1Service.getNum2();
            Indexs2 indexs2=new Indexs2();
            if(i21==null){
                indexs2.setTotal(0);
                indexs2.setIndex2List(null);
                return new JsonResult<>(ResultCode.SUCCESS,indexs2);
            }else{
                indexs2.setTotal(i21);
                if(start!=null&&limit!=null){
                    List<Index> list=index1Service.getPart2(start,limit);
                    List<Indexs2.Index2> finallist1=new ArrayList<>();
                    for(Index i:list){
                        Index indexa=index1Service.get1byid(i.getParent());
                        Indexs2.Index2 index2=new Indexs2.Index2();
                        index2.setName1(indexa.getName());
                        index2.setNickname1(indexa.getNickname());
                        index2.setId1(indexa.getId());
                        index2.setId(i.getId());
                        index2.setName(i.getName());
                        index2.setNickname(i.getNickname());
                        index2.setOther(i.getOther());
                        index2.setWeight(i.getWeight());
                        index2.setParent(i.getParent());
                        finallist1.add(index2);
                    }
                    indexs2.setIndex2List(finallist1);
                    return new JsonResult<>(ResultCode.SUCCESS,indexs2);
                }else {
                    List<Index> list=index1Service.get2All();
                    List<Indexs2.Index2> finallist1=new ArrayList<>();
                    for(Index i:list){
                        Index indexa=index1Service.get1byid(i.getParent());
                        Indexs2.Index2 index2=new Indexs2.Index2();
                        index2.setName1(indexa.getName());
                        index2.setNickname1(indexa.getNickname());
                        index2.setId(i.getId());
                        index2.setId1(indexa.getId());
                        index2.setName(i.getName());
                        index2.setNickname(i.getNickname());
                        index2.setOther(i.getOther());
                        index2.setWeight(i.getWeight());
                        index2.setParent(i.getParent());
                        finallist1.add(index2);
                    }
                    indexs2.setIndex2List(finallist1);
                    return new JsonResult<>(ResultCode.SUCCESS,indexs2);
                }
            }
        }
    }
    @GetMapping("delindex2")
    @Transactional
    public Object delIndex2(@RequestParam Integer id){
        index1Service.delete2byid(id);

        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @PostMapping("editindex2")
    @Transactional
    public Object editIndex2(@RequestBody Index index){
        if(index.getId()==null){
            Index index2=new Index();
            index2.setName(index.getName());
            index2.setNickname(index.getNickname());
            index2.setOther(index.getOther());
            index2.setWeight(index.getWeight());
            index2.setParent(index.getParent());
            index1Service.insert2(index2);
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }else{
            index1Service.update2(index.getName(),index.getNickname(),index.getWeight(),index.getOther(),index.getParent(),index.getId());
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }
    }
    @GetMapping("index1")
    public Object getIndex1(@RequestParam Integer start,Integer limit,String search){
        if(search.length()>0){
            Integer i10=index1Service.getNum1Bysearch(search);
            Indexs index10=new Indexs();
            if(i10==null) {
                index10.setTotal(0);
                index10.setIndexs(null);
                return new JsonResult<>(ResultCode.SUCCESS,index10);
            }else {
                index10.setTotal(i10);
                if(start!=null&&limit!=null){
                    index10.setIndexs(index1Service.getPart1Bylike(search,start,limit));
                    return new JsonResult<>(ResultCode.SUCCESS,index10);
                }else {
                    index10.setIndexs(index1Service.getAll1Bylike(search));
                    return new JsonResult<>(ResultCode.SUCCESS,index10);
                }
            }

        }else{
            Integer i11=index1Service.getNum1();
            Indexs index11=new Indexs();
            if(i11==null){
                index11.setTotal(0);
                index11.setIndexs(null);
                return new JsonResult<>(ResultCode.SUCCESS,index11);
            }else{
                index11.setTotal(i11);
                if(start!=null&&limit!=null){
                    index11.setIndexs(index1Service.getPart1(start,limit));
                    return new JsonResult<>(ResultCode.SUCCESS,index11);
                }else {
                    index11.setIndexs(index1Service.get1All());
                    return new JsonResult<>(ResultCode.SUCCESS,index11);
                }
            }
        }
    }
    @GetMapping("delindex1")
    @Transactional
    public Object delIndex1(@RequestParam Integer id){
        index1Service.delete1byid(id);

        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @PostMapping("editindex1")
    @Transactional
    public Object editIndex1(@RequestBody Index1 index1){
        if(index1.getId()==null){
            Index1 index11=new Index1();
            index11.setName(index1.getName());
            index11.setNickname(index1.getNickname());
            index11.setOther(index1.getOther());
            index11.setWeight(index1.getWeight());
            index1Service.insert1(index11);
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }else{
            index1Service.update1(index1.getName(),index1.getNickname(),index1.getWeight(),index1.getOther(),index1.getId());
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }
    }


    @GetMapping("users")
    public Object getUsers(@RequestParam Integer start,Integer limit,String search){
        if(search.length()>0){
            Integer u0=userService.getNumBysearch(search);
            Users users=new Users();
            if(u0==null) {
                users.setTotal(0);
                users.setUsers(null);
                return new JsonResult<>(ResultCode.SUCCESS,users);
            }else {
                users.setTotal(u0);
                if(start!=null&&limit!=null){
                    users.setUsers(userService.getPartBylike(search,start,limit));
                    return new JsonResult<>(ResultCode.SUCCESS,users);
                }else {
                    users.setUsers(userService.getAllBylike(search));
                    return new JsonResult<>(ResultCode.SUCCESS,users);
                }
            }

        }else{
            Integer u1=userService.getNum();
            Users users1=new Users();
            if(u1==null){
                users1.setTotal(0);
                users1.setUsers(null);
                return new JsonResult<>(ResultCode.SUCCESS,users1);
            }else{
                users1.setTotal(u1);
                if(start!=null&&limit!=null){
                    users1.setUsers(userService.getPart(start,limit));
                    return new JsonResult<>(ResultCode.SUCCESS,users1);
                }else {
                    users1.setUsers(userService.getAll());
                    return new JsonResult<>(ResultCode.SUCCESS,users1);
                }
            }
        }
    }
    @GetMapping("delusers")
    @Transactional
    public Object delUsers(@RequestParam Integer id){
        userService.deletebyid(id);
        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @PostMapping("editusers")
    @Transactional
    public Object editUsers(@RequestBody Recieveuser user){
        if(user.getId()==null){
            User user1=new User();
            user1.setName(user.getName());
            user1.setNickname(user.getNickname());
            user1.setRole(user.getRole());
            user1.setPassword(user.getPassword());
            userService.insert(user1);
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }else{
            if(user.getPassword()!=null) {
                userService.update1(user.getName(), user.getNickname(), user.getPassword(), user.getRole(), user.getId());
            }else{
                userService.updatepart(user.getName(),user.getNickname(),user.getRole(),user.getId());
            }
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }
    }
    @GetMapping("orgs")
    public Object getOrgs(@RequestParam Integer start,Integer limit,String search){
        if(search.length()>0){
            Integer o0=orgService.getNumBysearch(search);
            Orgs orgs=new Orgs();
            if(o0==null) {
                orgs.setTotal(0);
                orgs.setOrg1s(null);
                return new JsonResult<>(ResultCode.SUCCESS,orgs);
            }else {
                orgs.setTotal(o0);
                if(start!=null&&limit!=null){
                    List<Org> orgList=orgService.getPartBylike(search,start,limit);
                    List<Orgs.Org1> finalorg1s=new ArrayList<>();
                    for(Org org:orgList){
                        Orgs.Org1 org1=new Orgs.Org1();
                        org1.setId(org.getId());
                        org1.setName(org.getName());
                        org1.setNickname(org.getNickname());
                        org1.setType(org.getType());
                        org1.setCountries(ctooService.getAllcByoid(org.getId()));
                        finalorg1s.add(org1);
                    }
                    orgs.setOrg1s(finalorg1s);
                    return new JsonResult<>(ResultCode.SUCCESS,orgs);
                }else {
                    List<Org> orgList1=orgService.getAllBylike(search);
                    List<Orgs.Org1> finalorg1s1=new ArrayList<>();
                    for(Org org:orgList1){
                        Orgs.Org1 org1=new Orgs.Org1();
                        org1.setId(org.getId());
                        org1.setNickname(org.getNickname());
                        org1.setName(org.getName());
                        org1.setType(org.getType());
                        org1.setCountries(ctooService.getAllcByoid(org.getId()));
                        finalorg1s1.add(org1);
                    }
                    orgs.setOrg1s(finalorg1s1);
                    return new JsonResult<>(ResultCode.SUCCESS,orgs);
                }
            }

        }else{
            Integer o1=orgService.getNum();
            Orgs orgs1=new Orgs();
            if(o1==null){
                orgs1.setTotal(0);
                orgs1.setOrg1s(null);
                return new JsonResult<>(ResultCode.SUCCESS,orgs1);
            }else{
                orgs1.setTotal(o1);
                if(start!=null&&limit!=null){
                    List<Org> orgList1=orgService.getPart(start,limit);
                    List<Orgs.Org1> finalorg1s1=new ArrayList<>();
                    for(Org org:orgList1){
                        Orgs.Org1 org1=new Orgs.Org1();
                        org1.setNickname(org.getNickname());
                        org1.setId(org.getId());
                        org1.setName(org.getName());
                        org1.setType(org.getType());
                        org1.setCountries(ctooService.getAllcByoid(org.getId()));
                        finalorg1s1.add(org1);
                    }
                    orgs1.setOrg1s(finalorg1s1);
                    return new JsonResult<>(ResultCode.SUCCESS,orgs1);
                }else {
                    List<Org> orgList1=orgService.getAll();
                    List<Orgs.Org1> finalorg1s1=new ArrayList<>();
                    for(Org org:orgList1){
                        Orgs.Org1 org1=new Orgs.Org1();
                        org1.setId(org.getId());
                        org1.setNickname(org.getNickname());
                        org1.setName(org.getName());
                        org1.setCountries(ctooService.getAllcByoid(org.getId()));
                        org1.setType(org.getType());
                        finalorg1s1.add(org1);
                    }
                    orgs1.setOrg1s(finalorg1s1);
                    return new JsonResult<>(ResultCode.SUCCESS,orgs1);
                }
            }
        }
    }
    @GetMapping("delorgs")
    @Transactional
    public Object delOrgs(@RequestParam Integer id){
        orgService.deletebyid(id);
        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @PostMapping("editorgs")
    @Transactional
    public Object editOrgs(@RequestBody Orgs.Org1 org){
        if(org.getId()==null){
            Org org1=new Org();
            org1.setName(org.getName());
            org1.setNickname(org.getNickname());
            org1.setType(org.getType());
            int temp=orgService.insert(org1);
            List<String> countries=org.getCountries();
            for(String cou:countries){
                Countoorg countoorg=new Countoorg();
                countoorg.setCid(countryService.getidbyname(cou));
                countoorg.setOid(temp);
                ctooService.insert(countoorg);
            }
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }else{

            orgService.update(org.getName(),org.getNickname(),org.getType(),org.getId());
            ctooService.delcto(org.getId());
            List<String> stringList=org.getCountries();
            for(String list:stringList){
                Countoorg countoorg=new Countoorg();
                countoorg.setOid(org.getId());
                countoorg.setCid(countryService.getidbyname(list));
                ctooService.insert(countoorg);
            }
            return new JsonResult<>(ResultCode.SUCCESS,true);
        }
    }




}
