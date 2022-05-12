package com.terwergreen.com.terwergreen;

/**
 * 静态代码块测试
 *
 * @name: StaticBlockTest2
 * @author: terwer
 * @date: 2022-05-11 01:08
 **/
public class StaticBlockTest2 {
    static {
        System.out.println("静态代码块1执行");
    }

    {
        System.out.println("构造代码块1执行");
    }

    static {
        System.out.println("静态代码块2执行");
    }

    {
        System.out.println("构造代码块2执行");
    }

    public StaticBlockTest2() {
        System.out.println("构造函数执行");
    }

    public static void main(String[] args) {
        System.out.println("main方法执行");

        StaticBlockTest2 staticBlock = new StaticBlockTest2();
        staticBlock.test();
    }

    public void test() {
        System.out.println("实例方法执行");
    }

    // 运行结果
    // 静态代码块1执行
    // 静态代码块2执行
    // main方法执行
    // 构造代码块1执行
    // 构造代码块2执行
    // 构造函数执行
    // 实例方法执行
}

