# 自定义持久层框架
> 2022/03/14 校对完成 <i class="el-icon-success"></i>
>
> 文章更新历史
>
> 2022/03/14 初稿。
>
> 原文地址：[http://www.terwergreen.com/post/zi-ding-yi-chi-jiu-ceng-kuang-jia.html](http://www.terwergreen.com/post/zi-ding-yi-chi-jiu-ceng-kuang-jia.html)
# 核心要点

1、解析配置文件

1.1 数据库配置信息

1.2 sql的封装

2、构建SqlSessionFactory，注意这里的openSession方法

3、拿到SqlSesion

3.1 定义SqlSession基本方法

3.2 封装具体的执行逻辑，Executor

Execute的query方法就对应jdbc操作

3.3 优化，使用JDK动态代理避免statementId的硬编码

4、将SqlSession的操作封装到DAO层

# 自定义持久层框架设计思路

## 使用端（项目）

- 引入自定义持久层框架的jar包

- 提供两部分配置信息

    - 数据库配置信息

    - sql配置信息：sql语句、参数类型、返回值类型

- 使用配置文件来提供这两部分配置信息：

（1）`sqlMapConfig.xml` ：存放数据库配置信息，存放 `mapper.xml` 的全路径

（2）`mapper.xml`  ：存放sql配置信息

## 自定义持久层框架本身（工程）

本质是对JDBC代码进行封装

- 加载配置文件

  根据配置文件的路径记载成字节输入流，存储到内存中

  创建Resources类

  方法：InputStream getResourceAsStream(String path)

- 创建两个JavaBean（容器对象）：存放的是配置文件解析出来的内容

  Configuration：核心配置类，存放 `sqlMapConfig.xml` 解析出来的内容

  MappedStatement：映射配置类，存放 `mapper.xml` 解析出来的内容

- 解析配置文件：dom4j

  创建类：sqlSessionFactoryBuilder类，方法 build(InputStream in)

  1、使用dom4j解析配置文件，将解析出来的内容封装到威器对象中

  2、创建SqlSessionFactory对象，生产SqlSession（会话对象），工厂模式

- 创建SqlSessionFactory以及实现类DefaultSqlSessionFactory

  openSession()：生产SqlSession

    - 创建SqlSession接口及实现类DefaultSqlSession

      定义对数据库的CRUD操作：selectList()

      ​												selectOne()

      ​												update()

      ​												delete()

- 创建Executor实现类以及实现类SimpleExecutor

  query(Configuration configuration, MappedStatement mappedStatement, Object... params)：执行JDBC代码

# 代码实现

### 项目结构

```
.
├── IPersistence
│   ├── IPersistence.iml
│   ├── pom.xml
│   └── src
└── IPersistence_test
    ├── IPersistence_test.iml
    ├── pom.xml
    ├── src
    └── target
```

- sqlMapperConfig.xml配置文件

```xml
<configuration>
    <!-- 存放数据库配置信息 -->
    <dataSource>
        <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/zdy_mybatis"></property>
        <property name="username" value="root"></property>
        <property name="password" value="123456"></property>
    </dataSource>

    <!-- 存放mapper.xml全路径 -->
    <mapper resource="UserMapper.xml" />
</configuration>
```

- UserMapper.xml配置文件

```xml
<mapper namespace="user">
    <!-- sql的唯一标识：namespace.id组合：statementId -->
    <select id="selectList" resultType="com.terwergreen.pojo.User">
        select * from user
    </select>
    <select id="selectOne" resultType="com.terwergreen.pojo.User" parameterType="com.terwergreen.pojo.User">
        select * from user where id = #{id} and username = #{username}
    </select>
</mapper>
```

- 读取资源处理，Resources类

```java
/**
 * 资源处理类
 *
 * @name: Resource
 * @author: terwer
 * @date: 2022-03-14 12:57
 **/
public class Resources {
    /**
     * 根据配置文件的路径，将配置文件加载成字节输入流，存储到内存中
     *
     * @param path
     * @return
     */
    public static InputStream getResourceAsStream(String path) {
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return inputStream;
    }
}
```

- SqlSessionFactoryBuider工厂构建对象

```java
/**
 * 工厂构建对象
 *
 * @name: SqlSessionFactoryBuilder
 * @author: terwer
 * @date: 2022-03-14 15:18
 **/
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
        // 1、解析配置文件，将解析出来的内容封装到Configuration中
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(in);

        // 2、创建SqlSessionFactory对象
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }

}
```

- 配置文件解析

```java
/**
 * @name: XmlConfigBuilder
 * @author: terwer
 * @date: 2022-03-14 15:40
 **/
public class XmlConfigBuilder {

    private Configuration configuration;

    public XmlConfigBuilder() {
        configuration = new Configuration();
    }

    /**
     * 使用dom4j将配置文件进行解析，封装Configuration
     *
     * @param in
     * @return
     */
    public Configuration parse(InputStream in) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(in);
        // <confiruration>
        Element rootElement = document.getRootElement();

        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");

            properties.setProperty(name, value);
        }

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        configuration.setDataSource(comboPooledDataSource);

        // mapper.xml解析，拿到路径，加载成字节输入流，进行解析
        List<Element> mapperList= rootElement.selectNodes("//mapper");
        // <mapper>
        for (Element element : mapperList) {
            String mapperPath = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);

            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }

        return configuration;
    }
}
```

- mapper映射文件解析

```java
/**
 * mapper解析器
 *
 * @name: XmlMapperBuilder
 * @author: terwer
 * @date: 2022-03-14 16:16
 **/
public class XmlMapperBuilder {
    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream in) throws DocumentException {
        Document document = new SAXReader().read(in);

        // <mapper>
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");

        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");

            String sqlText = element.getTextTrim();

            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setStatementId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sqlText);

            Map<String, MappedStatement> mappedStatementMap = configuration.getMappedStatementMap();
            String statementId = namespace + "." + id;
            mappedStatementMap.put(statementId, mappedStatement);
        }
    }
}
```

- SqlSession的具体实现

```java
/**
 * @name: DefaultSqlSession
 * @author: terwer
 * @date: 2022-03-14 16:36
 **/
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return simpleExecutor.query(configuration, mappedStatement, params);
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询数据为空或者返回结果过多");
        }
    }
}
```

- 核心执行引擎Executor的实现

```java
/**
 * 执行器的实现
 *
 * @name: SimpleExecutor
 * @author: terwer
 * @date: 2022-03-14 16:53
 **/
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 注册驱动，获取链接
        Connection connection = configuration.getDataSource().getConnection();

        // 获取sql语句
        // 获取的sql
        // select * from user where id = #{id} and username = #{username}
        // 转换后的sql
        // select * from user where id = ? and username = ?
        String sql = mappedStatement.getSql();

        // 转换sql语句
        BoundSql boundSql = getBoundSql(sql);

        // 获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        // 设置参数
        // 参数全路径
        String parameterType = mappedStatement.getParameterType();
        Class<?> parameterClass = getClassType(parameterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            Field field = parameterClass.getDeclaredField(content);
            field.setAccessible(true);
            Object value = field.get(params[0]);

            preparedStatement.setObject(i + 1, value);
        }

        // 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String returnType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(returnType);
        Object o = resultTypeClass.newInstance();
        ArrayList<Object> objects = new ArrayList<>();

        // 封装返回结果集
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                // 获取字段值
                Object value = resultSet.getObject(columnName);

                // 使用反射或者内省，根据数据库表和实体的对应关系完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            objects.add(o);
        }

        return (List<E>) objects;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            Class<?> aClass = Class.forName(parameterType);
            return aClass;
        }
        return null;
    }

    /**
     * 1、将#{}使用?代替
     * 2、解析出#{}的值进行存储
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        // 标记处理类，配合标记解析器完成对占位符的解析
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);

        // 解析后的sql
        String parseSql = genericTokenParser.parse(sql);
        // 解析的参数名称
        List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);

        return boundSql;
    }
}
```

运行效果

<img src="https://gitee.com/youweics/upload/raw/main/img/20220314184626.png" alt="image-20220314182811682" style="zoom:50%;" />

### 问题修复

1、selectList打印的全部是同一个值

```
/Library/Java/JavaVirtualMachines/jdk1.8.0_291.jdk/Contents/Home/bin/java -... com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 com.terwergreen.test.IPersistenceTest,test2
Connected to the target VM, address: '127.0.0.1:50317', transport: 'socket'
log4j:WARN No appenders could be found for logger (com.mchange.v2.log.MLog).
log4j:WARN Please initialize the log4j system properly.
User{id=5, username='dali'}
User{id=5, username='dali'}
User{id=5, username='dali'}
User{id=5, username='dali'}
Disconnected from the target VM, address: '127.0.0.1:50317', transport: 'socket'

Process finished with exit code 0

```

修正方案

<img src="https://gitee.com/youweics/upload/raw/main/img/20220314190534.png" alt="image-20220314190526971" style="zoom:50%;" />

修正后

```
/Library/Java/JavaVirtualMachines/jdk1.8.0_291.jdk/Contents/Home/bin/java -... com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 com.terwergreen.test.IPersistenceTest,test2
Connected to the target VM, address: '127.0.0.1:50820', transport: 'socket'
log4j:WARN No appenders could be found for logger (com.mchange.v2.log.MLog).
log4j:WARN Please initialize the log4j system properly.
User{id=1, username='tyw'}
User{id=2, username='张月'}
User{id=4, username='haha'}
User{id=5, username='dali'}
Disconnected from the target VM, address: '127.0.0.1:50820', transport: 'socket'

Process finished with exit code 0
```

### 代码仓库

Gitee版

https://gitee.com/youweics/senior-java-engineer-road/tree/master/p7-skill/framework/mybatis/custom-persistence

Github版

https://github.com/terwer/senior-java-engineer-road/tree/master/p7-skill/framework/mybatis/custom-persistence

# 自定义持久层框架的优化

问题分析

1、Dao层使用持久层框架，存在代码重复，整个操作过程模板重复（加载配置文件、创建SqlSessionFactory、生产SqlSession）

2、存在硬编码（statementId）

解决思路

### getMapper+动态代理方式优化

使用代理模式生成Dao层接口的实现类

<img src="https://gitee.com/youweics/upload/raw/main/img/20220314210022.png" alt="image-20220314210007908" style="zoom:50%;" />

<img src="https://gitee.com/youweics/upload/raw/main/img/20220314212430.png" alt="image-20220314212415390" style="zoom:50%;" />

### 优化版代码

[https://gitee.com/youweics/senior-java-engineer-road/tree/mybatis-proxy/p7-skill/framework/mybatis/custom-persistence](https://gitee.com/youweics/senior-java-engineer-road/tree/mybatis-proxy/p7-skill/framework/mybatis/custom-persistence)

[https://github.com/terwer/senior-java-engineer-road/tree/mybatis-proxy/p7-skill/framework/mybatis/custom-persistence](https://github.com/terwer/senior-java-engineer-road/tree/mybatis-proxy/p7-skill/framework/mybatis/custom-persistence)

### 默认实现方式

[https://gitee.com/youweics/senior-java-engineer-road/tree/mybatis-normal/p7-skill/framework/mybatis/custom-persistence](https://gitee.com/youweics/senior-java-engineer-road/tree/mybatis-normal/p7-skill/framework/mybatis/custom-persistence)

[https://github.com/terwer/senior-java-engineer-road/tree/mybatis-normal/p7-skill/framework/mybatis/custom-persistence](https://github.com/terwer/senior-java-engineer-road/tree/mybatis-normal/p7-skill/framework/mybatis/custom-persistence)

