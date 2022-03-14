package com.terwergreen.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.terwergreen.io.Resources;
import com.terwergreen.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

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
