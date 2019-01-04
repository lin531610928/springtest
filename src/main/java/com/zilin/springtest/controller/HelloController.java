package com.zilin.springtest.controller;

import com.zilin.springtest.entity.User;
import com.zilin.springtest.service.IRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloController {
    @Autowired
    private IRegService regService;

    @RequestMapping("/114514")
    private String hello(){
        User a = regService.readUser("123");
        return "良い世、來いよ<br>"+a.getUserName()+"<br>"+a.getUserPassword()+"<br>"+a.getNickName();
    }

    @RequestMapping("/111")
    private String yingyingying(){
        User[] a = regService.readAllUsers();
        String b = "";
        for (int i=0;i<a.length;i++){
            b = b + a[i].getUserName() + "  " + a[i].getUserPassword() + "  " + a[i].getNickName() + "<br>";
        }
        return b;
    }

    @RequestMapping("/test")
    private User test(){
        User[] t = regService.readAllUsers();
        return t[0];
    }
}
