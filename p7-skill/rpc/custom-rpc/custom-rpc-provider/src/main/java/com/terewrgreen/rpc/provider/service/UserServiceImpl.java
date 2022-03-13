package com.terewrgreen.rpc.provider.service;

import com.terewrgreen.api.IUSerService;
import com.terewrgreen.pojo.User;
import com.terewrgreen.rpc.provider.anno.RpcService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务实现类
 *
 * @name: UserServiceImpl
 * @author: terwer
 * @date: 2022-03-09 23:34
 **/
@RpcService
@Service
public class UserServiceImpl implements IUSerService {
    Map<Object, User> userMap = new HashMap<>();

    @Override
    public User getById(int id) {
        User user = new User();
        user.setId(1);
        user.setName("唐有炜");
        userMap.put(user.getId(), user);

        User user2 = new User();
        user2.setId(2);
        user2.setName("张三");
        userMap.put(user2.getId(), user2);

        return userMap.get(id);
    }
}
