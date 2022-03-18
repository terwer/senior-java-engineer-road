package com.terwergreen.insert.multi;

import com.terwergreen.pojo.User;
import com.terwergreen.util.ConnectionFactory;
import com.terwergreen.util.ConnectionUtils;
import com.terwergreen.util.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 插入数据操作线程
 *
 * @name: InsertThread
 * @author: terwer
 * @date: 2022-03-18 23:23
 **/
public class InsertThread implements Runnable {
    private final Integer startIndex;
    private final Integer endIndex;
    private static int num = 0;

    public InsertThread(Integer startIndex, Integer endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        try {
            System.out.println("doWork开始");
            doWork(startIndex, endIndex);
        } catch (Throwable e) {
            System.out.println("doWork出错");
            e.printStackTrace();
        }
    }

    private static void doWork(Integer startIndex, Integer endIndex) throws Exception {
        for (int i = startIndex; i < endIndex; i++) {
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
        }

        num++;
        long endTime = System.currentTimeMillis();
        System.out.println("子线程" + num + "结束：" + endTime);
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
