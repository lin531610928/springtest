package com.zilin.springtest.service;

import com.zilin.springtest.entity.User;

public interface IRegService {
    User readUser(String userId);
    User[] readAllUsers();
}
