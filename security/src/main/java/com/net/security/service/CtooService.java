package com.net.security.service;

import com.net.security.bean.Countoorg;
import com.net.security.bean.Org;

import java.util.List;

public interface CtooService {
    List<String> getAllcByoid(int oid);
    void insert(Countoorg countoorg);
    void delcto(Integer oid);
}
