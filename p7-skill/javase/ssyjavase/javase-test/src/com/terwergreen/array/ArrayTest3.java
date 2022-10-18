package com.terwergreen.array;

/**
 * @name: ArrayTest3
 * @author: terwer
 * @date: 2022-10-18 00:58
 **/
public class ArrayTest3 {
    public static void main(String[] args) {
        Student[] s = new Student[100];

        for (int i = 0; i < s.length; i++) {
            s[i] = new Student();

            if (i % 2 == 0) {
                s[i].name = "zhangsan";
            } else {
                s[i].name = "lisi";
            }
        }

        for (int j = 0; j < s.length; j++) {
            System.out.println(s[j].name);
        }

    }
}

class Student {
    String name;
}
