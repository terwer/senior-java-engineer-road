package com.terwergreen.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.charset.StandardCharsets;

/**
 * 从缓冲区读取数据
 *
 * @name: GetBufferDemo
 * @author: terwer
 * @date: 2022-04-18 19:51
 **/
public class GetBufferDemo {
    public static void main(String[] args) {
        // 1.创建一个指定长度的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put("0123".getBytes(StandardCharsets.UTF_8));
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("remaining:" + byteBuffer.remaining());

        // 切换读模式
        System.out.println();
        System.out.println("=================");
        System.out.println("准备读数据:");
        byteBuffer.flip();
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("remaining:" + byteBuffer.remaining());
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.println(byteBuffer.get());
        }
        // 读取完毕后，继续读取会报错，超过limit
//        System.out.println(byteBuffer.get());
        // 读取指定字节
//        System.out.println("读取指定索引:");
//        System.out.println(byteBuffer.get(2));

        System.out.println("读取多个字节:");
        // 重复读取
        byteBuffer.rewind();
        byte[] dst = new byte[4];
        byteBuffer.get(dst);
        System.out.println(new String(dst));

        // 将缓冲区转化为字节数组返回
        System.out.println();
        System.out.println("===========");
        System.out.println("将缓冲区转化为字节数组:");
        byte[] array = byteBuffer.array();
        System.out.println(new String(array));

        // 切换写模式，会覆盖之前所有的值
        System.out.println();
        System.out.println("================");
        System.out.println("切换写模式，覆盖之前的值:");
        byteBuffer.clear();
        byteBuffer.put("test".getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(byteBuffer.array()));
    }
}
