package com.terwergreen.action.insert;

import com.terwergreen.pojo.User;
import com.terwergreen.util.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * 插入数据操作线程
 *
 * @name: InsertThread
 * @author: terwer
 * @date: 2022-03-18 23:23
 **/
public class InsertThread implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Integer startIndex;
    private final Integer endIndex;
    private static int subThreadNum = 0;

    public InsertThread(Integer startIndex, Integer endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        try {
            // logger.info("doWork开始");
            doWork(startIndex, endIndex);

            subThreadNum++;
            InsertAction insertAction = SpringBeanUtil.getBean(InsertAction.class);
            long startTime = insertAction.getStartTime();
            long endTime = System.currentTimeMillis();
            logger.info("子线程" + subThreadNum + "结束：" + endTime + "，耗时：" + calcTimeSeconds(endTime, startTime) + "秒=>" + calcTime(endTime, startTime) + "分钟");
        } catch (Throwable e) {
            logger.info("doWork出错");
            e.printStackTrace();
        }
    }

    private void doWork(Integer startIndex, Integer endIndex) throws Exception {
        for (int i = startIndex; i < endIndex; i++) {
            User user = new User();
            Integer index = i + 1;
            user.setId(index);
            user.setUsername("tyw" + index);
            user.setPassword("123456");
            user.setAge(20);
            user.setSex(0);

            doInsert(user);
        }
    }

    public float calcTimeSeconds(long endTime, long startTime) {
        return (endTime - startTime) / 1000F;
    }

    public float calcTime(long endTime, long startTime) {
        return (calcTimeSeconds(endTime, startTime)) / 60F;
    }

    @Transactional
    public void doInsert(User user) throws Exception {
        JdbcTemplate jdbcTemplate = SpringBeanUtil.getBean(JdbcTemplate.class);

        try {
            String sql = "insert into user(id,username,password,age,sex) values(?,?,?,?,?)";
            jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getAge(), user.getSex());
            // logger.info("数据已插入" + user);
        } catch (Exception e) {
            logger.info("数据插入异常" + user);
            e.printStackTrace();
        }
    }
}
