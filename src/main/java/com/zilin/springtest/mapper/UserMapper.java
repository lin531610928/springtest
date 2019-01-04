package com.zilin.springtest.mapper;

import com.zilin.springtest.entity.User;
import com.zilin.springtest.mysql.AdvancedSearch;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

public interface UserMapper {
    @Select("select * from springusers where userId = #{userId}")
    User findUserByUserid(@Param("userId") String userId);
    @Select("select * from springusers")
    User[] findAllUsers();
    @Select("select * from springusers where userName = #{userName} and userPassword = #{userPassword}")
    User findUserByLogin(@Param("userName") String userName, @Param("userPassword") String userPassword);
    @SelectProvider(method = "select",type = AdvancedSearch.class)
    User[] Search(String object);
}
