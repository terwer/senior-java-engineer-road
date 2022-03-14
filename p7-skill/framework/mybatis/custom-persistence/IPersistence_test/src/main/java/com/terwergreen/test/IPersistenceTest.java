package com.terwergreen.test;

import com.terwergreen.io.Resources;

import java.io.InputStream;

/**
 * 测试类
 *
 * @name: IPersistenceTest
 * @author: terwer
 * @date: 2022-03-14 13:01
 **/
public class IPersistenceTest {
    public void test(){
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        System.out.println(resourceAsStream == null);
    }
}
