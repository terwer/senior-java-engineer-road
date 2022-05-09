package com.terwergreen.scala

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
 * @date: 2022-04-17 14:20
 * */
object ServerDemo {
  @throws[IOException]
  def main(args: Array[String]) = { // 1.创建一个线程池，如果有客户端链接就创建一个线程与之通信
    val executorService = Executors.newCachedThreadPool
    // 2.创建ServerSocket
    val serverSocket = new ServerSocket(9999)
    System.out.println("服务器已启动")
    while ( {
      true
    }) { // 3.监听客户端
      val socket = serverSocket.accept
      System.out.println("有客户端链接")
      executorService.execute(() => handle(socket))
    }
  }

  private def handle(socket: Socket) = try {
    System.out.println("线程ID:" + Thread.currentThread.getId + ",线程名称：" + Thread.currentThread.getName)
    // 从连接中取出输入流
    val inputStream = socket.getInputStream
    val b = new Array[Byte](1024)
    val read = inputStream.read(b)
    System.out.println("客户端发过来的消息：" + new String(b, 0, read))
    // 链接中取出输出流并回话
    val outputStream = socket.getOutputStream
    outputStream.write("没有".getBytes)
  } catch {
    case e: Exception =>
      e.printStackTrace()
  } finally try socket.close()
  catch {
    case e: IOException =>
      e.printStackTrace()
  }
}