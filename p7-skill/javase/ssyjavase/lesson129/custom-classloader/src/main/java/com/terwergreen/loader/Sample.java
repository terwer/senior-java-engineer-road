package com.terwergreen.loader;

/**
 * @name: Sample
 * @author: terwer
 * @date: 2022-07-07 22:12
 **/
public class Sample {
    public int v1 = 1;

    public Sample() {
        System.out.println("Sample is loaded by: " + this.getClass().getClassLoader());

        new Dog();
    }
}
