package com.terwergreen.dao;

import com.terwergreen.pojo.User;

import java.util.List;

/**
 * 用户持久层
 *
 * @name: IUSerDao
 * @author: terwer
 * @date: 2022-03-14 19:38
 **/
public interface IUSerDao {
    /**
     *
     */
    public List<User> findAll() throws Exception;

    /**
     * 根据条件查询单个用户信息
     */
    public User findByCondition(User user) throws Exception;
}
