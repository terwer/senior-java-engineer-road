package com.terwergreen.rpc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 客户端的SpringBoot启动类
 *
 * @name: ClientBootstrapApplication
 * @author: terwer
 * @date: 2022-05-04 01:23
 **/
@SpringBootApplication
public class ClientBootstrapApplication{
    public static void main(String[] args) {
        SpringApplication.run(ClientBootstrapApplication.class, args);
    }
}
