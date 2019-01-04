package com.zilin.springtest;

import com.zilin.springtest.entity.User;
import com.zilin.springtest.entity.UserInfo;
import com.zilin.springtest.mapper.UserMapper;
import com.zilin.springtest.service.ILoginService;
import com.zilin.springtest.service.IRegService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringtestApplicationTests {

    @Autowired
    private IRegService regService;

    @Autowired
    private ILoginService loginService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        User[] a = regService.readAllUsers();
        String b;
        for (int i=0;i<a.length;i++){
            b = a[i].getUserName() + "  " + a[i].getUserPassword() + "  " + a[i].getNickName() + "<br>";
            System.out.println(b);
        }
    }

    @Test
    public void LoginTest() {
        User u = new User();
        u.setUserName("woyikoei");
        u.setUserPassword("6655");
        UserInfo a = loginService.UserLogin(u);
        System.out.println(a.getMsg());
    }

    @Test
    public void aa() {
        stringRedisTemplate.opsForList().rightPush("foo","123");
        stringRedisTemplate.opsForList().rightPush("foo","325");
        String a;
        while (stringRedisTemplate.opsForList().size("foo")!=0) {
            a = stringRedisTemplate.opsForList().leftPop("foo");
            System.out.println(a);
        }
    }

    @Test
    public void aab(){
        User[] u = userMapper.Search("woyi");
        System.out.println(u[0].getUserPassword());

    }
}
