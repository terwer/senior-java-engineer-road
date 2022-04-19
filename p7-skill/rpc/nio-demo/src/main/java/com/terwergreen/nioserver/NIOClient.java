package com.terwergreen.nioserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * 客户端
 *
 * @name: NIOClient
 * @author: terwer
 * @date: 2022-04-18 22:11
 **/
public class NIOClient {
    public static void main(String[] args) throws IOException {
        // 1. 打开通道
        SocketChannel socketChannel = SocketChannel.open();
        // 2. 设置连接IP和端口号
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        // 3. 写出数据
        socketChannel.write(ByteBuffer.wrap("你好，我是客户端".getBytes(StandardCharsets.UTF_8)));
        // 4. 读取服务器写回的数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(byteBuffer);
        System.out.println("服务端回话：" + new String(byteBuffer.array(), 0, read));
        // 5.释放资源
        socketChannel.close();
    }
}
