package com.terwergreen.nio;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 添加缓冲区
 *
 * @name: PutBufferDemo
 * @author: terwer
 * @date: 2022-04-18 19:27
 **/
public class PutBufferDemo {
    public static void main(String[] args) {
        // 1.创建一个指定长度的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println(byteBuffer.position());// 获取当前索引所在的位置
        System.out.println(byteBuffer.limit());// 最多能操作到哪个索引
        System.out.println(byteBuffer.capacity());// 返回缓冲区总长度
        System.out.println(byteBuffer.remaining());// 还有多少个能操作

//        byteBuffer.position(2);
//        byteBuffer.limit(4);
//        System.out.println();
//        System.out.println("============");
//        System.out.println(byteBuffer.position());// 获取当前索引所在的位置
//        System.out.println(byteBuffer.limit());// 最多能操作到哪个索引
//        System.out.println(byteBuffer.capacity());// 返回缓冲区总长度
//        System.out.println(byteBuffer.remaining());// 还有多少个能操作

        // 添加一个字节
        byteBuffer.put((byte) 97);
        System.out.println();
        System.out.println("============");
        System.out.println(byteBuffer.position());// 获取当前索引所在的位置
        System.out.println(byteBuffer.limit());// 最多能操作到哪个索引
        System.out.println(byteBuffer.capacity());// 返回缓冲区总长度
        System.out.println(byteBuffer.remaining());// 还有多少个能操作

        // 添加一个字节数组
        byteBuffer.put("test".getBytes(StandardCharsets.UTF_8));
        System.out.println();
        System.out.println("============");
        System.out.println(byteBuffer.position());// 获取当前索引所在的位置
        System.out.println(byteBuffer.limit());// 最多能操作到哪个索引
        System.out.println(byteBuffer.capacity());// 返回缓冲区总长度
        System.out.println(byteBuffer.remaining());// 还有多少个能操作

        // 超过缓冲区长度会报错
//        byteBuffer.put("1234567".getBytes(StandardCharsets.UTF_8));
//        System.out.println();
//        System.out.println("============");
//        System.out.println(byteBuffer.position());// 获取当前索引所在的位置
//        System.out.println(byteBuffer.limit());// 最多能操作到哪个索引
//        System.out.println(byteBuffer.capacity());// 返回缓冲区总长度
//        System.out.println(byteBuffer.remaining());// 还有多少个能操作

        // 如果缓冲区满了，可以调整position的位置，会覆盖之前对应索引的值
        byteBuffer.position(0);
        byteBuffer.put("1234567".getBytes(StandardCharsets.UTF_8));
        System.out.println();
        System.out.println("============");
        System.out.println(byteBuffer.position());// 获取当前索引所在的位置
        System.out.println(byteBuffer.limit());// 最多能操作到哪个索引
        System.out.println(byteBuffer.capacity());// 返回缓冲区总长度
        System.out.println(byteBuffer.remaining());// 还有多少个能操作
    }
}
