package com.terwergreen.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 插入数据
 *
 * @name: InsertTest
 * @author: terwer
 * @date: 2022-03-18 18:44
 **/
@Service
public class InsertTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void doInsert() {
        List<Map<String,Object>> list=jdbcTemplate.queryForList("select * from user ");
    }
}
