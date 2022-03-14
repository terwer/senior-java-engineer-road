package com.terwergreen.config;

import com.terwergreen.pojo.Configuration;
import com.terwergreen.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
