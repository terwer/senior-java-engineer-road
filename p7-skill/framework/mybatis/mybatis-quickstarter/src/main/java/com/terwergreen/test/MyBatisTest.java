package com.terwergreen.test;

import com.terwergreen.dao.IUserDao;
import com.terwergreen.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试类
 *
 * @name: MyBatisTest
 * @author: terwer
 * @date: 2022-03-15 12:17
 **/
public class MyBatisTest {
    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("user.findAll");
        for (User user : userList) {
            System.out.println(user);
        }

        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(8);
        user.setUsername("测试8");
        sqlSession.insert("user.saveUser", user);
        sqlSession.commit();

        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(8);
        user.setUsername("李四");
        sqlSession.update("user.updateUser", user);
        sqlSession.commit();

        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void test4() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("user.deleteUser", 6);
        sqlSession.commit();

        // 关闭资源
        sqlSession.close();
    }

    // ===============
    // Dao层代理开发方式
    @Test
    public void test5() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> userList = userDao.findAll();

        for (User user : userList) {
            System.out.println(user);
        }
    }


    /**
     * if测试
     *
     * @throws IOException
     */
    @Test
    public void test6() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user = new User();
        user.setId(2);
        List<User> userList = userDao.findByCondition(user);

        for (User user2 : userList) {
            System.out.println(user2);
        }
    }

    /**
     * where测试
     *
     * @throws IOException
     */
    @Test
    public void test7() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user = new User();
        user.setId(1);
        user.setUsername("tyw");
        List<User> userList = userDao.findByConditionWhere(user);

        for (User user2 : userList) {
            System.out.println(user2);
        }
    }

    /**
     * foreach测试
     *
     * @throws IOException
     */
    @Test
    public void test8() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        Integer[] ids = new Integer[]{1, 2, 4};
        List<User> userList = userDao.findByIds(ids);

        for (User user2 : userList) {
            System.out.println(user2);
        }
    }

    /**
     * sql抽取测试
     *
     * @throws IOException
     */
    @Test
    public void test9() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user2 = userDao.findById(1);

        System.out.println(user2);
    }
}
