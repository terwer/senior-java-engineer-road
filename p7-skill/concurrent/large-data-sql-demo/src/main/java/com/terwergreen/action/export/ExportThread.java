package com.terwergreen.action.export;

import com.terwergreen.action.insert.InsertAction;
import com.terwergreen.pojo.User;
import com.terwergreen.util.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 导出数据线程
 *
 * @name: ExportThread
 * @author: terwer
 * @date: 2022-03-19 17:45
 **/
public class ExportThread implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static int subThreadNum = 0;

    private final Integer startIndex;
    private final Integer pageSize;

    public ExportThread(Integer startIndex, Integer pageSize) {
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    @Override
    public void run() {
        try {
            doWork();

            subThreadNum++;
            InsertAction insertAction = SpringBeanUtil.getBean(InsertAction.class);
            long startTime = insertAction.getStartTime();
            long endTime = System.currentTimeMillis();
            logger.info("子线程" + subThreadNum + "结束：" + endTime + "，耗时：" + calcTimeSeconds(endTime, startTime) + "秒=>" + calcTime(endTime, startTime) + "分钟");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public float calcTimeSeconds(long endTime, long startTime) {
        return (endTime - startTime) / 1000F;
    }

    public float calcTime(long endTime, long startTime) {
        return (calcTimeSeconds(endTime, startTime)) / 60F;
    }

    private void doWork() throws Exception {
        queryData();
    }

    public void queryData() throws Exception {
        JdbcTemplate jdbcTemplate = SpringBeanUtil.getBean(JdbcTemplate.class);

        try {
            String selectSql = "select * from user limit ?,?";
            List<User> userList = jdbcTemplate.query(selectSql, new UserRowMapper(), new Object[]{startIndex, pageSize});

            for (User user : userList) {
                String insertSql = String.format("insert into sharding_user(id,username,password,age,sex) values(%s,%s,%s,%s,%s)",
                        user.getId(), user.getUsername(), user.getPassword(), user.getAge(), user.getSex());
                logger.info(insertSql);
            }
        } catch (Exception e) {
            logger.info("数据异常");
            e.printStackTrace();
        }
    }
}
