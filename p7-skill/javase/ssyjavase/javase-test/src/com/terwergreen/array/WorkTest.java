package com.terwergreen.array;

import java.util.Random;

/**
 * @name: WorkTest
 * @author: terwer
 * @date: 2022-10-19 00:10
 **/
public class WorkTest {
    public static void main(String[] args) {
        int[] nums = new int[50];

        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 10 + random.nextInt(50 - 10 + 1);
        }


        for (int j = 10; j <= 50; j++) {
            int count = 0;
            for (int m = 0; m < nums.length; m++) {
                if (nums[m] == j) {
                    count++;
                }
            }

            if (count > 0) {
                System.out.println(j + "出现的次数：" + count);
            }
        }


//        for (int k = 0; k < nums.length; k++) {
//            System.out.println(nums[k]);
//        }
    }
}
