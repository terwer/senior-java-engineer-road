package com.terwergreen.mapper;

import com.terwergreen.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 订单测试
 *
 * @name: IOrderMapperTest
 * @author: terwer
 * @date: 2022-08-31 22:52
 **/
public class IOrderMapperTest {
    private IOrderMapper orderMapper;
    private SqlSession sqlSession;

    @Before
    public void before() throws Exception {
        System.out.println("before...");
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession();
        // 这样也是可以的，这样的话后面就不用每次都设置了
        // sqlSession = sqlSessionFactory.openSession(true);
        orderMapper = sqlSession.getMapper(IOrderMapper.class);
    }

    @Test
    public void testFindOrder() throws Exception {
        List<Order> orderAndUser = orderMapper.findOrderAndUser();

        orderAndUser.forEach(order -> {
            System.out.println(order);
        });
    }
}
