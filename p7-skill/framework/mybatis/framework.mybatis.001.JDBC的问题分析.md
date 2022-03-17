# JDBC的问题分析
# 自定义RPC框架
> 2022/03/06 校对完成 <i class="el-icon-success"></i>
>
> 文章更新历史
>
> 2022/03/16 初稿。
>
> 原文地址：[http://www.terwergreen.com/post/jdbc-de-wen-ti-fen-xi.html](http://www.terwergreen.com/post/jdbc-de-wen-ti-fen-xi.html)
# 核心实现

```
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author terwer
 * @Description
 * @create 2021-11-30 23:18
 */
public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 1、加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2、获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8&useSSL=false", "root", "123456");

            // 3、定义sql语句
            String sql = "select * from user where username = ?";
            // 4、获取预处理对象
            preparedStatement = connection.prepareStatement(sql);
            // 5、设置参数
            preparedStatement.setString(1, "hp");
            // 6、拿到查询的数据库结果
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");

                User user = new User();
                user.setId(id);
                user.setUsername(username);

                System.out.println("user = " + user.toString());
            }

            // JDBC问题分析
            // 1、数据库链接信息等存在硬编码  解决：配置文件
            // 2、频繁创建释放数据库链接 解决：连接池(c3p0、druid)

            // 查询过程问题分析
            // 1、sql语句、参数、结果集存在硬编码 解决：配置文件

            // 结果集问题分析
            // 1、需要手动封装结果集 解决：反射进行对象映射、内省

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放资源");
        }
    }
}

```

1.1 问题分析

JDBC问题总结:

原始jdbc开发存在的问题如下:

1、 数据库连接创建、释放频繁造成系统资源浪费，从而影响系统性能。

2、 Sql语句在代码中硬编码，造成代码不易维护，实际应用中sql变化的可能较大，sql变动需要改变 java代码。

3、 使用preparedStatement向占有位符号传参数存在硬编码，因为sql语句的where条件不一定，可能 多也可能少，修改sql还要修改代码，系统不易维护。

4、 对结果集解析存在硬编码(查询列名)，sql变化导致解析代码变化，系统不易维护，如果能将数据库记录封装成 pojo对象解析比较方便

1.2 问题解决思路

1使用数据库连接池初始化连接资源

2将sql语句抽取到xml配置文件中

3使用反射、内省等底层技术，自动将实体与表进行属性与字段的自动映射

# mysql驱动链接

[https://files.cnblogs.com/files/tangyouwei/mysql-connector-java-5.1.49.jar.zip](https://files.cnblogs.com/files/tangyouwei/mysql-connector-java-5.1.49.jar.zip)

# 源码

Gitee版

[https://gitee.com/youweics/senior-java-engineer-road/blob/master/p7-skill/framework/mybatis/jdbc-simple/src/main/java/com/terwergreen/App.java](https://gitee.com/youweics/senior-java-engineer-road/blob/master/p7-skill/framework/mybatis/jdbc-simple/src/main/java/com/terwergreen/App.java)

Github版

[https://github.com/terwer/senior-java-engineer-road/blob/master/p7-skill/framework/mybatis/jdbc-simple/src/main/java/com/terwergreen/App.java](https://github.com/terwer/senior-java-engineer-road/blob/master/p7-skill/framework/mybatis/jdbc-simple/src/main/java/com/terwergreen/App.java)