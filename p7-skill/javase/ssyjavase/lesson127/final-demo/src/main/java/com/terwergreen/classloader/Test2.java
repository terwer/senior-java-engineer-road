package com.terwergreen.classloader;

/**
 * @name: FinalTest
 * @author: terwer
 * @date: 2022-07-05 21:51
 **/
public class Test2 {
    public static void main(String[] args) {
        System.out.println(FinalTest.x);
    }
}

class FinalTest {
    public static final int x = 6 / 3;

    static {
        System.out.println("FinalTest static block");
    }
}
