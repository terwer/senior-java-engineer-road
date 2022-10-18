package com.terwergreen.array;

/**
 * @name: ArrayTest5
 * @author: terwer
 * @date: 2022-10-18 01:38
 **/
public class ArrayTest5 {
    public static void main(String[] args) {
        /*
        int[][] a = new int[3][];
        a[0] = new int[2];
        a[1] = new int[3];
        a[2] = new int[1];
        */

        // int[][] a = new int[][3];

        int[][] a = new int[][]{{1, 2, 3}, {4, 5}};

        for (int[] ints : a) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
