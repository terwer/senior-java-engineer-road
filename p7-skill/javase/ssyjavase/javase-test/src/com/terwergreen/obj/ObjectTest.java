package com.terwergreen.obj;

/**
 * Object测试
 *
 * @name: ObjectTest
 * @author: terwer
 * @date: 2022-10-12 21:11
 **/
public class ObjectTest {
    public static void main(String[] args) {
        Object object = new Object();

        System.out.println(object);
        System.out.println(object.toString());

        String str = "aaa";
        System.out.println(str);
        System.out.println(str.toString());

        Student student = new Student();
        System.out.println(student);
        System.out.println(student.toString());

        int v16 = 0xAB2F;
        System.out.println(v16);

        int result = 10 * (int) Math.pow(16, 3) + 11 * (int) Math.pow(16, 2) + 2 * 16 + 15;
        System.out.println(result);
    }
}

class Student {
    public String toString() {
        return "Hello World";
    }
}