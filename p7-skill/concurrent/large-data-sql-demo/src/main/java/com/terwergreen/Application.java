package com.terwergreen;

import com.terwergreen.action.export.ExportAction;
import com.terwergreen.action.export.ExportConstant;
import com.terwergreen.action.insert.InsertAction;
import com.terwergreen.action.insert.InsertConstant;
import com.terwergreen.util.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动入口
 *
 * @name: Application
 * @author: terwer
 * @date: 2022-03-18 19:01
 **/
@SpringBootApplication
public class Application implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int numOfCores = Runtime.getRuntime().availableProcessors();
        logger.info("需要处理的数据总数：" + ExportConstant.MAX_COUNT);
        logger.info("当前处理器数目：" + numOfCores);
        logger.info("当前处理任务的线程数目：" + ExportConstant.MAX_THREAD_COUNT);

        logger.info("开始插入数据");
        // InsertAction insertAction = SpringBeanUtil.getBean(InsertAction.class);
        // insertAction.doAction();

        ExportAction exportAction = SpringBeanUtil.getBean(ExportAction.class);
        exportAction.doAction();
    }
}
