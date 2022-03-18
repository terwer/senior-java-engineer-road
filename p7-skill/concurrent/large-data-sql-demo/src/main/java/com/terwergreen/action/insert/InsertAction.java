package com.terwergreen.action.insert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 插入数据
 *
 * @name: InsertTest
 * @author: terwer
 * @date: 2022-03-18 18:44
 **/
@Service
public class InsertAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private long startTime = 0L;

    public long getStartTime() {
        return startTime;
    }

    public void doAction() {
        startTime = System.currentTimeMillis();
        logger.info("主线程开始：" + startTime);

        Integer times = InsertConstant.MAX_COUNT / InsertConstant.MAX_THREAD_COUNT;
        logger.info("每个线程处理的记录数：" + times);

        ExecutorService executorService = Executors.newFixedThreadPool(InsertConstant.MAX_THREAD_COUNT);

        for (Integer i = 0; i < InsertConstant.MAX_THREAD_COUNT; i++) {
            Integer startIndex = i * times;
            Integer endIndex = (i + 1) * times;

            /*
            logger.info("当前线程开始记录，startIndex=>" + startIndex);
            logger.info("当前线程结束记录，endIndex=>" + endIndex);
            logger.info("=======================\n");
             */

            executorService.execute(new InsertThread(startIndex, endIndex));
            // logger.info("线程" + (i + 1) + "创建完毕");
        }

        // 有多出来的
        if (InsertConstant.MAX_COUNT % InsertConstant.MAX_THREAD_COUNT > 0) {
            logger.info("有多出来的");
            Integer newStartIndex = InsertConstant.MAX_THREAD_COUNT * times;
            Integer newEndIndex = InsertConstant.MAX_COUNT;
            /*
            logger.info("当前线程开始记录，newStartIndex=>" + newStartIndex);
            logger.info("当前线程结束记录，newEndIndex=>" + newEndIndex);
            logger.info("=======================\n");
             */
            executorService.execute(new InsertThread(newStartIndex, newEndIndex));
        }

        try {
            Thread.sleep(6000);
            logger.info("睡眠完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        // long endTime = System.currentTimeMillis() - startTime;
        // float currentTime = endTime / 1000F;
        // logger.info("主线程结束：" + endTime);
        // logger.info("主线程结束，耗时：" + currentTime + "秒");
    }
}
