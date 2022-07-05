package com.terwergreen.classloader;

import java.util.Random;

/**
 * @name: Test3
 * @author: terwer
 * @date: 2022-07-05 22:24
 **/
public class Test3 {
    public static void main(String[] args) {
        System.out.println(FinalTest2.x);
    }
}

class FinalTest2{
    public static final int x = new Random().nextInt(100);

    static {
        System.out.println("FinalTest2 static block");
    }
}