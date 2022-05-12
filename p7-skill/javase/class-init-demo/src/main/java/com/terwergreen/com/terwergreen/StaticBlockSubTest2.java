package com.terwergreen.com.terwergreen;

/**
 * 静态代码块继承测试
 *
 * @name: StaticBlockTest
 * @author: terwer
 * @date: 2022-05-11 01:16
 **/
public class StaticBlockSubTest2 {
    static {
        System.out.println("父类静态代码块1执行");
    }

    {
        System.out.println("父类构造代码块1执行");
    }

    static {
        System.out.println("父类静态代码块2执行");
    }

    {
        System.out.println("父类构造代码块2执行");
    }

    public StaticBlockSubTest2() {
        System.out.println("父类构造函数执行");
    }

    public static void main(String[] args) {
        System.out.println("父类main方法执行");

        Sub2 sub2 = new Sub2();
        sub2.test();
    }

    public void test() {
        System.out.println("父类实例方法执行");
    }

    // 运行结果
    // 父类静态代码块1执行
    // 父类静态代码块2执行
    // 父类main方法执行
    // 子类静态代码块1执行
    // 子类静态代码块2执行
    // 父类构造代码块1执行
    // 父类构造代码块2执行
    // 父类构造函数执行
    // 子类构造代码块1执行
    // 子类构造代码块2执行
    // 子类构造函数执行
    // 子类实例方法执行
}

class Sub2 extends StaticBlockSubTest2 {
    static {
        System.out.println("子类静态代码块1执行");
    }

    {
        System.out.println("子类构造代码块1执行");
    }

    static {
        System.out.println("子类静态代码块2执行");
    }

    {
        System.out.println("子类构造代码块2执行");
    }

    public Sub2() {
        System.out.println("子类构造函数执行");
    }

    public void test() {
        System.out.println("子类实例方法执行");
    }
}