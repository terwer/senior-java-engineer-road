package com.terwergreen.rpc.provider.handler;

import com.alibaba.fastjson.JSON;
import com.terwergreen.common.RpcRequest;
import com.terwergreen.common.RpcResponse;
import com.terwergreen.rpc.provider.anno.RpcService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务端处理类
 * <p>
 * 1、将标有@RpcService注解的类进行缓存
 * 2、接收客户端请求
 * 3、根据传过来的beanName在缓存中查找对应的bean
 * 4、解析请求中的方法名、参数类型、参数信息
 *
 * @name: RpcServerHandler
 * @author: terwer
 * @date: 2022-03-10 00:22
 **/
@Component
@ChannelHandler.Sharable
public class RpcServerHandler extends SimpleChannelInboundHandler<String> implements ApplicationContextAware {
    private static final Map SERVICE_INSTANCE_MAP = new ConcurrentHashMap();

    /**
     * 1、将标有@RpcService注解的类进行缓存
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (serviceMap != null && serviceMap.size() > 0) {
            Set<Map.Entry<String, Object>> entries = serviceMap.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                Object serviceBean = entry.getValue();
                if (serviceBean.getClass().getInterfaces().length == 0) {
                    throw new RuntimeException("服务必须实现接口");
                }

                // 默认取第一个接口作为名称
                SERVICE_INSTANCE_MAP.put(serviceBean.getClass().getInterfaces()[0].getName(), serviceBean);
            }

        }
    }

    /**
     * 通道读取就绪事件
     *
     * @param channelHandlerContext
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 接收客户端请求，转换成RpcReuest
        RpcRequest rpcRequest = JSON.parseObject(msg, RpcRequest.class);
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(rpcRequest.getRequestId());

        try {
            Object result = handler(rpcRequest);
            rpcResponse.setResult(result);
        } catch (Exception e) {
            rpcResponse.setError(e.getMessage());
            e.printStackTrace();
        }

        ctx.writeAndFlush(JSON.toJSONString(rpcResponse));
    }

    /**
     * 业务逻辑处理方法
     *
     * @param rpcRequest
     * @return
     */
    private Object handler(RpcRequest rpcRequest) throws InvocationTargetException {
        // 根据传过来的beanName在缓存中查找对应的bean
        Object serviceBean = SERVICE_INSTANCE_MAP.get(rpcRequest.getClassName());
        if(null == serviceBean){
            throw new RuntimeException("根据beanName找不到服务"+rpcRequest.getClassName());
        }

        // 解析请求中的方法名、参数类型、参数信息
        Class<?> beanClass = serviceBean.getClass();
        String methodName = rpcRequest.getMethodName();
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] parameters = rpcRequest.getParameters();

        // 反射调用
        FastClass fastClass = FastClass.create(beanClass);
        FastMethod fastMethod = fastClass.getMethod(methodName, parameterTypes);
        Object result = fastMethod.invoke(serviceBean, parameters);

        return result;
    }
}
