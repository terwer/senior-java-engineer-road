package com.terwergreen.dao;

import com.terwergreen.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * 用户Dao层接口
 *
 * @name: IUserDao
 * @author: terwer
 * @date: 2022-03-16 20:53
 **/
public interface IUserDao {
    /**
     * 查询所有用户
     */
    public List<User> findAll() throws IOException;

    /**
     * 多条件组合查询用户：if案例
     */
    public List<User> findByCondition(User user);

    /**
     * 多条件组合查询用户：where案例
     */
    public List<User> findByConditionWhere(User user);

    /**
     * 多值查询：foerach案例
     */
    public List<User> findByIds(Integer[] ids);

    /**
     * 根据ID查询：sql抽取案例
     */
    public User findById(Integer id);
}
