package com.net.security.controller;

import com.net.security.bean.User;
import com.net.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("add")
    public Object add(){
        User user=new User();
        user.setName("sad");
        user.setNickname("aaa");
        user.setPassword("yhyhyhy");
        user.setRole(12);
        int id=userService.add(user);
        return id;
    }

    @GetMapping("findall")
    public Object find(){
        return userService.findall();
    }
    @GetMapping("findbyid")
    public User find_by_id(Long id){
        return userService.findbyid(id);
    }
}
