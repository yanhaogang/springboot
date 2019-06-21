package com.net.security.controller;

import com.net.security.bean.*;
import com.net.security.service.*;
import com.net.security.utils.FloatSolve;
import com.net.security.utils.JsonResult;
import com.net.security.utils.ResultCode;
import javafx.scene.shape.Arc;
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
    private ScoremanService scoremanService;
    @Autowired
    private Index1Service index1Service;
    @Autowired
    private SetService setService;
    @Autowired
    private ReferenceService referenceService;
    @Autowired
    private ScorefinalService scorefinalService;
    @Autowired
    private ArchiveService archiveService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private CtooService ctooService;



    private Finalscore finalscore;



    @RequestMapping("findall")
    public Object findall(){
        return scorefinalService.getAll();
    }
    @Transactional
    @PostMapping("submit")
    public JsonResult insertscore(@RequestBody Scoresadd scoresadd ,@CookieValue("userid") String id1){
        int setid=setService.getsetid();
        int counid=countryService.getidbyname(scoresadd.getCountry());
        HashMap<String,Float> hashMap= scoresadd.getScores();
        int userid=Integer.parseInt(id1);
        for(Map.Entry<String,Float> entry:hashMap.entrySet()){
            int id=index1Service.get3idbyname(entry.getKey());
            scorefinalService.updataScore(entry.getValue(),counid,id,setid,userid);
        }
        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @Transactional
    @PostMapping("undo")
    public JsonResult Deletefinal(@RequestBody Deindex deindex,@CookieValue("userid") String id1){
        int counid=countryService.getidbyname(deindex.getCountry());
        int userid=Integer.parseInt(id1);
        List<Integer> index2=index1Service.get2idbyparent(index1Service.get1idByname(deindex.getIndex()));
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
                if(scoreList!=null&&scoreList.size()!=0){
                    float temp = scoreList.get(0).getScore();
                    flag = 0;
                    for (Score ss : scoreList) {
                        if (temp != ss.getScore()) {
                            map.put(id3.getName(), 1);
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        map.put(id3.getName(), 0);
                    }

                }else {
                    map.put(id3.getName(),1);
                }
            }
            result.add(map);
        }
        return new JsonResult<>(ResultCode.SUCCESS,result);
    }
    @GetMapping("data")
    public Object getAlldata(@CookieValue("userid") String id) {
        Integer setid = setService.getsetid(), userid = Integer.parseInt(id);
        List<Index> index1 = index1Service.get1All();
        LinkedHashMap<Integer, List<Integer>> finalmap = new LinkedHashMap<>();
        for (Index index : index1) {
            List<Integer> temp = new ArrayList<>();
            List<Index> index2 = index1Service.get2Allbyparent(index.getId());
            for (Index ind : index2) {
                List<Index> index3=index1Service.get3Allbyparent(ind.getId());
                for(Index th:index3){
                    int a=1;
                    temp.add(th.getId());
                }

            }
            finalmap.put(index.getId(), temp);
        }
        List<Country> countries = countryService.getAll();
        List<LinkedHashMap<String, Object>> scorefinalmap = new ArrayList<>();
        for (Country ct : countries) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            for (Index i : index1) {
                //flag为1表示某一大指标没有评分
                int flag = 0;
                List<Integer> threeofone = finalmap.get(i.getId());
                if (threeofone.size()==0) {
                    map.put(i.getName(), "no");
                } else {
                    for (Integer n : threeofone) {
                        if(scorefinalService.getIsscorcedBy4id(n,ct.getId(),userid,setid)!=1){
                            flag=1;
                            break;
                        }
                    }
                }
                if (flag == 1) {
                    map.put(i.getName(), "no");
                } else {
                    map.put(i.getName(), "yes");
                }
            }
            map.put("country", ct.getName());
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
        int flag;
        int counid=countryService.getidbyname(country);
        int index1id=index1Service.get1idByname(index);
        List<Integer> index2=index1Service.get2idbyparent(index1id);
        List<Integer> final3id=new ArrayList<>();
        for(Integer i:index2){
            List<Integer> index3=index1Service.get3idbyparent(i);
            final3id.addAll(index3);
        }
        List<Detail> details=new ArrayList<>();
        for (Integer n : final3id) {
            flag = 0;
            StringBuffer linshi = new StringBuffer();
            Detail detail = new Detail();
            List<Score> list = scoremanService.getAllbycisid(counid, n, setService.getsetid());
            detail.setIndex(index1Service.get3nameByid(n));
            detail.setIndexname(index1Service.get3nicknameByid(n));
            if (list != null && list.size() != 0) {
                float temp = list.get(0).getScore();
                for (Score sc : list) {
                    if (temp != sc.getScore()) {
                        flag = 1;
                    }
                    linshi.append("评分员" + sc.getUserid() + ":" + sc.getScore() + ",");
                }
                detail.setDetail(linshi.toString());
                if (flag == 0) {
                    detail.setStatus("一致");
                    detail.setScore(temp);
                } else {
                    detail.setStatus("冲突");
                    detail.setScore(0);
                }
            } else{
                detail.setStatus("无评分");
                detail.setDetail("");
                detail.setScore(0);
            }
            details.add(detail);
        }

        return new JsonResult<>(ResultCode.SUCCESS,details);
    }
    /**
     *根据国家和三级指标返回对应评分依据
     */
    @PostMapping("reference")
    public Object getContent(@RequestParam String country,String index3){
        List<Country> countries=countryService.getAll();
        HashMap<String,String> counmap=new HashMap<>();
        for(Country c:countries){
            //不判断是否包含改国家中英文映射，节约时间,<String,String>=<"China","中国">
            counmap.put(c.getName(),c.getNickname());
        }
        List<Reference> references=referenceService.getBycandi(counmap.get(country),index3);
        if(references==null) return null;
        List<Classhelp> classhelps=new ArrayList<>();
        for (Reference reference:references){
            Classhelp classhelp=new Classhelp();
            classhelp.setId(reference.getId());
            classhelp.setEn(reference.getContent());
            classhelp.setCn(reference.getContentcn());
            classhelps.add(classhelp);
        }
        return new JsonResult<>(ResultCode.SUCCESS,classhelps);
    }
    @PostMapping("archive")
    @Transactional
    public Object dataprocess(@CookieValue("userid") String id){
        List<Country> countrys;
        int counid;
        int setid=setService.getsetid();
        archiveService.deleteCounBysetid(setid);
        archiveService.deleteOrgBysetid(setid);
        HashSet<String> cnentset=new HashSet<>();
        List<Score> scores;
        List<Finalscore> finalscores=new ArrayList<>();
        HashMap<String,Integer> cnameToid=new HashMap<>();
        float score=0;
        HashMap<String,Integer> indexToid=new HashMap<>();
        HashMap<String,Integer> orgToid=new HashMap<>();
        List<Index> index1s=index1Service.get1All();
        for(Index ind:index1s){
            indexToid.put(ind.getName(),ind.getId());
        }
        countrys=countryService.getAll();
        for(Country ct:countrys){
            cnameToid.put(ct.getName(),ct.getId());
            cnentset.add(ct.getContinent());
            //二级指标对应的分数
            LinkedHashMap<Integer,Float> hashMap2=new LinkedHashMap<>();
            //一级指标对应的分数
            LinkedHashMap<Integer,Float> hashMap1=new LinkedHashMap<>();
            finalscore=new Finalscore();
            finalscore.setCountry(ct.getName());
            finalscore.setContinent(ct.getContinent());
            counid=ct.getId();
            scores=scorefinalService.getAllbycounid(counid,Integer.parseInt(id),setid);
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
        for(Finalscore fs:finalscores){
            Archive archive0=new Archive();
            Archive archive1=new Archive();
            Archive archive2=new Archive();
            Archive archive3=new Archive();
            Archive archive4=new Archive();
            Archive archive5=new Archive();
            archive0.setMainid(cnameToid.get(fs.getCountry()));
            archive0.setIndex1id(-1);
            archive0.setIndex1name("总分");
            archive0.setScore(fs.getScore());
            archive0.setSetid(setid);
            archive0.setMainname(fs.getCountry());
            archive1.setMainid(cnameToid.get(fs.getCountry()));
            archive1.setIndex1id(indexToid.get("strategy"));
            archive1.setIndex1name("strategy");
            archive1.setScore(fs.getStrategy());
            archive1.setSetid(setid);
            archive1.setMainname(fs.getCountry());

            archive2.setMainid(cnameToid.get(fs.getCountry()));
            archive2.setIndex1id(indexToid.get("technical"));
            archive2.setIndex1name("technical");
            archive2.setScore(fs.getTechnical());
            archive2.setSetid(setid);
            archive2.setMainname(fs.getCountry());

            archive3.setMainid(cnameToid.get(fs.getCountry()));
            archive3.setIndex1id(indexToid.get("industry"));
            archive3.setIndex1name("industry");
            archive3.setScore(fs.getIndustry());
            archive3.setSetid(setid);
            archive3.setMainname(fs.getCountry());

            archive4.setMainid(cnameToid.get(fs.getCountry()));
            archive4.setIndex1id(indexToid.get("capacity"));
            archive4.setIndex1name("capacity");
            archive4.setScore(fs.getCapacity());
            archive4.setSetid(setid);
            archive4.setMainname(fs.getCountry());

            archive5.setMainid(cnameToid.get(fs.getCountry()));
            archive5.setIndex1id(indexToid.get("resources"));
            archive5.setIndex1name("resources");
            archive5.setScore(fs.getResources());
            archive5.setSetid(setid);
            archive5.setMainname(fs.getCountry());

            archiveService.addcoun(archive0);
            archiveService.addcoun(archive1);
            archiveService.addcoun(archive2);
            archiveService.addcoun(archive3);
            archiveService.addcoun(archive4);
            archiveService.addcoun(archive5);

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
            orgToid.put(org.getName(),org.getId());
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
        for(Finalscore forg:totallists){
            if(cnentset.contains(forg.getCountry())){
                Archive archive0=new Archive();
                Archive archive1=new Archive();
                Archive archive2=new Archive();
                Archive archive3=new Archive();
                Archive archive4=new Archive();
                Archive archive5=new Archive();

                archive0.setMainid(-1);
                archive0.setMainname(forg.getCountry());
                archive0.setIndex1id(-1);
                archive0.setIndex1name("总分");
                archive0.setScore(forg.getScore());
                archive0.setSetid(setid);

                archive1.setMainid(-1);
                archive1.setMainname(forg.getCountry());
                archive1.setIndex1id(indexToid.get("strategy"));
                archive1.setIndex1name("strategy");
                archive1.setScore(forg.getStrategy());
                archive1.setSetid(setid);

                archive2.setMainid(-1);
                archive2.setMainname(forg.getCountry());
                archive2.setIndex1id(indexToid.get("technical"));
                archive2.setIndex1name("techincal");
                archive2.setScore(forg.getTechnical());
                archive2.setSetid(setid);

                archive3.setMainid(-1);
                archive3.setMainname(forg.getCountry());
                archive3.setIndex1id(indexToid.get("industry"));
                archive3.setIndex1name("industry");
                archive3.setScore(forg.getIndustry());
                archive3.setSetid(setid);

                archive4.setMainid(-1);
                archive4.setMainname(forg.getCountry());
                archive4.setIndex1id(indexToid.get("capacity"));
                archive4.setIndex1name("capacity");
                archive4.setScore(forg.getCapacity());
                archive4.setSetid(setid);

                archive5.setMainid(-1);
                archive5.setMainname(forg.getCountry());
                archive5.setIndex1id(indexToid.get("resources"));
                archive5.setIndex1name("resources");
                archive5.setScore(forg.getResources());
                archive5.setSetid(setid);
                archiveService.addorg(archive0);
                archiveService.addorg(archive1);
                archiveService.addorg(archive2);
                archiveService.addorg(archive3);
                archiveService.addorg(archive4);
                archiveService.addorg(archive5);
            }else {
                Archive archive0=new Archive();
                Archive archive1=new Archive();
                Archive archive2=new Archive();
                Archive archive3=new Archive();
                Archive archive4=new Archive();
                Archive archive5=new Archive();

                archive0.setMainid(orgToid.get(forg.getCountry()));
                archive0.setMainname(forg.getCountry());
                archive0.setIndex1id(-1);
                archive0.setIndex1name("总分");
                archive0.setScore(forg.getScore());
                archive0.setSetid(setid);

                archive1.setMainid(orgToid.get(forg.getCountry()));
                archive1.setMainname(forg.getCountry());
                archive1.setIndex1id(indexToid.get("strategy"));
                archive1.setIndex1name("strategy");
                archive1.setScore(forg.getStrategy());
                archive1.setSetid(setid);

                archive2.setMainid(orgToid.get(forg.getCountry()));
                archive2.setMainname(forg.getCountry());
                archive2.setIndex1id(indexToid.get("technical"));
                archive2.setIndex1name("techincal");
                archive2.setScore(forg.getTechnical());
                archive2.setSetid(setid);

                archive3.setMainid(orgToid.get(forg.getCountry()));
                archive3.setMainname(forg.getCountry());
                archive3.setIndex1id(indexToid.get("industry"));
                archive3.setIndex1name("industry");
                archive3.setScore(forg.getIndustry());
                archive3.setSetid(setid);

                archive4.setMainid(orgToid.get(forg.getCountry()));
                archive4.setMainname(forg.getCountry());
                archive4.setIndex1id(indexToid.get("capacity"));
                archive4.setIndex1name("capacity");
                archive4.setScore(forg.getCapacity());
                archive4.setSetid(setid);

                archive5.setMainid(orgToid.get(forg.getCountry()));
                archive5.setMainname(forg.getCountry());
                archive5.setIndex1id(indexToid.get("resources"));
                archive5.setIndex1name("resources");
                archive5.setScore(forg.getResources());
                archive5.setSetid(setid);

                archiveService.addorg(archive0);
                archiveService.addorg(archive1);
                archiveService.addorg(archive2);
                archiveService.addorg(archive3);
                archiveService.addorg(archive4);
                archiveService.addorg(archive5);

            }
        }
        return new JsonResult<>(ResultCode.SUCCESS,true);
    }
    @PostMapping("init")
    @Transactional
    public Object initfinal(@CookieValue("userid") String id){
        int userid=Integer.parseInt(id);
        int setid=setService.getsetid();
        scorefinalService.deleteBy2id(userid,setid);
        List<Index> index3s=index1Service.get3All();
        List<Country> countries=countryService.getAll();
        for(Country country:countries){
            for(Index index:index3s){
                List<Score> scoreList=scoremanService.getAllbycisid(country.getId(),index.getId(),setid);
                if(scoreList.size()==0){
                    Score score1=new Score();
                    score1.setSetid(setid);
                    score1.setScore(0);
                    score1.setIsscored(0);
                    score1.setIndex3id(index.getId());
                    score1.setUserid(userid);
                    score1.setCounid(country.getId());
                    scorefinalService.InsertScore(score1);
                }else {
                    float temp=scoreList.get(0).getScore();
                    //flag置1表示有冲突
                    int flag=0;
                    for(Score ss:scoreList){
                        if(temp==ss.getScore()) continue;
                        flag=1;
                    }
                    Score score2=new Score();
                    score2.setSetid(setid);
                    score2.setIndex3id(index.getId());
                    score2.setUserid(userid);
                    score2.setCounid(country.getId());
                    if(flag==1){
                        score2.setScore(0);
                        score2.setIsscored(0);
                    }else{
                        score2.setIsscored(1);
                        score2.setScore(temp);
                    }
                    scorefinalService.InsertScore(score2);

                }
            }
        }
        return new JsonResult<>(ResultCode.SUCCESS,true);
    }

}
