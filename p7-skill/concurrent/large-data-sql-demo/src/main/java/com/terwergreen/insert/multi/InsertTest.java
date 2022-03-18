package com.terwergreen.insert.multi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 插入数据
 *
 * @name: InsertTest
 * @author: terwer
 * @date: 2022-03-18 18:44
 **/
public class InsertTest {
    public static void main(String[] args) {
        try {
            testMulti();
        } catch (Throwable e) {
            System.out.println("testMulti出错");
            e.printStackTrace();
        }
    }

    public static void testMulti() throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("主线程结束开始：" + startTime);

        Integer times = InsertConstant.MAX_COUNT / InsertConstant.MAX_THREAD_COUNT;
        // System.out.println("每个线程处理的记录数：" + times);

        ExecutorService executorService = Executors.newFixedThreadPool(InsertConstant.MAX_THREAD_COUNT*2);

        for (Integer i = 0; i < InsertConstant.MAX_THREAD_COUNT; i++) {
            Integer startIndex = i * times;
            Integer endIndex = (i + 1) * times;
            if (startIndex > 0) {
                startIndex += 1;
            }

            /*
            System.out.println("当前线程开始记录，startIndex=>" + startIndex);
            System.out.println("当前线程结束记录，endIndex=>" + endIndex);
            System.out.println("=======================");
            System.out.println();
            */
            executorService.execute(new InsertThread(startIndex, endIndex));
            System.out.println("线程" + (i + 1) + "创建完毕");
        }
        executorService.shutdown();

        long endTime = System.currentTimeMillis() - startTime;
        float currentTime = endTime / 1000F;
        System.out.println("主线程结束：" + endTime);
        System.out.println("主线程结束，耗时：" + currentTime + "秒");
    }
}
