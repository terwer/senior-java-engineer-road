package com.terwergreen.groovy

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 服务端
 *
 * @name: ServerDemo
 * @author: terwer
 * @date: 2022-05-09 14:20
 * */
class ServerDemo {
    static void main(String[] args) throws IOException {
        // 1.创建一个线程池，如果有客户端链接就创建一个线程与之通信
        ExecutorService executorService = Executors.newCachedThreadPool()
        // 2.创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(9999)
        System.out.println('服务器已启动')
        while (true) {
            // 3.监听客户端
            Socket socket = serverSocket.accept()
            System.out.println('有客户端链接')
            executorService.execute(() -> handle(socket))
        }
    }

    private static void handle(Socket socket) {
        try {
            System.out.println('线程ID:' + Thread.currentThread().id + ',线程名称：' + Thread.currentThread().name)
            // 从连接中取出输入流
            InputStream inputStream = socket.inputStream
            byte[] b = new byte[1024]
            int read = inputStream.read(b)
            System.out.println('客户端发过来的消息：' + new String(b, 0, read))

            // 链接中取出输出流并回话
            OutputStream outputStream = socket.outputStream
            outputStream.write('没有'.bytes)
        } catch (Exception e) {
            e.printStackTrace()
        } finally {
            try {
                socket.close()
            } catch (IOException e) {
                e.printStackTrace()
            }
        }
    }
}
