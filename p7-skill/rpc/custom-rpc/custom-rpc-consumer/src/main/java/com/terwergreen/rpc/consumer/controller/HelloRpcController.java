package com.terwergreen.rpc.consumer.controller;

import com.terwergreen.api.IUserService;
import com.terwergreen.pojo.User;
import com.terwergreen.rpc.consumer.proxy.RpcClientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 客户端控制器
 *
 * @name: HelloRpcController
 * @author: terwer
 * @date: 2022-05-04 01:31
 **/
@Controller
public class HelloRpcController {
    @RequestMapping("/hello")
    @ResponseBody
    public String HelloRpc(Integer id) {
        IUserService userService = (IUserService) RpcClientProxy.createProxy(IUserService.class);
        User user = userService.getById(id);
//        System.out.println(user);
        return"hello，" + user.getName();
    }
}
