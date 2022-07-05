package com.terwergreen.classloader;

/**
 * @name: Test7
 * @author: terwer
 * @date: 2022-07-05 23:45
 **/
public class Test7 {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = classLoader.loadClass("com.terwergreen.classloader.C");
        System.out.println("-------------------");

        Class.forName("com.terwergreen.classloader.C");
    }
}

class C {
    static {
        System.out.println("Class C");
    }
}
