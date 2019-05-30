package com.net.security.serviceimpl;

import com.net.security.bean.User;
import com.net.security.mapper.UserMapper;
import com.net.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void updatenameByid(String arg0, int arg1,int arg2) {
        userMapper.updatenameByid(arg0,arg1,arg2);
    }

    @Override
    //加入事物隔离，删除和添加都需要
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updatepart(String arg0, String arg1, Integer arg2, Integer arg3) {
        userMapper.updatepart(arg0,arg1,arg2,arg3);
    }

    @Override
    public Object findall() {
        return userMapper.getAll();
    }

    @Override
    public User findbyid(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> getPartBylike(String arg0, int arg1, int arg2) {
        return userMapper.getPartBylike(arg0,arg1,arg2);
    }

    @Override
    public Integer getNumBysearch(String _parameter) {
        return userMapper.getNumBysearch(_parameter);
    }

    @Override
    public Integer getNum() {
        return userMapper.getNum();
    }

    @Override
    public List<User> getPart(int arg0, int arg1) {
        return userMapper.getPart(arg0,arg1);
    }

    @Override
    public List<User> getAllBylike(String name) {
        return userMapper.getAllBylike(name);
    }

    @Override
    public void deletebyid(int id) {
        userMapper.deletebyid(id);
    }

    @Override
    public void update1(String arg0, String arg1, String arg2, Integer arg3,Integer arg4) {
        userMapper.update1(arg0,arg1,arg2,arg3,arg4);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public List<User> getByname(String name) {
        return userMapper.getByname(name);
    }

    @Override
    public User getBynp(String arg0, String arg1) {
        return userMapper.getBynp(arg0,arg1);
    }
}
