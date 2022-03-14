package com.terwergreen.sqlsession;

/**
 * @name: SqlSessionFactory
 * @author: terwer
 * @date: 2022-03-14 15:20
 **/
public interface SqlSessionFactory {
    public SqlSession openSession();
}
