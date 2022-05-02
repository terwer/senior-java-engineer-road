package com.terwergreen.nettyspringboot.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Websocket处理类
 * Websocket数据以帧的形式进行处理
 * 需要设置通道共享
 *
 * @name: WebsocketHandler
 * @author: terwer
 * @date: 2022-05-01 23:21
 **/
@Component
@ChannelHandler.Sharable
public class WebsocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    public static List<Channel> channelList = new ArrayList<>();

    /**
     * 通道就绪事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 有客户端连接时，将通道放入集合
        channelList.add(channel);
        System.out.println("有新的链接");
    }

    /**
     * 通道未就绪
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // channel下线
        Channel channel = ctx.channel();
        // 客户端连接端口，移除连接
        channelList.remove(channel);
        System.out.println("连接断开");
    }

    /**
     * 通道读取事件
     *
     * @param ctx
     * @param textWebSocketFrame
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String msg = textWebSocketFrame.text();
        System.out.println("接收到消息：" + msg);
        // 当前发送消息的通道
        Channel channel = ctx.channel();
        for (Channel channel1 : channelList) {
            // 排除自身通道
            if (channel != channel1) {
                channel1.writeAndFlush(new TextWebSocketFrame(msg));
            }
        }
    }

    /**
     * 异常处理事件
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel channel = ctx.channel();
        System.out.println("消息发送异常。");
        // 移除
        channelList.remove(channel);
    }
}
