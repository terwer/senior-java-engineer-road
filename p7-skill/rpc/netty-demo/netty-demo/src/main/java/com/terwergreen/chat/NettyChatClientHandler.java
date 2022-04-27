package com.terwergreen.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 聊天室客户端业务处理类
 *
 * @name: NettyChatClientHandler
 * @author: terwer
 * @date: 2022-04-27 23:45
 **/
public class NettyChatClientHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 通道读取事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
