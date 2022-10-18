package com.terwergreen.array;

/**
 * @name: ArrayTest2
 * @author: terwer
 * @date: 2022-10-18 00:17
 **/
public class ArrayTest2 {
    public static void main(String[] args) {
        Person[] p = new Person[3];

        // System.out.println(p[0]);

        p[0] = new Person(10);
        p[1] = new Person(20);
        p[2] = new Person(30);

        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i].age);
        }

        Person[] p2 = new Person[5];
        for (int j = 0; j < p2.length; j++) {
            System.out.println(p2[j]);
        }
    }
}

class Person {
    int age;

    public Person(int age) {
        this.age = age;
    }
}