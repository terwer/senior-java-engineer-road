package com.terewrgreen.rpc.consumer;

import com.terewrgreen.api.IUSerService;
import com.terewrgreen.pojo.User;
import com.terewrgreen.rpc.consumer.proxy.RpcClientProxy;

/**
 * 客户端启动类
 *
 * @name: ClientBootStrap
 * @author: terwer
 * @date: 2022-03-14 00:00
 **/
public class ClientBootStrap {
    public static void main(String[] args) {
        IUSerService userService = (IUSerService) RpcClientProxy.createProxy(IUSerService.class);
        User user = userService.getById(1);
        System.out.println(user);
    }
}
