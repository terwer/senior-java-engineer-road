package com.terwergreen.rpc.consumer.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

/**
 * 客户端处理类
 * 1、发送消息
 * 2、接收消息
 *
 * @name: RpcClientHandler
 * @author: terwer
 * @date: 2022-03-13 23:01
 **/
public class RpcClientHandler extends SimpleChannelInboundHandler implements Callable {

    private ChannelHandlerContext ctx;
    // 消息
    private String requestMessage;
    private String responseMessage;

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    /**
     * 通道读取就绪事件
     *
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected synchronized void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        responseMessage = (String) msg;
        // 唤醒等待线程
        notify();
    }

    /**
     * 通道就绪事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    @Override
    public synchronized Object call() throws Exception {
        // 消息发送
        ctx.writeAndFlush(requestMessage);
        // 线程等待
        wait();
        return responseMessage;
    }
}
