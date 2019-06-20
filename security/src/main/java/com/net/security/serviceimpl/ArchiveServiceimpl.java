package com.net.security.serviceimpl;

import com.net.security.bean.Archive;
import com.net.security.mapper.ArchiveMapper;
import com.net.security.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchiveServiceimpl implements ArchiveService {
    @Autowired
    private ArchiveMapper archiveMapper;
    @Override
    public void addcoun(Archive archive) {
        archiveMapper.addcoun(archive);
    }

    @Override
    public void addorg(Archive archive) {
        archiveMapper.addorg(archive);
    }
}
