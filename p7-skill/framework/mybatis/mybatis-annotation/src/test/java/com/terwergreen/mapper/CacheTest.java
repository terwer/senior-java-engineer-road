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

    @Before
    public void before() throws Exception {
        System.out.println("before...");
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession();
        // 这样也是可以的，这样的话后面就不用每次都设置了
        // sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Test
    public void testFindUserById() {
        User user = userMapper.findUserById(1);
        System.out.println(user);

        User user2 = userMapper.findUserById(1);
        System.out.println(user2);
    }

    @Test
    public void testFindUserById2() {
        // 第一次查询
        User user = userMapper.findUserById(1);
        System.out.println(user);

        // 更新操作
        user.setUsername("tyw");
        userMapper.update(user);

        // 第二次查询
        User user2 = userMapper.findUserById(1);
        System.out.println(user2);
    }
}
