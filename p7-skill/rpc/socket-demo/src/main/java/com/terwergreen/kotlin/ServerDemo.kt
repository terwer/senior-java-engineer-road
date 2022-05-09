package com.terwergreen.kotlin

import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

/**
 * 服务端
 *
 * @name: ServerDemo
 * @author: terwer
 * @date: 2022-05-09 23:14
 **/
object ServerDemo {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        // 1.创建一个线程池，如果有客户端链接就创建一个线程与之通信
        val executorService = Executors.newCachedThreadPool()
        // 2.创建ServerSocket
        val serverSocket = ServerSocket(9999)
        println("服务器已启动")
        while (true) {
            // 3.监听客户端
            val socket = serverSocket.accept()
            println("有客户端链接")
            executorService.execute { handle(socket) }
        }
    }

    private fun handle(socket: Socket) {
        try {
            println("线程ID:" + Thread.currentThread().id + ",线程名称：" + Thread.currentThread().name)
            // 从连接中取出输入流
            val inputStream = socket.getInputStream()
            val b = ByteArray(1024)
            val read = inputStream.read(b)
            println("客户端发过来的消息：" + String(b, 0, read))

            // 链接中取出输出流并回话
            val outputStream = socket.getOutputStream()
            outputStream.write("没有".toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                socket.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}