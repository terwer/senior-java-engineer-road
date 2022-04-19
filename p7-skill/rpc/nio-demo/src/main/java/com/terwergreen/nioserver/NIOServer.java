package com.terwergreen.nioserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * 服务端
 *
 * @name: NIOServer
 * @author: terwer
 * @date: 2022-04-18 21:59
 **/
public class NIOServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 1. 打开一个服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2. 绑定对应的端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 3. 通道默认是阻塞的，需要设置为非阻塞
        // true为阻塞，false为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务端启动成功=========");

        while (true) {
            // 4. 检查是否有客户端连接，有客户端连接会返回对应的通道
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                System.out.println("没有客户端连接，做别的事情");
                Thread.sleep(2000);
                continue;
            }

            // 5. 获取客户端传递过来的数据,并把数据放在byteBuffer这个缓冲区中
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 正数，读到的字节
            // 0，没有读到数据
            // -1，读到了文件末尾
            int read = socketChannel.read(byteBuffer);
            System.out.println("客户端发来的消息：" + new String(byteBuffer.array(), 0, read));

            // 6. 给客户端回写数据
            socketChannel.write(ByteBuffer.wrap("你好，我是服务端".getBytes(StandardCharsets.UTF_8)));

            // 7. 释放资源
            socketChannel.close();
        }

    }
}
