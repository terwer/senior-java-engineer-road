<?php

namespace com\terwergreen\php;

/**
 * 服务端
 *
 * @name: ServerDemo
 * @author: terwer
 * @date: 2022-05-10 14:20
 **/
class ServerDemo {
    public static function main($args) {
        $addr = "127.0.0.1";
        $port = 9999;

        // 创建server_socket
        $server_socket = socket_create(AF_INET, SOCK_STREAM, 0);
        // 绑定端口
        socket_bind($server_socket, $addr, $port);
        // 监听
        socket_listen($server_socket);
        echo "php版socket服务器启动成功\n";

        while (true) {
            $socket = socket_accept($server_socket);

            self::handle($socket);
        }

    }

    public static function handle($socket) {
        echo "处理客户端连接\n";

        $msg = socket_read($socket, 1024, PHP_BINARY_READ);
        echo "来自客户端的消息:$msg";

        $out_msg = "这是php版socket服务器发送过来的消息";
        socket_write($socket, $out_msg);

        socket_close($socket);
    }
}

$args = [];
ServerDemo::main($args);