package com.zilin.springtest.service;

import com.zilin.springtest.entity.User;
import com.zilin.springtest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class RegService implements IRegService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User readUser(String userId){
        User record = userMapper.findUserByUserid(userId);
        return record;
    }

    @Override
    public User[] readAllUsers(){
        User[] record = userMapper.findAllUsers();
        return record;
    }
}
