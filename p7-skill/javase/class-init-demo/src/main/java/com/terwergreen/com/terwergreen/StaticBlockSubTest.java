package com.terwergreen.com.terwergreen;

/**
 * 静态代码块继承测试
 *
 * @name: StaticBlockTest
 * @author: terwer
 * @date: 2022-05-11 01:16
 **/
public class StaticBlockSubTest {
    static {
        System.out.println("父类静态代码块执行");
    }

    public static void main(String[] args) {
        System.out.println("父类main方法执行");
    }

    // 运行结果
    // 父类静态代码块执行
    // 父类main方法执行
}

class Sub extends StaticBlockTest{
    static {
        System.out.println("子类静态代码块执行");
    }

    public static void main(String[] args) {
        System.out.println("子类main方法执行");
    }
}