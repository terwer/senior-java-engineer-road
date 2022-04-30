package com.terwergreen.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 聊天室服务端
 *
 * @name: NettyChatServer
 * @author: terwer
 * @date: 2022-04-27 22:23
 **/
public class NettyChatServer {
    private int port;

    public NettyChatServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup bossGroup = null;
        NioEventLoopGroup workerGroup = null;
        try {
            // 1. 创建bossGroup线程组: 处理网络事件--连接事件，默认是2*处理器线程数目
            bossGroup = new NioEventLoopGroup(1);
            // 2. 创建workerGroup线程组: 处理网络事件--读写事件,默认是2*处理器线程数目
            workerGroup = new NioEventLoopGroup();
            // 3. 创建服务端启动助手
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 4. 设置bossGroup线程组和workerGroup线程组
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)  // 5. 设置服务端通道实现为NIO
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)  // 6. 参数设置，设置活跃状态，child是设置workerGroup
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 7. 创建一个通道初始化对象
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 8. 向pipeline中添加自定义业务处理handler
                            // 添加编码器
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringEncoder());
                            // 自定义处理类
                            ch.pipeline().addLast(new NettyChatServerHandler());
                        }
                    });
            // 9. 启动服务端并绑定端口,同时将异步改为同步
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("群聊天室服务器启动成功");

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("端口绑定成功!");
                    } else {
                        System.out.println("端口绑定失败!");
                    }
                }
            });

            // 10. 关闭通道和关闭连接池(不是真正关闭，只是设置为关闭状态)
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyChatServer(9998).run();
    }
}
