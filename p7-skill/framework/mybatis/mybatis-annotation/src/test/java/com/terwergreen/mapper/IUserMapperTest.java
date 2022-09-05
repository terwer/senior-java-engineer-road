package com.terwergreen.mapper;

import com.terwergreen.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class IUserMapperTest {

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
    public void testFindAll() {
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    @Test
    public void add() throws IOException {
        User user = new User();
        user.setUsername("测试3");
        userMapper.add(user);

        // 这里一定要加，否则不会提交事务
        sqlSession.commit(true);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(3);
        user.setUsername("测试11");
        userMapper.update(user);

        // 这里一定要加，否则不会提交事务
        sqlSession.commit(true);
    }

    @Test
    public void delete() {
        userMapper.delete(3);

        // 这里一定要加，否则不会提交事务
        sqlSession.commit(true);
    }

    @Test
    public void testGetUserOrders() {
        List<User> userAndOrder = userMapper.findUserAndOrder();
        userAndOrder.forEach(user -> {
            System.out.println(user);
        });
    }
}