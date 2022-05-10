package main

import (
	"bytes"
	"fmt"
	"net"
)

/*
* 服务端
*
* @name: ServerDemo
* @author: terwer
* @date: 2022-05-09 23:14
**/
func main() {
	listen, _ := net.Listen("tcp", ":9999")
	fmt.Println("go的socket服务器已启动")

	for true {
		conn, _ := listen.Accept()
		handle(conn)
	}
}

func handle(conn net.Conn) {
	fmt.Println("处理客户端连接")

	// 收消息
	b := make([]byte, 1024)
	read, err := conn.Read(b)
	if err != nil {
		return
	}
	var dataBuffer bytes.Buffer
	dataBuffer.Write(b[:read])
	fmt.Println("接收到来自客户端的消息:")
	fmt.Print(dataBuffer.String())

	// 发消息
	var str string = "这是来着go的socket服务端发过来的消息"
	var outBytes []byte = []byte(str)
	conn.Write(outBytes)

	// 关闭链接
	conn.Close()
}
