package com.terwergreen.rmi.server;

import com.terwergreen.rmi.service.IUserService;
import com.terwergreen.rmi.service.UserServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI服务端
 *
 * @name: RMIServer
 * @author: terwer
 * @date: 2022-03-06 02:01
 **/
public class RMIServer {
    public static void main(String[] args) {
        try {
            // 1.注册Registry实例，绑定端口
            Registry registry = LocateRegistry.createRegistry(9998);
            // 2.创建远程对象
            IUserService userService = new UserServiceImpl();
            // 3.将远程对象注册到RMI服务器（既服务端注册表）
            registry.rebind("userService", userService);

            System.out.println("RMI服务端启动成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
