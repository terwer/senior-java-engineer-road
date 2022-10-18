package com.terwergreen.array;

/**
 * @name: ArrayTest6
 * @author: terwer
 * @date: 2022-10-18 20:56
 **/
public class ArrayTest6 {
    public static void main(String[] args) {
        I[] i = new I[2];
        i[0] = new C();
        i[1] = new C();

        for (int i1 = 0; i1 < i.length; i1++) {
            System.out.println(i[i1]);
        }

        I[] b = new I[]{new C(), new C()};
    }
}

interface I {

}

class C implements I {

}