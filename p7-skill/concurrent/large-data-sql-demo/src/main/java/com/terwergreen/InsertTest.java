package com.terwergreen;

import com.terwergreen.pojo.User;
import com.terwergreen.util.ConnectionFactory;
import com.terwergreen.util.ConnectionUtils;
import com.terwergreen.util.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 插入数据
 *
 * @name: InsertTest
 * @author: terwer
 * @date: 2022-03-18 18:44
 **/
public class InsertTest {
    private static final Integer MAX_COUNT = 1000_0;

    public static void main(String[] args) throws Exception {
        float totalTime = 0F;
        for (int i = 0; i < MAX_COUNT; i++) {
            long startTime = System.currentTimeMillis();

            User user = new User();
            Integer index = i + 1;
            user.setId(index);
            user.setUsername("tyw" + index);
            user.setPassword("123456");
            user.setAge(20);
            user.setSex(0);

            boolean isClose = false;
            if (i == MAX_COUNT - 1) {
                isClose = true;
            }
            doInsert(user, isClose);

            long endTime = System.currentTimeMillis();
            float currentTime = (endTime - startTime) / 1000F;
            totalTime += currentTime;
            System.out.println("第" + index + "次执行");
            System.out.println("本次耗时：" + Float.toString(currentTime) + "秒");
            System.out.println("总耗时：" + Float.toString(totalTime) + "秒");
            System.out.println("==============================");
            System.out.println();
        }
    }

    private static void doInsert(User user, boolean isClose) throws Exception {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TransactionManager transactionManager = null;

        try {
            ConnectionUtils connectionUtils = ConnectionFactory.getInstance();

            transactionManager = TransactionManager.getInstance();
            transactionManager.setConnectionUtils(connectionUtils);

            // 开启事务(关闭事务的自动提交)
            transactionManager.beginTransaction();

            //从连接池获取连接
            con = connectionUtils.getCurrentThreadConn();
            String sql = "update user set username='tyw' where id=1";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.executeUpdate();

            // 提交事务
            transactionManager.commit();
            System.out.println("数据已插入" + user);
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            if (transactionManager != null) {
                transactionManager.rollback();
            }
        } finally {
            if (isClose) {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }
    }
}
