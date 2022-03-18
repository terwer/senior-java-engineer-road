package com.terwergreen;

import com.terwergreen.action.insert.InsertAction;
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
        logger.info("开始插入数据");
        InsertAction insertAction = SpringBeanUtil.getBean(InsertAction.class);
        insertAction.doAction();
        // float time = insertAction.calcTime(1647624867640L, 1647624387757L);
        // System.out.println("耗时：" + time + "分钟");

        // logger.info("控制台已启动");
    }
}
