package com.terwergreen.mapper;

import com.terwergreen.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * MyBatis缓存测试
 *
 * @name: CacheTest
 * @author: terwer
 * @date: 2022-09-12 22:31
 **/
public class SecondCacheTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void secondLevelCache() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        IUserMapper userMapper1 = sqlSession1.getMapper(IUserMapper.class);
        IUserMapper userMapper2 = sqlSession2.getMapper(IUserMapper.class);

        User user1 = userMapper1.findUserById(1);
        // 清空一级缓存
        sqlSession1.close();
        User user2 = userMapper2.findUserById(1);
        System.out.println(user1 == user2);
    }
}
