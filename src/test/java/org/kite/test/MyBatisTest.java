package org.kite.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kite.mybatis.builder.BuilderApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import test.dao.entity.User;
import test.service.IUserService;
import test.service.impl.UserServiceImpl;


import java.sql.Timestamp;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

/**
 * MyBatisTest
 *
 * @author fengzheng
 * @date 2018/1/10
 */
// version 1.3
//@SpringBootTest(classes = {UserServiceImpl.class})
//@SpringApplicationConfiguration(classes = BuilderApplication.class)

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {BuilderApplication.class,UserServiceImpl.class})
@ActiveProfiles(value = "test")
public class MyBatisTest {


    @Autowired
    private IUserService userService;

    private final Random random = new Random();

    @Test
    public void insert(){
        User user = buildUser(random.nextInt());
        userService.insert(user);
    }


    public static User buildUser(int index){
        User user = new User();
        user.setAge(12);
        user.setName("myname" + index);
        user.setBirthday(new Timestamp(currentTimeMillis()));
        user.setSex(Boolean.FALSE);
        user.setDecimalType(1000000L);
        user.setFloatType(10000000F);
        user.setWeight(8989898D);
        return user;
    }
}
