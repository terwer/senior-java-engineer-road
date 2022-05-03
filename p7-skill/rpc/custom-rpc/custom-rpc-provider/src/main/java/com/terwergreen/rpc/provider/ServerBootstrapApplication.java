package com.terwergreen.rpc.provider;

import com.terwergreen.rpc.provider.server.RpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

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
        int port = 9999;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }

        int finalPort = port;
        new Thread(new Runnable() {
            @Override
            public void run() {
                rpcServer.startServer("127.0.0.1", finalPort);
            }
        }).start();
    }
}
