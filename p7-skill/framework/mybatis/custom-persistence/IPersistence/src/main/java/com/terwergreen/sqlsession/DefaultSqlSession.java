package com.terwergreen.sqlsession;

import com.terwergreen.pojo.Configuration;
import com.terwergreen.pojo.MappedStatement;

import java.util.List;

/**
 * @name: DefaultSqlSession
 * @author: terwer
 * @date: 2022-03-14 16:36
 **/
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return simpleExecutor.query(configuration, mappedStatement, params);
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询数据为空或者返回结果过多");
        }
    }
}
