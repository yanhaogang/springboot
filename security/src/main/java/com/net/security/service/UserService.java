package com.net.security.service;

import com.net.security.bean.User;

public interface UserService {
public int add(User user);

public Object findall();

public User findbyid(Long id);

}
