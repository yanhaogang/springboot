package com.net.security.service;

import com.net.security.bean.Archive;

import java.util.List;

public interface ArchiveService {
    void addcoun(Archive archive);
    void addorg(Archive archive);

    void deleteCounBysetid(int setid);
    void deleteOrgBysetid(int setid);
    List<Archive> getAll1Bysetid(int setid);
    List<Archive> getAll2Bysetid(int setid);
    List<Archive> getcByMainname(String mainname);
    Archive getBy3(int arg0,String arg1,int arg2);

    List<Archive> getcBy2(String arg0,int arg1);
    List<Archive> getoBy2(String arg0,int arg1);
}
