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
}
