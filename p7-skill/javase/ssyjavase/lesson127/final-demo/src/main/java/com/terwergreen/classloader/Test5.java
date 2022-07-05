package com.terwergreen.classloader;

/**
 * @name: Test5
 * @author: terwer
 * @date: 2022-07-05 23:02
 **/
public class Test5 {
    static {
        System.out.println("Test5 static block");
    }

    public static void main(String[] args) {
        Parent2 parent;

        System.out.println("-----------------");

        parent = new Parent2();

        System.out.println(Parent2.a);

        System.out.println(Child2.b);
    }
}

class Parent2 {
    static int a = 3;

    static {
        System.out.println("Parent2 static block");
    }
}

class Child2 extends Parent2 {
    static int b = 4;

    static {
        System.out.println("Child2 static block");
    }
}