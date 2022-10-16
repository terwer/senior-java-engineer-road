package com.terwergreen.str;

/**
 * equals测试
 *
 * @name: EqualsTest
 * @author: terwer
 * @date: 2022-10-15 22:00
 **/
public class EqualsTest {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan");
        Student s2 = new Student("zhangsan");

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}

class Student {
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Student) {
            Student student = (Student) anObject;
            if (this.name.equals(student.name)) {
                return true;
            }
        }
        return false;
    }
}
