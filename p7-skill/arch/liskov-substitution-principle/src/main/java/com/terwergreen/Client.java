package com.terwergreen;

/**
 * @name: Client
 * @author: terwer
 * @date: 2022-04-15 18:28
 **/
public class Client {
    public static void main(String[] args) {
        // C c = new C1();
        // int result = c.func(1, 2);
        // System.out.println("1+2=" + result);

        C2 c2 = new C2();
        int result1 = c2.func(1, 2);
        System.out.println("1+2=" + result1);
        int result2 = c2.func2(1, 2);
        System.out.println("2-1=" + result2);
    }
}
