package com.terwergreen.sqlsession;

import com.terwergreen.pojo.Configuration;
import com.terwergreen.pojo.MappedStatement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
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

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理为Dao接口生成代理对象
        return (T) Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 底层还是执行JDBC
                // 准备参数1
                // statemendid=namespace.id，获取不到
                // statemendid=接口全限定名.方法名
                String clazzName = method.getDeclaringClass().getName();
                String methodName = method.getName();
                String statementId = clazzName + "." + methodName;

                // 准备参数2：params=args

                // 获取被调用方法的返回类型
                Type genericReturnType = method.getGenericReturnType();
                // 判断是否进行了泛型类型参数化
                if (genericReturnType instanceof ParameterizedType) {
                    return selectList(statementId, args);
                }

                return selectOne(statementId, args);
            }
        });
    }
}
