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
public class CacheTest {
    private IUserMapper userMapper;
    private SqlSession sqlSession;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws Exception {
        System.out.println("before...");
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession();
        // 这样也是可以的，这样的话后面就不用每次都设置了
        // sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Test
    public void testCache() {
        User user = userMapper.findUserById(1);
        System.out.println(user);

        User user2 = userMapper.findUserById(1);
        System.out.println(user2);
    }

    @Test
    public void firstLevelCache() {
        // 第一次查询
        User user = userMapper.findUserById(1);
        System.out.println(user);

        // 更新操作
        user.setUsername("tyw");
        userMapper.update(user);
        // sqlSession.commit();
        // sqlSession.clearCache();

        // 第二次查询
        User user2 = userMapper.findUserById(1);
        System.out.println(user2);
    }

    @Test
    public void secondLevelCache() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        IUserMapper userMapper1 = sqlSession1.getMapper(IUserMapper.class);
        IUserMapper userMapper2 = sqlSession2.getMapper(IUserMapper.class);
        IUserMapper userMapper3 = sqlSession3.getMapper(IUserMapper.class);

        User user1 = userMapper1.findUserById(1);
        // 清空一级缓存
        sqlSession1.close();
        User user2 = userMapper2.findUserById(1);
        System.out.println(user1 == user2);

        // User user3 = userMapper3.findUserById(1);

    }
}
