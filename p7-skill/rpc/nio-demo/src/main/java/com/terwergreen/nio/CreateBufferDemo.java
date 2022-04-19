package com.terwergreen.nio;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 创建缓冲区
 *
 * @name: CreateBufferDemo
 * @author: terwer
 * @date: 2022-04-18 17:38
 **/
public class CreateBufferDemo {
    public static void main(String[] args) {
        // 1.创建一个指定长度的缓冲区，ByteBuffer为例
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        for (int i = 0; i < 4; i++) {
            System.out.println(byteBuffer.get());
        }

        // 在此调用会报错
        // System.out.println(byteBuffer.get());
        System.out.println("==================");
        System.out.println();

        // 2.创建一个有内容的缓冲区
        ByteBuffer wrap = ByteBuffer.wrap("test".getBytes(StandardCharsets.UTF_8));
        for (int i = 0; i < 4; i++) {
            System.out.println(wrap.get());
        }
    }
}
