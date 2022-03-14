package com.terwergreen.sqlsession;

import com.terwergreen.config.BoundSql;
import com.terwergreen.pojo.Configuration;
import com.terwergreen.pojo.MappedStatement;
import com.terwergreen.utils.GenericTokenParser;
import com.terwergreen.utils.ParameterMapping;
import com.terwergreen.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行器的实现
 *
 * @name: SimpleExecutor
 * @author: terwer
 * @date: 2022-03-14 16:53
 **/
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 注册驱动，获取链接
        Connection connection = configuration.getDataSource().getConnection();

        // 获取sql语句
        // 获取的sql
        // select * from user where id = #{id} and username = #{username}
        // 转换后的sql
        // select * from user where id = ? and username = ?
        String sql = mappedStatement.getSql();

        // 转换sql语句
        BoundSql boundSql = getBoundSql(sql);

        // 获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        // 设置参数
        // 参数全路径
        String parameterType = mappedStatement.getParameterType();
        Class<?> parameterClass = getClassType(parameterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            Field field = parameterClass.getDeclaredField(content);
            field.setAccessible(true);
            Object value = field.get(params[0]);

            preparedStatement.setObject(i + 1, value);
        }

        // 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String returnType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(returnType);
        ArrayList<Object> objects = new ArrayList<>();

        // 封装返回结果集
        while (resultSet.next()) {
            Object o = resultTypeClass.newInstance();

            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                // 获取字段值
                Object value = resultSet.getObject(columnName);

                // 使用反射或者内省，根据数据库表和实体的对应关系完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            objects.add(o);
        }

        return (List<E>) objects;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            Class<?> aClass = Class.forName(parameterType);
            return aClass;
        }
        return null;
    }

    /**
     * 1、将#{}使用?代替
     * 2、解析出#{}的值进行存储
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        // 标记处理类，配合标记解析器完成对占位符的解析
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);

        // 解析后的sql
        String parseSql = genericTokenParser.parse(sql);
        // 解析的参数名称
        List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);

        return boundSql;
    }
}
