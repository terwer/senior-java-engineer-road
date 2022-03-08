package com.terwergreen.rmi.service;

import com.terwergreen.rmi.pojo.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * @name: 用户信息实现类
 * @author: terwer
 * @date: 2022-03-06 01:57
 **/
public class UserServiceImpl extends UnicastRemoteObject implements IUserService {
    Map<Object, User> userMap = new HashMap();

    public UserServiceImpl() throws RemoteException {
        super();

        User user1 = new User();
        user1.setId(1);
        user1.setName("张三");
        userMap.put(user1.getId(), user1);

        User user2 = new User();
        user2.setId(2);
        user2.setName("李四");
        userMap.put(user2.getId(), user2);
    }

    @Override
    public User getUserById(int id) throws RemoteException {
        return userMap.get(id);
    }
}
