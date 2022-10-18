package com.terwergreen.array;

/**
 * @name: ArrayTest4
 * @author: terwer
 * @date: 2022-10-18 01:08
 **/
public class ArrayTest4 {
    public static void main(String[] args) {
        int[][] i = new int[2][3];

        System.out.println(i instanceof Object);
        System.out.println(i[0] instanceof Object);
        System.out.println(i[0] instanceof int[]);
        // System.out.println(i[0][0] instanceof Object);

        int idx = 0;
        for (int k = 0; k < 2; k++) {
            for (int j = 0; j < 3; j++) {
                i[k][j] = 2 * (idx + 1);
                idx++;
            }
        }

        for (int[] ints : i) {
            for (int anInt : ints) {
                System.out.println(anInt);
            }
        }
    }
}
