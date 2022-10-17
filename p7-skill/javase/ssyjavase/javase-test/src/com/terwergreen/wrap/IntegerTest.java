package com.terwergreen.wrap;

/**
 * @name: IntegerTest
 * @author: terwer
 * @date: 2022-10-17 22:38
 **/
public class IntegerTest {
    public static void main(String[] args) {
        int a = 10;
        Integer integer = new Integer(a);

        int b = integer.intValue();
        System.out.println(b == a);

    }
}
