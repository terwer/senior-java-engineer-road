package com.terwergreen

/**
 * MainKt
 *
 * @name: MainKt
 * @author: terwer
 * @date: 2022-05-08 14:35
 **/
import java.sql.Connection
import java.sql.DriverManager

object MainKt {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        var connection: Connection? = null
        try {
            // 1、加载驱动
            // The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
            // 备注：mysql8.0的driver不需要在注册了
            // Class.forName("com.mysql.jdbc.Driver");

            // 2、获取链接
            val url = "jdbc:mysql://localhost:3306/jdbc_simple?charcterEncoding=utf-8&useSSL=false"
            connection = DriverManager.getConnection(url, "root", "123456")

            // 3、执行sql，包括设置参数
            val sql = "select * from user where name=?"
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, "tyw")

            // 4、处理结果集
            val resultSet = preparedStatement.executeQuery()
            while (resultSet.next()) {

                val user = UserKt()
                val id = resultSet.getInt("id")
                val name = resultSet.getString("name")
                user.id = id
                user.name = name
                println("user = $user")

                val user2 = UserKt()
                val id2 = resultSet.getInt(1)
                val name2 = resultSet.getString(2)
                user2.id = id
                user2.name = name2
                println("user2 = $user2")

                // JDBC问题分析
                // 1、数据库链接信息等存在硬编码  解决：配置文件
                // 2、频繁创建释放数据库链接 解决：连接池(c3p0、druid)

                // 查询过程问题分析
                // 1、sql语句、参数、结果集存在硬编码 解决：配置文件

                // 结果集问题分析
                // 1、需要手动封装结果集 解决：反射进行对象映射、内省
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection!!.close()
        }
    }
}