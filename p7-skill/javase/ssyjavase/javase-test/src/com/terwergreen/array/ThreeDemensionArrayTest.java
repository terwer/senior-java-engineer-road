package com.terwergreen.array;

/**
 * @name: ThreeDemensionArrayTest
 * @author: terwer
 * @date: 2022-10-18 21:35
 **/
public class ThreeDemensionArrayTest {
    public static void main(String[] args) {
        int[][][] a = new int[2][3][4];

        System.out.println(a);
        System.out.println(a instanceof int[][][]);
        System.out.println(a instanceof Object);

        System.out.println(a[0] instanceof int[][]);
        System.out.println(a[0][0] instanceof int[]);

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                for (int k = 0; k < a[i][j].length; k++) {
                    a[i][j][k] = 100;
                }
            }
        }
    }
}
