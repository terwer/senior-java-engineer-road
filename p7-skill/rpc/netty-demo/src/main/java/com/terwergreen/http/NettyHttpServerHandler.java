package com.terwergreen.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * HTTP处理类
 *
 * @name: NettyHttpServerHandler
 * @author: terwer
 * @date: 2022-04-29 01:07
 **/
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取就绪事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 1.判断是否是HTTP请求
        if (msg instanceof HttpRequest) {
            DefaultHttpRequest request = (DefaultHttpRequest) msg;
            System.out.println("浏览器请求路径：" + request.uri());

            // 图标不响应
            if("/favicon.ico".equals(request.uri())){
                System.out.println("图标不处理");
                return;
            }

            // 2.响应浏览器
            ByteBuf byteBuf = Unpooled.copiedBuffer("<h1>你好，我是Netty服务端</h1>", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            ctx.writeAndFlush(response);
        }
    }
}
