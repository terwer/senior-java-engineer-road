package com.terwergreen.array;

import java.util.Random;

/**
 * @name: WorkTest
 * @author: terwer
 * @date: 2022-10-19 00:10
 **/
public class WorkTest2 {
    public static void main(String[] args) {
        int[] count = new int[41];

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int number = 10 + random.nextInt(50 - 10 + 1);
            System.out.println(number);
            count[number - 10]++;
        }


        for (int j = 0; j < count.length; j++) {
            if (count[j] == 0) {
                continue;
            }

            System.out.println((10 + j) + "出现的次数：" + count[j]);
        }

        int max = count[0];
        int maxNum = 10;
        for (int k = 0; k < count.length; k++) {
            if (max < count[k]) {
                max = count[k];
            }

            if (max == count[k]) {
                maxNum = k + 10;
                System.out.println(maxNum);
            }
        }
        System.out.println("最大的数字出现的次数：" + max);
    }
}
