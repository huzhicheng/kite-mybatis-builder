package test.service;

import test.dao.entity.User;

import java.util.List;

/**
 * IUserService
 *
 * @author fengzheng
 * @date 2018/1/10
 */
public interface IUserService {

    /**
     * 插入一条
     * @param user
     * @return
     */
    User insert(User user);



    /**
     * 根据主键获取记录
     * @param id
     * @return
     */
    User getByPrimary(int id);

    /**
     * 根据条件获取记录
     * @return
     */
    User getByExample();


    /**
     * 获取 #{count} 条记录
     * @param count
     * @return
     */
    List<User> getMuiltUsers(int count);

}
