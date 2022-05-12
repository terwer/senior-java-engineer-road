package com.terwergreen.com.terwergreen;

/**
 * 单独类静态代码块
 *
 * @name: StaticBlockTest
 * @author: terwer
 * @date: 2022-05-11 01:16
 **/
public class StaticBlockTest {
    static {
        System.out.println("静态代码块执行");
    }

    public static void main(String[] args) {
        System.out.println("main方法执行");
    }

    // 运行结果
    // 静态代码块执行
    // main方法执行
}
