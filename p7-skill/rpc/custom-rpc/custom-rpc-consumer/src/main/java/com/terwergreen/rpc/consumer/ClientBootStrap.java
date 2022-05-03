package com.terwergreen.rpc.consumer;

import com.terwergreen.api.IUserService;
import com.terwergreen.pojo.User;
import com.terwergreen.rpc.consumer.proxy.RpcClientProxy;

/**
 * 客户端启动类
 *
 * @name: ClientBootStrap
 * @author: terwer
 * @date: 2022-03-14 00:00
 **/
public class ClientBootStrap {
    public static void main(String[] args) {
        IUserService userService = (IUserService) RpcClientProxy.createProxy(IUserService.class);
        User user = userService.getById(1);
        System.out.println(user);
    }
}
