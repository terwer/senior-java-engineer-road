package com.terwergreen.classloader;

/**
 * @name: MyTest1
 * @author: terwer
 * @date: 2022-03-07 00:45
 **/
public class MyTest1 {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());

        Class clazz2 = Class.forName("com.terwergreen.classloader.C");
        System.out.println(clazz2.getClassLoader());
    }
}

class C{

}