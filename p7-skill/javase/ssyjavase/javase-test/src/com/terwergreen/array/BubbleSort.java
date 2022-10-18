package com.terwergreen.array;

/**
 * @name: BubbleSort
 * @author: terwer
 * @date: 2022-10-18 21:46
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = new int[]{3, 5, 6, 2, 4, 7};

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        bubbleSort(a);

        System.out.println();
        System.out.println("-------------------");

        for (int j = 0; j < a.length; j++) {
            System.out.print(a[j] + " ");
        }
    }

    public static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            System.out.println("第" + (i) + "次比较");

            for (int j = 0; j < a.length - i - 1; j++) {
                System.out.println("第" + (j) + "个数与第" + (j + 1) + "个数比较");
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    System.out.println("交换大小");
                }
            }

            for (int k = 0; k < a.length; k++) {
                System.out.print(a[k] + " ");
            }
            System.out.println();
            System.out.println("====================");
        }
    }
}
