package com.zilin.springtest.controller;

import com.zilin.springtest.service.IPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class PrintController {

    @Autowired
    private IPrintService printService;

    /*@RequestMapping("/test")
    public void test1(){
        printService.ServerSocketDemo();
    }*/
}
