package com.terwergreen.api;


import com.terwergreen.pojo.User;

/**
 * 用户服务
 *
 * @name: IUserService
 * @author: terwer
 * @date: 2022-03-09 23:14
 **/
public interface IUserService {
    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    User getById(int id);
}
