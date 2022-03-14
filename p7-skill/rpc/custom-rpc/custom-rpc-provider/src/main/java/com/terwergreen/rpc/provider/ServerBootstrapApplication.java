package com.terwergreen.rpc.provider;

import com.terwergreen.rpc.provider.server.RpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @name: ServerBootstrapApplication
 * @author: terwer
 * @date: 2022-03-09 23:46
 **/
@SpringBootApplication
public class ServerBootstrapApplication implements CommandLineRunner {
    @Autowired
    private RpcServer rpcServer;

    public static void main(String[] args) {
        SpringApplication.run(ServerBootstrapApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                rpcServer.startServer("127.0.0.1", 9999);
            }
        }).start();
    }
}
