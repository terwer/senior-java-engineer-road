package com.terewrgreen.rpc.provider.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对外暴露服务接口
 *
 * @name: RpcService
 * @author: terwer
 * @date: 2022-03-09 23:49
 **/
@Target(ElementType.TYPE)// 作用在接口和类上
@Retention(RetentionPolicy.RUNTIME)// 运行时可以获取
public @interface RpcService {

}

