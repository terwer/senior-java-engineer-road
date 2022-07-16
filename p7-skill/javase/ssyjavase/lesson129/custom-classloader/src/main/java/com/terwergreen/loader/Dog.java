package com.terwergreen.loader;

/**
 * @name: Dog
 * @author: terwer
 * @date: 2022-07-07 22:13
 **/
public class Dog {
    public Dog() {
        System.out.println("Dog is loaded by: " + this.getClass().getClassLoader());
    }
}
