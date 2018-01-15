package org.kite.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kite.mybatis.builder.BuilderApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import test.dao.entity.User;
import test.dao.entity.UserExample;
import test.dao.mapper.UserMapper;

import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


/**
 * MybatisTest1
 *
 * @author fengzheng
 * @date 2018/1/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BuilderApplication.class,UserMapper.class})
@ActiveProfiles(value = "test")
public class MybatisTest1 {

    private final Random random = new Random();

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = MyBatisTest.buildUser(random.nextInt());
        int result = userMapper.insert(user);
        assertThat(result,greaterThan(0));
    }

    @Test
    public void selectWithLimit(){
        UserExample userExample = new UserExample();
        userExample.setLimit(5);
        userExample.setOffset(5);
        userExample.setOrderByClause(" id desc ");
        List<User> users = userMapper.selectByExample(userExample);
        for(User user:users){
            System.out.println("" + user.getId() +"|||" + user.getName());
        }
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        assertThat(users,not(empty()));
    }



}
