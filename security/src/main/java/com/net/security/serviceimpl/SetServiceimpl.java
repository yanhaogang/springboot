package com.net.security.serviceimpl;

import com.net.security.bean.Set;
import com.net.security.mapper.SetMapper;
import com.net.security.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SetServiceimpl implements SetService {
    @Autowired
    private SetMapper setMapper;
    @Override
    public Integer getsetid() {
        return setMapper.getsetid();
    }


    @Override
    public List<Set> getAllset() {
        return setMapper.getAllset();
    }

    @Override
    public void to1Byid(int id) {
        setMapper.to1Byid(id);
    }

    @Override
    public void updateByid(String arg0, Date arg1, int arg2) {
        setMapper.updateByid(arg0,arg1,arg2);
    }

    @Override
    public List<Set> getPartBylike(String arg0, int arg1, int arg2) {
        return setMapper.getPartBylike(arg0,arg1,arg2);
    }

    @Override
    public List<Set> getAllBylike(String _parameter) {
        return setMapper.getAllBylike(_parameter);
    }

    @Override
    public Integer getNumBysearch(String _parameter) {
        return setMapper.getNumBysearch(_parameter);
    }

    @Override
    public Integer getNum() {
        return setMapper.getNum();
    }

    @Override
    public List<Set> getPart(int arg0, int arg1) {
        return setMapper.getPart(arg0,arg1);
    }

    @Override
    public void insert(Set set) {
        setMapper.insert(set);
    }

    @Override
    public void deleteSetbyid(int id) {
        setMapper.deleteSetbyid(id);
    }

    @Override
    public void to0Byid(int id) {
        setMapper.to0Byid(id);
    }
}
