package com.terwergreen.sqlsession;

import com.terwergreen.config.XmlConfigBuilder;
import com.terwergreen.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * 工厂构建对象
 *
 * @name: SqlSessionFactoryBuilder
 * @author: terwer
 * @date: 2022-03-14 15:18
 **/
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
        // 1、解析配置文件，将解析出来的内容封装到Configuration中
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(in);

        // 2、创建SqlSessionFactory对象
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }

}
