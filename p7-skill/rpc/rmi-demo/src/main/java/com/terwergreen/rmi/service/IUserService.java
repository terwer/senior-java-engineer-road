package com.terwergreen.rmi.service;

import com.terwergreen.rmi.pojo.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 用户信息接口
 *
 * @name: IUserService
 * @author: terwer
 * @date: 2022-03-06 01:55
 **/
public interface IUserService extends Remote {
    User getUserById(int id) throws RemoteException;
}
