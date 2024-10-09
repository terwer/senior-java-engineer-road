import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import space.terwer.mapper.IUserMapper;
import space.terwer.pojo.User;

import java.io.InputStream;
import java.util.List;

/**
 * @author terwer on 2024/10/9
 */
public class MainTest {
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
    public void testPageHelper() {
        //设置分⻚参数
        PageHelper.startPage(1, 2);
        List<User> select = userMapper.findAll();
        for (User user : select) {
            System.out.println(user);
        }
    }
}
