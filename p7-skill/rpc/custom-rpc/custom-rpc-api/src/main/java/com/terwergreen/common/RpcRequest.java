package com.terwergreen.common;

import lombok.Data;

/**
 * @name: RpcRequest
 * @author: terwer
 * @date: 2022-03-09 23:16
 **/
@Data
public class RpcRequest {
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;
    /**
     * 参数
     */
    private Object[] parameters;
}
