package com.zilin.springtest.service;

import com.zilin.springtest.entity.User;
import com.zilin.springtest.entity.UserInfo;
import com.zilin.springtest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class LoginService implements ILoginService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo UserLogin(User user) {
        UserInfo result = new UserInfo();
        if (user.getUserName() == null || user.getUserName() == "") {
            result.setResult("0");
            result.setMsg("UserName can't be empty");
            return result;
        }
        if (user.getUserPassword() == null || user.getUserPassword() == "") {
            result.setResult("0");
            result.setMsg("Password can't be empty");
            return result;
        }
        User record = userMapper.findUserByLogin(user.getUserName(),user.getUserPassword());
        if (record == null) {
            result.setResult("0");
            result.setMsg("UserName or password is failed");
            return result;
        }
        result.setResult("1");
        result.setMsg("OK");
        result.setUser(record);
        return result;
    }
}
