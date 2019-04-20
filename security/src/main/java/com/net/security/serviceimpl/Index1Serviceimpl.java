package com.net.security.serviceimpl;

import com.net.security.bean.Index;
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
    public Index get2byid(int id) {
        return index1Mapper.get2byid(id);
    }

    @Override
    public String get1namebyid(int id) {
        return index1Mapper.get1namebyid(id);
    }

    @Override
    public Index get1byid(int id) {
        return index1Mapper.get1byid(id);
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
    public Index get3byid(int id) {
        return index1Mapper.get3byid(id);
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
    public Integer get2parentbyname(String name) {
        return index1Mapper.get2parentbyname(name);
    }

    @Override
    public List<Integer> get3idbyparent(int parent) {
        return index1Mapper.get3idbyparent(parent);
    }
}
