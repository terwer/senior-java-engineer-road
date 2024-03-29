package com.terwergreen.classloader;

/**
 * @name: Test6
 * @author: terwer
 * @date: 2022-07-05 23:27
 **/
public class Test6 {
    public static void main(String[] args) {
        System.out.println(Child3.a);

        Child3.dosomething();
    }
}

class Parent3 {
    static int a = 3;

    static {
        System.out.println("Parent3 static block");
    }

    static void dosomething() {
        System.out.println("do something");
    }
}

class Child3 extends Parent3 {
    static {
        System.out.println("Child3 static block");
    }
}