package com.terwergreen.mapper;

import com.terwergreen.pojo.User;

import java.util.List;

/**
 * 用户映射
 *
 * @name: UserMapper
 * @author: terwer
 * @date: 2022-03-28 00:03
 **/
public interface UserMapper {
    // 查询所有用户信息以及用户关联的订单信息
    public List<User> findAll();
}
