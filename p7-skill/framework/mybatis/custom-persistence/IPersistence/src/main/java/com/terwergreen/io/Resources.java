package com.terwergreen.io;

import java.io.InputStream;

/**
 * 资源处理类
 *
 * @name: Resource
 * @author: terwer
 * @date: 2022-03-14 12:57
 **/
public class Resources {
    /**
     * 根据配置文件的路径，将配置文件加载成字节输入流，存储到内存中
     *
     * @param path
     * @return
     */
    public static InputStream getResourceAsStream(String path) {
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return inputStream;
    }
}
