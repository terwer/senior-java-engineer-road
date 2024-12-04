package space.terwer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import space.terwer.mapper.UserMapper;
import space.terwer.pojo.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author terwer on 2024/12/4
 */
public class UserTest {
    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUsername("测试4");
            user.setId(4);
            // insert 接口
            // 保存一个实体，null值也会保存，不会使用数据库默认值
            userMapper.insert(user);
            sqlSession.commit();
        }
    }

    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectByPrimaryKey(1L).orElseThrow();
            System.out.println("test get user: " + user.getUsername());
        }
    }
}
