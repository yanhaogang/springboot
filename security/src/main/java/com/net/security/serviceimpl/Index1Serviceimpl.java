package com.net.security.serviceimpl;

import com.net.security.bean.Index;
import com.net.security.bean.Index1;
import com.net.security.mapper.Index1Mapper;
import com.net.security.service.Index1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Index1Serviceimpl implements Index1Service {
    @Autowired
    private Index1Mapper index1Mapper;
    @Override
    public String get3nameByid(int id) {
        return index1Mapper.get3namebyid(id);
    }
    @Override
    public Index get1byid(int id) {
        return index1Mapper.get1byid(id);
    }

    @Override
    public Index get2byid(int id) {

        return index1Mapper.get2byid(id);


    }

    @Override
    public String get3nicknameByid(int id) {
        return index1Mapper.get3nicknameByid(id);
    }

    @Override
    public List<Index> getPart3Bylike(String arg0, int arg1, int arg2) {
        return index1Mapper.getPart3Bylike(arg0,arg1,arg2);
    }

    @Override
    public Integer getNum3Bysearch(String _parameter) {
        return index1Mapper.getNum3Bysearch(_parameter);
    }

    @Override
    public Integer getNum3() {
        return index1Mapper.getNum3();
    }

    @Override
    public List<Index> getPart3(int arg0, int arg1) {
        return index1Mapper.getPart3(arg0,arg1);
    }

    @Override
    public List<Index> getAll3Bylike(String name) {
        return index1Mapper.getAll3Bylike(name);
    }

    @Override
    public void delete3byid(int id) {
        index1Mapper.delete3byid(id);
    }

    @Override
    public Index get3byid(int id) {

        return index1Mapper.get3byid(id);
    }

    @Override
    public String get1namebyid(int id) {
        return index1Mapper.get1namebyid(id);
    }

    @Override
    public Integer get1idByname(String name) {
        return index1Mapper.get1idByname(name);
    }



    @Override
    public List<String> get2namebyparent(int parent) {
        return index1Mapper.get2namebyparent(parent);
    }

    @Override
    public List<String> get3nameByparent(int parent) {
        return index1Mapper.get3namebyparent(parent);
    }

    @Override
    public List<Index> get1All() {
        return index1Mapper.get1All();
    }

    @Override
    public List<Index> get2All() {
        return index1Mapper.get2All();
    }

    @Override
    public List<Index> get3All() {
        return index1Mapper.get3All();
    }



    @Override
    public List<Index> get3Allbyparent(int parent) {
        return index1Mapper.get3Allbyparent(parent);
    }

    @Override
    public List<Index> get2Allbyparent(int parent) {
        return index1Mapper.get2Allbyparent(parent);
    }

    @Override
    public List<Integer> get2idbyparent(int parent) {
        return index1Mapper.get2idbyparent(parent);
    }

    @Override
    public Integer get3idbyname(String name) {
        return index1Mapper.get3idbyname(name);
    }

    @Override
    public void insert3(Index index) {
        index1Mapper.insert3(index);
    }

    @Override
    public List<Index> getPart2Bylike(String arg0, int arg1, int arg2) {
        return index1Mapper.getPart2Bylike(arg0,arg1,arg2);
    }

    @Override
    public Integer getNum2Bysearch(String _parameter) {
        return index1Mapper.getNum2Bysearch(_parameter);
    }

    @Override
    public Integer getNum2() {
        return index1Mapper.getNum2();
    }

    @Override
    public List<Index> getPart2(int arg0, int arg1) {
        return index1Mapper.getPart2(arg0,arg1);
    }

    @Override
    public List<Index> getAll2Bylike(String name) {
        return index1Mapper.getAll2Bylike(name);
    }

    @Override
    public void delete2byid(int id) {
        index1Mapper.delete2byid(id);
    }

    @Override
    public void insert2(Index index) {
        index1Mapper.insert2(index);
    }

    @Override
    public void update2(String arg0, String arg1, Float arg2, String arg3, Integer arg4,Integer arg5) {
        index1Mapper.update2(arg0,arg1,arg2,arg3,arg4,arg5);
    }

    @Override
    public List<Index> getPart1Bylike(String arg0, int arg1, int arg2) {
        return index1Mapper.getPart1Bylike(arg0,arg1,arg2);
    }

    @Override
    public Integer getNum1Bysearch(String _parameter) {
        return index1Mapper.getNum1Bysearch(_parameter);
    }

    @Override
    public Integer getNum1() {
        return index1Mapper.getNum1();
    }

    @Override
    public List<Index> getPart1(int arg0, int arg1) {
        return index1Mapper.getPart1(arg0,arg1);
    }

    @Override
    public List<Index> getAll1Bylike(String name) {
        return index1Mapper.getAll1Bylike(name);
    }

    @Override
    public void delete1byid(int id) {
        index1Mapper.delete1byid(id);
    }

    @Override
    public void insert1(Index1 index1) {
        index1Mapper.insert1(index1);
    }

    @Override
    public void update1(String arg0, String arg1, Float arg2, String arg3, Integer arg4) {
        index1Mapper.update1(arg0,arg1,arg2,arg3,arg4);
    }

    @Override
    public void update3(String arg0, String arg1, Float arg2, String arg3, Integer arg4,Integer arg5) {
        index1Mapper.update3(arg0,arg1,arg2,arg3,arg4,arg5);
    }

    @Override
    public Integer get2parentbyname(String name) {
        return index1Mapper.get2parentbyname(name);
    }

    @Override
    public List<Integer> get3idbyparent(int parent) {
        return index1Mapper.get3idbyparent(parent);
    }
}
