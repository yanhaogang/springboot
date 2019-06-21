package com.net.security.serviceimpl;

import com.net.security.bean.Archive;
import com.net.security.mapper.ArchiveMapper;
import com.net.security.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void deleteCounBysetid(int setid) {
        archiveMapper.deleteCounBysetid(setid);
    }

    @Override
    public void deleteOrgBysetid(int setid) {
        archiveMapper.deleteOrgBysetid(setid);
    }

    @Override
    public List<Archive> getAll1Bysetid(int setid) {
        return archiveMapper.getAll1Bysetid(setid);
    }

    @Override
    public List<Archive> getAll2Bysetid(int setid) {
        return archiveMapper.getAll2Bysetid(setid);
    }

    @Override
    public List<Archive> getcBy2(String arg0, int arg1) {
        return archiveMapper.getcBy2(arg0,arg1);
    }

    @Override
    public List<Archive> getcByMainname(String mainname) {
        return archiveMapper.getcByMainname(mainname);
    }

    @Override
    public Archive getBy3(int arg0, String arg1, int arg2) {
        return archiveMapper.getBy3(arg0,arg1,arg2);
    }

    @Override
    public List<Archive> getoBy2(String arg0, int arg1) {
        return archiveMapper.getoBy2(arg0,arg1);
    }
}
