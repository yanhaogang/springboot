package com.net.security.controller;

import com.net.security.service.CountryService;
import com.net.security.utils.JsonResult;
import com.net.security.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/admin")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminController {
    @Autowired
    private CountryService service;

    @GetMapping("country")
    @Transactional
    public Object deletecoun(@RequestParam(value = "id",required = false,defaultValue = "0") int id){
        service.deletebyid(id);
        return new  JsonResult(ResultCode.SUCCESS,true);
    }


}
