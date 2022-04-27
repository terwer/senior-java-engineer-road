package com.terwergreen.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Netty聊天室业务处理类
 *
 * @name: NettyChatServerHandler
 * @author: terwer
 * @date: 2022-04-27 22:31
 **/
public class NettyChatServerHandler extends SimpleChannelInboundHandler<String> {
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
        System.out.println("【服务端】：" + channel.remoteAddress().toString().substring(1) + "上线。");
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
        System.out.println("【" + channel.remoteAddress().toString().substring(1) + "】下线。");
    }

    /**
     * 通道读取事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 当前发送消息的通道
        Channel channel = ctx.channel();
        for (Channel channel1 : channelList) {
            // 排除自身通道
            if (channel != channel1) {
                channel1.writeAndFlush("【" + channel.remoteAddress().toString().substring(1) + "】说：" + msg);
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
        System.out.println("【" + channel.remoteAddress().toString().substring(1) + "】发送异常。");
        // 移除
        channelList.remove(channel);
    }
}
