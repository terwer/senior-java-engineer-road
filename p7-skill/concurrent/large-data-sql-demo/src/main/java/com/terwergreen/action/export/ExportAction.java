package com.terwergreen.action.export;

import com.terwergreen.action.insert.InsertThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 数据导出
 *
 * @name: ExportTest
 * @author: terwer
 * @date: 2022-03-18 18:44
 **/
@Service
public class ExportAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private long startTime = 0L;

    public long getStartTime() {
        return startTime;
    }

    public void doAction() {
        startTime = System.currentTimeMillis();
        logger.info("主线程开始：" + startTime);

        Integer times = ExportConstant.MAX_COUNT / ExportConstant.MAX_THREAD_COUNT;
        logger.info("每个线程处理的记录数：" + times);

        ExecutorService executorService = Executors.newFixedThreadPool(ExportConstant.MAX_THREAD_COUNT);

        for (Integer i = 0; i < ExportConstant.MAX_THREAD_COUNT; i++) {
            Integer startIndex = i * times;
            Integer endIndex = (i + 1) * times;

            executorService.execute(new ExportThread(startIndex, times));
        }

        // 有多出来的
        if (ExportConstant.MAX_COUNT % ExportConstant.MAX_THREAD_COUNT > 0) {
            logger.info("有多出来的");
            Integer newStartIndex = ExportConstant.MAX_THREAD_COUNT * times;
            Integer newEndIndex = ExportConstant.MAX_COUNT;

            executorService.execute(new InsertThread(newStartIndex, times));
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
