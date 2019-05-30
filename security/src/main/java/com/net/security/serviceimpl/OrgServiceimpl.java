package com.net.security.serviceimpl;

import com.net.security.bean.Org;
import com.net.security.mapper.OrgMapper;
import com.net.security.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgServiceimpl implements OrgService {
    @Autowired
    private OrgMapper orgMapper;

    @Override
    public List<String> getallorg() {
        return orgMapper.getallorg();
    }

    @Override
    public List<Org> getPartBylike(String arg0, int arg1, int arg2) {
        return orgMapper.getPartBylike(arg0,arg1,arg2);
    }

    @Override
    public Integer getNumBysearch(String _parameter) {
        return orgMapper.getNumBysearch(_parameter);
    }

    @Override
    public Integer getNum() {
        return orgMapper.getNum();
    }

    @Override
    public List<Org> getPart(int arg0, int arg1) {
        return orgMapper.getPart(arg0,arg1);
    }

    @Override
    public List<Org> getAllBylike(String name) {
        return orgMapper.getAllBylike(name);
    }

    @Override
    public void deletebyid(int id) {
        orgMapper.deletebyid(id);
    }

    @Override
    public int insert(Org org) {
        orgMapper.insert(org);
        return org.getId();
    }

    @Override
    public void update(String arg0, String arg1, String arg2, Integer arg3) {
        orgMapper.update(arg0,arg1,arg2,arg3);
    }

    @Override
    public List<Org> getAll() {
        return orgMapper.getAll();
    }
}
