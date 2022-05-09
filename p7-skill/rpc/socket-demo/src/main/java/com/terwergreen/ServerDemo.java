package com.terwergreen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.execute(() -> handle(socket));
        }
    }

    private static void handle(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] b = new byte[1024];
            int read = inputStream.read(b);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("no".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
