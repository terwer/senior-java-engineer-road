package com.terewrgreen.rpc.consumer.client;

import com.terewrgreen.rpc.consumer.handler.RpcClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Rpc客户端
 * 1、连接netty服务端
 * 2、提供给调用者关闭资源的方法
 * 3、提供消息发送的方法
 *
 * @name: RpcClient
 * @author: terwer
 * @date: 2022-03-13 21:04
 **/
public class RpcClient {

    private NioEventLoopGroup group;
    private Channel channel;

    private String ip;
    private int port;

    private RpcClientHandler rpcClientHandler = new RpcClientHandler();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public RpcClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        initClient();
    }

    /**
     * 初始化客户端，连接netty服务端
     */
    public void initClient() {

        try {
            // 创建线程组
            group = new NioEventLoopGroup();
            //  创建启动助手
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_TIMEOUT, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            // String 编解码器
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 客户端处理类
                            pipeline.addLast(rpcClientHandler);
                        }
                    });
            channel = bootstrap.connect(ip, port).sync().channel();
            System.out.println("===========客户端启动成功==========");
        } catch (Exception e) {
            if (channel != null) {
                channel.close();
                System.out.println("客户端关闭channel");
            }
            if (group != null) {
                group.shutdownGracefully();
                System.out.println("客户端关闭group");
            }
            e.printStackTrace();
        }
    }

    public void close(){
        if (channel != null) {
            channel.close();
            System.out.println("外部调用客户端关闭channel");
        }
        if (group != null) {
            group.shutdownGracefully();
            System.out.println("外部调用客户端关闭group");
        }
    }

    public Object send(String msg) throws ExecutionException, InterruptedException {
        rpcClientHandler.setRequestMessage(msg);
        Future future = executorService.submit(rpcClientHandler);
        return future.get();
    }
}
