package com.terwergreen.sqlsession;

import java.util.List;

/**
 * @name: SqlSession
 * @author: terwer
 * @date: 2022-03-14 16:35
 **/
public interface SqlSession {
    /**
     * 查询所有
     */
    public <E> List<E> selectList(String statementId, Object... params) throws Exception;

    /**
     * 查询单个
     */
    public <T> T selectOne(String statementId, Object... params) throws Exception;
}
