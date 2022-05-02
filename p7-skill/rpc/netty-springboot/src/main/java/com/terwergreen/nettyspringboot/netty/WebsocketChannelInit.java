package com.terwergreen.nettyspringboot.netty;

import com.terwergreen.nettyspringboot.config.NettyConfig;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通道初始化对象
 *
 * @name: WebsocketChannelInit
 * @author: terwer
 * @date: 2022-05-01 23:11
 **/
@Component
public class WebsocketChannelInit extends ChannelInitializer {

    @Autowired
    private NettyConfig nettyConfig;

    @Autowired
    private WebsocketHandler websocketHandler;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        // 对HTTP协议的支持
        pipeline.addLast(new HttpServerCodec());

        // 对大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());

        // post请求分为三个部分：request line/request header/message body
        // 对POST请求的支持，将多个信息转化成单一的request/response对象
        pipeline.addLast(new HttpObjectAggregator(8000));

        // 对WebSocket协议的支持
        // 将http协议升级为ws协议
        pipeline.addLast(new WebSocketServerProtocolHandler(nettyConfig.getPath()));

        // 自定义处理handler
        pipeline.addLast(websocketHandler);
    }
}
