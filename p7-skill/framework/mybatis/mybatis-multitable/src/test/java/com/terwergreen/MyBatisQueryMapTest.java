package com.terwergreen;

import com.terwergreen.mapper.OrderMapper;
import com.terwergreen.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * MyBatis查询映射测试
 *
 * @name: MyBatisQueryMapTest
 * @author: terwer
 * @date: 2022-03-17 17:10
 **/
public class MyBatisQueryMapTest {
    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper userMapper = sqlSession.getMapper(OrderMapper.class);

        List<Order> orderList = userMapper.findOrderAndUser();
        for (Order order : orderList) {
            System.out.println(order);
        }
    }
}
