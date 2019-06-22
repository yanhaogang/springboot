package com.net.security.serviceimpl;

import com.net.security.bean.Reference;
import com.net.security.mapper.ReferenceMapper;
import com.net.security.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceServiceimpl implements ReferenceService {
    @Autowired
    private ReferenceMapper referenceMapper;
    @Override
    public List<Reference> getBycandi(String arg0, String arg1) {
        return referenceMapper.getBycandi(arg0,arg1);
    }

    @Override
    public void delByid(int id) {
        referenceMapper.delByid(id);
    }

    @Override
    public void insert(Reference reference) {
        referenceMapper.insert(reference);
    }
}
