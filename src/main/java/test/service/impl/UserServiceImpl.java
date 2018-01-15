package test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dao.entity.User;
import test.dao.mapper.UserMapper;
import test.service.IUserService;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author fengzheng
 * @date 2018/1/10
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入一条
     * @param user
     * @return
     */
    @Override
    public User insert(User user){
        userMapper.insert(user);
        return user;
    }



    /**
     * 根据主键获取记录
     * @param id
     * @return
     */
    @Override
    public User getByPrimary(int id){
        return null;
    }

    /**
     * 根据条件获取记录
     * @return
     */
    @Override
    public User getByExample(){
        return null;
    }


    /**
     * 获取 #{count} 条记录
     * @param count
     * @return
     */
    @Override
    public List<User> getMuiltUsers(int count){
        return null;
    }
}
