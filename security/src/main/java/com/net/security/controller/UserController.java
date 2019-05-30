package com.net.security.controller;

import com.net.security.bean.Reciveuser;
import com.net.security.bean.Returnuser;
import com.net.security.bean.User;
import com.net.security.service.UserService;
import com.net.security.utils.JsonResult;
import com.net.security.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("findall")
    public Object find(){
        return userService.findall();
    }
    @GetMapping("findbyid")
    public User find_by_id(Long id){
        return userService.findbyid(id);
    }

    @PostMapping("update")
    public void update(){
        userService.updatenameByid("评分员丁",1,4);
    }


    @PostMapping("login")
    public Object login(@RequestBody Reciveuser reciveuser, HttpServletResponse response){
        String name=reciveuser.getName();
        String password=reciveuser.getPassword();
        if(userService.getByname(name)==null){
            return new JsonResult<>(ResultCode.FALSE,"用户名不存在！");

        }else{
            if(userService.getBynp(name,password)==null){
                return new JsonResult<>(ResultCode.FALSE,"密码错误！");
            }else {
                int id=userService.getBynp(name,password).getId();
                Cookie cookie=new Cookie("user",String.valueOf(id));
                response.addCookie(cookie);
                cookie.setMaxAge(60*60*24);
                Returnuser returnuser=new Returnuser();
                User user=userService.getBynp(name,password);
                returnuser.setName(user.getName());
                returnuser.setNickname(user.getNickname());
                returnuser.setRole(user.getRole());
                return new JsonResult<>(ResultCode.SUCCESS,returnuser);
            }
        }
    }
}
