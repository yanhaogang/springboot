package com.net.security.serviceimpl;

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
}
