package com.terwergreen.sqlsession;

import com.terwergreen.pojo.Configuration;

/**
 * 默认工厂实现类
 *
 * @name: DefaultSqlSessionFactory
 * @author: terwer
 * @date: 2022-03-14 16:32
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
