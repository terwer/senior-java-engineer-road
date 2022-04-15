package com.terwergreen;

/**
 * Java课程
 *
 * @name: JavaCourse
 * @author: terwer
 * @date: 2022-04-14 19:49
 **/
public class JavaCourse implements ICourse {
    @Override
    public Integer getId() {
        return 1;
    }

    @Override
    public String getName() {
        return "Java进阶";
    }

    @Override
    public Float getPrice() {
        return 1.1f;
    }
}
