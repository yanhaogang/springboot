package com.net.security.serviceimpl;

import com.net.security.bean.User;
import com.net.security.mapper.UserMapper;
import com.net.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    //加入事物隔离，删除和添加都需要
    @Transactional(propagation = Propagation.REQUIRED)
    public int add(User user) {
        userMapper.insert(user);
        int id=user.getId();
        return id;
    }

    @Override
    public Object findall() {
        return userMapper.getAll();
    }

    @Override
    public User findbyid(Long id) {
        return userMapper.findById(id);
    }


}
