package com.terewrgreen.api;

import com.terewrgreen.pojo.User;

/**
 * 用户服务
 *
 * @name: IUSerService
 * @author: terwer
 * @date: 2022-03-09 23:14
 **/
public interface IUSerService {
    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    User getById(int id);
}
