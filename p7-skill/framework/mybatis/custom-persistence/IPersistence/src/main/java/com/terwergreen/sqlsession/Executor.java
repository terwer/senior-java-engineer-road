package com.terwergreen.sqlsession;

import com.terwergreen.pojo.Configuration;
import com.terwergreen.pojo.MappedStatement;

import java.util.List;

/**
 * 执行器接口
 *
 * @name: Executor
 * @author: terwer
 * @date: 2022-03-14 16:51
 **/
public interface Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
