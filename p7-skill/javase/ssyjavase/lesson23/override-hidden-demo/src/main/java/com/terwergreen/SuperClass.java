package com.terwergreen;

/**
 * @name: SuperClass
 * @author: terwer
 * @date: 2022-05-01 21:04
 **/
public class SuperClass {
    public static int i = 1;
    public int j = 2;
    public final int k = 3;

    public static void method1() {
        System.out.println("SuperClass Method1");
    }

    public void method2() {
        System.out.println("SuperClass Method2");
    }

    public final void method3() {
        System.out.println("SuperClass Method3");
    }
}

