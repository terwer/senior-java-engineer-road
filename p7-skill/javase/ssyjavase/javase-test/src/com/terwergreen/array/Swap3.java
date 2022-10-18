package com.terwergreen.array;

/**
 * @name: Swap3
 * @author: terwer
 * @date: 2022-10-18 20:50
 **/
public class Swap3 {
    public static void main(String[] args) {
        int[] i = {1, 2};
        swap(i);

        for (int j = 0; j < i.length; j++) {
            System.out.println(i[j]);
        }
    }

    public static void swap(int[] i) {
        int temp = i[0];
        i[0] = i[1];
        i[1] = temp;
    }
}
