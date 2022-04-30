package com.terwergreen.nettyspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 聊天控制器
 *
 * @name: ChatController
 * @author: terwer
 * @date: 2022-04-30 23:22
 **/
@Controller
public class ChatController {
    @RequestMapping("/")
    public String chat() {
        return "chat";
    }
}
