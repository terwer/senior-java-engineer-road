package com.terwergreen;

/**
 * @name: SubClass
 * @author: terwer
 * @date: 2022-05-01 21:04
 **/
public class SubClass extends SuperClass {
    // 无论是不是static，都能隐藏父类的变量i
    public static int i = 2;
    public static int j = 1;
    // 无论是不是final，都能隐藏父类的变量k
    public final int k = 4;

    public static void method1() {
        System.out.println("SubClass Method1");
    }

    public void method2() {
        System.out.println("SubClass Method2");
    }
}
