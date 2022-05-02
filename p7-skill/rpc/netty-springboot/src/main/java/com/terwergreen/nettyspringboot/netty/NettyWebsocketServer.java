package com.terwergreen.nettyspringboot.netty;

import com.terwergreen.nettyspringboot.config.NettyConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Netty的Websocket服务器
 *
 * @name: NettyWebsocketServer
 * @author: terwer
 * @date: 2022-05-01 00:11
 **/
@Component
public class NettyWebsocketServer implements Runnable {
    @Autowired
    private NettyConfig nettyConfig;
    @Autowired
    private WebsocketChannelInit websocketChannelInit;

    private NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    @Override
    public void run() {
        try {
            // 1.创建服务端启动助手
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 2.设置线程组
            serverBootstrap.group(bossGroup, workerGroup);
            // 3.设置参数
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(websocketChannelInit);
            // 4.启动服务端
            ChannelFuture channelFuture = serverBootstrap.bind(nettyConfig.getPort()).sync();
            System.out.println("------Netty服务端启动成功------");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 关闭资源-容器销毁时候关闭
     */
    @PreDestroy
    public void close() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
