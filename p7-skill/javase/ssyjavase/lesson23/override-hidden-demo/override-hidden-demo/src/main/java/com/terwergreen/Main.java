package com.terwergreen;

/**
 * 主函数入口
 *
 * @author terwer
 * @version 1.0
 **/
public class Main {
    public static void main(String[] args) {

        SuperClass sc = new SubClass();
        System.out.println("i = " + sc.i);
        System.out.println("j = " + sc.j);
        System.out.println("k = " + sc.k);

        sc.method1();//静态方法只能被隐藏
        sc.method2();

        SubClass subc = new SubClass();
        System.out.println("i = " + subc.i);
        System.out.println("j = " + subc.j);
        System.out.println("k = " + subc.k);

        subc.method1();
        subc.method2();
    }
}
