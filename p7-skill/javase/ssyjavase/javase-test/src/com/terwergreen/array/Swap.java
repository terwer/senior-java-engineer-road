package com.terwergreen.array;

/**
 * @name: Swap
 * @author: terwer
 * @date: 2022-10-18 20:26
 **/
public class Swap {
    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        swap(a, b);

        System.out.println("a=" + a);
        System.out.println("b=" + b);

        System.out.println("-------------------");

        int temp = a;
        a = b;
        b = temp;

        System.out.println("a=" + a);
        System.out.println("b=" + b);

        System.out.println("-------------------");

        int x = 3;
        int y = 4;
        x = x + y;
        y = x - y;
        x = x - y;
        System.out.println("x=" + x);
        System.out.println("y=" + y);
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }
}
