package com.terwergreen.export;

import com.terwergreen.util.ConnectionFactory;
import com.terwergreen.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 数据导出
 *
 * @name: ExportTest
 * @author: terwer
 * @date: 2022-03-18 18:44
 **/
public class ExportTest {
    public static void main(String[] args) throws Exception {
        doExport();
    }

    private static void doExport() throws Exception {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            ConnectionUtils connectionUtils = ConnectionFactory.getInstance();

            //从连接池获取连接
            con = connectionUtils.getCurrentThreadConn();
            String sql = "select * from user";
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
