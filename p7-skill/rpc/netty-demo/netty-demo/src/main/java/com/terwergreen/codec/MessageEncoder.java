package com.terwergreen.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * 消息编码器
 *
 * @name: MessageEncoder
 * @author: terwer
 * @date: 2022-04-26 21:28
 **/
public class MessageEncoder extends MessageToMessageEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        System.out.println("消息正在编码");
        String str = (String) msg;
        out.add(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));
    }
}
