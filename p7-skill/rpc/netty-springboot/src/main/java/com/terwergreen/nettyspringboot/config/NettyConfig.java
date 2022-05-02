package com.terwergreen.nettyspringboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Netty配置类
 *
 * @name: NettyConfig
 * @author: terwer
 * @date: 2022-05-01 00:04
 **/
@Component
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyConfig {
    // netty监听端口
    private int port;
    // webdocket访问路径
    private String path;
}
