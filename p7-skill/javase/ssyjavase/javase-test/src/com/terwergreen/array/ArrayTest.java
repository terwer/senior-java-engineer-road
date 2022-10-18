package com.terwergreen.array;

import java.util.Arrays;

/**
 * @name: ArrayTest
 * @author: terwer
 * @date: 2022-10-17 22:47
 **/
public class ArrayTest {
    public static void main(String[] args) {
        /*
        int[] a = new int[4];

        a[0] = 1;
        a[1] = 2;
        a[2] = 3;
        a[3] = 4;

        System.out.println(a[3]);

        System.out.println("----------------");
        */

        /*
        int a[] = new int[2];
        a[1] = 2;
        System.out.println(a[1]);
         */

        /*
        int[] a = {1, 2, 3, 4};
        System.out.println(a[2]);

        int[] b = new int[]{1, 2, 3, 4};
        System.out.println(b[3]);
        */

        /*
        int[] a = new int[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1;
        }

        for (int item : a) {
            System.out.println(item);
        }
        */

        /*
        int[] a = new int[4];
        System.out.println(a[0]);

        boolean[] b = new boolean[10];
        System.out.println(b[0]);
        // a.length = 200;
         */

        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};

        boolean isEqual = isEqual(a, b);

        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(Arrays.equals(a, b));

        System.out.println("---------------------");
        System.out.println(isEqual);
    }

    public static boolean isEqual(int[] a, int[] b) {
        boolean isEqual = true;

        if (a == null || b == null) {
            return false;
        }

        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                isEqual = false;
                break;
            }
        }
        return isEqual;
    }
}
