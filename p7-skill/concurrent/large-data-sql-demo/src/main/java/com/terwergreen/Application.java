package com.terwergreen;

import com.terwergreen.action.ExportTest;
import com.terwergreen.action.InsertTest;
import com.terwergreen.util.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 项目启动入口
 *
 * @name: Application
 * @author: terwer
 * @date: 2022-03-18 19:01
 **/
@SpringBootApplication
@ComponentScan
public class Application implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        // SpringBeanUtil.setApplicationContext(applicationContext);

    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("开始插入数据");
        InsertTest insertTest = SpringBeanUtil.getBean(InsertTest.class);
        insertTest.doInsert();

        logger.info("控制台已启动");
    }
}
