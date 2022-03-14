package com.terwergreen.common;

import lombok.Data;

/**
 * @name: RpcResponse
 * @author: terwer
 * @date: 2022-03-09 23:17
 **/
@Data
public class RpcResponse {
    /**
     * 响应ID
     */
    private String requestId;
    /**
     * 错误信息
     */
    private String error;
    /**
     * 响应结果
     */
    private Object result;
}
