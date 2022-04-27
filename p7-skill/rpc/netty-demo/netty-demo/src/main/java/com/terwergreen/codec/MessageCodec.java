package com.terwergreen.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * 编解码器
 *
 * @name: MessageCodec
 * @author: terwer
 * @date: 2022-04-27 01:58
 **/
public class MessageCodec extends MessageToMessageCodec {

    /**
     * 编码
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        System.out.println("消息正在编码，使用编解码器");
        String str = (String) msg;
        out.add(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));
    }

    /**
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        System.out.println("正在进行消息解码，使用编解码器");
        ByteBuf byteBuf = (ByteBuf)msg;
        // 传递到下一个handler
        out.add(byteBuf.toString(CharsetUtil.UTF_8));
    }
}
