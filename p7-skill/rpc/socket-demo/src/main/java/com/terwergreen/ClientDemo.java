package com.terwergreen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 *
 * @name: ClientDemo
 * @author: terwer
 * @date: 2022-04-17 15:30
 **/
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        while (true) {
            // 1.创建客户端socket
            Socket s = new Socket("127.0.0.1", 9999);
            // 2.从连接中获取输出流并发送消息
            OutputStream os = s.getOutputStream();
            System.out.println("请输入:");
            Scanner sc = new Scanner(System.in);
            String msg = sc.nextLine();
            os.write(msg.getBytes());

            // 3.从连接中取出输入流并接受会话
            InputStream is = s.getInputStream();
            byte[] b = new byte[1024];
            // 下面写法错了
            // int read = is.read();
            // 应该是
            int read = is.read(b);
            System.out.println("老板说:" + new String(b, 0, read).trim());

            s.close();
        }
    }
}
