package com.terwergreen.rpc.provider.server;

import com.terwergreen.rpc.provider.handler.RpcServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 对外服务
 *
 * @name: RpcServer
 * @author: terwer
 * @date: 2022-03-09 23:53
 **/
@Service
public class RpcServer implements DisposableBean {
    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;

    @Autowired
    private RpcServerHandler rpcServerHandler;

    public void startServer(String ip, int port) {
        try {
            // 1、创建线程组
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();

            // 2、创建服务端启动助手
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            // 3、设置参数
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            // 添加String的编解码器
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 业务处理类
                            pipeline.addLast(rpcServerHandler);
                        }
                    });

            // 4、绑定端口
            ChannelFuture sync = serverBootstrap.bind(ip, port).sync();
            System.out.println("===========服务端启动成功=============");
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
                System.out.println("finally bossGroup成功关闭");
            }
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
                System.out.println("finally workerGroup成功关闭");
            }
        }
    }

    @Override
    public void destroy() throws Exception {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
            System.out.println("destroy bossGroup成功关闭");
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
            System.out.println("destroy workerGroup成功关闭");
        }
    }
}
