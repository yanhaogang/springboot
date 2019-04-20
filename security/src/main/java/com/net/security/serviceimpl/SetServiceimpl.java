package com.net.security.serviceimpl;

import com.net.security.mapper.SetMapper;
import com.net.security.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetServiceimpl implements SetService {
    @Autowired
    private SetMapper setMapper;
    @Override
    public int getsetid() {
        return setMapper.getsetid();
    }



}
