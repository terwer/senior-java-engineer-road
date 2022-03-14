package com.terwergreen.test;

import com.terwergreen.io.Resources;
import com.terwergreen.pojo.User;
import com.terwergreen.sqlsession.SqlSession;
import com.terwergreen.sqlsession.SqlSessionFactory;
import com.terwergreen.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * 测试类
 *
 * @name: IPersistenceTest
 * @author: terwer
 * @date: 2022-03-14 13:01
 **/
public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(1);
        user.setUsername("tyw");
        User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);
    }
}
