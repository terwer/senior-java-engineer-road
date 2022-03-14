package com.terwergreen.rpc.consumer.proxy;

import com.alibaba.fastjson.JSON;
import com.terwergreen.common.RpcRequest;
import com.terwergreen.common.RpcResponse;
import com.terwergreen.rpc.consumer.client.RpcClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * 客户端代理类，创建代理对象
 * 1、封装request请求对象
 * 2、创建RpcClient对象
 * 3、发送消息
 * 4、返回结果
 *
 * @name: RpcClientProxy
 * @author: terwer
 * @date: 2022-03-13 23:45
 **/
public class RpcClientProxy {
    public static Object createProxy(Class serviceClass) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{serviceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 1、封装request请求对象
                RpcRequest rpcRequest = new RpcRequest();
                rpcRequest.setRequestId(UUID.randomUUID().toString());
                rpcRequest.setClassName(method.getDeclaringClass().getName());
                rpcRequest.setMethodName(method.getName());
                rpcRequest.setParameterTypes(method.getParameterTypes());
                rpcRequest.setParameters(args);

                // 2、创建RpcClient对象
                RpcClient rpcClient = new RpcClient("127.0.0.1", 9999);

                try {
                    // 3、发送消息
                    Object responseMessage = rpcClient.send(JSON.toJSONString(rpcRequest));

                    // 4、返回结果
                    RpcResponse response = JSON.parseObject(responseMessage.toString(), RpcResponse.class);
                    if (response.getError() != null) {
                        throw new RuntimeException(response.getError());
                    }
                    Object result = response.getResult();
                    Object object = JSON.parseObject(result.toString(), method.getReturnType());
                    return object;
                } catch (Exception e) {
                    throw e;
                } finally {
                    rpcClient.close();
                }


            }
        });
    }
}
