package com.zilin.springtest.controller;

import com.zilin.springtest.entity.User;
import com.zilin.springtest.entity.UserInfo;
import com.zilin.springtest.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @RequestMapping("/login")
    private UserInfo Login(@RequestBody @Valid User user){
        UserInfo record = loginService.UserLogin(user);
        return record;
    }
}
