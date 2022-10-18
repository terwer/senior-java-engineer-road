package com.terwergreen.array;

/**
 * @name: ArraySearchTest
 * @author: terwer
 * @date: 2022-10-18 22:28
 **/
public class ArraySearchTest {
    public static void main(String[] args) {
        // int[] a = new int[]{1, 5, 6, 7, 10, 3, 9};
        // int value = 9;

        //  int result = search(a, 9);

        int[] b = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int result = binarySearch(b, 9);

        if (result > 0) {
            System.out.println("找到了，索引为" + result);
        } else {
            System.out.println("未找到");
        }
    }

    public static int search(int[] array, int value) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int binarySearch(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        int middle;

        while (left <= right) {
            middle = (left + right) / 2;

            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k]);
                if (k == middle) {
                    System.out.print("#");
                }
                System.out.print(" ");
            }

            System.out.println();

            if (array[middle] == value) {
                return middle;
            }

            if (value > array[middle]) {
                left = middle + 1;
            }

            if (value < array[middle]) {
                right = middle - 1;
            }
        }

        return -1;
    }
}
