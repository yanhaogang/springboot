package com.net.security.serviceimpl;

import com.net.security.bean.Countoorg;
import com.net.security.mapper.CtooMapper;
import com.net.security.service.CtooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CtooServiceimpl implements CtooService {
    @Autowired
    private CtooMapper ctooMapper;
    @Override
    public List<String> getAllcByoid(int oid) {
        return ctooMapper.getAllcByoid(oid);
    }

    @Override
    public void insert(Countoorg countoorg) {
        ctooMapper.insert(countoorg);
    }

    @Override
    public void delcto(Integer oid) {
        ctooMapper.delcto(oid);
    }
}
