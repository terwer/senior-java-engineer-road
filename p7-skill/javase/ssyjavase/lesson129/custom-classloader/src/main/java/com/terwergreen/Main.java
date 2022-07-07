package com.terwergreen;

/**
 * @name: Main
 * @author: terwer
 * @date: 2022-07-07 21:33
 **/
public class Main {
    public static void main(String[] args) throws Exception{
        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath("/home/terwer/Downloads/f1");

        MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
        loader2.setPath("/home/terwer/Downloads/f2");

        MyClassLoader loader3 = new MyClassLoader(null, "loader3");
        loader3.setPath("/home/terwer/Downloads/f3");

        test(loader2);
        test(loader3);
    }

    public static void test(ClassLoader loader) throws Exception {
        Class clazz = loader.loadClass("com.terwergreen.loader.Sample");
        Object object = clazz.getDeclaredConstructor().newInstance();
    }
}