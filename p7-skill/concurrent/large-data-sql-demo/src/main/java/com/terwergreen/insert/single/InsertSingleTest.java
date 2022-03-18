package com.terwergreen.insert.single;

import com.terwergreen.insert.multi.InsertConstant;
import com.terwergreen.pojo.User;
import com.terwergreen.util.ConnectionFactory;
import com.terwergreen.util.ConnectionUtils;
import com.terwergreen.util.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @name: InsertSingleTest
 * @author: terwer
 * @date: 2022-03-18 23:49
 **/
public class InsertSingleTest {
    public static void main(String[] args) throws Exception {
        testSingle();
    }

    // 单个线程
    public static void testSingle() throws Exception {
        for (int i = 0; i < InsertConstant.MAX_COUNT; i++) {
            long startTime = System.currentTimeMillis();

            User user = new User();
            Integer index = i + 1;
            user.setId(index);
            user.setUsername("tyw" + index);
            user.setPassword("123456");
            user.setAge(20);
            user.setSex(0);

            boolean isClose = false;
            if (i == InsertConstant.MAX_COUNT - 1) {
                isClose = true;
            }
            doInsert(user, isClose);

            long endTime = System.currentTimeMillis();
            float currentTime = (endTime - startTime) / 1000F;
            System.out.println("第" + index + "次执行");
            System.out.println("本次耗时：" + Float.toString(currentTime) + "秒");
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
            String sql = "insert into user(id,username,password,age,sex) values(?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setInt(5, user.getSex());
            preparedStatement.executeUpdate();

            // 提交事务
            transactionManager.commit();
            // System.out.println("数据已插入" + user);
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            if (transactionManager != null) {
                System.out.println("数据插入异常" + user);
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
