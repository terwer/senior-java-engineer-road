#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <stdbool.h>
#include <time.h>
#include <unistd.h>

int main() {
    int serv_sock = 0, sock = 0;
    struct sockaddr_in serv_addr;

    char sendBuff[2015];
    time_t ticks;

    // 创建socket
    serv_sock = socket(AF_INET, SOCK_STREAM, 0);
    if (serv_sock < 0) {
        exit(1);
    }
    memset(&serv_addr, '0', sizeof(serv_addr));
    memset(sendBuff, '0', sizeof(sendBuff));

    serv_addr.sin_family = AF_INET;
    // serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    // serv_addr.sin_port = 9999;
    serv_addr.sin_port = htons(9999);

    // 绑定ip与端口
    bind(serv_sock, (struct sockaddr *) &serv_addr, sizeof(serv_addr));

    // 开启监听
    listen(serv_sock, 10);
    printf("C语言版的socket服务器启动成功\n");

    while (true) {
        // 接收消息
        sock = accept(serv_sock, (struct sockaddr *) NULL, NULL);

        // 发送消息
        snprintf(sendBuff, sizeof(sendBuff), "这是从C语言版的socket服务器发送过来的消息:%.24s\r\n", ctime(&ticks));
        write(sock, sendBuff, strlen(sendBuff));

        // 关闭
        // close(server_socket);
        close(sock);

        sleep(1);
    }
}
