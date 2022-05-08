package com.terwergreen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection connection = null;

        try {
            // 1、加载驱动
            // The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
            // 备注：mysql8.0的driver不需要在注册了
            Class.forName("com.mysql.jdbc.Driver");

            // 2、获取链接
            String url = "jdbc:mysql://localhost:3306/jdbc_simple?charcterEncoding=utf-8&useSSL=false";
            connection = DriverManager.getConnection(url, "root", "123456");

            // 3、执行sql，包括设置参数
            String sql = "select * from user where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "tyw");

            // 4、处理结果集
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                user.setId(id);
                user.setName(name);
                System.out.println("user = " + user);

                User user2 = new User();
                Integer id2 = resultSet.getInt(1);
                String name2 = resultSet.getString(2);
                user2.setId(id);
                user2.setName(name2);
                System.out.println("user2 = " + user2);

                // JDBC问题分析
                // 1、数据库链接信息等存在硬编码  解决：配置文件
                // 2、频繁创建释放数据库链接 解决：连接池(c3p0、druid)

                // 查询过程问题分析
                // 1、sql语句、参数、结果集存在硬编码 解决：配置文件

                // 结果集问题分析
                // 1、需要手动封装结果集 解决：反射进行对象映射、内省

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
