package com.terwergreen.rmi.client;

import com.terwergreen.rmi.pojo.User;
import com.terwergreen.rmi.service.IUserService;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI客户端
 *
 * @name: RMIClient
 * @author: terwer
 * @date: 2022-03-06 19:25
 **/
public class RMIClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        // 1、获取Registry实例
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9998);
        // 2、通过Registry查找远程对象
        IUserService userService = (IUserService) registry.lookup("userService");
        User user = userService.getUserById(1);
        System.out.println("userName = " + user.getName());
    }
}
