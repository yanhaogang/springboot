package com.net.security.serviceimpl;

import com.net.security.bean.Set;
import com.net.security.mapper.SetMapper;
import com.net.security.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
