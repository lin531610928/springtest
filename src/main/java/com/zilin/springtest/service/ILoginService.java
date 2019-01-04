package com.zilin.springtest.service;

import com.zilin.springtest.entity.User;
import com.zilin.springtest.entity.UserInfo;

public interface ILoginService {
    UserInfo UserLogin(User user);
}
